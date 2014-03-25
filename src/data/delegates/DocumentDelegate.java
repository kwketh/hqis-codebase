package data.delegates;

import data.base.Document;
import data.base.Field;

public interface DocumentDelegate
{
    public void onDocumentFieldModified(Document document, Field field);
}
