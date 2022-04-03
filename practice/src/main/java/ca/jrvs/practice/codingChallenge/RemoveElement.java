package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Remove-Element-a361b510197d4333abd3849776d63afe
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        //go through the array. each element is checked. if not match, move value eto slow pointer & slowpointer++. if match, skip
        //actual new array length is slowpointer.
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
