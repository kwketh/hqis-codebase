package data.fields;

import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Observable;

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
        setChanged();
    }

    @Override
    public void toJSON(JsonWriter writer) throws IOException
    {
        if (m_value != null && (m_value instanceof String))
            writer.value(m_value);
        else {
            writer.nullValue();
            throw new Error("Text value must be an instance of String");
        }
    }
}
