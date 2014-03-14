package data;

import com.google.gson.stream.JsonWriter;
import data.base.Field;

import java.io.*;

public class Utils
{
    public static String serialiseField(Field field)
    {
        StringWriter writer = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(writer);
        try {
            field.toJSON(jsonWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
