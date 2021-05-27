package com.company.jan;

import java.util.*;

public class prblmslvng16jan {

    /*Hotel Bookings Possible
Problem Description

A hotel manager has to process N advance bookings of rooms for the next season. His hotel has K rooms.

Bookings contain an arrival date and a departure date.

He wants to find out whether there are enough rooms in the hotel to satisfy the demand.

Write a program that solves this problem in time O(N log N) */

    public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {

        Collections.sort(arrive);
        Collections.sort(depart);
        int size = arrive.size();
        for (int i = 0; i < size; i++) {
            if (i + K < size && arrive.get(i + K) < depart.get(i)) {
                return false;
            }
        }
        return true;
    }

    /*Find duplicate rows in a binary matrix
Given a binary matrix A of integers 0 and 1, of size N x M.

Find and return the indices of the rows which are duplicate of rows which are already present in the matrix.

If row[i] and row[j] are same and i < j then answer will contain only index j.

Note: Rows are numbered from top to bottom and columns are numbered from left to right. There will be at least one duplicate row in the matrix.*/


    public ArrayList<Integer> duplRows(ArrayList<ArrayList<Integer>> A) {
        ArrayList<Integer> res = new ArrayList<>();

        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < A.size(); i++) {
            String temp = "";
            for (int j = 0; j < A.get(i).size(); j++) {
                temp += A.get(i).get(j);
            }

            if (hm.containsKey(temp)) {
                int t = hm.get(temp);
                if (t > i)
                    res.add(t + 1);
                else
                    res.add(i + 1);

            } else {
                hm.put(temp, i);
            }

        }

