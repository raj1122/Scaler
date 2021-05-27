package com.company.Feb;

import java.util.*;

public class search2 {

    /*Special Integer
Problem Description

Given an array of integers A and an integer B, find and return the maximum value K such that there is no subarray in A of size K with sum of elements greater than B.*/

    public int SpecialInteger(ArrayList<Integer> A, int B) {

        int ans = A.size();
        int sum = 0;
        int start = 0, end;

        for (int i = 0; i < A.size(); i++) {
            sum += A.get(i);
            while (sum > B) {
                //take tot sum and dcrease sum if possible and count current length
                sum -= A.get(start);
                start++;
                ans = Math.min(ans, i - start + 1);
                if (sum == 0)
                    break;
            }

            if (sum == 0) {
                ans = -1;
                break;
            }

        }

        return ans;

    }

    /*Painter's Partition Problem
Problem Description

Given 2 integers A and B and an array of integers C of size N. Element C[i] represents length of ith board.
You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters available and each of them takes B units of time to paint 1 unit of board.

Calculate and return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board.
NOTE:
1. 2 painters cannot share a board to paint. That is to say, a board cannot be painted partially by one painter, and partially by another.
2. A painter will only paint contiguous boards. Which means a configuration where painter 1 paints board 1 and 3 but not 2 is invalid.

Return the ans % 10000003.*/

    public int getMax(ArrayList<Integer> C) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < C.size(); i++)
            if (C.get(i).intValue() > max)
                max = C.get(i).intValue();
        return max;
    }

    // return the sum of the elements in the array
    public int getSum(ArrayList<Integer> C) {
        int total = 0;
        for (int i = 0; i < C.size(); i++)
            total += C.get(i).intValue();
        return total;
    }

    int numberOfPainters(ArrayList<Integer> C, int n, int maxLen) {
        long mod = 10000003;
        long total = 0;
        int numPainters = 1;

        for (int i = 0; i < n; i++) {
            total = total + (C.get(i).intValue() % mod);

            if (total > maxLen) {

                // for next count
                total = (C.get(i).intValue() % mod);
                numPainters++;
            }
        }

        return numPainters;
    }

    public int paint(int A, int B, ArrayList<Integer> C) {
        int lo = getMax(C);
        int hi = getSum(C);
        long mod = 10000003;
        int minTime = Integer.MAX_VALUE;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            int reqPainter = numberOfPainters(C, C.size(), mid);
            if (reqPainter > A)
                lo = mid + 1;
            else {
                minTime = mid;
                hi = mid - 1;
            }


        }

