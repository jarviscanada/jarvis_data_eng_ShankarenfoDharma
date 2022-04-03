package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Duplicates-from-Sorted-Array-ef931ec74a794291b08e6eb1de4ecdac
 */
public class DuplicateSortedArrays {
    public int removeDuplicates(int[] nums){
        int i = 0;
        //go through the array. each element is checked. if not match, move value eto slow pointer & slowpointer++. if match, skip
        //actual new array length is slowpointer.
        if(nums.length == 1)
            return 1;

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}