        return res;

    }

    /*Xor queries
Problem Description

You are given an array A (containing only 0 and 1) as element of N length.
Given L and R, you need to determine value of XOR of all elements from L to R (both inclusive) and number of unset bits (0's) in the given range of the array.*/

    public ArrayList<ArrayList<Integer>> xorQueires(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> prefSum = new ArrayList<>();

        prefSum.add(A.get(0));
        for (int i = 1; i < A.size(); i++) {
            int t = prefSum.get(i - 1) + A.get(i);
            prefSum.add(t);
        }

        for (int i = 0; i < B.size(); i++) {
            int l = B.get(i).get(0) - 1;
            int r = B.get(i).get(1) - 1;

            ArrayList<Integer> ans = new ArrayList<>();
            int t = 0;
            if (l == 0) {
                t = prefSum.get(r);
            } else {

                t = prefSum.get(r) - prefSum.get(l - 1);

            }

            /*if number of 1 is odd then xor will be 1*/

            if ((t % 2) == 0) {
                ans.add(0);
            } else
                ans.add(1);

            /*logic for unset bits
             * len -t willbe totalnmumberof 0
             * 1 has 1 zero so total num ofzero (len-t) + t
             * */
            int len = r - l + 1;
            int unsetBits = (len - t);
            ans.add(unsetBits);

            res.add(ans);
        }

        return res;
    }

    /*Kth Row of Pascal's Triangle
Given an index k, return the kth row of the Pascal's triangle.

Pascal's triangle : To generate A[C] in row R, sum up A'[C] and A'[C-1] from previous row R - 1.*/


    public ArrayList<Integer> getRow(int A) {

        ArrayList<Integer> ans = new ArrayList<>();
        int prev = 1;
        ans.add(prev);
        int curr = 0;
        for (int i = 1; i <= A; i++) {
            curr = (prev * (A - i + 1)) / i;
            ans.add(curr);
            prev = curr;
        }

        return ans;
    }

    /*Find Determinant
Problem Description

You are given an N X N(where N = 2 or N = 3) 2D integer matrix A. You have to find the value of its determinant (det(A) or |A|).*/

    public int solve(final List<ArrayList<Integer>> A) {
        int row = A.size();
        int col = A.get(0).size();
        if (row == 2) {
            int t = (A.get(0).get(0) * A.get(1).get(1)) - (A.get(1).get(0) * A.get(0).get(1));
            return t;
        } else {
            int a = A.get(0).get(0) * ((A.get(1).get(1) * A.get(2).get(2)) - (A.get(1).get(2) * A.get(2).get(1)));
            int b = A.get(0).get(1) * ((A.get(1).get(0) * A.get(2).get(2)) - (A.get(2).get(0) * A.get(1).get(2)));

            int c = A.get(0).get(2) * ((A.get(1).get(0) * A.get(2).get(1)) - (A.get(1).get(1) * A.get(2).get(0)));
            return ((a + c) - b);

        }

    }

    /*Sub-matrix Sum Queries
Problem Description

Given a matrix of integers A of size N x M and multiple queries Q, for each query find and return the submatrix sum.

Inputs to queries are top left (b, c) and bottom right (d, e) indexes of submatrix whose sum is to find out.

NOTE:

Rows are numbered from top to bottom and columns are numbered from left to right.
Sum may be large so return the answer mod 109 + 7.*/

    public ArrayList<Integer> submatrixSum(ArrayList<ArrayList<Integer>> A, ArrayList<Integer> B, ArrayList<Integer> C, ArrayList<Integer> D, ArrayList<Integer> E) {

        ArrayList<Integer> res = new ArrayList<>();
        int t;
        int col = A.get(0).size();
        int row = A.size();
        long[][] prefSum = new long[row][col];
        int tli, tlj, rbi, rbj;
        int idx = 0;
        int mod = 1000000000 + 7;

        for (int i = 0; i < col; i++)
            prefSum[0][i] = A.get(0).get(i);


        for (int i = 1; i < row; i++)
            for (int j = 0; j < col; j++)
                prefSum[i][j] = (A.get(i).get(j) % mod + prefSum[i - 1][j] % mod) % mod;


        for (int i = 0; i < row; i++)
            for (int j = 1; j < col; j++)
                prefSum[i][j] = (prefSum[i][j] % mod + prefSum[i][j - 1] % mod) % mod;


        long ans = 0;
        while (idx < B.size()) {
            /*ityerating over every array with idx*/
            ans = 0;
            tli = B.get(idx) - 1;
            tlj = C.get(idx) - 1;
            rbi = D.get(idx) - 1;
            rbj = E.get(idx) - 1;

            ans = prefSum[rbi][rbj];
            // ans=prefSum[rbi][rbj]-prefSum[rbi][tlj-1]-prefSum[tli-1][tlj]+prefSum[tli-1][tlj-1];
            if (tli > 0)
                ans = ans - prefSum[tli - 1][rbj];

            // Remove elements between (0, 0) and (rbi, tlj-1)
            if (tlj > 0)
                ans = ans - prefSum[rbi][tlj - 1];

            // Add aux[tli-1][tlj-1] as elements between (0, 0)
            // and (tli-1, tlj-1) are subtracted twice
            if (tli > 0 && tlj > 0)
                ans = ans + prefSum[tli - 1][tlj - 1];
            while (ans < 0)
                ans += 1000000007;

            ans = ans % 1000000007;

            res.add((int) ans);
            idx++;
        }

        return res;
    }

    /*Remove Duplicates from Sorted Array II
Remove Duplicates from Sorted Array

Given a sorted array, remove the duplicates in place such that each element can appear atmost twice and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

Note that even though we want you to return the new length, make sure to change the original array as well in place

For example, Given input array A = [1,1,1,2],

Your function should return length = 3, and A is now [1,1,2].*/

    public int removeDuplicates(ArrayList<Integer> a) {
        int size = a.size();

        int len = 2;
        int n = a.size();
        if (n <= 2) return n;
        int l = 2;
        for (int r = 2; r < size; r++) {
            int t = a.get(l - 2);
            int t1 = a.get(r);
            if (t != t1) {
                a.set(l, a.get(r));
                l++;
            }
        }

        return l;
    }


    int removeDuplicates1(ArrayList<Integer> a) {
        int len = 2;
        int n = a.size();
        if (n <= 2) return n;
        for (int i = 2; i < n; i++) {
            if (a.get(i) != a.get(len - 2) || a.get(i) != a.get(len - 1)) {
                a.set(len, a.get(i));
                len++;
            }
        }
        return len;
    }

    /*
    * Merge Two Sorted Arrays
Problem Description

Given two sorted integer arrays A and B, merge B and A as one sorted array and return it as an output.*/

    public ArrayList<Integer> mergeArray(final List<Integer> A, final List<Integer> B) {
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.size() && j < B.size()) {
            if (A.get(i) <= B.get(j)) {
                res.add(A.get(i));
                i++;
            } else {
                res.add(B.get(j));
                j++;
            }
        }

        while (i < A.size()) {
            res.add(A.get(i));
            i++;
        }
        while (j < B.size()) {
            res.add(B.get(j));
            j++;
        }
        return res;
    }

    /*Sum of all Submatrices
Problem Description

Given a 2D Matrix A of dimensions N*N, we need to return sum of all possible submatrices.*/

    public int solve(ArrayList<ArrayList<Integer>> A) {
        int sum = 0;

        int n = A.size();

        int tl = 0;
        int br = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tl = (i + 1) * (j + 1);
                br = (n - i) * (n - j);
                sum += (tl * br) * A.get(i).get(j);

            }

        }

        return sum;
    }
}
