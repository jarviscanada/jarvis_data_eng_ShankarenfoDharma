package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * This Dao responsible from getting market data from IexCloud
 */
@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig){
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }

    @Override
    public <S extends IexQuote> S save(S s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * ID data type is String (ticker) and T is IexQuote. In the implementation, this method calls the IEX endpoint and deserializes IEX JSON HTTP response into IexQuote. If the ticker is not found, this method returns Optional.empty
     * @param ticker = id
     * @return quote if found, Optional.empty if not found
     */
    @Override
    public Optional<IexQuote> findById(String ticker) {
        Optional targetQuote;
        List<IexQuote> quotes = (List<IexQuote>) findAllById(Collections.singletonList(ticker));

        if (quotes.size() == 0)
            return Optional.empty();
        else if (quotes.size() == 1 )
            targetQuote = Optional.of(quotes.get(0));
        else
            throw new DataRetrievalFailureException("Unexpected number of quotes");

        return targetQuote;
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<IexQuote> findAll() {
        return null;
    }

    /**
     * Get Quotes from IEX
     * @param tickers list of tickers
     * @return list of iexquote object
     */
    @Override
    public Iterable<IexQuote> findAllById(Iterable<String> tickers) {
        String tickersString = String.join(",",tickers);
        String targetURL = (String.format(IEX_BATCH_URL,tickersString));
        String response = executeHttpGet(targetURL)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));

        //Array of JSON documents
        JSONObject IexQuotesJson = new JSONObject(response);
        //Get number of documents
        if (IexQuotesJson.length() == 0) {
            throw new IllegalArgumentException("Invalid ticker");
        }
        ObjectMapper mapper = new ObjectMapper();
        List<IexQuote> resultQuotes = new ArrayList<>();
        tickers.forEach( ticker -> {
            try {
                JSONObject quote = IexQuotesJson.getJSONObject(ticker).getJSONObject("quote");
                IexQuote tickerData = mapper.readValue(quote.toString(), IexQuote.class);
                resultQuotes.add(tickerData);
            } catch (IOException | JSONException e) {
                throw new IllegalArgumentException("Invalid tickers");
            }
        });
        return resultQuotes;

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(IexQuote iexQuote) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private Optional<String> executeHttpGet(String url){
        HttpGet request = new HttpGet(url);
        try {
            return Optional.ofNullable(EntityUtils.toString(getHttpClient().execute(request).getEntity()));
        } catch (IOException e) {
            throw new DataRetrievalFailureException("Http GET batch quotes failure");
        }
    }

    private CloseableHttpClient getHttpClient(){
        return HttpClients.custom().setConnectionManager(httpClientConnectionManager).setConnectionManagerShared(true).build();
    }
}
