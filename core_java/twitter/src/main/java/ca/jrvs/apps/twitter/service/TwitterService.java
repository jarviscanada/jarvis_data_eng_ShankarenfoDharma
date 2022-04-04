package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class TwitterService implements Service{
    private CrdDao dao;

    @Autowired
    public TwitterService(CrdDao dao) {this.dao = dao;}

    @Override
    public Tweet postTweet(Tweet tweet) {
        //validate tweet: Text length, lat/lon range, id format
        //Twitter id == 0 && id_str == null is new tweet (no ID, skip)
        if(tweet.getText().length() >= 140)
            throw new RuntimeException("Tweet status/text length exceeds 140.");
        if( !(tweet.getId() == 0 && tweet.getId_str() == null) && !String.valueOf(tweet.getId()).equals(tweet.getId_str()) )
            throw new RuntimeException("Tweet id mismatch.");
        if( !((tweet.getCoordinates().getCoordinates()[0] >= -180 && tweet.getCoordinates().getCoordinates()[0] <= 180) && (tweet.getCoordinates().getCoordinates()[1] >= -90 && tweet.getCoordinates().getCoordinates()[1] <= 90)) )
            throw new RuntimeException("Incorrect coordinates in tweet");
        return (Tweet) dao.create(tweet);
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        //check id
        try{
            Long.parseLong(id);
        }catch (NumberFormatException e) {
            throw new RuntimeException("ID not numeric/parseable.");
        }
        return (Tweet) dao.findById(id);
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        //check all ids
        for (String id:ids) {
            try{
                Long.parseLong(id);
            }catch (NumberFormatException e) {
                throw new RuntimeException("ID not numeric/parseable.");
            }
        }
        List<Tweet> deleteds= new LinkedList<>();
        //delete tweets one by one
        for (String id:ids) {
            deleteds.add((Tweet) dao.deleteById(id));
        }

        return deleteds;
    }
}
