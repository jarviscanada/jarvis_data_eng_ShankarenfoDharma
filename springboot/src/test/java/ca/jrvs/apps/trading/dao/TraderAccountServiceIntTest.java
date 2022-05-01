package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.service.TraderAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderAccountServiceIntTest {
    private Trader savedTrader;
    @Autowired
    private TraderAccountService traderAccountService;
    @Autowired
    private TraderDao traderDao;
    @Autowired
    private AccountDao accountDao;

    @Test
    public void testAccountServices(){
        savedTrader = new Trader();
        savedTrader.setEmail("AAEEHH@yahoo.no");
        savedTrader.setCountry("CA");
        savedTrader.setFirst_name("John");
        savedTrader.setLast_name("UGABUUGA");
        savedTrader.setDob(new Date(2012,2,21));
        savedTrader = traderAccountService.createTraderAccount(savedTrader);
        assertNotNull(savedTrader.getID());
        traderAccountService.deposit(savedTrader.getID(), 2000d);
        try {
            traderAccountService.withdraw(savedTrader.getID(), 3000d);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        Account associatedAccount = traderAccountService.withdraw(savedTrader.getID(), 1000d);
        assertEquals(1000d, associatedAccount.getAmount(),10d);
        traderAccountService.withdraw(savedTrader.getID(),1000d);   //empty account
        traderAccountService.deleteTraderById(savedTrader.getID());
    }
}
