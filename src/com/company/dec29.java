package com.company;

import java.util.*;

public class dec29 {
/*
* Alternate positive and negative elements
Problem Description

Given an array of integers A, arrange them in an alternate fashion such that every non-negative number is followed by negative and vice-versa, starting from a negative number, maintaining the order of appearance. The number of non-negative and negative numbers need not be equal.

If there are more non-negative numbers they appear at the end of the array. If there are more negative numbers, they too appear at the end of the array.

Note: Try solving with O(1) extra space.*/

    public ArrayList<Integer> AlternateposNeg(ArrayList<Integer> A) {

        int size = A.size();
        int opi = -1;
        for (int i = 0; i < size; i++) {

            if (opi >= 0) {
                if (((A.get(i) >= 0) && (A.get(opi) < 0)) || ((A.get(i) < 0) && (A.get(opi) >= 0))) {

                    int tmp = A.get(i);
                    for (int j = i; j > opi; j--) {
                        A.set(j, A.get(j - 1));
                    }
                    A.set(opi, tmp);

                    if (i - opi >= 2)
                        opi = opi + 2;
                    else
                        opi = -1;
                }

            }

            if (opi == -1) {
                boolean isEven = ((i % 2) == 0);
                if (
                        ((A.get(i) >= 0) && (isEven == true))
                                || ((A.get(i) < 0) && (isEven != true))
                ) {
                    opi = i;
                }
            }

        }

        return A;
    }

    public int[] AlternateposNeg(int[] A) {

        int n = A.length;

        int neg = 0, pos = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] < 0)
                neg++;
            else
                pos++;
        }
        // Sort the array
        Arrays.sort(A);

        if (neg <= pos) {
            fill1(A, neg, pos);
        }
        else {
            // reverse the array in this condition
            reverse(A, n);
            fill2(A, neg, pos);
        }

        return A;
    }

    static void fill1(int a[], int neg, int pos)
    {
        if (neg % 2 == 1) {
            for (int i = 1; i < neg; i += 2) {
                int c = a[i];
                int d = a[i + neg];
                int temp = c;
                a[i] = d;
                a[i + neg] = temp;
            }
        }
        else {
            for (int i = 1; i <= neg; i += 2) {
                int c = a[i];
                int d = a[i + neg - 1];
                int temp = c;
                a[i] = d;
                a[i + neg - 1] = temp;
            }
        }
    }
    // function which works in the condition when number of
    // negative numbers are greater than positive numbers
    static void fill2(int a[], int neg, int pos)
    {
        if (pos % 2 == 1) {
            for (int i = 1; i < pos; i += 2) {
                int c = a[i];
                int d = a[i + pos];
                int temp = c;
                a[i] = d;
                a[i + pos] = temp;
            }
        }
        else {
            for (int i = 1; i <= pos; i += 2) {
                int c = a[i];
                int d = a[i + pos - 1];
                int temp = c;
                a[i] = d;
                a[i + pos - 1] = temp;
            }
        }
    }
    static void reverse(int a[], int n)
    {
        int i, k, t;
        for (i = 0; i < n / 2; i++) {
            t = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = t;
        }
    }
/*
* Chocolate Shops
There are N shops for chocolates and there is an "offer that you can't refuse". Each of the shops has some amount of chocolates represented as an array A[] of size N where Ai represents the number of chocolates ith shop has. So according to the offer, you can select any number of contiguous shops but those shops should have a non-decreasing order of amount of chocolates. Determine the maximum number of chocolates that you can have.*/


    public int ChocolateShops(int A, ArrayList<Integer> B) {
        int curr = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < B.size(); i++) {
            int j = i;
            curr = B.get(i);
            while ((j + 1) < B.size() && B.get(j) <= B.get(j + 1)) {
                j++;
                curr += B.get(j);
            }

            if (curr > max)
                max = curr;
        }
        return max;
    }
    /*
    * Book Lover
Problem Description

You love reading books, and you also love reading books that you've already read (weird you).

In a day, you read a total of N times, consisting of some books. Each book is represented by an index in array A.

You write the number of each book you read in the order of reading them in the array A.

You are supposed to find the book that you've least recently read (the book you haven't read for as long as possible) out of the books you've read that day (Books in array A).*/

    public int Books(ArrayList<Integer> A) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < A.size(); i++) {
            hm.put(A.get(i), i);
        }

        for (Map.Entry<Integer, Integer> al : hm.entrySet()) {
            if (al.getValue() < min)
                min = al.getValue();
        }
        return A.get(min);

    }

