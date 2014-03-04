package fabric;

import org.json.JSONException;
import org.json.JSONObject;

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

    public JSONObject getJsonObject() throws JSONException
    {
        JSONObject jsonObject = new JSONObject(m_responseText);
        return jsonObject;
    }
}
