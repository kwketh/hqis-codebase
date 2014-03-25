package fabric;

import ch.boye.httpclientandroidlib.Consts;
import ch.boye.httpclientandroidlib.HttpEntity;
import ch.boye.httpclientandroidlib.client.config.RequestConfig;
import ch.boye.httpclientandroidlib.client.methods.CloseableHttpResponse;
import ch.boye.httpclientandroidlib.client.methods.HttpGet;
import ch.boye.httpclientandroidlib.client.methods.HttpPost;
import ch.boye.httpclientandroidlib.client.methods.HttpUriRequest;
import ch.boye.httpclientandroidlib.entity.ContentType;
import ch.boye.httpclientandroidlib.entity.StringEntity;
import ch.boye.httpclientandroidlib.impl.client.CloseableHttpClient;
import ch.boye.httpclientandroidlib.impl.client.HttpClients;
import ch.boye.httpclientandroidlib.message.BasicHeader;
import ch.boye.httpclientandroidlib.protocol.HTTP;
import ch.boye.httpclientandroidlib.util.EntityUtils;

// import javax.swing.*;
import java.io.IOException;

public class Connection
{
    static private String m_baseUrl = "http://ec2-54-229-223-2.eu-west-1.compute.amazonaws.com/";

    static public String getGatewayUrl()
    {
        String baseUrl = m_baseUrl;
        if (baseUrl.indexOf("http://") != 0 && baseUrl.indexOf("https://") != 0)
            baseUrl = "http://" + baseUrl;
        if (baseUrl.charAt(baseUrl.length() - 1) != '/')
            baseUrl = baseUrl + "/";
        return baseUrl;
    }

    static public void setGatewayUrl(String baseUrl)
    {
        m_baseUrl = baseUrl;
    }

    static public Response doPostRequest(String remoteLocation, String postData) throws IOException
    {
        StringEntity requestEntity = new StringEntity(postData, ContentType.create("plain/text", Consts.UTF_8));
        HttpPost request = new HttpPost(getGatewayUrl() + remoteLocation);
        request.setEntity(requestEntity);
        return doRequest(request);
    }

    static public Response doGetRequest(String remoteLocation) throws IOException
    {
        HttpGet request = new HttpGet(getGatewayUrl() + remoteLocation);
        return doRequest(request);
    }

    static private Response doRequest(HttpUriRequest request) throws IOException
    {
        Response ret = null;
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(8000)
                .setConnectTimeout(8000)
                .setConnectionRequestTimeout(8000)
                .setStaleConnectionCheckEnabled(true)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Unable to perform an online request. Please ensure there is internet\nconnectivity or contact system administrator.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Unable to perform an online request. Please ensure there is internet\nconnectivity or contact system administrator.");
            e.printStackTrace();
            response = null;
        }
        System.out.println("response = " + response);
        if (response != null) {
            try {
                HttpEntity entity = response.getEntity();
                Integer responseCode = response.getStatusLine().getStatusCode();
                String responseText = EntityUtils.toString(entity);
                ret = new Response(responseCode, responseText);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        }
        return ret;
    }
}
