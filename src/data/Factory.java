package data;

import app.documents.Questionnaire;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import data.base.Document;
import data.base.Field;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Factory
{
    final static HashMap<String, Class> m_documentRegistry = new HashMap<String, Class>();
    static
    {
        m_documentRegistry.put("questionnaire", Questionnaire.class);

    }

    public static <E extends Field> E makeField(Class<E> fieldClass)
    {
        E field = null;
        try {
            field = fieldClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return field;
    }

    public static <E extends Field> E makeField(Class<E> fieldClass, String id)
    {
        E field = makeField(fieldClass);
        field.setId(id);
        return field;
    }

    public static <E extends Document> E constructDocument(JsonElement documentJson)
    {
        JsonObject jsonObject = documentJson.getAsJsonObject();
        String id = jsonObject.get("id").getAsString();
        String type = jsonObject.get("type").getAsString();
        Class<E> documentClass = m_documentRegistry.get(type);
        E document = null;
        try {
            document = documentClass.getConstructor(String.class).newInstance(id);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            document.fromJSON(documentJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
