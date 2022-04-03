package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

public class TestMergeSortedArray {
    @Test
    public void testMergeSortedArray(){
        MergeSortedArray mSA = new MergeSortedArray();
        int[] nums1 = {1,2,3,4,4,7,0,0,0};
        int[] nums2 = {2,5,10};
        mSA.merge(nums1,6,nums2,3);
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i]+" ");
        }
    }
}
