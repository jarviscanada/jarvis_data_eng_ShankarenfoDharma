package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.model.Coordinate;
import ca.jrvs.apps.twitter.model.Entity;
import ca.jrvs.apps.twitter.model.Tweet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class TwitterUtils {
    public static Tweet CreateTweetObject(String text, double[] coordinates){
        Tweet resultingTweet = new Tweet();
        resultingTweet.setText(text);
        ArrayList<String> hashtags = new ArrayList<>();
        ArrayList<String> mentions = new ArrayList<>();
        for(String word : text.split(" ")) {
            //from text, get hashtags [#$$$$]
            if(word.startsWith("#"))
                hashtags.add(word);
            //from text, get mentions [@$$$$]
            if(word.startsWith("@"))
                mentions.add(word);
        }
        Coordinate coordinate = new Coordinate();
        Entity entity = new Entity();
        LinkedList list = new LinkedList();
        list.addAll(hashtags);
        entity.setHashtags(list);

        list = new LinkedList();
        list.addAll(mentions);
        entity.setUserMentions(list);

        list = new LinkedList();
        list.add(entity);
        resultingTweet.setEntities(list);

        coordinate.setCoordinates(coordinates);
        resultingTweet.setCoordinates(coordinate);

        return resultingTweet;
    }
}
