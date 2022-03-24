package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestLinkedListCycles {
    @Test
    public void testCycles(){
        LinkedListCycle cycle = new LinkedListCycle();
        LinkedListCycle.ListNode node = new LinkedListCycle.ListNode(1);
        LinkedListCycle.ListNode dupe = new LinkedListCycle.ListNode(2);
        node.next = dupe;
        dupe.next = new LinkedListCycle.ListNode(3);
        dupe.next.next = new LinkedListCycle.ListNode(4);
        dupe.next.next.next = new LinkedListCycle.ListNode(5);
        dupe.next.next.next.next = dupe;
        assertTrue(cycle.hasCycle(node));
        assertTrue(cycle.hasCyclePointers(node));

        dupe.next.next.next.next = null;
        assertFalse(cycle.hasCycle(node));
        assertFalse(cycle.hasCyclePointers(node));

        node.next = dupe;
        dupe.next = new LinkedListCycle.ListNode(3);
        dupe.next.next = new LinkedListCycle.ListNode(3);
        dupe.next.next.next = new LinkedListCycle.ListNode(1);
        dupe.next.next.next.next = null;
        assertFalse(cycle.hasCycle(node));
        assertFalse(cycle.hasCyclePointers(node));
    }
}
