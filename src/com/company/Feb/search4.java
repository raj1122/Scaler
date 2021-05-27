package com.company.Feb;

import java.util.*;

public class search4 {

    /*Smallest Good Base
Given an integer A, we call k >= 2 a good base of A, if all digits of A base k are 1. Now given a string representing A, you should return the smallest good base of A in string format.*/

    public String SmallestBase(String A) {
        long x = Long.parseLong(A);
        List<Long> listt = new ArrayList<>();
        listt.add(x - 1);
        long y = x - 1;
        for (int i = 2; i < 63; i++) {
            double val = Math.pow(y, 1.0 / i);
            long value = (long) val;
            for (int j = 0; j > -3 && value + j > 1; j--) {
                long d = value + j;
                if (y % d == 0) {
                    long poly = getPolynomial(d, i);

                    if (poly == x) {
                        listt.add(d);
                    }
                }
            }
        }
        long end = listt.get(listt.size() - 1);
        return end + "";
    }

    public long getPolynomial(long d, int n) {
        long out = 1;
        long k = 1;
        for (int i = 0; i < n; i++) {
            k *= d;
            out += k;
        }
        return out;
    }

    /*Maximum height of staircase
Problem Description

Given an integer A representing the number of square blocks. The height of each square block is 1. The task is to create a staircase of max height using these blocks.

The first stair would require only one block, the second stair would require two blocks and so on.

Find and return the maximum height of the staircase.*/
    public int Height(int A) {

        int sum = A * 2;
        int height = (int) Math.sqrt(sum);
        int temp = (height * (height + 1)) / 2;
        if (temp > A) {
            height--;
        }
        return height;

    }

    /*Matrix Search
Problem Description

Given a matrix of integers A of size N x M and an integer B. Write an efficient algorithm that searches for integar B in matrix A.

This matrix A has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than or equal to the last integer of the previous row.
Return 1 if B is present in A, else return 0.

NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.*/
    public int searchMatrix(int[][] A, int B) {
        int i = 0, j = A[0].length - 1;
        while (i < A.length && j >= 0) {

            if (A[i][j] == B) {
                return 1;
            }
            if (A[i][j] > B)
                j--;
            else // if mat[i][j] < x
                i++;

        }

        return 0;
    }

    /*Find a peak element
Problem Description

Given an array of integers A, find and return the peak element in it. An array element is peak if it is NOT smaller than its neighbors.

For corner elements, we need to consider only one neighbor. We ensure that answer will be unique.

NOTE: Users are expected to solve this in O(log(N)) time.*/

    public int peakElement(ArrayList<Integer> A) {
        int start = 0;
        int end = A.size() - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;

            if ((mid == 0 || A.get(mid).intValue() >= A.get(mid - 1).intValue()) &&
                    (mid == A.size() - 1 || A.get(mid).intValue() >= A.get(mid + 1).intValue())
            )
                return A.get(mid);
            if (mid > 0 && A.get(mid - 1).intValue() > A.get(mid).intValue())
                end = mid - 1;
            else
                start = mid + 1;
        }
        return -1;
    }


    /*Single Element in a Sorted Array
Problem Description

Given a sorted array of integers A where every element appears twice except for one element which appears once, find and return this single element that appears only once.

NOTE: Users are expected to solve this in O(log(N)) time.*/
    public int SingleElement(ArrayList<Integer> A) {


        int start = 0;
        int end = A.size() - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;

            if (start == end)
                return A.get(mid);


            int midElem = A.get(mid);
            int midpElem = A.get(mid + 1);
            int midsElem = A.get(mid - 1);
            if (mid % 2 == 0) {
                if (midElem == midpElem)
                    start = mid + 2;
                else
                    end = mid;
            }
            // If mid is odd
            else if (mid % 2 == 1) {
                if (midElem == midsElem)
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }

        return -1;

    }

    /*Sorted Insert Position
Problem Description

Given a sorted array A of size N and a target value B, return the index (0-based indexing) if the target is found.
If not, return the index where it would be if it were inserted in order.

NOTE: You may assume no duplicates in the array. Users are expected to solve this in O(log(N)) time.*/

    public int searchInsert(ArrayList<Integer> A, int B) {

        int start = 0;
        int end = A.size() - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;

            if ((mid % 2) == 0) {
                if (A.get(mid) == A.get(mid + 1))
                    start = mid + 2;
                else
                    end = mid;
            } else {
                if (A.get(mid) == A.get(mid - 1))
                    start = mid + 2;
                else
                    end = mid - 1;
            }
        }

        return -1;


    }

    /*Rotated Sorted Array Search
Problem Description

Given a sorted array of integers A of size N and an integer B.

array A is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2 ).

You are given a target value B to search. If found in the array, return its index, otherwise return -1.

You may assume no duplicate exists in the array.

NOTE: Users are expected to solve this in O(log(N)) time.*/

    public int search(final List<Integer> A, int B) {

        int start = 0;
        int end = A.size() - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            int midElem = A.get(mid);
            int sElem = A.get(start);
            int endElem = A.get(end);
            if (midElem == B)
                return mid;
            //if current number and mid is sorted  search in left
            if (sElem <= midElem) {
                //in sorted array it exist search on left side lese right side
                if (B >= sElem && B <= midElem)
                    end = mid - 1;
                else
                    start = mid + 1;
            } else {
                if (B >= midElem && B <= endElem)
                    //if array exist on right side of array
                    start = mid + 1;
                else
                    end = mid - 1;
            }


        }

        return -1;
    }
}

