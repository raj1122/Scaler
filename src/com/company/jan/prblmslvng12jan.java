package com.company.jan;

import java.util.HashMap;
import java.util.*;

public class prblmslvng12jan {

    /*Remove Duplicates from Sorted Array II
Remove Duplicates from Sorted Array

Given a sorted array, remove the duplicates in place such that each element can appear atmost twice and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

Note that even though we want you to return the new length, make sure to change the original array as well in place

For example, Given input array A = [1,1,1,2],

Your function should return length = 3, and A is now [1,1,2].*/


    public int maxthreeArr(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int a = A.size() - 1, b = B.size() - 1, c = C.size() - 1;
        int max = 0, min = 0;
        boolean isFound = false;
        int ans = Integer.MAX_VALUE;
        while (a >= 0 && b >= 0 && c >= 0) {


            //take the max value of all 3
            int maxe = Math.max(A.get(a), Math.max(B.get(b), C.get(c)));

            //take the min value of all 3

            int mine = Math.min(A.get(a), Math.min(B.get(b), C.get(c)));

            //take the diffrenece and find the min

            ans = Math.min(ans, maxe - mine);

            //decrease the pointer according to condition

            if (A.get(a) == maxe) a--;

            else if (B.get(b) == maxe) b--;

            else if (C.get(c) == maxe) c--;


        }

        return ans;
    }

    /*N/3 Repeat Number
You're given a read only array of n integers. Find out if any integer occurs more than n/3 times in the array in linear time and constant additional space.

If so, return the integer. If not, return -1.

If there are multiple solutions, return any one.*/

    public int repeatedNumber(final List<Integer> a) {

        int size = a.size();
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < a.size(); i++) {
            if (hm.containsKey(a.get(i))) {
                hm.put(a.get(i), 1 + hm.get(a.get(i)));
            } else {
                hm.put(a.get(i), 1);
            }

            int t = hm.get(a.get(i));
            if (t > (size / 3)) {
                return a.get(i);
            }
        }

