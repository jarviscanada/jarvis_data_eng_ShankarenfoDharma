package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDuplicateSortedArray {
    @Test
    public void testDuplicateSorted(){
        DuplicateSortedArrays dSA =new DuplicateSortedArrays();
        int[] nums = {1,1,1,2,2,3};
        assertEquals(3, dSA.removeDuplicates(nums));
        for (int i = 0; i < 3; i++) {
            System.out.print(nums[i]+ " ");
        }
        nums = new int[] {1,2,2,3,3};
        assertEquals(3, dSA.removeDuplicates(nums));
        for (int i = 0; i < 3; i++) {
            System.out.print(nums[i]+ " ");
        }
        nums = new int[] {1,6,9};
        assertEquals(3, dSA.removeDuplicates(nums));
        for (int i = 0; i < 3; i++) {
            System.out.print(nums[i]+ " ");
        }
    }
}
