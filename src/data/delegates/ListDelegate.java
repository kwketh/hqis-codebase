package data.delegates;

import data.base.Field;

public interface ListDelegate
{
    void onListItemAdded(Field field);
    void onListItemRemoved(Field field);
    void onListAnyItemModified(String eventName, Field field);
}
