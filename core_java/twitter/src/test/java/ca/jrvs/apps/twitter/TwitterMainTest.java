package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.spring.TwitterCLISpringBoot;
import org.junit.Test;

public class TwitterMainTest {
    @Test
    public void testMain(){
        TwitterCLISpringBoot.main(new String[]{"post","afiouiqffdvsdawedf","23:84"});
        TwitterCLISpringBoot.main(new String[]{"show","1512172159904489480","text"});
        TwitterCLISpringBoot.main(new String[]{"delete","1512172159904489480"});
    }
}
