package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.helper.HttpHelper;
import ca.jrvs.apps.twitter.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.assertNotNull;

public class TwitterControllerIntTest {
    Controller controller;
    @Before
    public void setup(){
        String CONSUMER_KEY = System.getenv("CONSUMERKEY");
        String CONSUMER_SECRET = System.getenv("CONSUMERSECRET");
        String ACCESS_KEY = System.getenv("ACCESSKEY");
        String ACCESS_SECRET = System.getenv("ACCESSSECRET");
        //dependency
        HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_KEY,ACCESS_SECRET);
        this.controller = new TwitterController(new TwitterService(new TwitterDao(httpHelper)));
    }

    @Test
    public void test() throws Exception {
        Logger logger = org.slf4j.LoggerFactory.getLogger(TwitterServiceIntTest.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //Correct methods- create, show, delete
        Tweet response = controller.postTweet(new String[]{"post","A test of your reflexes! @WOL #Endwalker","4:29"});
        assertNotNull(response);
        logger.debug(objectMapper.writeValueAsString(response));
        response = controller.showTweet(new String[]{"show", response.getId_str()});
        assertNotNull(response);
        logger.debug(objectMapper.writeValueAsString(response));
        controller.deleteTweet(new String[]{"delete", response.getId_str()});
        assertNotNull(response);
    }
}
