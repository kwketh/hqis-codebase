package data.delegates;

import data.base.Document;

public interface DocumentStorageDelegate
{
    void onStorageDocumentAdded(Document document);
    void onStorageDocumentModified(Document document);
    void onStorageDocumentRemoved(Document document);
}
