package com.company.jan;

import java.util.*;

public class problemSolving310JAN {

    /*Pubg
Problem Description

There are N players each with strength A[i]. when player i attack player j, player j strength reduces to max(0, A[j]-A[i]). When a player's strength reaches zero, it loses the game and the game continues in the same manner among other players until only 1 survivor remains.

Can you tell the minimum health last surviving person can have?*/
    public int pubg(ArrayList<Integer> A) {
        int min = Integer.MAX_VALUE;
        int mIdx = -1;
        for (int i = 0; i < A.size(); i++) {
            if (min > A.get(i)) {
                min = A.get(i);
                mIdx = i;
            }

        }
        if (min == 1)
            return 1;
        while (A.size() > 1) {
            for (int i = 0; i < A.size(); i++) {
                if (A.get(i).intValue() == min && mIdx == i)
                    continue;

                int t = A.get(i) % min;
                if (t < min && t != 0) {
                    min = t;
                    mIdx = i;

                }
                if (t == 1)
                    return 1;
                if (t == 0) {
                    if (i < mIdx)
                        mIdx--;
                    A.remove(i);
                    break;
                }
                A.set(i, t);
            }
        }
        return A.get(0);
    }


    /*Find if two rectangles overlap
Problem Description

Given eight integers A, B, C, D, E, F, G and H which represent two rectangles in a 2D plane.
For the first rectangle it's bottom left corner is (A, B) and top right corner is (C, D) and for the second rectangle it's bottom left corner is (E, F) and top right corner is (G, H).

Find and return whether the two rectangles overlap or not.*/
    public int rectanglesOverlap(int A, int B, int C, int D, int E, int F, int G, int H) {


        boolean horizontal, vertical;

        horizontal = (A >= G || E >= C);

        vertical = (D <= F || H <= B);

        return horizontal && vertical ? 0 : 1;
    }




    /*Sorted Permutation Rank
Problem Description

Given a string A. Find the rank of the string amongst its permutations sorted lexicographically.
Assume that no characters are repeated.

Note: The answer might not fit in an integer, so return your answer % 1000003
*/
    static void prefsumwithcnt(int[] count, String str) {
        for (int i = 0; i < str.length(); ++i)
            ++count[str.charAt(i)];
        for (int i = 1; i < 256; ++i)
            count[i] += count[i - 1];
    }
    static void updatecountwithchar(int[] count, char ch) {
        int i;
        for (i = ch; i < 256; ++i)
            --count[i];
    }
    public int findRank(String A) {

        long mod = 1000003;
        int len = A.length();
        long rank = 1;

        int count[] = new int[256];
        //init array with prefix sum so that we can easily find char smaller than curr chr
        prefsumwithcnt(count, A);

        for (int i = 0; i < len; ++i) {
            //mul = (long) (mul/(len - i));
            // count number of chars smaller than str[i]
            // fron str[i+1] to str[len-1]
            //bca
            //1,2,3

            long t = (count[A.charAt(i) - 1] % mod);
            long t1 = (fact(len - i - 1) % mod);
            rank = ((rank % mod) + ((t * t1) % mod)) % mod;

            // Reduce count of characters greater than str[i]
            updatecountwithchar(count, A.charAt(i));
        }

        return (int) rank;
    }

    static long fact(int n) {
        if (n == 0 || n == 1)
            return 1;
        return ((n % 1000003) * (fact(n - 1) % 1000003)) % 1000003;
    }




















    /*Special Subsequences "AG"
Problem Description

You have given a string A having Uppercase English letters.

You have to find that how many times subsequence "AG" is there in the given string.

NOTE: Return the answer modulo 109 + 7 as the answer can be very large.*/

    public int SubsequencesAG(String A) {
        int a[] = new int[A.length()];
        int cnt = 0;


        int currSum = 0;
        if (A.charAt(A.length() - 1) == 'G')
            a[A.length() - 1] = 1;
        for (int i = A.length() - 2; i >= 0; i--) {
            int t = 0;
            if (A.charAt(i) == 'G') {
                t = 1;

            }
            a[i] = a[i + 1] + t;
        }
        for (int i = 0; i < A.length(); i++) {

            if (A.charAt(i) == 'A') {
                if ((i + 1) < A.length()) {
                    cnt += a[i + 1];
                }
            }
        }


        return cnt;
    }

    /*Closest MinMax
Problem Description
Given an array A. Find the size of the smallest subarray such that it contains atleast one occurrence of the maximum value of the array
and atleast one occurrence of the minimum value of the array.
*/
    public int closeMinMax(ArrayList<Integer> A) {

        int res = 0;
        int l = 0, r = 0;
        boolean isMax = false;
        boolean isMin = false;
        int mi = -1, mx = -1;
        int ans = Integer.MAX_VALUE;
        int size = A.size();
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {

            if (max < A.get(i)) {
                max = A.get(i);

            }
            if (min > A.get(i)) {
                min = A.get(i);
            }
        }


        for (int i = 0; i < size; i++) {
            if (A.get(i) == max)
                mx = i;
            if (A.get(i) == min)
                mi = i;
            if (mi != -1 && mx != -1) {
                ans = Math.min(ans, Math.abs(mi - mx) + 1);
            }
        }
        return ans;
    }

