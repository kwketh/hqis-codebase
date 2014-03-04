package fabric;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Connection
{
    static private final String baseUrl = "http://ec2-54-229-223-2.eu-west-1.compute.amazonaws.com/";

    static public Response doPostRequest(String remoteLocation, String postData) throws IOException
    {
        Response ret = null;
        StringEntity requestEntity = new StringEntity(postData, ContentType.create("plain/text", Consts.UTF_8));
        HttpPost request = new HttpPost(baseUrl + remoteLocation);
        request.setEntity(requestEntity);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            Integer responseCode = response.getStatusLine().getStatusCode();
            String responseText = EntityUtils.toString(entity);
            ret = new Response(responseCode, responseText);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return ret;
    }

    static public Response doGetRequest(String remoteLocation) throws IOException
    {
        Response ret = null;
        HttpGet request = new HttpGet(baseUrl + remoteLocation);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            Integer responseCode = response.getStatusLine().getStatusCode();
            String responseText = EntityUtils.toString(entity);
            ret = new Response(responseCode, responseText);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return ret;
    }
}
