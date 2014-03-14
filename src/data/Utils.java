package data;

import com.google.gson.stream.JsonWriter;
import data.base.Field;

import java.io.*;

public class Utils
{
    public static String serialiseField(Field field) throws IOException
    {
        StringWriter writer = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(writer);
        field.toJSON(jsonWriter);
        return writer.toString();
    }
}
