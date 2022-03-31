package ca.jrvs.practice.codingChallenge;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * https://www.notion.so/jarvisdev/Find-the-Duplicate-Number-a038b999c74341fa838de1f0f19ea4d6
 */
public class FindDuplicateNum {
    //Solution 1: sorting
    /**
     * use count[] array size n. count[] element with more than 1 is duplicate
     */
    public int findDuplicateSort(int[] nums){
        int[] count = new int[nums.length+1];
        for(int e=0; e<count.length;e++){
            count[e] = 0;
        }
        for(int i = 0; i < nums.length; i++){
            count[nums[i]]++;
        }
        for(int i = 1; i <count.length; i++){
            if(count[i] > 1){
                return i;
            }
        }
        return -1;
    }

    //Solution 2: set
    public int findDuplicateSet(int[] nums){
        Set<Integer> uniqueInts = new LinkedHashSet<>();
        for(int num:nums){
            if(!uniqueInts.add(num))
                return num;
        }
        return 0;
    }
}
