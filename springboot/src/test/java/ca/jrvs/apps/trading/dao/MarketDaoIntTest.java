package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MarketDaoIntTest {
    private MarketDataDao dao;
    private Logger logger = LoggerFactory.getLogger(MarketDaoIntTest.class);

    @Before
    public void init(){
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1");
        marketDataConfig.setToken(System.getenv("IEX_TOKEN"));
        dao = new MarketDataDao(cm,marketDataConfig);
    }

    @Test
    public void findIEXQuoteByTickers() throws IOException {
        List<IexQuote> quoteList = (List<IexQuote>) dao.findAllById(Arrays.asList("AAPL","FB"));
        assertEquals(2,quoteList.size());
        assertEquals("AAPL",quoteList.get(0).getSymbol());

        try{
            dao.findAllById(Arrays.asList("AAPL","FB2"));
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            fail();
        }
    }

    @Test
    public void findByTicker() {
        String ticker = "AAPL";
        IexQuote quote = dao.findById(ticker).get();
        assertEquals(ticker,quote.getSymbol());
    }

}
