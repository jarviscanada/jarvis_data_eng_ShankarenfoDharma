package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
    @Mock
    CrdDao dao;

    @InjectMocks
    TwitterService service;

    @Test
    public void serviceUnitTests(){
        Logger logger = org.slf4j.LoggerFactory.getLogger(TwitterHttpHelperTest.class);

        when(dao.create(any())).thenReturn(new Tweet());
        Tweet responseTweet = service.postTweet(TwitterUtils.CreateTweetObject("text",new double[] {50d,0d}));
        assertNotNull(responseTweet);

        //invalid coordinates- create
        try {
            service.postTweet(TwitterUtils.CreateTweetObject("text", new double[]{950d, -9980d}));
            fail();
        } catch (RuntimeException e){
            assertTrue(true);
        }

    }
}
