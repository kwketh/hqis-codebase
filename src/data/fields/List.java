package data.fields;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import data.base.Field;

/**
 * base.List class.
 *
 * The intention for this class is represent a
 * list of fields.
 *
 * Group class responsibility is to be able to
 * serialise and un-serialise all fields values.
 */
public class List<E extends Field> extends Field
{
    /**
     * List of fields the group contains.
     */
    protected ArrayList<E> m_fields = new ArrayList<E>();
    protected Class<E> m_itemClass = null;

    public List(String id, Class<E> itemClass)
    {
        super(id);
        m_itemClass = itemClass;
    }

    public void add(E field)
    {
        m_fields.add(field);
    }

    @Override
    public void toJSON(JsonWriter writer) throws IOException
    {
        JsonWriter array = writer.beginArray();
        for (Field field : m_fields)
            field.toJSON(array);
        array.endArray();
    }

    @Override
    public void fromJSON(JsonElement element) throws IOException
    {
        if (!element.isJsonArray())
            throw new Error("Group failed to parse json element (element is not an Object)");
        JsonArray array = element.getAsJsonArray();
        for (int i = 0; i < array.size(); i++) {
            JsonElement iterElement = array.get(i);
            String id = iterElement.getAsJsonObject().get("id").getAsString();
            try {
                E newItem = null;
                try {
                    newItem = m_itemClass.getConstructor(String.class).newInstance(id);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                newItem.fromJSON(iterElement);
                add(newItem);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
