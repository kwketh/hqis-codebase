package data.storage;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import core.Platform;
import data.base.Document;
import data.fields.Text;
import data.loaders.DocumentLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DocumentStorage
{
    static final ArrayList<Document> m_documents = new ArrayList<Document>();

    static private String getDocumentFilePath(String documentId)
    {
        return core.Platform.getFilePath(Platform.Directory.DIRECTORY_DOCUMENTS, documentId + ".json");
    }

    static public void add(Document document)
    {
        if (m_documents.contains(document))
            return;

        m_documents.add(document);
        saveDocumentLocally(document);
    }

    static public void remove(Document document)
    {
        if (!m_documents.contains(document))
            return;

        m_documents.remove(document);
    }

    static private void prepareDirectories()
    {
        for (Platform.Directory directory: Platform.Directory.values())
        {
            final File file = new File(Platform.getDirectoryPath(directory));
            if (!file.isDirectory())
                file.mkdirs();
        }
    }

    static private Document getLocalDocument(String documentId)
    {
        final String documentPath = getDocumentFilePath(documentId);
        final File file = new File(documentPath);
        if (!file.exists())
            return null;
        String documentJson = null;
        try
        {
            documentJson = new Scanner(file).useDelimiter("\\Z").next();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        DocumentLoader loader = new DocumentLoader();
        loader.loadFromJson(documentJson);
        return loader.constructDocument();
    }

    static private void saveDocumentLocally(Document document)
    {
        prepareDirectories();
        boolean requiresUpdate = false;

        Document existingDocument = getLocalDocument(document.getId());
        if (existingDocument != null)
        {
            if (document.getDate().after(existingDocument.getDate()))
                requiresUpdate = true;
        } else
            requiresUpdate = true;

        if (requiresUpdate)
        {
            final String documentPath = getDocumentFilePath(document.getId());
            final File file = new File(documentPath);
            try
            {
                file.createNewFile();
                String documentContents = data.Utils.serialiseField(document);
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
                BufferedWriter fileBuffer = new BufferedWriter(fileWriter);
                fileBuffer.write(documentContents);
                fileBuffer.close();
                System.out.println("Document " + document.getId() + " has been saved locally.");
            }
            catch (IOException e)
            {
                System.out.println("Error saving document locally (serialisation or saving file failed)");
                e.printStackTrace();
            }
        }
    }

    static private void saveDocumentRemotely(Document document)
    {
        try
        {
            DocumentLoader loader = new DocumentLoader();
            String documentJson = data.Utils.serialiseField(document);
            loader.store(document.getId(), document.getType(), documentJson);
            System.out.println("Local document " + document.getId() + " has been stored remotely.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    static ArrayList<Document> filterDocumentsByNewer(ArrayList<Document> documents)
    {
        final HashMap<String, Document> newerDocuments = new HashMap<String, Document>();
        for (Document document : m_documents)
        {
            String documentId = document.getId();
            java.util.Date lastModified = document.getDate();

            boolean isNewer = false;
            if (newerDocuments.containsKey(documentId)) {
                Document otherDocument = newerDocuments.get(documentId);
                if (lastModified.after(otherDocument.getDate())) {
                    isNewer = true;
                }
            } else {
                isNewer = true;
            }

            if (isNewer) {
                newerDocuments.put(documentId, document);
            }
        }
        return new ArrayList<Document>(newerDocuments.values());
    }

    static void saveLocalStorage()
    {
        ArrayList<Document> newerDocuments = filterDocumentsByNewer(m_documents);
        for (Document document : newerDocuments)
            saveDocumentLocally(document);
    }

    static void saveRemoteStorage()
    {
        ArrayList<Document> newerDocuments = filterDocumentsByNewer(m_documents);
        for (Document document : newerDocuments)
            saveDocumentRemotely(document);
    }

    static void loadLocalStorage()
    {
        final String documentsPath = Platform.getDirectoryPath(Platform.Directory.DIRECTORY_DOCUMENTS);
        final File documentsFolder = new File(documentsPath);
        for (final File fileEntry : documentsFolder.listFiles())
        {
            if (!fileEntry.isFile())
                return;

            String documentJson;
            try {
                documentJson = new Scanner(fileEntry).useDelimiter("\\Z").next();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                continue;
            }

            DocumentLoader loader = new DocumentLoader();
            loader.loadFromJson(documentJson);
            add(loader.constructDocument());
        }
        System.out.println("Local documents (" + documentsFolder.listFiles().length + ") has been loaded.");
    }

    static public void sync()
    {
        /* Save (or update) all documents locally first */
        saveLocalStorage();

        /* Then load all documents that may be missing in the storage */
        loadLocalStorage();

        /* Finally attempt syncing all documents on the remote server */
        saveRemoteStorage();

        System.out.println("Documents have been synced.");
    }
}
