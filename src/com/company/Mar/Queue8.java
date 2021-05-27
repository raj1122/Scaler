package com.company.Mar;

import java.util.*;

public class Queue8 {

    public void execueTfn() {
        Integer[] arr1 = {2, 3, 1, 5, 4};
        Integer[] arr2 = {1, 3, 5, 4, 2};
        ArrayList<Integer> al1 = new ArrayList<>();
        ArrayList<Integer> al2 = new ArrayList<>();
        Collections.addAll(al1, arr1);
        Collections.addAll(al2, arr2);
//        FirstNegNum(arrl,4);
        TaskScheduling(al1, al2);

    }

    /*Task Scheduling
Problem Description

A CPU has N tasks to be performed. It is to be noted that the tasks have to be performed in a specific order to avoid deadlock. In every clock cycle the CPU can either perform a task or move it to the back of the queue. You are given the current state of the scheduler queue in an array A and the required order of the tasks in an array B.

Determine the minimum number of clock cycles to complete all the tasks.*/

    public int TaskScheduling(ArrayList<Integer> A, ArrayList<Integer> B) {

        Queue<Integer> q = new LinkedList<>(A);
        int ans = q.size();
        int bIdx = 0;
        while (!q.isEmpty()) {
            while (!q.isEmpty() && q.peek().intValue() == B.get(bIdx).intValue()) {
                q.remove();
                bIdx++;
            }
            if (!q.isEmpty() && q.peek().intValue() != B.get(bIdx).intValue()) {
                ans++;
                int t = q.remove();
                q.add(t);
            }
        }
        return ans;
    }

    /*First negative integer in window size B
Problem Description

Given an array of integers A of size N and an integer B.

Find the first negative integer for each and every window(contiguous subarray) of size B.

If a window does not contain a negative integer, then return 0 for that window.*/

    public ArrayList<Integer> FirstNegNum(ArrayList<Integer> A, int B) {
        ArrayList<Integer> res = new ArrayList<>();
        Deque<Integer> minQ = new LinkedList<>();

        int i = 0;
        while (i < B) {
            while (!minQ.isEmpty() && A.get(minQ.peekLast()) >= 0 && A.get(minQ.peekLast()) >= A.get(i))
                minQ.removeLast(); // Remove from rear

            minQ.addLast(i);
            i++;
        }

        // Process rest of the Array elements
        while (i < A.size()) {
            int t = A.get(minQ.peekFirst());
            if (t < 0)
                res.add(t);
            else
                res.add(0);


            while (!minQ.isEmpty() && minQ.peekFirst() < (i - B + 1))
                minQ.removeFirst();

            while (!minQ.isEmpty() && A.get(minQ.peekLast()) >= 0 && A.get(minQ.peekLast()) >= A.get(i))
                minQ.removeLast(); // Remove from rear

            minQ.addLast(i);
            i++;
        }

        int t = A.get(minQ.peekFirst());
        if (t < 0)
            res.add(t);
        else
            res.add(0);


        return res;
    }


    /*Sum of min and max
Problem Description

Given an array A of both positive and negative integers.

Your task is to compute sum of minimum and maximum elements of all sub-array of size B.

NOTE: Since the answer can be very large, you are required to return the sum modulo 109 + 7.*/

