package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.service.QuoteService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(Application.class);
    private static final String GENERIC_USAGE_LINE="quote [id]";
    private QuoteService quoteService;

    //@Value("${app.init.dailylist}")
    //private String[] initDailyList;

    @Autowired
    public Application(QuoteService quoteService){this.quoteService = quoteService;}

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class,args);

//        SpringApplication app = new SpringApplication(Application.class);
//        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
//        if(args.length == 0){
//            throw new IllegalArgumentException(GENERIC_USAGE_LINE);
//        }
//        switch (args[0].toLowerCase(Locale.ROOT)) {
//            case "quote":
//                if(!args[1].contains(","))
//                    displayQuote(quoteService.findIexQuoteByTicker(args[1]));
//                else
//                    quoteService.findIexQuotesByTickers(args[1]).forEach(quote -> displayQuote(quote));
//                break;
//            default:
//                throw new IllegalArgumentException(GENERIC_USAGE_LINE);
//        }
    }

    private void displayQuote(IexQuote iexQuoteByTicker){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            System.out.println(objectMapper.writeValueAsString(iexQuoteByTicker));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert Quote object into string.",e);
        }
    }
}
