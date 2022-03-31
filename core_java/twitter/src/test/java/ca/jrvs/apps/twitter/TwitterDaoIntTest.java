package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinate;
import ca.jrvs.apps.twitter.model.Entity;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TwitterDaoIntTest {
    private TwitterDao dao;

    @Before
    public void setup(){
        String CONSUMER_KEY = System.getenv("CONSUMERKEY");
        String CONSUMER_SECRET = System.getenv("CONSUMERSECRET");
        String ACCESS_KEY = System.getenv("ACCESSKEY");
        String ACCESS_SECRET = System.getenv("ACCESSSECRET");
        //dependency
        HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_KEY,ACCESS_SECRET);
        this.dao = new TwitterDao(httpHelper);
    }

    @Test
    public void create() throws Exception{
        Logger logger = org.slf4j.LoggerFactory.getLogger(TwitterHttpHelperTest.class);
        //create tweet
        String hshtag = "#abc";
        String text = "@someone something "+hshtag+" "+System.currentTimeMillis();
        double lat =1d;
        double lon = -1d;
        Tweet postTweet = new Tweet();
        postTweet.setText(text);
        Coordinate coordinate = new Coordinate();
        Entity entity = new Entity();
        LinkedList list = new LinkedList();
        list.add(hshtag);
        entity.setHashtags(list);
        list = new LinkedList();
        list.add("@someone");
        entity.setUserMentions(list);
        coordinate.setCoordinates(new double[] {lat,lon});
        postTweet.setCoordinates(coordinate);
        list = new LinkedList();
        list.add(entity);
        postTweet.setEntities(list);
        //display object as json
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        logger.debug(objectMapper.writeValueAsString(postTweet));

        //request make tweet
        Tweet responseTweet = dao.create(postTweet);
        logger.debug(objectMapper.writeValueAsString(responseTweet));

        //assertions
        assertEquals(text,responseTweet.getText());
        assertNotNull(responseTweet.getCoordinates());
        assertEquals(2,responseTweet.getCoordinates().getCoordinates().length);

        Tweet findTweet = dao.findById(responseTweet.getId_str());
        logger.debug(objectMapper.writeValueAsString(findTweet));
        assertEquals(responseTweet.getId(),findTweet.getId());

        Tweet deleteTweet = dao.deleteById(responseTweet.getId_str());
        logger.debug(objectMapper.writeValueAsString(deleteTweet));
        assertEquals(responseTweet.getId(),deleteTweet.getId());
    }
}
