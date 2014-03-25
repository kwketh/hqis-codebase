package data.fields;

import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class Boolean extends data.base.Field
{
    java.lang.Boolean m_value = null;

    public Boolean() {}

    public Boolean(String id)
    {
        super(id);
    }

    public java.lang.Boolean getValue()
    {
        return m_value;
    }

    public void setValue(java.lang.Boolean value)
    {
        m_value = value;
        setChanged();
        notifyObservers("onBooleanChanged");
    }

    @Override
    public void toJSON(JsonWriter writer) throws IOException
    {
        if (m_value != null && (m_value instanceof java.lang.Boolean))
            writer.value(m_value);
        else {
            writer.nullValue();
        }
    }

    @Override
    public void fromJSON(JsonElement element) throws IOException
    {
        if (element.isJsonPrimitive())
            m_value = element.getAsBoolean();
        else
            m_value = null;
    }
}
