package data.loaders;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import data.base.Document;
import fabric.Response;

import java.io.IOException;

public class DocumentLoader
{
    private JsonElement m_jsonElement = null;

    public void loadFromJson(String jsonContents)
    {
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(jsonContents);
        m_jsonElement = rootElement;
    }

    public void loadFromJson(JsonElement jsonElement)
    {
        m_jsonElement = jsonElement;
    }

    public void loadFromRemote(String id, String type) throws IOException
    {
        fabric.Response response = fabric.Connection.doGetRequest("doc/" + type + "/" + id);
        m_jsonElement = response.getJsonElement();
    }

    public void store(String id, String type, String documentJson) throws IOException
    {
        fabric.Connection.doPostRequest("doc/" + type + "/" + id, documentJson);
    }

    public <E extends Document> E constructDocument()
    {
        return data.Factory.constructDocument(m_jsonElement);
    }
}
