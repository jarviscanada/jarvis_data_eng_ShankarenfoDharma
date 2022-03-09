package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestTwoSum {
    @Test
    public void twoSumTestTest(){
        TwoSum tS = new TwoSum();
        int[] ans = new int[] {0,1};
        assertArrayEquals((tS.twoSum(new int[] {1,2,3,4,5}, 3)), ans);
        assertArrayEquals((tS.twoSum(new int[] {1,2,3,4,5}, 7)), new int[] {1,4});
        assertArrayEquals((tS.twoSum(new int[] {1,2,3,4,5}, 9)), new int[] {3,4});
        assertArrayEquals((tS.twoSum(new int[] {8,8,5,1,10}, 16)), new int[]{0,1});
        assertArrayEquals((tS.twoSum(new int[] {8,8,5,1,10}, 13)), new int[]{0,2});

    }
}