    public int MinMaxSubarray(ArrayList<Integer> A, int B) {
        long sum = 0;
        long mod = (int) 1e9 + 7;
        Deque<Integer> minQ = new LinkedList<>();
        Deque<Integer> maxQ = new LinkedList<>();

        int i = 0;
        while (i < B) {
            while (!minQ.isEmpty() && A.get(minQ.peekLast()) >= A.get(i))
                minQ.removeLast(); // Remove from rear

            // Remove all previous smaller that are elements
            // are useless.
            while (!maxQ.isEmpty() && A.get(maxQ.peekLast()) <= A.get(i))
                maxQ.removeLast(); // Remove from rear

            // Add current element at rear of both deque
            maxQ.addLast(i);
            minQ.addLast(i);
            i++;
        }

        // Process rest of the Array elements
        while (i < A.size()) {
            long min = 1L * A.get(minQ.peekFirst());
            long max = 1L * A.get(maxQ.peekFirst());
            long t1 = calculateModOnAddition(max, min);
            sum = calculateModOnAddition(sum, t1);


            while (!minQ.isEmpty() && minQ.peekFirst() <= i - B)
                minQ.removeFirst();
            while (!maxQ.isEmpty() && maxQ.peekFirst() <= i - B)
                maxQ.removeFirst();

            while (!minQ.isEmpty() && A.get(minQ.peekLast()) >= A.get(i))
                minQ.removeLast(); // Remove from rear

            // remove all previous smaller that are elements
            // are useless
            while (!maxQ.isEmpty() && A.get(maxQ.peekLast()) <= A.get(i))
                maxQ.removeLast(); // Remove from rear

            // Add current element at rear of both deque
            maxQ.addLast(i);
            minQ.addLast(i);
            i++;
        }

        // Sum of minimum and maximum element of last window
        long min = 1L * A.get(minQ.peekFirst());
        long max = 1L * A.get(maxQ.peekFirst());
        long t1 = calculateModOnAddition(max, min);
        sum = calculateModOnAddition(sum, t1);

        return (int) (sum % mod);
    }

    public long calculateModOnAddition(long num1, long num2) {
        long mod = (long) (1e9 + 7);
        //if number is negative then adding mod
        if (num1 < 0)
            num1 = num1 + 1000000007;

        //if number is negative then adding mod
        if (num2 < 0)
            num2 = num2 + 1000000007;

        //taking mod if number is greater than mod in case of negative numbers
        num1 = num1 % 1000000007;
        num2 = num2 % 1000000007;

        //rule of mod in addition
        long t1 = ((num1 % mod) + (num2 % mod)) % mod;
        return t1;

    }

        /*Gas Station
Given two integer arrays A and B of size N. There are N gas stations along a circular route, where the amount of gas at station i is A[i].

You have a car with an unlimited gas tank and it costs B.get(i) of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the minimum starting gas station's index if you can travel around the circuit once, otherwise return -1.

You can only travel in one direction. i to i+1, i+2, ... n-1, 0, 1, 2.. Completing the circuit means starting at i and ending up at i again.

*/

