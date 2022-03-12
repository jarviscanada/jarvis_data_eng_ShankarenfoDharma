package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class TestLinkedListDuplicates {
    @Test
    public void linkedListDuplicates(){
        LinkedList<Integer> ll = new LinkedList<>();
        ll.addAll(Arrays.asList(3,4,5,6,7,3,2,3,5,3));
        LinkedList<Integer> lA = new LinkedList<>();
        lA.addAll(Arrays.asList(3,4,5,6,7,2));
        RemoveLinkedListDuplicates rLLD = new RemoveLinkedListDuplicates();
        assertEquals(true,lA.equals(rLLD.removeDuplicates(ll)));
    }
}
