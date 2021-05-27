package com.company.Mar;

import java.util.*;

/**
 * Definition for singly-linked list.
 */


public class LinkedList10 {

    static ListNode head;
    static ListNode head1;
    static ListNode head2;

    static class ListNode {

        public int val;
        public ListNode next;

        public ListNode() {

        }

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public void startFunction() {

        Integer[] arr1 = {1, 2, 3, 4, 5};
        ArrayList<Integer> al1 = new ArrayList<>();
        Collections.addAll(al1, arr1);

        Integer[] arr2 = {5, 6, 6};
        ArrayList<Integer> al2 = new ArrayList<>();
        Collections.addAll(al2, arr2);


        LinkedList10 llist = new LinkedList10();

        for (int i = al1.size() - 1; i >= 0; i--) {
            llist.pushA(al1.get(i));
        }


        for (int i = al2.size() - 1; i >= 0; i--) {
            llist.pushB(al2.get(i));
        }
//        llist.addTwoNumbers(head1,head2);
//        llist.LongestPalindromic(head1);
//        reverseBetween(head1,13,15);
        swapPairs(head1);


    }



    /*Swap List Nodes in pairs
Problem Description

Given a linked list A, swap every two adjacent nodes and return its head.

NOTE: Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.*/

    public ListNode swapPairs(ListNode A) {


        if (A == null || A.next == null)
            return A;

        ListNode curr = A.next;
        ListNode prev = A;
        ListNode start = curr;
        while (true) {
            ListNode next = curr.next;
            curr.next = prev;

            if (next == null || next.next == null) {
                prev.next = next;
                break;
            }
            prev.next = next.next;
            prev = next;
            curr = prev.next;
        }


        return start;
    }

    /*Reverse Link List II
Problem Description

Reverse a linked list A from position B to C.

NOTE: Do it in-place and in one-pass.*/
    public ListNode reverseBetween(ListNode A, int B, int C) {

        int i = 1;

        ListNode curr = A;

        ListNode next = null;
        ListNode prev = null;
        ListNode prev1 = null;
        ListNode start = A;
        while (i != B) {
            prev1 = curr;
            curr = curr.next;
            i++;
        }

        prev = null;
        ListNode curr1 = curr;
        while (curr != null && i <= C) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            i++;
        }
//        prev.next=curr;
        curr1.next = curr;
        if (B != 1) {
            prev1.next = prev;
            return start;
        } else
            return prev;

    }

