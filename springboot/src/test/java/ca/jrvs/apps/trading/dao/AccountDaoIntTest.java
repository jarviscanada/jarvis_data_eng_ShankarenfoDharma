package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class AccountDaoIntTest {

    @Autowired
    private AccountDao dao;

    @Autowired
    private TraderDao traderDao;

    private Account savedAccount;
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
        traderDao.save(savedTrader);
        savedAccount = new Account();
        savedAccount.setID(1);
        savedAccount.setTrader_id(1);
        savedAccount.setAmount(200d);
        dao.save(savedAccount);
    }

    @Test
    public void testAccountDao(){
        assertEquals(1, dao.count());
        assertTrue(dao.existsById(1));
        assertNotNull(dao.findAllById(Arrays.asList(1)).get(0).getAmount());
    }

    @After
    public void deleteOne(){
        dao.deleteById(savedAccount.getID());
        traderDao.deleteById(savedTrader.getID());
    }
}
