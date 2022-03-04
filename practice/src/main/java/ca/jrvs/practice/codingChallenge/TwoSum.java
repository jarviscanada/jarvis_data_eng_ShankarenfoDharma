package ca.jrvs.practice.codingChallenge;

/**
 * Ticket: https://www.notion.so/jarvisdev/Two-Sum-067fee9f084e437caaf80d01c7078695
 */
public class TwoSum {
    /**
     * brute force method
     * Big-O: O(n^2)
     * Justification: Go through the array for each item
     * @param nums is the array to be searched
     * @param target is the pair sum target
     * @return two-array of indices that if the value are summed will result in target
     */
    public int[] twoSum(int[] nums, int target) {
        for(int firstIndex = 0; firstIndex < nums.length; firstIndex++){
            for(int secondIndex = firstIndex+1; secondIndex < nums.length; secondIndex++){
                if(nums[firstIndex] + nums[secondIndex] == target){
                    return new int[] {firstIndex,secondIndex};
                }
            }
        }
        return null;
    }
}
