package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
        when(dao.create(any())).thenReturn(new Tweet());
        service.postTweet(TwitterUtils.CreateTweetObject("text",new double[] {50d,0d}));
    }
}
