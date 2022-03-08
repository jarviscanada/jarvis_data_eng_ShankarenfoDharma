package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAtoi {
    @Test
    public void testAtoi(){
        Atoi atoi = new Atoi();
        assertEquals(atoi.parseAtoi("12"),12);
        assertEquals(atoi.parseAtoi("   -80 "),-80);
        assertEquals(atoi.parseAtoi("00112"),112);
        assertEquals(atoi.parseAtoi("-19900"),-19900);
        assertEquals(atoi.parseAtoi("2147483648"),Integer.MAX_VALUE);
        assertEquals(atoi.parseAtoi("-2147483648"),Integer.MIN_VALUE);

        assertEquals(12,atoi.manualAtoi("12"));
        assertEquals(atoi.manualAtoi("   -80 "),-80);
        assertEquals(atoi.manualAtoi("00112"),112);
        assertEquals(atoi.manualAtoi("-19900"),-19900);
        assertEquals(atoi.manualAtoi("2147483648"),Integer.MAX_VALUE);
        assertEquals(atoi.manualAtoi("-2147483648"),Integer.MIN_VALUE);
    }
}
