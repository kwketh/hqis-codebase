package core.adapters;

import data.base.Field;
import data.fields.List;
import data.delegates.ListDelegate;

import javax.swing.*;
import javax.swing.event.ListDataEvent;

public class ListModel<E extends Field> extends AbstractListModel implements ListDelegate
{
    private List m_list;

    public ListModel(List<E> list)
    {
        m_list = list;
        m_list.setDelegate(this);
    }

    @Override
    public int getSize()
    {
        return m_list.values().size();
    }

    @Override
    public Object getElementAt(int index)
    {
        if (getSize() == 0 || index >= getSize())
            return null;
        return m_list.values().get(index);
    }

    @Override
    public void onListItemAdded(Field field)
    {
        Integer index = m_list.values().indexOf(field);
        fireIntervalAdded(this, index, index);
    }

    @Override
    public void onListItemRemoved(Field field)
    {
        fireContentsChanged(this, 0, getSize() - 1);
    }

    @Override
    public void onListAnyItemModified(String eventName, Field field)
    {
        fireContentsChanged(this, 0, getSize() - 1);
    }
}
