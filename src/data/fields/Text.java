package data.fields;

import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class Text extends data.base.Field
{
    String m_value = null;

    public Text()
    {
        super();
    }

    public Text(String id)
    {
        super(id);
    }

    public String getValue()
    {
        return m_value;
    }

    public void setValue(String value)
    {
        m_value = value;
        setChanged();
        notifyObservers("onTextModified");
    }

    @Override
    public void toJSON(JsonWriter writer) throws IOException
    {
        if (m_value != null && (m_value instanceof String))
            writer.value(m_value);
        else {
            writer.nullValue();
        }
    }

    @Override
    public void fromJSON(JsonElement element) throws IOException
    {
        if (element.isJsonPrimitive())
            m_value = element.getAsString();
        else
            m_value = null;
    }
}
