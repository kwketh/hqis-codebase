package fabric;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.*;
import java.io.IOException;

public class Connection
{
    static private final String baseUrl = "http://ec2-54-229-223-2.eu-west-1.compute.amazonaws.com/";

    static public Response doPostRequest(String remoteLocation, String postData) throws IOException
    {
        StringEntity requestEntity = new StringEntity(postData, ContentType.create("plain/text", Consts.UTF_8));
        HttpPost request = new HttpPost(baseUrl + remoteLocation);
        request.setEntity(requestEntity);
        return doRequest(request);
    }

    static public Response doGetRequest(String remoteLocation) throws IOException
    {
        HttpGet request = new HttpGet(baseUrl + remoteLocation);
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
            JOptionPane.showMessageDialog(null, "Unable to perform an online request. Please ensure there is internet\nconnectivity or contact system administrator.", "Error", JOptionPane.ERROR_MESSAGE);
            response = null;
        }
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
