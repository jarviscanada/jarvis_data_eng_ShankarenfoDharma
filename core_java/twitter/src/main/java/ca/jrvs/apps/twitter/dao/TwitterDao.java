package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Repository
public class TwitterDao implements CrdDao<Tweet,String>{
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TwitterDao.class);
    //URI parts
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH= "/1.1/statuses/update.json";
    private static final String SHOW_PATH= "/1.1/statuses/show.json";
    private static final String DELETE_PATH= "/1.1/statuses/destroy";
    //URI symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";
    //response code
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;

    @Autowired
    public TwitterDao(HttpHelper httpHelper) {this.httpHelper = httpHelper;}

    @Override
    public Tweet create(Tweet tweet) {
        URI uri;
        try {
            PercentEscaper percentEscaper = new PercentEscaper("",false);
            StringBuilder uriSt = new StringBuilder();
            uriSt.append(API_BASE_URI+POST_PATH+QUERY_SYM+"status"+EQUAL+percentEscaper.escape(tweet.getText()));
            if(tweet.getCoordinates() != null && tweet.getCoordinates().getCoordinates() != null){
                uriSt.append(AMPERSAND+"lat"+EQUAL+tweet.getCoordinates().getCoordinates()[0]+AMPERSAND+"lon"+EQUAL+tweet.getCoordinates().getCoordinates()[1]);
            }
            uri = new URI(uriSt.toString());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid tweet input",e);
        }
        HttpResponse response = httpHelper.httpPost(uri);

        return parseResponse(response,HTTP_OK);
    }

    @Override
    public Tweet findById(String s) {
        URI uri;
        try {
            uri = new URI(API_BASE_URI+SHOW_PATH+QUERY_SYM+"id"+EQUAL+s);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid tweet ID URI",e);
        }
        HttpResponse response = httpHelper.httpGet(uri);

        return parseResponse(response,HTTP_OK);
    }

    @Override
    public Tweet deleteById(String s) {
        URI uri;
        try {
            uri = new URI(API_BASE_URI+DELETE_PATH+"/"+s+".json");
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid tweet ID",e);
        }
        HttpResponse response = httpHelper.httpPost(uri);

        return parseResponse(response,HTTP_OK);
    }

    public Tweet parseResponse(HttpResponse response, int expectedCode){
        Tweet resultTweet = null;

        int responseStatus = response.getStatusLine().getStatusCode();
        if(responseStatus != expectedCode){
            try{
                logger.debug(EntityUtils.toString(response.getEntity()));
            }catch(IOException e){
                logger.error("response has no entity");
            }
            throw new RuntimeException("Unexpected HTTP status: "+responseStatus);
        }

        if(response.getEntity() == null){
            throw new RuntimeException("Empty response body");
        }

        //convert response into str
        String rawJSON;
        try{
            rawJSON = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Failed converting entity to JSON string.", e);
        }

        //convert json str into Tweet object
        try{
            resultTweet = toObjectFromJSON(rawJSON,Tweet.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert JSON to object", e);
        }

        return resultTweet;
    }

    private static <T>T toObjectFromJSON(String JSON, Class DTO) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return (T) mapper.readValue(JSON, DTO);
    }
}
