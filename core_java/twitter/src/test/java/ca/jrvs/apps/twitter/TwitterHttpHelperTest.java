package ca.jrvs.apps.twitter;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;

import java.net.URI;

public class TwitterHttpHelperTest {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TwitterHttpHelperTest.class);
    private static String CONSUMER_KEY = System.getenv("CONSUMERKEY");
    private static String CONSUMER_SECRET = System.getenv("CONSUMERSECRET");
    private static String ACCESS_KEY = System.getenv("ACCESSKEY");
    private static String ACCESS_SECRET = System.getenv("ACCESSSECRET");
    @Test
    public void twitterHTTPHelperTest() throws Exception{
        TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_KEY,ACCESS_SECRET);
        HttpResponse response = helper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=first_tweet2"));
        logger.debug(EntityUtils.toString(response.getEntity()));
    }
}
