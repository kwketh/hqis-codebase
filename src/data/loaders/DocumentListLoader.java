package data.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import data.base.Document;
import java.io.IOException;
import java.util.ArrayList;

public class DocumentListLoader
{
    private JsonElement m_jsonElement = null;

    public void loadFromJson(String json)
    {
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(json);
        m_jsonElement = rootElement;
    }

    public void loadFromRemote(String type) throws IOException
    {
        fabric.Response response = fabric.Connection.doGetRequest("doclist/" + type);
        m_jsonElement = response.getJsonElement();
    }

    public <E extends Document> ArrayList<E> constructDocuments() throws IOException
    {
        ArrayList<E> documents = new ArrayList<E>();
        JsonObject response = m_jsonElement.getAsJsonObject();
        JsonArray list = response.getAsJsonArray("list");
        for (JsonElement jsonElement : list) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String id = jsonObject.getAsJsonPrimitive("id").getAsString();
            String type = jsonObject.getAsJsonPrimitive("type").getAsString();
            DocumentLoader loader = new DocumentLoader();
            loader.loadFromRemote(id, type);
            documents.add(loader.<E>constructDocument());
        }
        return documents;
    }
}