/*
* Minimum Difference Puzzle
Problem Description

There is a shop whose assistant told you that there are A puzzles in the shop and you want to buy B of them. They might differ in difficulty and size. The first jigsaw puzzle consists of A1 pieces, the second one consists of A2 pieces and so on.

You decided that the difference between the numbers of pieces in bought puzzles must be as small as possible. Let x be the number of pieces in the largest puzzle that the you buy and y be the number of pieces in the smallest such puzzle. You want to choose such B puzzles that x-y is as minimum as possible. Find the least possible value of x-y.*/


    public int puzzle(ArrayList<Integer> A, int B) {
        Collections.sort(A);
        System.out.println(A);
        int min = Integer.MAX_VALUE;
        int cur_sum;
        for (int i = 0; (B + i - 1) < A.size(); i++) {
            cur_sum = A.get(B + i - 1) - A.get(i);
            if (cur_sum < min)
                min = cur_sum;

        }
        return min;
    }

    /*
    * You are given an array A having N integers.

You have to divide / split the array into 2 subsequence partitions such that:

Both the partitions are non-empty.
Each integer A[i] in the array belongs to exactly one of the two partitions.
Absolute difference between the maximum of first partition and the minimum of second partition is minimum possible.
If B and C represent the two partitions, then size(B) >= 1, size(C) >= 1 and |max(B) - min(C)| is minimum possible. You have to find such a spliting and tell the minimum value of |max(B) - max(C)|.*/

    public int divideSub(ArrayList<Integer> A) {
        Collections.sort(A);
        System.out.println(A);
        int size = A.size();

        int cur_sum;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size - 1; i++) {
            cur_sum = Math.abs(A.get(i + 1) - A.get(i));
            if (cur_sum < min)
                min = cur_sum;
        }
        return min;


    }


    /*Sum the Difference
Problem Description

Given an integer array A of size N.
You have to find all possible non-empty subsequence of the array of numbers and then, for each subsequence, find the difference between the largest and smallest numbers in that subsequence Then add up all the differences to get the number.

As the number may be large, output the number modulo 1e9 + 7 (1000000007).

NOTE: Subsequence can be non-contiguous.*/
    public int SumDifference(ArrayList<Integer> A) {

        Collections.sort(A);

        long totSum = 0;
        long min, max;
        double pow1;
        int size = A.size();

        long t;
        for (int i = 0; i < A.size(); i++) {

            t = A.get(i);
            pow1 = Math.pow(2, (size - i - 1));

            min = (long) (t % 1000000007 * pow1 % 1000000007) % 1000000007;

            pow1 = (Math.pow(2, (i - 0)));

            max = (long) (1l * t % 1000000007 * 1l * pow1 % 1000000007) % 1000000007;

            totSum += (max % 1000000007 - min % 1000000007 + 1000000007) % 1000000007;


        }
        return (int) (totSum % 1000000007);
    }














    /*
    * Sort the Unsorted Array
Problem Description

You are given an integer array A having N integers.

You have to find the minimum length subarray A[l..r] such that sorting this subarray makes the whole array sorted.*/


    public int sortSubarray(ArrayList<Integer> A) {
        ArrayList<Integer> B = (ArrayList<Integer>) A.clone();
        Collections.sort(B);
//        System.out.println(A);
//        System.out.println(B);


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
            return 0;
        }
        return ((secIdx - fstIdx) + 1);


    }






    /*
    * Construct Array
Problem Description

Simba has an integer array of length A. Despite having insisted alot, He is not ready to reveal the array to his friend Expert. But now, he is ready to reveal some hints about the array and challenges Expert to find the values of his hidden array. The hints revealed are as follows:

The array is sorted by values.
All the elements in the array are distinct and positive (greater than 0).
The array contains two elements B and C such that B < C.
Difference between all adjacent (consecutive) elements are equal.
Among all the arrays satisfying the above conditions, his array has the minimum possible maximum element value.
Can you help Expert to construct such an array and surprise Simba*/

    public ArrayList<Integer> consArray(final int A, final int B, final int C) {
        ArrayList<Integer> al = new ArrayList<>();
        ArrayList<Integer> al1 = null;
        int d = C - B;
        int cnt;

        boolean isFirst = false;
        boolean isSec = false;
        int min = Integer.MAX_VALUE;
        int cur_min = Integer.MAX_VALUE;
        /*fixed a*/
        for (int i = 1; i <= C; i++) {

            /*fixed d*/
            for (int j = 1; j <= d; j++) {

                int cur_sum = i;

                al.clear();
                isFirst = false;
                isSec = false;
                cur_min = C;
                for (int k = 1; k <= A; k++) {

                    if (cur_sum == B) {
                        isFirst = true;
                    }
                    if (cur_sum == C) {
                        isSec = true;
                    }
                    if (cur_sum >= C) {
                        cur_min = cur_sum;
                    }
                    al.add(cur_sum);
                    cur_sum = cur_sum + j;
                }
                if (isFirst == true && isSec == true) {
                    if (cur_min < min) {
                        min = cur_min;
                        //System.out.println(al);
                        al1 = (ArrayList<Integer>) al.clone();
                    }

                }


            }
        }
        System.out.println(al1);
        return al;
    }
    /*
    * First Missing Integer
Problem Description

Given an unsorted integer array A of size N. Find the first missing positive integer.

Note: Your algorithm should run in O(n) time and use constant space.*/

    public int firstMissingPositive(ArrayList<Integer> A) {

        for (int i = 0; i < A.size(); i++) {

            while (A.get(i) >= 1 && A.get(i) <= A.size() && A.get(i) != A.get(A.get(i) - 1)) {
                //making every element to its place
                int t = A.get(i);
                int t1 = A.get(A.get(i) - 1);
                int t2 = A.get(i) - 1;
                A.set(i, t1);
                A.set(t2, t);

            }
        }

        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) != i + 1) {
                return i + 1;
            }
        }

        // Nothing is present return last index
        return A.size() + 1;
    }

    /*
    * Even Partition
Problem Description

Mohit and Akshat are great fans of even numbers and they have A number of apples with them. They want to divide the apples in such a way that each of the two gets positive even number of apples, at the same time both should have unequal number of apples. The boys are extremely tired and want to start their meal as soon as possible, that's why you should help them and find out, if they can divide in the way they want.*/
    public int EvenPartition(int A) {
        if (A < 3)
            return 0;
        if ((A % 2) != 0)
            return 0;

        boolean isTrue = false;
        for (int i = 1; (2 * i) < A; i++) {
            int res = A - (2 * i);
            if (res != (2 * i))
                isTrue = true;

        }
        if (isTrue)
            return 1;
        else
            return 0;
    }
}
