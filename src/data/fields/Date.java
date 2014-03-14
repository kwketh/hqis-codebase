package data.fields;

import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Date extends data.base.Field
{
    static final DateFormat m_dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss,SSS'Z'");
    static {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        m_dateFormat.setTimeZone(tz);
    }

    java.util.Date m_date = null;

    public Date()
    {
        super();
    }

    public Date(String id)
    {
        super(id);
    }

    public java.util.Date getValue()
    {
        return m_date;
    }

    public void setValue(java.util.Date value)
    {
        m_date = value;
        setChanged();
    }

    @Override
    public void toJSON(JsonWriter writer) throws IOException
    {
        String isoFormat = m_dateFormat.format(m_date);

        if (m_date != null && (m_date instanceof java.util.Date))
            writer.value(isoFormat);
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
        try {
            m_date = m_dateFormat.parse(element.getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
