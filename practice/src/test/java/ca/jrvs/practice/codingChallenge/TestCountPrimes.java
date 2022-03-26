package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCountPrimes {
    @Test
    public void primeCounter(){
        CountPrimes cP = new CountPrimes();
        assertEquals(4,cP.primeCount(10));
    }
}
