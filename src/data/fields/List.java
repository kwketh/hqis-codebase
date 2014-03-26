package data.fields;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;
import data.base.Field;
import data.delegates.ListDelegate;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * base.List class.
 *
 * The intention for this class is represent a
 * list of fields.
 *
 * Group class responsibility is to be able to
 * serialise and un-serialise all fields values.
 */

public class List<E extends Field> extends Field implements Observer
{
    /**
     * List of fields the group contains.
     */
    protected ArrayList<E> m_fields = new ArrayList<E>();
    protected Class<E> m_itemClass = null;
    protected ListDelegate m_delegate = null;

    public List(String id, Class<E> itemClass)
    {
        super(id);
        m_itemClass = itemClass;
    }

    public void setDelegate(ListDelegate delegate)
    {
        m_delegate = delegate;
    }

    public void add(E field)
    {
        m_fields.add(field);
        field.addObserver(this);

        setChanged();
        notifyObservers("onListItemAdded");

        if (m_delegate != null)
            m_delegate.onListItemAdded(field);
    }

    public void remove(E field)
    {
        m_fields.remove(field);
        field.deleteObserver(this);

        setChanged();
        notifyObservers("onListItemRemoved");

        if (m_delegate != null)
            m_delegate.onListItemRemoved(field);
    }

    public E getById(String id)
    {
        for (E field : m_fields)
            if (field.getId().equals(id))
                return field;
        return null;
    }

    public ArrayList<E> values()
    {
        return m_fields;
    }

    @Override
    public void toJSON(JsonWriter writer) throws IOException
    {
        JsonWriter array = writer.beginArray();
        for (Field field : m_fields)
            field.toJSON(array);
        array.endArray();
    }

    @Override
    public void fromJSON(JsonElement element) throws IOException
    {
        if (!element.isJsonArray())
            throw new Error("Group failed to parse json element (element is not an Object)");
        JsonArray array = element.getAsJsonArray();
        for (int i = 0; i < array.size(); i++) {
            JsonElement iterElement = array.get(i);
            try {
                String id = iterElement.getAsJsonObject().get("id").getAsString();
                E newItem = null;
                try {
                    newItem = m_itemClass.getConstructor(String.class).newInstance(id);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                newItem.fromJSON(iterElement);
                add(newItem);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Observable sender, Object argument)
    {
        if (sender instanceof Field && argument instanceof String)
        {
            Field field = (Field)sender;
            String eventName = (String)argument;

            setChanged();
            notifyObservers("onListItemModified");

            if (m_delegate != null)
                m_delegate.onListItemModified(eventName, field);
        }
    }
}
