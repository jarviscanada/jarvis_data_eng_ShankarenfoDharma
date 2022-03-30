package ca.jrvs.apps.twitter;

import com.google.gdata.util.common.base.PercentEscaper;
import com.sun.org.slf4j.internal.LoggerFactory;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import java.util.Arrays;

public class TwitterAPITest {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TwitterAPITest.class);
    private static String CONSUMER_KEY = "ZxNkrMv6NaEH9OQDEZnQVkxbh";
    private static String CONSUMER_SECRET = "pYyO1e7NJLdajKqdgwZSQEiotYVZpjkf2hwFO700wGaC2DefVE";
    private static String ACCESS_KEY = "1498715648758714372-niob4NBQdBmfx8FLwV6CwMoLDkRjCi";
    private static String ACCESS_SECRET = "zpSjTwlKI59onKHia5RleHiIzerJkEjO5r6awIedYiEgv";

    public static void main(String[] args) throws Exception{
        //OAuth Setup
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY,CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_KEY,ACCESS_SECRET);

        //http get request
        String status = "today is gud day";
        PercentEscaper percentEscaper = new PercentEscaper("",false);
        HttpPost request = new HttpPost("https://api.twitter.com/1.1/statuses/update.json?status="+percentEscaper.escape(status));

        //add headers to request
        consumer.sign(request);

        logger.debug("Http Request headers:");
        Arrays.stream(request.getAllHeaders()).forEach(x->logger.debug(String.valueOf(x)));

        //send requesst
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);

        logger.debug(EntityUtils.toString(response.getEntity()));
    }
}
