package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestNumberSwap {
    @Test
    public void testNumberSwap(){
        SwapTwoNumbers sTN = new SwapTwoNumbers();
        assertArrayEquals(new int[]{3,2}, sTN.swapNumbersArithmetic(new int[]{2,3}) );
        assertArrayEquals(new int[]{3,2}, sTN.swapNumbersBits(new int[]{2,3}) );
    }
}
