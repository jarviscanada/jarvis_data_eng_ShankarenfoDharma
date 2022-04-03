package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/Contains-Duplicate-89130cd5a48d466cbc234ceb3463eeac
 */
public class ContainsDuplicate {
    //bruteforce
    public boolean containsDuplicateLoops(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j<nums.length; j++){
                if(nums[i] == nums[j])
                    return true;
            }
        }
        return false;
    }

    //using set
    public boolean containsDuplicateSet(int[] nums) {
        Set uniques = new HashSet<>();
        for (int num:nums) {
            if(!uniques.add(num)){
                return true;
            }
        }
        return false;
    }
}
