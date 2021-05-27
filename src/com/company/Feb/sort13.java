package com.company.Feb;

import javafx.util.Pair;

import javax.swing.*;
import java.util.*;

class pair {
    int x;
    int y;

    pair(int x1, int y1) {
        x = x1;
        y = y1;
    }
}


public class sort13 {

    /*Minimum Swaps 2
Problem Description

Given an array of integers A of size N that is a permutation of [0, 1, 2, ..., (N-1)]. It is allowed to swap any two elements (not necessarily consecutive).

Find the minimum number of swaps required to sort the array in ascending order.*/

    public int minSwaps(ArrayList<Integer> A) {
        ArrayList<pair> al = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            pair p = new pair(A.get(i), i);
            al.add(p);
        }

        Collections.sort(al, new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return o1.x - o2.x;
            }
        });
        int cnt = 0;
        for (int i = 0; i < A.size(); i++) {
            if (al.get(i).y == i) continue;
            else {
                cnt++;

                int idx = al.get(i).y;
                Collections.swap(al, i, idx);
                i--;
            }

        }

        return (cnt);
    }

    /*B Closest Points to Origin
Problem Description

We have a list A, of points(x,y) on the plane. Find the B closest points to the origin (0, 0).

Here, the distance between two points on a plane is the Euclidean distance.

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in.)

NOTE: Euclidean distance between two points P1(x1,y1) and P2(x2,y2) is sqrt( (x1-x2)2 + (y1-y2)2 ).*/
    public ArrayList<ArrayList<Integer>> Borigin(ArrayList<ArrayList<Integer>> A, int B) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> al = new ArrayList<Integer>();
        long min = Long.MAX_VALUE;
        int idx = 0;
        long dist[] = new long[A.size()];
        for (int i = 0; i < A.size(); i++) {
            long x1 = A.get(i).get(0).intValue() * A.get(i).get(0).intValue();
            long x2 = A.get(i).get(1).intValue() * A.get(i).get(1).intValue();
            dist[i] = x1 + x2;
        }

        Arrays.sort(dist);
        for (int i = 0; i < A.size(); i++) {
            long x1 = A.get(i).get(0).intValue() * A.get(i).get(0).intValue();
            long x2 = A.get(i).get(1).intValue() * A.get(i).get(1).intValue();
            long sum = x1 + x2;
            if (sum <= dist[B - 1]) {
                al.add(A.get(i).get(0));
                al.add(A.get(i).get(1));
                res.add(new ArrayList<>(al));
                al.clear();
            }
        }


        return res;
    }
    /*MAXIMUM AND MINIMUM MAGIC
Problem Description

Given an array of integers A of size N where N is even.

Divide the array into two subsets such that

1.Length of both subset is equal.
2.Each element of A occurs in exactly one of these subset.
Magic number = sum of absolute difference of corresponding elements of subset.

Note: You can reorder the position of elements within the subset to find the value of magic number.

For Ex:-
subset 1 = {1, 5, 1},
subset 2 = {1, 7, 11}
Magic number = abs(1 - 1) + abs(5 - 7) + abs(1 - 11) = 12*/

    public ArrayList<Integer> maxminMagic(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        Collections.sort(A);

        long mod = (long) Math.pow(10, 9) + 7;


        long min = 0;
        long max = 0;
        int l = 0;
        int r = A.size() - 1;
        for (int i = 0; i < A.size() - 1; i = i + 2) {
            min = min + (A.get(i + 1).intValue() - A.get(i).intValue());
            min = min % mod;
        }

        while (l < r) {
            max = max + (A.get(r).intValue() - A.get(l).intValue());
            max = max % mod;
            l++;
            r--;
        }
        ans.add((int) max);
        ans.add((int) min);
        return ans;
    }


    /*Reverse pairs
Problem Description

Given an array of integers A, we call (i, j) an important reverse pair if i < j and A[i] > 2*A[j].
Return the number of important reverse pairs in the given array A.*/

    public int ReversePairs(int[] A) {
        long count = ReversePair1(A, 0, A.length - 1);
        return (int) count;
    }

    public long ReversePair1(int[] a, int l, int r) {

        long count = 0;
//        long mod=1000000007;

        if (l < r) {
            int m = (l + r) / 2;

            count += ReversePair1(a, l, m);
//            count=(count%mod);

            count += ReversePair1(a, m + 1, r);
//            count=(count%mod);

            count += mergeSortAndCount1(a, l, m, r);
//            count=(count%mod);
        }

        return (count);
    }

    public long mergeSortAndCount1(int[] a, int l, int m, int r) {
//        long mod=1000000007;
        // Left subarray
        int[] left = Arrays.copyOfRange(a, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(a, m + 1, r + 1);

        int i = 0, j = 0, k = l;
        long swaps = 0;
        //integarte two arrays

        while (i < left.length && j < right.length) {
            if (left[i] <= 2 * right[j])
                i++;
            else {

                j++;
                //logic to count number of element if fist half greter thn second half firt element
                swaps += (m + 1) - (l + i);
            }
        }

        i = 0;
        j = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                a[k++] = left[i++];
            else {
                a[k++] = right[j++];
            }
        }
        while (i < left.length)
            a[k++] = left[i++];
        while (j < right.length)
            a[k++] = right[j++];
        return (swaps);
    }




    /*Inversion count in an array
Problem Description

Given an array of integers A. If i < j and A[i] > A[j] then the pair (i, j) is called an inversion of A. Find the total number of inversions of A modulo (109 + 7).*/


    public int InversionCount(int[] A) {

        int l = 0;
        long mod = 1000000007;
        int r = A.length - 1;
        long cnt = InversionCount1(A, 0, r);
        return (int) (cnt % mod);


    }

    public int InversionCount1(int[] a, int l, int r) {

        long count = 0;
        long mod = 1000000007;

        if (l < r) {
            int m = (l + r) / 2;

            count += InversionCount1(a, l, m);
            count = (count % mod);

            count += InversionCount1(a, m + 1, r);
            count = (count % mod);

            count += mergeSortAndCount(a, l, m, r);
            count = (count % mod);
        }

        return (int) (count % mod);
    }

    public int mergeSortAndCount(int[] a, int l, int m, int r) {
        long mod = 1000000007;
        // Left subarray
        int[] left = Arrays.copyOfRange(a, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(a, m + 1, r + 1);

        int i = 0, j = 0, k = l;
        long swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                a[k++] = left[i++];
            else {

                a[k++] = right[j++];
                //logic to count number of element if fist half greter thn second half firt element
                swaps += (m + 1) - (l + i);
                swaps = (swaps % mod);
            }
        }
        while (i < left.length)
            a[k++] = left[i++];
        while (j < right.length)
            a[k++] = right[j++];
        return (int) (swaps % mod);
    }



    /*Reverse pairs
Problem Description

Given an array of integers A, we call (i, j) an important reverse pair if i < j and A[i] > 2*A[j].
Return the number of important reverse pairs in the given array A.

*/

    public int ReversePair(ArrayList<Integer> A) {

        for (int i = 0; i < A.size(); i++) {


        }

        return 0;
    }

    /*Largest Number
Problem Description

Given a array A of non negative integers, arrange them such that they form the largest number.

Note: The result may be very large, so you need to return a string instead of an integer.*/
    public String largestNumber(final List<Integer> A) {
        ArrayList<Integer> A1 = new ArrayList<Integer>(A);
        StringBuilder ans = new StringBuilder();
        String t1, t2;
        Collections.sort(A1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String t1 = o1.toString() + o2.toString();
                String t2 = o2.toString() + o1.toString();
                //System.out.println(t1+"-"+t2+">"+t1.compareTo(t2));
                return t1.compareTo(t2) > 0 ? -1 : 1;
            }
        });


        if (A1.get(A1.size() - 1) == 0)
            return "0";

        for (Integer itr : A1) {

            ans.append(itr);
        }

        return ans.toString();
    }

    /*Array with consecutive elements
Problem Description

Given an array of positive integers A, check and return whether the array elements are consecutive or not.
NOTE: Try this with O(1) extra space.*/
    public int solve(ArrayList<Integer> A) {
        Collections.sort(A);

        for (int i = 0; i < A.size() - 1; i++) {

            int t = A.get(i + 1) - 1;
            int t1 = A.get(i);
            if (t1 != t)
                return 0;
        }
        return 1;

    }

    /*Maximum Unsorted Subarray
Problem Description

Given an array A of non-negative integers of size N. Find the minimum sub-array Al, Al+1 ,..., Ar such that if we sort(in ascending order) that sub-array, then the whole array should get sorted. If A is already sorted, output -1.

*/

    public ArrayList<Integer> subUnsort(ArrayList<Integer> A) {

        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<Integer> B = (ArrayList<Integer>) A.clone();
        Collections.sort(B);


        boolean fstFound = false;
        int fstIdx = 0;
        boolean secFound = false;
        int secIdx = 0;
        int t, t1;
        for (int i = 0; i < B.size(); i++) {
            t = A.get(i);
            t1 = B.get(i);
            if (t != t1) {

                if (fstFound != true) {
                    fstFound = true;
                    fstIdx = i;
                } else {
                    secIdx = i;
                }
            }
        }

        if (fstFound == false) {
            res.add(-1);
            return res;
        }
        res.add(fstIdx);
        res.add(secIdx);
        return res;
    }
}