//        int t= (int) (((minTime%mod)*(B%mod))%mod);
        int t = (int) (minTime % mod);
        return t;
    }









    /*Little Ponny and Mobile Phones
Problem Description

Little pony is going to buy some mobile phones for his friends. As there many models available in the market at different prices, He is confused and wants to know the maximum distinct models of a mobile phone he can buy for his friends, given that he has a total X amount of money.

You are given an array A of size N. denoting the size of prices of different models. The array is sorted based on prices in increasing order. You are given another array B of size Q denoting the queries. In i'th query, you need to tell the maximum distinct models of mobile phones he can buy with B[i] money.*/


    int binarySearch(ArrayList<Integer> A, int val) {
        int l = 1, h = A.size(), ans = 0;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (A.get(mid - 1) > val) {
                h = mid - 1;
            } else {
                l = mid + 1;
                ans = mid;
            }
        }

        return ans;
    }

    public ArrayList<Integer> ponnyMobile(ArrayList<Integer> A, ArrayList<Integer> B) {

        ArrayList<Integer> ans = new ArrayList<Integer>();
        int tot = 0;
        for (int i = 0; i < A.size(); i++) {
            if (i != 0) {
                A.set(i, A.get(i) + A.get(i - 1));
            }
        }
        for (int i = 0; i < B.size(); i++) {
            int cnt = binarySearch(A, B.get(i));
            ans.add(cnt);

        }

        return ans;
    }

    /*Ath Magical Number
Problem Description

Given three positive integers A, B and C.

Any positive integer is magical if it is divisible by either B or C.

Return the Ath magical number. Since the answer may be very large, return it modulo 109 + 7.*/

    public int solve(int A, int B, int C) {
        long low, high, target, mid;
        long lcmAB = lcm(B, C);
        low = 1;
        long mod = (long) 1e9;
        high = (long) 1e17;
        while ((low < high)) {
            mid = (low + high) / 2;
            //number divisor by B + no div by C  ----- common divisor and checking whether mid lies with target
            target = (mid / B) + (mid / C) - (mid / lcmAB);
            if (target < A)
                low = mid + 1;
            else
                high = mid;
        }
        return (int) (high % mod);

    }

    private long lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    private int gcd(int A, int B) {

        long a = Math.max(A, B);
        long b = Math.min(A, B);
        while (b > 0) {
            a = a % b;
            long t = a;
            a = b;
            b = t;

        }
        return (int) a;
    }

    /*Square Root of Integer
Problem Description

Given an integer A.

Compute and return the square root of A.

If A is not a perfect square, return floor(sqrt(A)).

DO NOT USE SQRT FUNCTION FROM STANDARD LIBRARY.

NOTE: Do not use sort function from standard library. Users are expected to solve this in O(log(A)) time.

*/
    public int sqrt(int A) {


        if (A == 0 || A == 1)
            return A;

        long i = 1, res = 1;

        while (res <= A) {
            i++;
            res = i * i;
        }
        return (int) (i - 1);
    }

    /*Given an array of integers A of size N and an integer B.

College library has N books,the ith book has A[i] number of pages.

You have to allocate books to B number of students so that maximum number of pages alloted to a student is minimum.

A book will be allocated to exactly one student.
Each student has to be allocated at least one book.
Allotment should be in contiguous order, for example: A student cannot be allocated book 1 and book 3, skipping book 2.
Calculate and return that minimum possible number.



NOTE: Return -1 if a valid assignment is not possible.
There are 2 number of students. Books can be distributed in following fashion :
        1) [12] and [34, 67, 90]
        Max number of pages is allocated to student 2 with 34 + 67 + 90 = 191 pages
        2) [12, 34] and [67, 90]
        Max number of pages is allocated to student 2 with 67 + 90 = 157 pages
        3) [12, 34, 67] and [90]
        Max number of pages is allocated to student 1 with 12 + 34 + 67 = 113 pages
        Of the 3 cases, Option 3 has the minimum pages = 113.*/
    public int books(ArrayList<Integer> A, int B) {
        long sum = 0;
        if (A.size() < B)
            return -1;
        for (int i = 0; i < A.size(); i++)
            sum += A.get(i);
        long start = 0, end = sum;
        long result = Integer.MAX_VALUE;
        while (start <= end) {
            long mid = (start + end) / 2;
            if (isPossible(A, B, mid)) {
                result = Math.min(result, mid);
                end = mid - 1;
            } else
                start = mid + 1;

        }

        return (int) result;
    }

    public boolean isPossible(ArrayList<Integer> a, int b, long mid) {
        int req = 1;
        int currSUm = 0;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > mid)
                return false;
            if (currSUm + a.get(i) > mid) {
                req++;
                currSUm = a.get(i);

                if (req > b)
                    return false;
            } else
                currSUm += a.get(i);

        }
        return true;
    }



    /*Aggressive cows
Problem Description

Farmer John has built a new long barn, with N stalls. Given an array of integers A of size N where each element of the array represents the location of the stall, and an integer B which represent the number of cows.

His cows don't like this barn layout and become aggressive towards each other once put into a stall. To prevent the cows from hurting each other, John wants to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. What is the largest minimum distance?*/

    public int solve(ArrayList<Integer> A, int B) {


        Collections.sort(A);
        int ub = A.get(A.size() - 1);
        int lb = 1;

        int ans = 0;
        while (lb <= ub) {
            int mid = (lb + ub) / 2;
            int cow = 1;
            int prev = A.get(0);
            //checking distiance with count of cows and check whether it is fisible
            for (int i = 1; i < A.size(); i++) {
                if (A.get(i) - prev >= mid) {
                    cow++;
                    prev = A.get(i);
                    if (B == cow) break;
                }
            }
            if (cow == B) {
                ans = mid;
                lb = mid + 1;
            } else ub = mid - 1;
        }
        return ans;
    }
}
