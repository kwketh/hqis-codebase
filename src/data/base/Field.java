package data.base;

import org.json.JSONException;
import org.json.JSONWriter;

import java.util.Observable;

/**
 * base.Field class.
 *
 * The class intention is to represent data
 * definition for each different kinds of
 * types, such "Text", "Number" or "List".
 *
 * The class responsibility is to be able to
 * enforce derived classes to implement the
 * abstract method toJSON and to specify the
 * field id in the constructor.
 *
 * The id is used to distinguish multiple fields
 * and the id should be unique per Group
 * (data.fields.Group).
 *
 * This in general will improve code consistency
 * and to enforce certain rules before the
 * compilation actually happens (most importantly
 * to ensure program correctness).
 */
abstract public class Field extends Observable
{
    protected String m_id;

    public Field(String id)
    {
        m_id = id;
    }

    public String getId()
    {
        return m_id;
    }

    abstract public void toJSON(JSONWriter writer) throws JSONException;
}