    public int canCompleteCircuit1(final List<Integer> A, final List<Integer> B) {
        int sumGas = 0;
        int sumCost = 0;
        int start = 0;
        int tank = 0;
        for (int i = 0; i < A.size(); i++) {
            sumGas += A.get(i);
            sumCost += B.get(i);
            tank += A.get(i) - B.get(i);
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        if (sumGas < sumCost) {
            return -1;
        } else {
            return start;
        }

    }

    public int canCompleteCircuit(final List<Integer> A, final List<Integer> B) {
        int start = 0;
        int end = 1;

        int ans = A.get(start) - B.get(start);

        if (A.size() == 1 && ans == 0)
            return 0;

        while (end != start || ans < 0) {
            //if visitn station makes your ans negative remove all node from starting
            while (ans < 0 && start != end) {
                ans -= A.get(start) - B.get(start);
                start = (start + 1) % A.size();
                if (start == 0)
                    return -1;
            }

            ans += A.get(end) - B.get(end);
            //if end and start same it means all nodes are traverser and end wil be end=(end+1)%n
            end = (end + 1) % A.size();
        }
        return start;
    }

    /*N integers containing only 1, 2 & 3
Problem Description

Given an integer A. Find and Return first positive A integers in ascending order containing only digits 1, 2 and 3.

NOTE: All the A integers will fit in 32 bit integer.*/

    public ArrayList<Integer> Nint123(int A) {

        Queue<Integer> q = new LinkedList<Integer>();


        if (A == 1) {
            q.add(1);
            return new ArrayList(q);
        }


        if (A == 2) {
            q.add(1);
            q.add(2);
            return new ArrayList(q);
        }

        if (A == 3) {
            q.add(1);
            q.add(2);
            q.add(3);
            return new ArrayList(q);
        }


        q.add(1);
        q.add(2);
        q.add(3);
        ArrayList<Integer> res = new ArrayList<>(q);
        int cur = 3;
        String ans = new String();
        while (q.size() < A) {
            StringBuilder sb = new StringBuilder(String.valueOf(q.peek()));
            q.remove();


            sb.append("1");
            q.add(Integer.parseInt(sb.toString()));
            res.add(Integer.parseInt(sb.toString()));
            cur++;
            if (cur == A) {
                ans = sb.toString();
                return res;
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append("2");
            q.add(Integer.parseInt(sb.toString()));
            res.add(Integer.parseInt(sb.toString()));

            cur++;
            if (cur == A) {
                ans = sb.toString();
                return res;
            }


            sb.deleteCharAt(sb.length() - 1);
            sb.append("3");
            q.add(Integer.parseInt(sb.toString()));
            res.add(Integer.parseInt(sb.toString()));

            cur++;
            if (cur == A) {
                ans = sb.toString();
                return res;
            }
        }

        StringBuilder sb = new StringBuilder(ans);
        return res;
    }

    /*Reversing Elements Of Queue
Problem Description

Given an array of integers A and an integer B. We need to reverse the order of the first B elements of the array, leaving the other elements in the same relative order.

NOTE: You are required to first insert elements into an auxiliary queue then perform Reversal of first B elements.*/

    public ArrayList<Integer> ReversingElements(ArrayList<Integer> A, int B) {
        ArrayList<Integer> res = new ArrayList<>();

        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < A.size(); i++) {
            dq.addLast(A.get(i));
        }

        for (int i = 0; i < B; i++) {
            dq.addLast(dq.removeFirst());
        }

        for (int i = 0; i < A.size(); i++) {
            if (i < B) {
                res.add(dq.removeLast());
            } else
                res.add(res.size(), dq.removeFirst());

        }
        return res;

    }


    /*First non-repeating character
Problem Description

Given a string A denoting a stream of lowercase alphabets.

You have to make new string B. B is formed such that we have to find first non-repeating character each time a character is inserted to the stream and append it at the end to B. if no non-repeating character is found then append '#' at the end of B.*/

    public String RepeatChar(String A) {

        HashMap<Character, Integer> hm = new HashMap<>();
        Queue<Character> q = new LinkedList<>();
        StringBuilder ans = new StringBuilder("");
        for (int i = 0; i < A.length(); i++) {
            boolean ifFirst = true;

            if (hm.containsKey(A.charAt(i))) {
                ifFirst = false;
                hm.computeIfPresent(A.charAt(i), (key, val) -> val + 1);
            } else
                hm.computeIfAbsent(A.charAt(i), k -> 1);

            q.add(A.charAt(i));
            while (!q.isEmpty()) {
                if (hm.get(q.peek()) > 1) {
                    q.remove();
                } else {
                    ans.append(q.peek());
                    break;

                }
            }

            if (q.isEmpty())
                ans.append("#");


        }
        return ans.toString();
    }

    /*Perfect Numbers
Problem Description

Given an integer A, you have to find the Ath Perfect Number.

A Perfect Number has the following properties:

It comprises only 1 and 2.

The number of digits in a Perfect number is even.

It is a palindrome number.

For example 11, 22, 112211 are Perfect numbers, where 123, 121, 782, 1 are not.*/
//1,2, 11,22,1111,1221,
// 11,22,1111,1221,


    public String PerfectNumber(int A) {
        Queue<String> q = new LinkedList<>();
        q.add("11");
        q.add("22");
        int cnt = 0;


        if (A == 1)
            return "11";
        if (A == 2)
            return "22";
        A = A - 2;
        while (A > 0) {
            String temp = q.remove();

            String first = temp.substring(0, temp.length() / 2);

            String second = temp.substring(temp.length() / 2);
            q.add(first + "11" + second);
            A--;
            if (A == 0)
                return first + "11" + second;

            q.add(first + "22" + second);
            A--;
            if (A == 0)
                return first + "22" + second;


        }
        return "";
    }


}
