package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinate;
import ca.jrvs.apps.twitter.model.Entity;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterRestDaoUnitTest {
    @Mock
    HttpHelper mockHelper;
    @InjectMocks
    TwitterDao dao;

    @Test
    public void showTweet() throws Exception{
        Logger logger = org.slf4j.LoggerFactory.getLogger(TwitterHttpHelperTest.class);
        //create tweet
        String text = "@someone something #abc "+System.currentTimeMillis();
        Tweet postTweet = TwitterUtils.CreateTweetObject(text, new double[] {1d,-1d});

        //intentional fail
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try{
            dao.create(postTweet);
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        //test bypass parseResponseBody
        String tweetJsonStr = "{\n" +
                "   \"created_at:\":\"Mon Feb 18 21:24:39 +0000 2019\",\n" +
                "   \"id\":109760783,\n" +
                "   \"id_str\":\"109760783\",\n" +
                "   \"text\":\"test with loc123\",\n" +
                "   \"entities\":{\n" +
                "       \"hashtags\":[],\n" +
                "       \"user_mentions\":[]\n" +
                "       },\n" +
                "   \"coordinates\":null,\n" +
                "   \"retweet_count\":0,\n" +
                "   \"favorite_count\":0,\n" +
                "   \"favorited\":false,\n" +
                "   \"retweeted\":false\n" +
                "}";
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        Tweet expectedTweet = mapper.readValue(tweetJsonStr, Tweet.class);
        //mock responsebody
        doReturn(expectedTweet).when(spyDao).parseResponse(any(), anyInt());
        Tweet tweet = spyDao.create(postTweet);
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }
}
