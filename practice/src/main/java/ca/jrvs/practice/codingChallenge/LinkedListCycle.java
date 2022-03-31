package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/LinkedList-Cycle-cf7e0fe8c59e4907a533cb09e8c5aef9
 */
public class LinkedListCycle {
    static class ListNode {
     int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }

    //Custom solution: Use set
    public boolean hasCycle(ListNode head) {
        Set<ListNode> uniqueNodes = new HashSet<>();
        ListNode pointer = head;
        while (pointer != null){
            if(!uniqueNodes.add(pointer)){
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    //Solution 1: Use pointers
    public boolean hasCyclePointers(ListNode head){
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while (fastPointer != null && fastPointer.next != null && fastPointer.next.next != null){
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if(fastPointer == slowPointer){
                return true;
            }
        }
        return false;
    }
}
