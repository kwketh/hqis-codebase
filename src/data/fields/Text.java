package data.fields;

import org.json.JSONException;
import org.json.JSONWriter;

public class Text extends data.base.Field
{
    String m_value;

    public Text(String id)
    {
        super(id);
        m_value = null;
    }

    public Text(String id, String value)
    {
        super(id);
        m_value = value;
    }

    public String getValue()
    {
        return m_value;
    }

    public void setValue(String value)
    {
        m_value = value;
    }

    @Override
    public void toJSON(JSONWriter writer) throws JSONException
    {
        if (m_value != null && !(m_value instanceof String))
            throw new Error("Text value must be an instance of String");
        writer.value(m_value);
    }
}
