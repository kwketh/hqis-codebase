package fabric;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

public class Response
{
    private Integer m_responseCode = null;
    private String m_responseText = null;

    Response(Integer responseCode, String responseText)
    {
        m_responseCode = responseCode;
        m_responseText = responseText;
    }

    public Integer getResponseCode()
    {
        return m_responseCode;
    }

    public String getResponseText()
    {
        return m_responseText;
    }

    public JsonElement getJsonElement() throws IOException
    {
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(m_responseText);
        return rootElement;
    }
}
