package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TestPrintLetter {
    @Test
    public void testPrintLetter(){
        PrintLetter pl = new PrintLetter();
        assertEquals(true,pl.printLetterNumber("abcee").equals("a1b2c3e5e5"));
        assertEquals(true,pl.printLetterNumber("AAA").equals("A27A27A27"));
    }
}
