package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestReverseLinkedList {
    @Test
    public void testReverseLinkedList(){
        ReverseLinkedList rLL = new ReverseLinkedList();
        ReverseLinkedList.ListNode head = new ReverseLinkedList.ListNode(1);
        head.next = new ReverseLinkedList.ListNode(2);
        head.next.next = new ReverseLinkedList.ListNode(3);
        head.next.next.next = new ReverseLinkedList.ListNode(4);

        ReverseLinkedList.ListNode ans = rLL.reverseList(head);
        assertNotNull(ans);
        while(ans != null){
            System.out.print(ans.val+"-");
            ans = ans.next;
        }

        head = new ReverseLinkedList.ListNode(1);
        head.next = new ReverseLinkedList.ListNode(2);
        head.next.next = new ReverseLinkedList.ListNode(3);
        head.next.next.next = new ReverseLinkedList.ListNode(4);

        ans = rLL.reverseListRecursive(head);
        assertNotNull(ans);
        while(ans != null){
            System.out.print(ans.val+"-");
            ans = ans.next;
        }
    }
}
