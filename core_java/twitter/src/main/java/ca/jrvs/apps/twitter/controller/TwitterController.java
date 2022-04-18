package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.TwitterUtils;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller {
    private static final String COORD_SEP  =":";
    private static final String COMMA = ",";
    private Service service;
    @Autowired
    public TwitterController(Service service){this.service = service;};

    @Override
    public Tweet postTweet(String[] args) {
        if(args.length != 3){
            throw new IllegalArgumentException("USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }
        String text = args[1];
        String coordText = args[2];
        String[] coordArray = coordText.split(COORD_SEP);
        if(coordArray.length != 2 || StringUtils.isEmpty(text)){
            throw new IllegalArgumentException("Invalid coordinates. USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }
        Double lat = null;
        Double lon = null;
        try{
            lat = Double.parseDouble(coordArray[0]);
            lon = Double.parseDouble(coordArray[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid coordinates. USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }
        Tweet createTweet = TwitterUtils.CreateTweetObject(text, new double[] {lon,lat});
        return service.postTweet(createTweet);
    }

    @Override
    public Tweet showTweet(String[] args) {
        if(!(args.length == 2 || args.length == 3)){
            throw new IllegalArgumentException("USAGE: TwitterCLIApp show \"tweet_id\" [\"properties\"]");
        }
        String id = args[1];
        String fields = null;
        String[] fieldsArray = new String[0];
        if(args.length == 3){
            fields = args[2];
            fieldsArray = fields.split(COMMA);
        }
            
        if(id.isEmpty()){
            throw new IllegalArgumentException("USAGE: TwitterCLIApp show \"tweet_id\" [\"properties\"]");
        }
        return service.showTweet(id, fieldsArray);
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        if(args.length != 2){
            throw new IllegalArgumentException("USAGE: TwitterCLIApp delete [id1,id2..]");
        }
        String ids = args[1];
        String[] idArray = ids.split(COMMA);
        if(ids.isEmpty()){
            throw new IllegalArgumentException("USAGE: TwitterCLIApp delete [id1,id2..]");
        }
        return service.deleteTweets(idArray);
    }
}
