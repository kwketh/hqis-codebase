package data.loaders;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import data.base.Document;

import java.io.IOException;

public class DocumentLoader
{
    private JsonElement m_jsonObject = null;

    public void loadFromJson(String json)
    {
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(json);
        m_jsonObject = rootElement;
    }

    public void loadFromRemote(String id, String type) throws IOException
    {
        fabric.Response response = fabric.Connection.doGetRequest("doc/" + type + "/" + id);
        m_jsonObject = response.getJsonElement();
    }

    public void store(String id, String type, String documentJson) throws IOException
    {
        fabric.Connection.doPostRequest("doc/" + type + "/" + id, documentJson);
    }

    public <E extends Document> E constructDocument()
    {
        return data.Factory.constructDocument(m_jsonObject);
    }
}
