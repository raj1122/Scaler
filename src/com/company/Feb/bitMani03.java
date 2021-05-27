package com.company.Feb;

import java.util.*;

public class bitMani03 {

    /*Strange Equality
Problem Description

Given an integer A.
Two numbers X and Y are defined as follows:

X is the greatest number smaller than A such that XOR sum of X and A is the same as the sum of X and A.
Y is the smallest number greater than A such that XOR sum of Y and A is the same as the sum of Y and A.
Find and return the XOR of X and Y.

NOTE 1: XOR of X and Y is defined as X ^ Y where '^' is the BITWISE XOR operator.

NOTE 2: Your code will be run against a maximum of 100000 Test Cases.*/

    public int StrangeEquality(int A) {

        int c = 1;

        //Here A always be zero.....
        //B is just greater than N which give B+N=B^N.....
        //so B&N have to be zero....
        //Lets take example N=5...which is    0101 then the next number would be which has all bits oposite
        //would be 1010....
        //so it's 10....

        int totSetbits = 0;
        for (int i = 0; i < 32; i++) {
            if ((A & (1 << i)) > 0)
                totSetbits++;

        }
        c = 1 + totSetbits;
        // it can directly calculated as pow(2,c)...which give minimum no of c bits  is 16...
        // then subtract A from it.....
        //and then subtract from 1...
        //You get desired result....
        while ((A >> (c - 1)) > 0) {

            c++;//count number of bits of N

        }
        return (int) (Math.pow(2, c) - A - 1);
    }

    /*Interesting Array
Problem Description

You have an array A with N elements. We have 2 types of operation available on this array :
We can split a element B into 2 elements C and D such that B = C + D.
We can merge 2 elements P and Q as one element R such that R = P^Q i.e XOR of P and Q.
You have to determine whether it is possible to make array A containing only 1 element i.e. 0 after several splits and/or merge?

*/

    public String InterestingArray(ArrayList<Integer> A) {

        int odd = 0;
        for (int i = 0; i < A.size(); i++) {
            if ((A.get(i).intValue() % 2) != 0)
                odd++;

        }
        return ((odd % 2) == 0) ? "YES" : "NO";

    }

    /*Different Bits Sum Pairwise
Problem Description

We define f(X, Y) as number of different corresponding bits in binary representation of X and Y.
For example, f(2, 7) = 2, since binary representation of 2 and 7 are 010 and 111, respectively. The first and the third bit differ, so f(2, 7) = 2.

You are given an array of N positive integers, A1, A2 ,..., AN. Find sum of f(Ai, Aj) for all pairs (i, j) such that 1 ≤ i, j ≤ N. Return the answer modulo 109+7.*/

    public int cntBits(ArrayList<Integer> A) {

        long res = 0;

        long mod = 1000000007;
        for (int i = 0; i < 32; i++) {
            int count = 0;

            for (int j = 0; j < A.size(); j++)
                if ((A.get(j) & (1 << i)) == 0)
                    count++;
            long t = (count % mod);
            long t1 = ((A.size() - count) % mod);
            res += ((t * t1 * 2) % mod);
        }

        return (int) (res % mod);
    }

    /*Min XOR value
Problem Description

Given an integer array A of N integers, find the pair of integers in the array which have minimum XOR value. Report the minimum XOR value.*/


    public int findMinXor(ArrayList<Integer> A) {


        Collections.sort(A);
        int xor = 0;
        int minXor = Integer.MAX_VALUE;
        for (int i = 0; i < A.size() - 1; i++) {
            xor = A.get(i) ^ A.get(i + 1);
            minXor = Math.min(minXor, xor);
        }

        return minXor;
    }


    /*
Problem Description

Given an array of numbers A , in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

Note: Output array must be sorted.*/


    public ArrayList<Integer> single3(ArrayList<Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();

        int xor = 0;
        int i;
        int xorA = 0;
        int xorB = 0;
        for (i = 0; i < A.size(); i++) {
            xor = xor ^ A.get(i);
        }

        for (i = 0; i < 32; i++) {
            if ((xor & (1 << i)) > 0) {
                break;
            }
        }

        if (i == 32)
            return res;

        for (int j = 0; j < A.size(); j++) {
            int t = A.get(j);
            if ((t & (1 << i)) > 0) {
                xorA = xorA ^ t;
            }

        }

        xorB = xor ^ xorA;

        res.add(Math.min(xorA, xorB));
        res.add(Math.max(xorA, xorB));


        return res;
    }

    /*Divide Integers
Problem Description

Divide two integers without using multiplication, division and mod operator.

Return the floor of the result of the division.

Also, consider if there can be overflow cases i.e output is greater than INT_MAX, return INT_MAX.

NOTE: INT_MAX = 2^31 - 1*/

    public int divide(int A, int B) {
        int q = 0;
        int s = 0;


        long A1 = A;
        long B1 = B;

        if ((A1 > 0 && B1 < 0) || (A1 < 0 && B1 > 0))
            s = -1;
        else
            s = 1;


        if ((B1 == 1 && A1 >= Integer.MAX_VALUE) || (B1 == -1 && A1 == Integer.MIN_VALUE))

            return Integer.MAX_VALUE;

        if (A1 == Integer.MIN_VALUE && B1 == 1) return Integer.MIN_VALUE;

        A1 = Math.abs(A1);
        B1 = Math.abs(B1);

        if (A1 == 0 || B1 == 0)
            return 0;
        if (A1 == 1 || B1 == 1)
            return (int) (s * ((A1 == 1) ? B1 : A1));
        while (A1 >= B1) {
            A1 -= B1;
            ++q;
        }

        return s * q;
    }

}
