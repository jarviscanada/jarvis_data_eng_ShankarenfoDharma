package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {
    @Autowired
    private QuoteDao dao;

    private Quote savedQuote;

    @Before
    public void insertOne(){
        savedQuote = new Quote();
        savedQuote.setAskPrice(10d);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setAskSize(10);
        savedQuote.setBidSize(10);
        savedQuote.setLastPrice(10.1d);
        savedQuote.setID("aapl");
        dao.save(savedQuote);
    }

    @Test
    public void testQuoteDao(){
        assertTrue(dao.existsById("aapl"));
        assertNotNull(dao.findById("aapl").get().getAskPrice());
        assertEquals(1, ((Collection<Quote>)dao.findAll()).size());
        savedQuote.setBidSize(120);
        dao.save(savedQuote);
        Quote targetQuote = dao.findById("aapl").get();
        assertEquals(120, targetQuote.getBidSize().intValue());
    }

    @After
    public void deleteOne(){
        dao.deleteById(savedQuote.getID());
    }
}
