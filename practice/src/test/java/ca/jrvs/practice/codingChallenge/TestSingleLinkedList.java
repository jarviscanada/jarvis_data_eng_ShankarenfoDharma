package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

public class TestSingleLinkedList {
    @Test
    public void removeFromEnd(){
        RemoveNFromEndOfLinkedList remover = new RemoveNFromEndOfLinkedList();
        RemoveNFromEndOfLinkedList.ListNode lNodeHead = new RemoveNFromEndOfLinkedList.ListNode(1);
        RemoveNFromEndOfLinkedList.ListNode lNodeNext = new RemoveNFromEndOfLinkedList.ListNode(2);
        lNodeHead.next = lNodeNext;
        lNodeNext.next = new RemoveNFromEndOfLinkedList.ListNode(3);
        lNodeNext.next.next = new RemoveNFromEndOfLinkedList.ListNode(4);
        lNodeNext.next.next.next = new RemoveNFromEndOfLinkedList.ListNode(5);

        remover.removeNthFromEnd(lNodeHead,2);
        while(lNodeHead.next != null){
            System.out.print(lNodeHead.val);
            lNodeHead = lNodeHead.next;
        }
        System.out.println(lNodeHead.val);

        lNodeHead = new RemoveNFromEndOfLinkedList.ListNode(1);
        lNodeHead.next = new RemoveNFromEndOfLinkedList.ListNode(2);
        remover.removeNthFromEnd(lNodeHead,1);
        while(lNodeHead.next != null){
            System.out.print(lNodeHead.val);
            lNodeHead = lNodeHead.next;
        }
        System.out.println(lNodeHead.val);
    }


}
