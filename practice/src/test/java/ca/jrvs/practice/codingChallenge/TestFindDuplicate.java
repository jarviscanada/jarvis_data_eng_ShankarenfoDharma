package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFindDuplicate {
    @Test
    public void testFindDupe(){
        FindDuplicateNum fDN = new FindDuplicateNum();
        assertEquals(3, fDN.findDuplicateSet(new int[]{3,1,3,4,2}));
        assertEquals(2, fDN.findDuplicateSet(new int[]{1,3,2,4,2}));
        assertEquals(3, fDN.findDuplicateSort(new int[]{3,1,3,4,2}));
        assertEquals(2, fDN.findDuplicateSort(new int[]{1,3,2,4,2}));
    }
}
