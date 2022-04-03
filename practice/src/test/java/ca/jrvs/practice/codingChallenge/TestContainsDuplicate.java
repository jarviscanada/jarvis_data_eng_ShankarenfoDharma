package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestContainsDuplicate {
    @Test
    public void testContainsDupes(){
        ContainsDuplicate cD = new ContainsDuplicate();
        assertTrue(cD.containsDuplicateSet(new int[] {1,2,3,4,1}));
        assertTrue(cD.containsDuplicateLoops(new int[] {1,2,3,4,1}));
        assertTrue(!cD.containsDuplicateSet(new int[] {1,2,3}));
        assertTrue(!cD.containsDuplicateLoops(new int[] {1,2,3,4}));
    }
}
