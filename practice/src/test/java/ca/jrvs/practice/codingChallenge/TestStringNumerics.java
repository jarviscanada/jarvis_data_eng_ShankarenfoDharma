package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestStringNumerics {
    @Test
    public void testStringNumerics(){
        String numeric = "12343245";
        String antiNumeric = "143Afsa";
        DetectStringNumerics detector = new DetectStringNumerics();
        assertTrue(detector.isStringNumericsASCII(numeric));
        assertTrue(detector.isStringNumericsRegex(numeric));
        assertTrue(detector.isStringNumericsAPI(numeric));
        assertFalse(detector.isStringNumericsASCII(antiNumeric));
        assertFalse(detector.isStringNumericsRegex(antiNumeric));
        assertFalse(detector.isStringNumericsAPI(antiNumeric));
    }
}
