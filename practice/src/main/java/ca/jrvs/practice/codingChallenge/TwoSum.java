package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

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

    /**
     * hash search method
     * Big-O: O(n)
     * Justification: Go through array once
     * @param nums
     * @param target
     * @return
     * transform array into hashmap (key is value). For each (until match is found) item, find the possible match target-sum1=[sum2] ask if hashmap contains key of sum2. if found return value(index) as array
     */
    public int[] twoSumOne(int[] nums, int target){
        HashMap<Integer,Integer> hashNums = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            hashNums.put(nums[i],i);
        }
        //foreach needs to finish, cannot return in the middle of operation
//        hashNums.forEach((index, sum1) -> {
//            int sum2 = target-sum1;
//            if(hashNums.containsKey(sum2) && hashNums.get(sum2) != index){
//                int[] ans = {index, hashNums.get(sum2)};
//            }
//        });
        for (int index = 0; index < nums.length; index++) {
            int sum2 = target-nums[index];
            if(hashNums.containsKey(sum2) && hashNums.get(sum2) != index){
                return new int[] {index, hashNums.get(sum2)};
            }
        }
        return null;
    }
}
