package com.company.jan;

import java.util.*;


class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

public class timespace27JAN {

    public int bar(int x, int y) {
        if (y == 0) return 0;
        return (x + bar(x, y - 1));
    }

    public int foo(int x, int y) {
        if (y == 0) return 1;
        return bar(x, foo(x, y - 1));
    }

    /*Minimum operations of given type to make all elements of a matrix equal
Problem Description

Given a matrix of integers A of size N x M and an integer B.

In a single operation, B can be added to or subtracted from any element of the matrix.

Find and return the minimum number of operations required to make all the elements of the matrix equal and if it impossible return -1 instead.

NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.*/
    public int sameMatric(ArrayList<ArrayList<Integer>> A, int B) {
        int sum = 0;

        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(i).size(); j++) {
                int t = A.get(i).get(j);
                al.add(t);
            }
        }

        Collections.sort(al);
        int size = al.size();
        int ans = 0;
        if ((size % 2) == 0) {
            int t = al.get(size / 2);
            int t1 = al.get((size / 2) - 1);
            ans = Math.min(t, t1);
        } else {
            ans = al.get(size / 2);
        }

        for (int i = 0; i < size; i++) {
            int t = al.get(i);
            if (t == ans)
                continue;

            int exp = (Math.max(t, ans) - Math.min(t, ans));

            if ((exp % B) != 0)
                return -1;
            sum = sum + (exp / B);

        }

        return sum;
    }

    /*Merge Intervals
Problem Description

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.*/

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {

        intervals.add(newInterval);

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        Stack<Interval> st = new Stack<>();
        st.push(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            Interval itr = st.peek();
            if (itr.end < intervals.get(i).start) {
                st.push(intervals.get(i));
            } else if (itr.end < intervals.get(i).end) {
                itr.end = intervals.get(i).end;
                st.pop();
                st.push(itr);
            }

        }

        ArrayList<Interval> res = new ArrayList<>(st);

        return res;
    }

    /*Max Chunks To Make Sorted II
Problem Description

Given an array of integers (not necessarily distinct) A, if we split the array into some number of "chunks" (partitions), and individually sort each chunk. After concatenating them, the result equals the sorted array.

What is the most number of chunks we could have made?*/
    public int maxchunk(ArrayList<Integer> A) {

        int mol[] = new int[A.size()];
        int mor[] = new int[A.size()];
        int size = A.size();
        int res = 1;
        mol[0] = A.get(0);
        for (int i = 1; i < size; i++)
            mol[i] = Math.max(mol[i - 1], A.get(i));
        mor[size - 1] = A.get(size - 1);
        for (int i = size - 2; i >= 0; i--)
            mor[i] = Math.min(mor[i + 1], A.get(i));

        for (int i = 0; i < size - 1; i++) {
            if (mol[i] <= mor[i + 1])
                res++;
        }

        return res;
    }

    /*Wave Array
Problem Description

Given an array of integers A, sort the array into a wave like array and return it, In other words, arrange the elements into a sequence such that a1 >= a2 <= a3 >= a4 <= a5.....

NOTE : If there are multiple answers possible, return the one that's lexicographically smallest.*/

    public ArrayList<Integer> wave(ArrayList<Integer> A) {
        Collections.sort(A);
        int n = A.size();

        for (int i = 2; i <= n; i += 2) {
            int t = A.get(i - 2);
            A.set((i - 2), A.get(i - 1));
            A.set((i - 1), t);
        }

        return A;
    }

    /*Max Sum Contiguous Subarray
Problem Description

Find the contiguous subarray within an array, A of length N which has the largest sum.*/

    public int maxSubArray(final List<Integer> A) {

        int size = A.size();
        int msf = Integer.MIN_VALUE, meh = 0;

        for (int i = 0; i < size; i++) {
            meh = meh + A.get(i);
            if (meh < A.get(i))
                meh = A.get(i);

            if (msf < meh)
                msf = meh;

        }
        return msf;
    }

    /*Rain Water Trapped
Problem Description

Given a vector A of non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.*/

    public int trap(final List<Integer> A) {
        int res = 0;

        int lmax = 0, rmax = 0;
        int l = 0, h = A.size() - 1;

        while (l <= h) {
            if (A.get(l) < A.get(h)) {
                if (A.get(l) > lmax)

                    lmax = A.get(l);
                else

                    res += lmax - A.get(l);
                l++;
            } else {
                if (A.get(h) > rmax)

                    rmax = A.get(h);

                else
                    res += rmax - A.get(h);
                h--;
            }
        }

        return res;


    }
}
