package data.fields;

import java.util.ArrayList;

import data.base.Field;
import org.json.*;

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

    public List(String id)
    {
        super(id);
    }

    public void add(E field)
    {
        m_fields.add(field);
    }

    @Override
    public void toJSON(JSONWriter writer) throws JSONException
    {
        JSONWriter array = writer.array();
        for (Field field : m_fields)
            field.toJSON(array);
        array.endArray();
    }

}
