package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.Security_OrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderAccountService {
    private TraderDao traderDao;
    private AccountDao accountDao;
    private PositionDao positionDao;
    private Security_OrderDao securityOrderDao;

    @Autowired
    TraderAccountService(TraderDao traderDao, AccountDao accountDao, PositionDao positionDao, Security_OrderDao securityOrderDao){
        this.traderDao = traderDao;
        this.accountDao = accountDao;
        this.positionDao = positionDao;
        this.securityOrderDao = securityOrderDao;
    }

    /**
     * Create a new trader and initialize a corresponding account with 0 amount.
     * - Validate user input
     * - create trader
     * - create account
     * - create, setup, return traderAccountView
     */
    public Trader createTraderAccount(Trader trader){
        //validation
        if(trader.getID() != null || trader.getEmail() == null || trader.getCountry() == null || trader.getDob() == null || trader.getFirst_name() == null || trader.getLast_name() ==null){
            throw new IllegalArgumentException("Trader has ID/Missing fields.");
        }
        //create trader
        Trader newTrader = traderDao.save(trader);
        //create corresponding account
        Account newAccount = new Account();
        newAccount.setTrader_id(newTrader.getID());
        newAccount.setAmount(0d);
        newAccount=accountDao.save(newAccount);
        //TODO: return traderAccountView?

        return newTrader;
    }

    /**
     * Trader can be deleted if there is no open position and 0 cash balance
     * - Validate id
     * - get account and check balance
     * - get positions by id, and check status
     * - delete Security Orders, account, trader (in order)
     */
    public void deleteTraderById(Integer traderId){
        if(traderId == null || !traderDao.existsById(traderId)){
            throw new IllegalArgumentException("Empty id or trader id not found in database");
        }
        Account associatedAccount = accountDao.getAccountOfTraderId(traderId).get();
        if(associatedAccount.getAmount() != 0){
            throw new IllegalArgumentException("Trader has more than 0 cash in account.");
        }
        List<Position> associatedPositions = positionDao.findAllPositionsOfTraderId(traderId);
        associatedPositions.forEach(position -> {
            if(position.getPosition() != 0){
                throw new IllegalArgumentException("Trader has active position(s).");
            }
        });
        //delete: security orders, account, trader
        securityOrderDao.deleteByAccountId(associatedAccount.getID());
        accountDao.deleteById(associatedAccount.getID());
        traderDao.deleteById(traderId);
    }

    /**
     * Deposit a fund to traderId's account
     * -validate input
     * -find matching account of trader
     * -update account funds
     */
    public Account deposit(Integer traderId, Double fund){
        if(fund <= 0 || !traderDao.existsById(traderId)){
            throw new IllegalArgumentException("Unable to add funds- funds added are 0 or trader not found");
        }
        Account targetAccount = accountDao.getAccountOfTraderId(traderId).get();
        targetAccount.setAmount(targetAccount.getAmount()+fund);
        accountDao.save(targetAccount);
        return targetAccount;
    }
    /**
     * Deposit a fund to traderId's account
     * -validate input
     * -find matching account of trader
     * -update account funds
     */
    public Account withdraw(Integer traderId, Double fund){
        if(fund <= 0 || !traderDao.existsById(traderId)){
            throw new IllegalArgumentException("Unable to add funds- funds added are 0 or trader not found");
        }
        Account targetAccount = accountDao.getAccountOfTraderId(traderId).get();
        if(targetAccount.getAmount() < fund){
            throw new IllegalArgumentException("Unable to withdraw, exceeding account funds.'");
        }
        targetAccount.setAmount(targetAccount.getAmount()-fund);
        accountDao.save(targetAccount);
        return targetAccount;
    }
}
