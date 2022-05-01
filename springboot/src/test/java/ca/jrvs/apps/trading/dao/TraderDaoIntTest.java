package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Trader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderDaoIntTest {
    @Autowired
    private TraderDao dao;

    private Trader savedTrader;

    @Before
    public void insertOne(){
        savedTrader = new Trader();
        savedTrader.setID(1);
        savedTrader.setDob(new Date(1995,5,21));
        savedTrader.setCountry("England");
        savedTrader.setEmail("Horosho@mail.me");
        savedTrader.setFirst_name("Emmy");
        savedTrader.setLast_name("WatSon");
        dao.save(savedTrader);
    }

    @Test
    public void testTraderDao(){
        assertEquals(1, ((Collection<Trader>)dao.findAll()).size());
        assertTrue(dao.existsById(1));
        assertNotNull(dao.findById(1).get().getEmail());
    }

    @After
    public void deleteOne(){
        dao.deleteById(savedTrader.getID());
    }
}
