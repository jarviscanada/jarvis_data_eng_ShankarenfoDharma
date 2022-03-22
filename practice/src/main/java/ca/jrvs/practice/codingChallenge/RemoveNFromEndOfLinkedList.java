package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Nth-Node-From-End-of-LinkedList-985686a70b9c4e83bb6c46023f915517
 */
public class RemoveNFromEndOfLinkedList {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    /**
     * Solution 1: Two pointers- 0th & nth. Proceed until second pointer reaches end, remove first pointer
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode firstPointer = head;
        ListNode secondPointer = head;
        for(int i = 0; i < n; i++){
            secondPointer = secondPointer.next;
        }

        while(secondPointer != null && secondPointer.next != null){
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next;
        }

        //do removal
        firstPointer.next = firstPointer.next.next;

        return head;
    }
}
