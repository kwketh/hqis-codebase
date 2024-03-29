package data.base;

import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
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

    public Field()
    {
        this(data.Utils.newId());
    }

    public Field(String id)
    {
        setId(id);
    }

    public void setId(String id) {
        m_id = id;
    }

    public String getId() {
        return m_id;
    }

    abstract public void toJSON(JsonWriter writer) throws IOException;
    abstract public void fromJSON(JsonElement element) throws IOException;
}
