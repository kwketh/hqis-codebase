package data.storage;

import core.Platform;
import data.base.Document;
import data.loaders.DocumentLoader;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DocumentStorage
{
    static final ArrayList<Document> m_documents = new ArrayList<Document>();

    static private String getDocumentFilePath(Document document)
    {
        return core.Platform.getFilePath(Platform.Directory.DIRECTORY_DOCUMENTS, document.getId() + ".json");
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

    static private void saveDocumentLocally(Document document)
    {
        prepareDirectories();

        final String documentPath = getDocumentFilePath(document);
        final File file = new File(documentPath);
        try {
            file.createNewFile();
            String documentContents = data.Utils.serialiseField(document);
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter fileBuffer = new BufferedWriter(fileWriter);
            fileBuffer.write(documentContents);
            fileBuffer.close();
            System.out.println("Local document saved in " + documentPath);
        } catch (JSONException e)
        {
            System.out.println("Error saving document locally (serialisation failed)");
            e.printStackTrace();
        } catch (IOException e)
        {
            System.out.println("Error saving document locally (saving file failed)");
            e.printStackTrace();
        }
    }

    static private void saveDocumentRemotely(Document document)
    {
        try {
            DocumentLoader loader = new DocumentLoader(document.getId(), document.getType());
            String documentContents = data.Utils.serialiseField(document);
            loader.store(documentContents);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static void saveLocalStorage()
    {
        for (Document document : m_documents)
            saveDocumentLocally(document);
    }

    static void saveRemoteStorage()
    {
        for (Document document : m_documents)
            saveDocumentRemotely(document);
    }

    static void loadLocalStorage()
    {
        final String documentsPath = Platform.getDirectoryPath(Platform.Directory.DIRECTORY_DOCUMENTS);
        final File documentsFolder = new File(documentsPath);
        for (final File fileEntry : documentsFolder.listFiles())
        {
            if (fileEntry.isFile())
            {
                // todo: read file and deserialise into Document object
            }
        }
    }

    static public void sync()
    {
        /* Save (or update) all documents locally first */
        saveLocalStorage();

        /* Then attempt syncing all documents on the remote server */
        saveRemoteStorage();
    }
}