    /*Longest Palindromic List
Problem Description

Given a linked list of integers. Find and return the length of the longest palindrome list that exists in that linked list.

A palindrome list is a list that reads the same backward and forward.

Expected memory complexity : O(1)*/
    public int LongestPalindromic(ListNode A) {
        ListNode curr = A;
        ListNode prev = null;
        ListNode next = null;

        int res = 0;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            res = Math.max(res, 2 * countNode(prev, next) + 1);
            res = Math.max(res, 2 * countNode(curr, next));
            prev = curr;
            curr = next;
        }
        return res;
    }

    public int countNode(ListNode prev, ListNode forward) {
        int cnt = 0;
        while (prev != null && forward != null) {
            if (prev.val == forward.val)
                cnt++;
            else
                break;
            prev = prev.next;
            forward = forward.next;
        }
        return cnt;
    }





    /*Add Two Numbers as Lists
Problem Description

You are given two linked lists, A and B representing two non-negative numbers.

The digits are stored in reverse order and each of their nodes contain a single digit.

Add the two numbers and return it as a linked list.*/

    public ListNode addTwoNumbers(ListNode A, ListNode B) {
        ListNode head = null;
        ListNode ptr = null;
        long sum = 0;
        while (A != null && B != null) {
            sum = sum + A.val + B.val;
            if (head == null) {
                head = new ListNode((int) (sum % 10));
                sum = sum / 10;
                ptr = head;
            } else {
                ptr.next = new ListNode((int) (sum % 10));
                sum = sum / 10;
                ptr = ptr.next;
            }
            A = A.next;
            B = B.next;
        }

        if (A == null) {
            while (B != null) {
                sum = sum + B.val;
                ptr.next = new ListNode((int) (sum % 10));
                sum = sum / 10;
                B = B.next;
                ptr = ptr.next;
            }
        } else {
            while (A != null) {
                sum = sum + A.val;
                ptr.next = new ListNode((int) (sum % 10));
                sum = sum / 10;
                A = A.next;
                ptr = ptr.next;
            }
        }

        if (sum > 0)
            ptr.next = new ListNode((int) (sum % 10));

        return head;
    }

    /*Merge Two Sorted Lists
Problem Description

Merge two sorted linked lists A and B and return it as a new list.

The new list should be made by splicing together the nodes of the first two lists, and should also be sorted.*/

    public ListNode mergeTwoLists(ListNode A, ListNode B) {
        if (A == null)
            return B;
        if (B == null)
            return A;
        ListNode head = null;
        ListNode ptr = null;
        if (A.val < B.val) {
            head = A;
            A = A.next;
        } else {
            head = B;
            B = B.next;
        }
        ptr = head;
        while (A != null && B != null) {
            if (A.val < B.val) {
                ptr.next = A;
                A = A.next;
            } else {
                ptr.next = B;
                B = B.next;
            }
            ptr = ptr.next;
        }
        if (A != null) {
            ptr.next = A;
        } else {
            ptr.next = B;
        }

        return head;
    }
    /*Partition List
Problem Description

Given a linked list A and a value B, partition it such that all nodes less than B come before nodes greater than or equal to B.

You should preserve the original relative order of the nodes in each of the two partitions.*/

    public ListNode partition(ListNode A, int B) {
        ListNode n1, n2;
        n1 = new ListNode(0);
        n2 = new ListNode(0);
        ListNode n1s = n1;
        ListNode n2s = n2;

        while (A != null) {
            if (A.val < B) {
                n1.next = A;
                n1 = n1.next;
//                n1.next=null;
            } else {
                n2.next = A;
                n2 = n2.next;
//                n2.next=null;
            }
            A = A.next;
        }
        n1.next = null;
        n2.next = null;
        n1.next = n2s.next;


        return n1s.next;
    }

    /*Remove Loop from Linked List
Problem Description

Given a linked list which contains some loop.

You need to find the node, which creates a loop, and break it by making the node point to NULL.*/

    public ListNode checkLoop(ListNode A) {

        if (A == null || A.next == null)
            return A;

        ListNode slow = A.next;
        ListNode fast = A.next.next;
        while (fast.next != null && fast.next.next != null) {
            if (slow == fast) {
                ListNode node = removeLoop(slow, A);
                return node;
            }
            slow = slow.next;
            fast = fast.next.next;


        }

        return A;

    }

    private ListNode removeLoop(ListNode slow, ListNode head) {

        ListNode ptr1 = head;
        ListNode prevSlow = head;
        while (ptr1 != slow) {
            ptr1 = ptr1.next;
            prevSlow = slow;
            slow = slow.next;
        }

        prevSlow.next = null;


        return head;
    }

    /*Palindrome List
Problem Description

Given a singly linked list A, determine if its a palindrome. Return 1 or 0 denoting if its a palindrome or not, respectively.*/

    public int lPalin(ListNode A) {

        ListNode mid = getMid(A);
        ListNode node2 = reverseList(mid.next);
        mid.next = null;
        ListNode node1 = A;


        while (node1 != null && node2 != null) {
            if (node1.val != node1.val)
                return 0;

            node1 = node1.next;
            node2 = node2.next;
        }

        return 1;
    }

    /*Sort List
Problem Description

Sort a linked list, A in O(n log n) time using constant space complexity.*/

    public ListNode sortList(ListNode A) {


        return mergeSort(A);


    }

    private ListNode mergeSort(ListNode A) {

        if (A == null || A.next == null)
            return A;
        ListNode middle = getMid(A);
        ListNode middleNext = middle.next;
        middle.next = null;
        ListNode left = mergeSort(A);
        ListNode right = mergeSort(middleNext);
        ListNode sortList = MergeSort(left, right);

        return sortList;

    }

    private ListNode MergeSort(ListNode left, ListNode right) {

        ListNode ans = null;
        if (left == null)
            return right;
        if (right == null)
            return left;

        if (left.val <= right.val) {
            ans = left;
            ans.next = MergeSort(left.next, right);
        } else {
            ans = right;
            ans.next = MergeSort(left, right.next);
        }
        return ans;
    }


    private ListNode getMid(ListNode A) {

        if (A == null)
            return A;
        ListNode slow = A;
        ListNode fast = A;


        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }



    /*Reorder List
Problem Description

Given a singly linked list A

 A: A0 → A1 → … → An-1 → An
reorder it to:

 A0 → An → A1 → An-1 → A2 → An-2 → …
You must do this in-place without altering the nodes' values.*/

    public ListNode reorderList(ListNode A) {
        ListNode slow = A;
        ListNode fast = A;
        ListNode prevSlow = null;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            prevSlow = slow;
            slow = slow.next;
        }


        ListNode firstHalf = A;
        ListNode secHalf = slow.next;
        slow.next = null;


        secHalf = reverseList(secHalf);


        ListNode ans = new ListNode(0);
        ListNode curr = ans;

        while (firstHalf != null && secHalf != null) {
            if (firstHalf != null) {
                curr.next = firstHalf;
                curr = curr.next;
                firstHalf = firstHalf.next;
            }

            if (secHalf != null) {
                curr.next = secHalf;
                curr = curr.next;
                secHalf = secHalf.next;
            }

        }

        if (firstHalf != null)
            curr.next = firstHalf;

        ans = ans.next;
        return ans;
    }


    public ListNode reverseList(ListNode head) {
        ListNode next = null;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    /*K reverse linked list
Problem Description

Given a singly linked list A and an integer B, reverse the nodes of the list B at a time and return modified linked list.*/

    public ListNode reverseList(ListNode A, int B) {
        ListNode currHead = revere(A, B);

        return currHead;
    }

    private ListNode revere(ListNode a, int b) {
        ListNode next = null;
        ListNode prev = null;
        ListNode curr = a;
        int i = 0;
        while (i < b && curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            i++;
        }
        //track of first element after reversing and send next node for reversing
        //we dont need to call recursively for last eleemtn
        if (next != null)
            a.next = revere(next, b);

//        System.out.println(a.val);

        //return first group of last element as new head
        return prev;


    }

    public ListNode removeNthFromEnd(ListNode A, int B) {

        ListNode fast = A;
        ListNode slow = A;


        for (int i = 0; i < B + 1; i++) {

            //if i am at last node
            if (fast.next == null) {
                if (i <= (B - 1)) {
                    A = A.next;
                    return A;
                }
            }
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return A;
    }


    public ListNode pushA(int new_data) {
        ListNode new_node = new ListNode(new_data);
        new_node.next = head1;
        head1 = new_node;
        return new_node;
    }

    public ListNode pushB(int new_data) {
        ListNode new_node = new ListNode(new_data);
        new_node.next = head2;
        head2 = new_node;
        return new_node;
    }


}
