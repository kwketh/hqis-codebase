package data;

import data.base.Field;
import org.json.JSONException;
import org.json.JSONWriter;

import java.io.StringWriter;

public class Utils
{
    public static String serialiseField(Field field) throws JSONException
    {
        StringWriter stringWriter = new StringWriter();
        JSONWriter jsonWriter = new JSONWriter(stringWriter);
        field.toJSON(jsonWriter);
        return stringWriter.toString();
    }
}
