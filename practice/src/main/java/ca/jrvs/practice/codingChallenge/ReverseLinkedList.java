package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Reverse-Linked-List-78f29fa654904f8fb355c6b0e7fe9c3e
 */
public class ReverseLinkedList {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    //solution 1: iteration
    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode target = null;
        ListNode next = head.next;
        while(next != null){
            next = current.next;
            current.next =  target;
            target = current;
            if(next == null)
                break;
            current = next;
        }
        return current;
    }

    //solution 2: Recursion
    public ListNode reverseListRecursive(ListNode head){
        if(head == null) {
            return null;
        }
        // last node or only one node
        if(head.next == null) {
            return head;
        }

        ListNode next = reverseListRecursive(head.next);
        head.next.next =  head;
        head.next = null;
        return next;
    }
}
