package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.Security_Order;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class Security_OrderDaoTest {
    @Autowired
    private Security_OrderDao securityOrderDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private QuoteDao quoteDao;
    @Autowired
    private TraderDao traderDao;

    private Trader savedTrader;
    private Account savedAccount;
    private Quote savedQuote;
    private Security_Order savedSecurityOrder;

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
        accountDao.save(savedAccount);
        savedQuote = new Quote();
        savedQuote.setAskPrice(10d);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setAskSize(10);
        savedQuote.setBidSize(10);
        savedQuote.setLastPrice(10.1d);
        savedQuote.setID("aapl");
        quoteDao.save(savedQuote);
        savedSecurityOrder = new Security_Order();
        savedSecurityOrder.setID(1);
        savedSecurityOrder.setAccount_id(1);
        savedSecurityOrder.setTicker("aapl");
        savedSecurityOrder.setNotes("NOTESNOTES");
        savedSecurityOrder.setPrice(12.0d);
        savedSecurityOrder.setSize(10);
        savedSecurityOrder.setStatus("OK");
        securityOrderDao.save(savedSecurityOrder);
    }

    @Test
    public void testAccountDao(){
        List<Security_Order> targetList = securityOrderDao.findAll();
        assertTrue(targetList.size() != 0);
        Security_Order targetOrder = securityOrderDao.findById(savedSecurityOrder.getID()).get();
        assertEquals(targetOrder.getPrice(),savedSecurityOrder.getPrice());
    }

    @After
    public void deleteOne(){
        securityOrderDao.deleteById(savedSecurityOrder.getID());
        accountDao.deleteById(savedAccount.getID());
        traderDao.deleteById(savedTrader.getID());
        quoteDao.deleteById(savedQuote.getTicker());
    }
}
