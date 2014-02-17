package data;

import data.base.Field;
import org.json.JSONException;
import org.json.JSONWriter;

import java.io.StringWriter;

public class Utils
{
    public static String serialiseField(Field field)
    {
        StringWriter stringWriter = new StringWriter();
        JSONWriter jsonWriter = new JSONWriter(stringWriter);
        try
        {
            field.toJSON(jsonWriter);
        }
        catch (JSONException e)
        {
            System.out.println("Error serialising field");
            e.printStackTrace();
        }
        return stringWriter.toString();
    }
}
