package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {
    @Mock
    Service service;

    @InjectMocks
    TwitterController controller;

    @Test
    public void test(){
        when(service.postTweet(any())).thenReturn(new Tweet());
        //run post
        Tweet response = controller.postTweet(new String[]{"post","Louisuoix","14:40"});
        assertNotNull(response);
    }
}
