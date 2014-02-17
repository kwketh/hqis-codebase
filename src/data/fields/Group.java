package data.fields;

import java.util.HashMap;

import data.base.Field;
import org.json.*;

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
abstract public class Group extends Field
{
    /**
     * List of fields the group contains.
     */
    protected HashMap<String, Field> m_fields = new HashMap<String, Field>();

    public Group(String id)
    {
        super(id);
        setupFields();
    }

    abstract protected void setupFields();

    public void addField(Field field)
    {
        if (m_fields.containsKey(field.getId()))
            throw new Error("data.fields.Group cannot have two fields with same id");

        m_fields.put(field.getId(), field);
    }

    public Field lookupField(String id)
    {
        return m_fields.get(id);
    }

    @Override
    public void toJSON(JSONWriter writer) throws JSONException
    {
        JSONWriter object = writer.object();
        for (Field field : m_fields.values()) {
            object.key(field.getId());
            field.toJSON(object);
        }
        object.endObject();
    }
}
