package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.helper.HttpHelper;
import ca.jrvs.apps.twitter.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TwitterCLIApp {
    private String GENERIC_USAGE_LINE = "USAGE: TwitterCLIApp post|show|delete [options]";
    private Controller controller;

    @Autowired
    public TwitterCLIApp(Controller controller){this.controller = controller;}

    public static void main(String[] args) throws Exception {
        //env vars get
        String CONSUMER_KEY = System.getenv("CONSUMERKEY");
        String CONSUMER_SECRET = System.getenv("CONSUMERSECRET");
        String ACCESS_KEY = System.getenv("ACCESSKEY");
        String ACCESS_SECRET = System.getenv("ACCESSSECRET");

        //Java components
        HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_KEY,ACCESS_SECRET);
        CrdDao crdDao = new TwitterDao(httpHelper);
        Service service = new TwitterService(crdDao);
        Controller controller = new TwitterController(service);
        TwitterCLIApp app = new TwitterCLIApp(controller);

        //start app
        app.run(args);
    }

    public void run(String[] args){
        if(args.length == 0){
            throw new IllegalArgumentException(GENERIC_USAGE_LINE);
        }
        switch (args[0].toLowerCase(Locale.ROOT)){
            case "post":
                displayTweet(controller.postTweet(args));
                break;
            case "show":
                displayTweet(controller.showTweet(args));
                break;
            case "delete":
                controller.deleteTweet(args).forEach(this::displayTweet);
                break;
            default:
                throw new IllegalArgumentException(GENERIC_USAGE_LINE);
        }
    }

    private void displayTweet(Tweet tweet){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            System.out.println(objectMapper.writeValueAsString(tweet));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert tweet object into string.",e);
        }
    }
}
