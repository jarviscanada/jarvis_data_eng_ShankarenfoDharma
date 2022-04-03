package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static java.util.Arrays.sort;

public class TestRemoveElement {
    @Test
    public void removeElement(){
        RemoveElement rE = new RemoveElement();
        int[] nums = {3,2,2,3}; // Input array
        int val = 3; // Value to remove
        int[] expectedNums = {2,2};
        int k = rE.removeElement(nums,val);
        assert k == expectedNums.length;
        sort(nums, 0, k); // Sort the first k elements of nums
        for (int i = 0; i < expectedNums.length; i++) {
            assert nums[i] == expectedNums[i];
        }

        nums = new int[] {0,1,2,2,3,0,4,2};
        val = 2;
        expectedNums = new int[] {0,1,4,0,3};
        k = rE.removeElement(nums,val);
        assert k == expectedNums.length;
        sort(nums, 0, k); // Sort the first k elements of nums
        for (int i = 0; i < expectedNums.length; i++) {
            System.out.print(nums[i]+" ");
        }
        System.out.println("\nExpected contains: ");
        for (int i = 0; i < expectedNums.length; i++) {
            System.out.print(expectedNums[i]+" ");
        }

    }
}
