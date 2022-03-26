package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMissingNumber {
    @Test
    public void testMissingNums(){
        MissingNumber mN = new MissingNumber();
        assertEquals(8, mN.missingNumberSum(new int[]{9,6,4,2,3,5,7,0,1}));
        assertEquals(8, mN.missingNumberSet(new int[]{9,6,4,2,3,5,7,0,1}));
        assertEquals(8, mN.missingNumberGauss(new int[]{9,6,4,2,3,5,7,0,1}));
        assertEquals(2, mN.missingNumberSum(new int[]{3,0,1}));
        assertEquals(2, mN.missingNumberSet(new int[]{3,0,1}));
        assertEquals(2, mN.missingNumberGauss(new int[]{3,0,1}));
    }
}
