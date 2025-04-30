package com.bnjmnbrmn;

import java.util.List;

public class ReverseLinkedList206 {

    public static void main(String[] args) {
        ListNode listNode = new ReverseLinkedList206().reverseList(new ListNode(5, new ListNode(6, new ListNode(7))));
        System.out.println("listNode = " + listNode);


    }

    public ListNode reverseList(ListNode head) {
        return reversedListWithTail(head)[0];
    }

    private ListNode[] reversedListWithTail(ListNode head) {
        if (head == null) {
            return new ListNode[] {null, null};
        }
        if (head.next == null)
            return new ListNode[] {head, head};

        ListNode[] reversedListWithTail = reversedListWithTail(head.next);
        ListNode reversedTail = reversedListWithTail[0];
        ListNode reversedTailTail = reversedListWithTail[1];
        reversedTailTail.next = head;
        head.next = null;
        return new ListNode[] {reversedTail, head};
    }


//    public ListNode reverseList(ListNode head) {
//        ListNode currentNewListHead = null;
//        ListNode prevNewListHead = null;
//        while (head != null) {
//            currentNewListHead = head;
//            head = head.next;
//            currentNewListHead.next = prevNewListHead;
//            prevNewListHead = currentNewListHead;
//        }
//        return currentNewListHead;
//    }

//    public ListNode reverseList(ListNode head) {
//        ListNode current = head;
//        ListNode newListHead = null;
//        while (current != null) {
//            newListHead = new ListNode(current.val, newListHead);
//            current = current.next;
//        }
//        return newListHead;
//    }

//    public ListNode reverseList(ListNode head) {
//        ListNode current = head;
//        ListNode newListHead = null;
//        while (current != null) {
//            if (newListHead == null) {
//                newListHead = new ListNode(current.val);
//            } else {
//                newListHead = new ListNode(current.val, newListHead);
//            }
//            current = current.next;
//        }
//
//        return newListHead;
//    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
