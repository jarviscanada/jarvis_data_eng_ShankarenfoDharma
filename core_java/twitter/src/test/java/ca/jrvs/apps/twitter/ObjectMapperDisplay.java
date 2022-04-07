package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

public class ObjectMapperDisplay {
    @Test
    public void displayTest(){
        Tweet tweet = new Tweet();
        tweet.setText("JUST TEXT");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            System.out.println(objectMapper.writeValueAsString(tweet));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
