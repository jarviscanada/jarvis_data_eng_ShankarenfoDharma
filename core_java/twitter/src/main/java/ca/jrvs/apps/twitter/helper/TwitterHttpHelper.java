package ca.jrvs.apps.twitter.helper;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
public class TwitterHttpHelper implements HttpHelper {
    private OAuthConsumer consumer;
    private HttpClient httpClient;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TwitterHttpHelper.class);

    public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String accessSecret){
        consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
        consumer.setTokenWithSecret(accessToken,accessSecret);
        httpClient = new DefaultHttpClient();
    }

    //default constructor
    public TwitterHttpHelper(){
        String CONSUMER_KEY = System.getenv("CONSUMERKEY");
        String CONSUMER_SECRET = System.getenv("CONSUMERSECRET");
        String ACCESS_KEY = System.getenv("ACCESSKEY");
        String ACCESS_SECRET = System.getenv("ACCESSSECRET");

        consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY,CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_KEY,ACCESS_SECRET);
        httpClient = new DefaultHttpClient();
    }

    @Override
    public HttpResponse httpPost(URI uri) {
        try{
            return executeHttpRequest(HttpMethod.POST,uri,null);
        } catch (OAuthException | IOException e){
            throw new RuntimeException("Failed to execute POST",e);
        }
    }

    @Override
    public HttpResponse httpGet(URI uri) {
        try{
            return executeHttpRequest(HttpMethod.GET,uri,null);
        } catch (OAuthException | IOException e){
            throw new RuntimeException("Failed to execute GET",e);
        }
    }

    private HttpResponse executeHttpRequest(HttpMethod method, URI uri, StringEntity entity) throws IOException, OAuthException{
        if(method == HttpMethod.GET){
            HttpGet request = new HttpGet(uri);
            consumer.sign(request);
            return httpClient.execute(request);
        } else if (method == HttpMethod.POST){
            HttpPost request = new HttpPost(uri);
            consumer.sign(request);
            return httpClient.execute(request);
        } else {
            throw new IllegalArgumentException("Unkown method: "+method.name());
        }
    }
}