    /*Max Non Negative SubArray
Given an array of integers, A of length N, find out the maximum sum sub-array of non negative numbers from A.

The sub-array should be contiguous i.e., a sub-array created by choosing the second and fourth element and skipping the third element is invalid.

Maximum sub-array is defined in terms of the sum of the elements in the sub-array.

Find and return the required subarray.*/

    public ArrayList<Integer> maxset(ArrayList<Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<Integer> newArray = new ArrayList<Integer>();
        int size = A.size();
        long sumMax = Integer.MIN_VALUE;
        long currSum = 0;
        for (Integer al : A) {
            if (al >= 0) {
                currSum += al;
                newArray.add(al);
            } else {
                currSum = 0;
                newArray = new ArrayList<>();
            }
            if ((sumMax < currSum) || ((sumMax == currSum) && (newArray.size() > res.size()))) {
                sumMax = currSum;
                res = newArray;

            }

        }

        return res;

    }

    /*Beggars Outside Temple
There are N (N > 0) beggars sitting in a row outside a temple. Each beggar initially has an empty pot. When the devotees come to the temple, they donate some amount of coins to these beggars. Each devotee gives a fixed amount of coin(according to his faith and ability) to some K beggars sitting next to each other.

Given the amount donated by each devotee to the beggars ranging from i to j index, where 1 <= i <= j <= N, find out the final amount of money in each beggar's pot at the end of the day, provided they don't fill their pots by any other means.*/

    public ArrayList<Integer> Beggars(int A, ArrayList<ArrayList<Integer>> B) {

        /*set l anmd r+1 in coin array and take prefix sum*/
        ArrayList<Integer> res = new ArrayList<>();
        Integer prefixSum[] = new Integer[A];
        Integer coins[] = new Integer[A];
        Arrays.fill(prefixSum, 0);
        Arrays.fill(coins, 0);

        int size = B.size();
        int m;
        int l, r;
        for (int i = 0; i < B.size(); i++) {
            l = B.get(i).get(0) - 1;
            r = B.get(i).get(1) - 1;
            m = B.get(i).get(2);
            coins[l] += m;

            if ((r + 1) < A) {
                coins[r + 1] -= m;
            }
        }


        prefixSum[0] = coins[0];

        for (int i = 1; i < A; ++i) {

            prefixSum[i] = prefixSum[i - 1] + coins[i];
            ;

        }

        Collections.addAll(res, prefixSum);

        return res;
    }

    /*Pick from both sides!
Problem Description

Given an integer array A of size N.

You can pick B elements from either left or right end of the array A to get maximum sum.

Find and return this maximum possible sum.

NOTE: Suppose B = 4 and array A contains 10 elements then:

You can pick first four elements or can pick last four elements or can pick 1 from front and 3 from back etc . you need to return the maximum possible sum of elements you can pick*/
    public int pickFromBothSide(ArrayList<Integer> A, int B) {

        int cnt = 0;

        int sum = 0;
        int r = A.size() - 1, l = 0, tsum;
        int itr = 0;
        for (int i = 0; i < B; i++) {
            sum += A.get(i);
            l = i;
        }

        tsum = sum;
        while (itr < B) {
            tsum = tsum - A.get(l);
            tsum = tsum + A.get(r);

            r--;
            l--;
            itr++;
            if (tsum > sum) {
                sum = tsum;
            }
        }
        return sum;
    }


    /*
    * Repeat and Missing Number Array
You are given a read only array of n integers from 1 to n.

Each integer appears exactly once except A which appears twice and B which is missing.

Return A and B.

Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Note that in your output A should precede B.*/
    public ArrayList<Integer> repeatedNumber(final List<Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();
        long n = A.size();
        long sumNum = ((n * (n + 1)) / 2);
        long t1 = (n * (n + 1));
        long t2 = ((2 * n) + 1);
        long sumprod = ((t1 * t2) / 6);
        long arrSum = 0;
        long arrProdSum = 0;
        for (int i = 0; i < n; i++) {
            sumNum -= (long) A.get(i);
            sumprod -= ((long) A.get(i)) * (A.get(i));
        }

        /*m-r=Sn
         * m+r=t
         * 2m=sn/t*/
        long t = (long) (sumprod / sumNum);
        long missing = (long) ((sumNum + t) / 2);
        long rep = t - missing;

        res.add((int) (rep % 1000000007));
        res.add((int) (missing % 1000000007));
        return res;
    }
}
