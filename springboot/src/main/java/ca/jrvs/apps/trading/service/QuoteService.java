package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import com.sun.org.apache.xpath.internal.operations.Quo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private QuoteDao quoteDao;
    private MarketDataDao marketDataDao;

    @Autowired
    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao){
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    public IexQuote findIexQuoteByTicker(String ticker){
        return marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker+" is invalid"));
    }

    public List<IexQuote> findIexQuotesByTickers(String tickers){
        return (List<IexQuote>) marketDataDao.findAllById(Arrays.asList(tickers.split(",")));
    }

    /**
     * Update quote table from IEXCloud
     *  -   get all quotes from psql DB
     *  -   foreach ticker get IEXQuote
     *  -   convert IEXQuote to Quote object
     *  -   save quote to DB
     * @throws ca.jrvs.apps.trading.dao for ticker not found in IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public void updateMarketData(){
        Iterable<Quote> localDBQuotes = quoteDao.findAll();
        localDBQuotes.forEach(quote ->{
            IexQuote targetIexQuote = marketDataDao.findById(quote.getTicker()).get();
            Quote updatedQuote = convertIexQuoteToQuote(targetIexQuote);
            quoteDao.save(updatedQuote);
        });
    }

    //Helper method convert IEXQuote to quote
    protected static Quote convertIexQuoteToQuote(IexQuote iexQuote){
        Quote resultQuote = new Quote();
        resultQuote.setID(iexQuote.getSymbol());
        if(iexQuote.getLatestPrice() == null)
        {resultQuote.setLastPrice(0d);}
        else
        {resultQuote.setLastPrice(iexQuote.getLatestPrice());}
        if(iexQuote.getIexBidPrice() == null)
        {resultQuote.setBidPrice(0d);}
        else
        {resultQuote.setBidPrice(iexQuote.getIexBidPrice());}
        if(iexQuote.getIexBidSize() == null)
        {resultQuote.setBidSize(0);}
        else
        {resultQuote.setBidSize(iexQuote.getIexBidSize());}
        if(iexQuote.getIexAskPrice() == null)
        {resultQuote.setAskPrice(0d);}
        else
        {resultQuote.setAskPrice(iexQuote.getIexAskPrice());}
        if(iexQuote.getIexAskSize() == null)
        {resultQuote.setAskSize(0);}
        else
        {resultQuote.setAskSize(iexQuote.getIexAskSize());}
        return resultQuote;
    }

    /** Validate (against IEX) and save given tickers to quote table
     *  -   get IexQuote(s)
     *  -   convert to Quote
     *  -   save quotes to DB
     * @throws IllegalArgumentException for invalid input
     */
    public List<Quote> saveQuotes(List<String> tickers){
        List<Quote> quoteDatas = new LinkedList<>();
        for (String ticker:tickers) {
            IexQuote targetIexQuote = findIexQuoteByTicker(ticker);
            Quote newQuote = convertIexQuoteToQuote(targetIexQuote);
            quoteDatas.add(newQuote);
        }
        quoteDao.saveAll(quoteDatas);
        return quoteDatas;
    }
    public Quote saveQuote(String quote){
        IexQuote target = findIexQuoteByTicker(quote);
        Quote targetQuote = convertIexQuoteToQuote(target);
        return saveQuote(targetQuote);
    }
    public Quote saveQuote(Quote quote){
        return quoteDao.save(quote);
    }
    public List<Quote> findAllQuotes(){
        return (List<Quote>) quoteDao.findAll();
    };
}
