package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestLargestSmallest {
    @Test
    public void largestSmallest(){
        FindLargestSmallest fLS = new FindLargestSmallest();
        assertArrayEquals(new int[]{12,5}, fLS.findLargestSmallestAPI(new int[] {5,6,7,12,9,11}));
        assertArrayEquals(new int[]{12,5}, fLS.findLargestSmallestLoop(new int[] {5,6,7,12,9,11}));
        assertArrayEquals(new int[]{12,5}, fLS.findLargestSmallestStream(new int[] {5,6,7,12,9,11}));
    }
}
