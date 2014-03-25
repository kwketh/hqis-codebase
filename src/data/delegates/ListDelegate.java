package data.delegates;

import data.base.Field;

public interface ListDelegate
{
    void onListItemAdded(Field field);
    void onListItemRemoved(Field field);
    void onListItemModified(String eventName, Field field);
}
