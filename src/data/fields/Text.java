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
        notifyObservers();
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

    @Override
    public void fromJSON(JsonElement element) throws IOException
    {
       if (!element.isJsonPrimitive())
           throw new Error("Text json parsing failed (element is not a primitive type)");
       setValue(element.getAsString());
    }
}
