package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.MarketDataConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    private Logger logger = LoggerFactory.getLogger(AppConfig.class);
//
//    @Bean Application application(QuoteService quoteService){
//        return new Application(quoteService);
//    }

//    @Bean
//    public QuoteService quoteService(MarketDataDao marketDataDao){
//        return new QuoteService(marketDataDao);
//    }

//    @Bean
//    public MarketDataDao marketDataDao(MarketDataConfig marketDataConfig){
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//        cm.setMaxTotal(50);
//        cm.setDefaultMaxPerRoute(50);
//        return new MarketDataDao(cm,marketDataConfig);
//    }

    @Bean
    public MarketDataConfig marketDataConfig(){
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1");
        marketDataConfig.setToken(System.getenv("IEX_TOKEN"));
        return marketDataConfig;
    }

    @Bean
    public HttpClientConnectionManager httpClientConnectionManager(){
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);
        return cm;
    }

    @Bean
    public DataSource dataSource() {
//        String jdbcUrl =
//                "jdbc:postgresql://" +
//                        System.getenv("PSQL_HOST") + ":" +
//                        System.getenv("PSQL_PORT") +
//                        "/" +
//                        System.getenv("PSQL_DB");
        String user = System.getenv("PSQL_USER");
        String password = System.getenv("PSQL_PASSWORD");
        String psql_url = System.getenv("PSQL_URL");

        //Never log your credentials/secrets. Use IDE debugger instead
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(psql_url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }
}
