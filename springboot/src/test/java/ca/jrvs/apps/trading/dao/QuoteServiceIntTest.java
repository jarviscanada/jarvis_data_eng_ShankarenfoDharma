package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.service.QuoteService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {
    @Autowired
    private QuoteService quoteService;
    @Autowired
    private QuoteDao quoteDao;

    @Before
    public void setup(){quoteDao.deleteAll();}

    @Test
    public void findIexQuoteByTicker(){
        IexQuote result = quoteService.findIexQuoteByTicker("AAPL");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            System.out.println(objectMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert quote object into string.",e);
        }
        assertEquals("AAPL",result.getSymbol());
    }

    @Test
    public void saveQuotesAndUpdate(){
        //quoteService.saveQuote("MSFT");
        quoteService.saveQuotes(Arrays.asList("AAPL","FB"));
        quoteService.updateMarketData();
        List<Quote> quotes = quoteService.findAllQuotes();

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            System.out.println(objectMapper.writeValueAsString(quotes));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert quote object into string.",e);
        }
    }

    @After
    public void cleanUp(){
        quoteDao.deleteAll();
    }
}
