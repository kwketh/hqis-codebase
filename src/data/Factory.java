package data;

import app.documents.Questionnaire;
import app.fields.Question;
import data.base.Field;

import java.util.HashMap;
import java.util.Map;

public class Factory
{
    private static final Map<String, Class> registeredFields = new HashMap<String, Class>();

    public static<E extends Field> void registerField(String type, Class<E> fieldClass)
    {
        registeredFields.put(type, fieldClass);
    }

    public static<E extends Field> E makeField(String type) throws IllegalAccessException, InstantiationException
    {
        Class<E> field = registeredFields.get(type);
        E instance = field.newInstance();
        return instance;
    }
}
