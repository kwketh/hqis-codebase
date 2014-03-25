package data.delegates;

import data.base.Field;

public interface GroupDelegate
{
    void onGroupFieldAdded(Field field);
    void onGroupFieldRemoved(Field field);
    void onGroupFieldModified(String eventName, Field field);
}
