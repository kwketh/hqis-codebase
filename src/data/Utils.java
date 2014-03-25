package data;

import com.google.gson.stream.JsonWriter;
import data.base.Field;
import data.fields.List;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class Utils
{
    public static String newId()
    {
        byte[] randomBytes = new byte[64];
        new Random().nextBytes(randomBytes);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(randomBytes);
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

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
