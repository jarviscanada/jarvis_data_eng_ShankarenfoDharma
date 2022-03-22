package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

public class TestMiddleLinkedList {
    @Test
    public void testMiddleLinkedList(){
        MiddleLinkedList mLL = new MiddleLinkedList();
        MiddleLinkedList.ListNode headNode = new MiddleLinkedList.ListNode(1);
        headNode.next = new MiddleLinkedList.ListNode(2);
        headNode.next.next = new MiddleLinkedList.ListNode(3);
        headNode.next.next.next = new MiddleLinkedList.ListNode(4);
        headNode.next.next.next.next = new MiddleLinkedList.ListNode(5);
        headNode.next.next.next.next.next = new MiddleLinkedList.ListNode(6);
        MiddleLinkedList.ListNode middleNode = mLL.middleNode(headNode);
        while(middleNode != null){
            System.out.print(middleNode.val);
            middleNode = middleNode.next;
        }
        
        headNode = new MiddleLinkedList.ListNode(1);
        headNode.next = new MiddleLinkedList.ListNode(2);
        headNode.next.next = new MiddleLinkedList.ListNode(3);
        headNode.next.next.next = new MiddleLinkedList.ListNode(4);
        headNode.next.next.next.next = new MiddleLinkedList.ListNode(5);
        middleNode = mLL.middleNode(headNode);
        while(middleNode != null){
            System.out.print(middleNode.val);
            middleNode = middleNode.next;
        }
    }
}
