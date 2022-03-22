package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Middle-of-the-Linked-List-b17dd03e0dc7428486994e713e3a80c0
 */
public class MiddleLinkedList {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Solution 1: Use 2 pointers: 1 normal, 1 fast (jump ahead 2x). fastP reaches end, normal is middle
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode firstPointer = head;
        ListNode secondPointer = head;

        while(secondPointer != null && secondPointer.next != null){
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next.next;
        }

        return firstPointer;
    }
}
