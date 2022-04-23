//package ca.jrvs.apps.trading;
//
//import ca.jrvs.apps.trading.dao.MarketDataDao;
//import ca.jrvs.apps.trading.model.MarketDataConfig;
//import ca.jrvs.apps.trading.service.QuoteService;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//
//public class AppConfig {
//    private Logger logger = LoggerFactory.getLogger(AppConfig.class);
//
//    public static void main(String[] args) throws Exception {
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        Application app = context.getBean(Application.class);
//        app.run(args);
//    }
//
//    @Bean
//    public MarketDataConfig marketDataConfig(){
//        MarketDataConfig marketDataConfig = new MarketDataConfig();
//        marketDataConfig.setHost("https://cloud.iexapis.com/v1");
//        marketDataConfig.setToken(System.getenv("IEX_TOKEN"));
//        return marketDataConfig;
//    }
//
//    @Bean
//    public MarketDataDao marketDataDao(){
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//        cm.setMaxTotal(50);
//        cm.setDefaultMaxPerRoute(50);
//        return new MarketDataDao(cm,marketDataConfig());
//    }
//}
