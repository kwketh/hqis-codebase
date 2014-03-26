package data.fields;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import data.base.Field;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observer;

/**
 * base.Group class.
 *
 * The intention for this class is to describe
 * and represent a group of fields.
 *
 * Group class responsibility is to be able to
 * serialise and un-serialise the fields values
 * including their ids.
 */
public class Group extends Field implements Observer
{
    /**
     * List of fields the group contains.
     */
    protected LinkedHashMap<String, Field> m_fields = new LinkedHashMap<String, Field>();

    public Group(String id, ArrayList<Field> fields)
    {
        super(id);
        for (Field field : fields)
            addField(field);
        setId(id);
    }

    public void setId(String id)
    {
        super.setId(id);
        Text idField = lookupField("id");
        if (idField != null)
            idField.setValue(id);
    }

    public void addField(Field field)
    {
        if (m_fields.containsKey(field.getId()))
            throw new Error("data.fields.Group cannot have two fields with same id");

        m_fields.put(field.getId(), field);
        field.addObserver(this);
    }

    public<FieldType> FieldType lookupField(String id)
    {
        if (m_fields != null && m_fields.containsKey(id))
            return (FieldType)m_fields.get(id);
        else
            return null;
    }

    @Override
    public void toJSON(JsonWriter writer) throws IOException
    {
        JsonWriter object = writer.beginObject();
        for (Field field : m_fields.values()) {
            object.name(field.getId());
            field.toJSON(object);
        }
        object.endObject();
    }

    @Override
    public void fromJSON(JsonElement element) throws IOException
    {
        if (!element.isJsonObject())
            throw new Error("Group failed to parse json element (element is not an Object)");
        JsonObject object = element.getAsJsonObject();
        for (Map.Entry<String,JsonElement> entry : object.entrySet()) {
            Field field = lookupField(entry.getKey());
            field.fromJSON(entry.getValue());
        }
    }

    @Override
    public void update(java.util.Observable sender, Object argument)
    {
        if (sender instanceof Field && argument instanceof String)
        {
            Field field = (Field)sender;
            String eventName = (String)argument;

            setChanged();
            notifyObservers("onGroupFieldModified");
        }
    }
}