        return -1;
    }

    /*
    *
    * Collections.sort(A)
    * for i 0 to n
    * t=A.get(i)
    * if( t>= p1 && (t%p1)==0)
            print A.get(i)

    * else if( t>= p2 && (t%p2)==0)
            print A.get(i)
      else if( t>= p3 && (t%p3)==0)
            print A.get(i)

    *
    * */
    /*Minimum Absolute Difference
Problem Description

Given an array of integers A, find and return the minimum value of | A [ i ] - A [ j ] | where i != j and |x| denotes the absolute value of x.*/

    public int minAbs(ArrayList<Integer> A) {


        int ans = (int) (2e9);
        int n = A.size();
        Collections.sort(A);
        for (int i = 1; i < n; i++) ans = Math.min(ans, A.get(i) - A.get(i - 1));
        return ans;
    }

    /*Find Determinant
Problem Description

You are given an N X N(where N = 2 or N = 3) 2D integer matrix A. You have to find the value of its determinant (det(A) or |A|).*/

    public int solve(final List<ArrayList<Integer>> A) {

        int res = 0;

        int r = A.size();
        int c = r;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {


                if (i == 0) {


                }


            }

        }
        return res;
    }

    /*Column Sum
Problem Description

You are given a 2D integer matrix A, return a 1D integer vector containing column-wise sums of original matrix.*/

    public ArrayList<Integer> colSum(ArrayList<ArrayList<Integer>> A) {
        ArrayList<Integer> res = new ArrayList<>();

        int sum = 0;
        int t = 0, t1 = 0;
        if (A.size() > A.get(0).size()) {

            t1 = A.get(0).size();
            t = A.size();
        } else {
            t = A.size();
            t1 = A.get(0).size();
        }


        for (int i = 0; i < t1; i++) {
            sum = 0;

            for (int j = 0; j < t; j++) {
                sum += A.get(j).get(i);
            }
            res.add(sum);

        }
        return res;
    }

    /*Maximum Difference
Given an array of integers A and an integer B. Find and return the maximum value of | s1 - s2 |

where s1 = sum of any subset of size B, s2 = sum of elements of A - sum of elemets of s1

Note |x| denotes the absolute value of x.*/

    public int Maximumdiff(ArrayList<Integer> A, int B) {

        Collections.sort(A);
        int sum = 0;
        int s1 = 0;

        for (int i = 0; i < A.size(); i++) {
            sum += A.get(i);
        }


        if ((A.size() / 2) >= B) {
            for (int i = 0; i < B; i++)
                s1 += A.get(i);

        } else {
            for (int i = A.size() - B; i < A.size(); i++)
                s1 += A.get(i);
        }


        int s2 = sum - s1;
        return Math.abs(s1 - s2);
    }

    /*Game of Bottles
Problem Description

Given an array of integers A of size N which denotes N cylindrical empty bottles. The radius of the ith bottle is A.get(i).
You can put the ith bottle into the jth bottle if the following conditions are met:

ith bottle is not put into another bottle.
jth bottle dosen't contain any other bottle.
The radius of bottle i is smaller than bottle j (A[i] < A[j]).
You can put bottles into each other any number of times. You want to MINIMIZE the number of visible bottles. A bottle is called visible if it is not put into any other bottle.

Find and return the minimum number of visible bottles.
1      2    3
*/

    public int bottle(ArrayList<Integer> A) {
        HashMap<Integer, Integer> mp = new HashMap<Integer,
                Integer>();
        int ans = 0;
        for (int i = 0; i < A.size(); i++) {
            if (mp.containsKey(A.get(i))) {
                mp.put(A.get(i), mp.get(A.get(i)) + 1);
            } else {
                mp.put(A.get(i), 1);
            }
            ans = Math.max(ans, mp.get(A.get(i)));
        }

        return ans;
    }

    /*Sliding Window Maximum
Problem Description

Given an array of integers A. There is a sliding window of size B which is moving from the very left of the array to the very right. You can only see the B numbers in the window. Each time the sliding window moves rightwards by one position. You have to find the maximum for each window.

Return an array C, where C[i] is the maximum value in the array from A[i] to A[i+B-1].

Refer to the given example for clarity.

NOTE: If B > length of the array, return 1 element with the max of the array.*/

    public ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {

        ArrayList<Integer> res = new ArrayList<>();
        Deque<Integer> dq = new LinkedList<Integer>();
        int i = 0;
        int size = A.size();
        int max;
        while (i < B) {
            while (!dq.isEmpty() && A.get(i) >= A.get(dq.peekLast()))
                dq.removeLast();


            dq.addLast(i);
            i++;
        }


        while (i < size) {
            //System.out.print(A.get(dq.peek()) + " ");
            res.add(A.get(dq.peek()));
            while ((!dq.isEmpty()) && dq.peek() <= i - B)
                dq.removeFirst();


            while ((!dq.isEmpty()) && A.get(i) >= A.get(dq.peekLast()))
                dq.removeLast();

            dq.addLast(i);
            i++;
        }

        //System.out.print(A.get(dq.peek()));
        res.add(A.get(dq.peek()));
        return res;

    }

    /*Maximum Absolute Difference
Problem Description

You are given an array of N integers, A1, A2, .... AN.

Return the maximum value of f(i, j) for all 1 ≤ i, j ≤ N. f(i, j) is defined as |A[i] - A[j]| + |i - j|, where |x| denotes absolute value of x.*/

    public int maxArr(ArrayList<Integer> A) {

        int size = A.size();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if (max < (A.get(i) + i)) {
                max = A.get(i) + i;
            }
            if (min > (A.get(i) + i)) {
                min = A.get(i) + i;
            }

            if (max1 < (A.get(i) - i)) {
                max1 = A.get(i) - i;
            }
            if (min1 > (A.get(i) - i)) {
                min1 = A.get(i) - i;
            }

        }
        return Math.max((max - min), (max1 - min1));
    }

    /*Majority Element
Given an array of size n, find the majority element. The majority element is the element that appears more than floor(n/2) times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example :

Input : [2, 1, 2]
Return  : 2 which occurs 2 times which is greater than 3/2.

*/

    public int majorityElement(final List<Integer> A) {
        HashMap<Integer, Integer> hm = new HashMap<>();

        //Collections.sort(A);
        int cnt = 0;
        int size = A.size();
        int t1 = (int) Math.floor(size / 2);
        for (int i = 0; i < size; i++) {

            if (hm.containsKey(A.get(i))) {
                int t2 = hm.get(A.get(i)) + 1;
                if (t2 > t1) {
                    return A.get(i);
                }
                hm.put(A.get(i), t2);
            } else {
                if (1 > t1) {
                    return A.get(i);
                }

                hm.put(A.get(i), 1);
            }
        }


        return cnt;
    }
}
