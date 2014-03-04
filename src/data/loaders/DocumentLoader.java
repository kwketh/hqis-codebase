package data.loaders;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DocumentLoader
{
    final private String m_id;
    final private String m_type;
    private JSONObject m_jsonObject = null;

    public DocumentLoader(String id, String type)
    {
        m_id = id;
        m_type = type;
    }

    public String getId()
    {
        return m_id;
    }

    public String getType()
    {
        return m_type;
    }

    public void load() throws IOException, JSONException
    {
        fabric.Response response = fabric.Connection.doGetRequest("doc/" + getType() + "/" + getId());
        m_jsonObject = response.getJsonObject();
        // todo: deserialise the json object into Document object
    }

    public void store(String documentContents) throws IOException
    {
        fabric.Connection.doPostRequest("doc/" + getType() + "/" + getId(), documentContents);
    }
}
