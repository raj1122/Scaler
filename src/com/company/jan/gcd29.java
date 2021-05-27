package com.company.jan;

import java.util.*;

public class gcd29 {


    /*Pubg
Problem Description

There are N players each with strength A[i]. when player i attack player j, player j strength reduces to max(0, A[j]-A[i]). When a player's strength reaches zero, it loses the game and the game continues in the same manner among other players until only 1 survivor remains.

Can you tell the minimum health last surviving person can have?*/

    public int pubg(ArrayList<Integer> A) {
        int cnt = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < A.size(); i++) {
            if (max < A.get(i))
                max = A.get(i);
        }

        return cnt;
    }

    /*Solve sub-sequences and return OR
Problem Description

A subsequence is a sequence that can be derived from another sequence by deleting some elements without changing the order of the remaining elements. For example, the sequence {2, 3, 5} is a subsequence of {1, 2, 3, 4, 5} obtained after removal of elements {1, 4}.

Given is an array of integers A of size N. An array of size N can have (2^N - 1) number of non empty subsequences.*/
    public int subSequencesOR(ArrayList<Integer> A) {

        int gcd1 = A.get(0);

        for (int i = 1; i < A.size(); i++) {
            gcd1 = gcd(gcd1, A.get(i));
        }


        int ans = (int) gcd1;
        return (int) (ans == 1 ? 0 : 1);
    }

    /*Finding Position
Problem Description

Given an integer A which denotes the number of people standing in the queue.

A selection process follows a rule where people standing on even positions are selected. Of the selected people a queue is formed and again out of these only people on even position are selected.

This continues until we are left with one person. Find and return the position of that person in the original queue.*/
    public int findPos(int A) {
        int count = 0;
        while (A > 1) {
            count += 1;
            A = A / 2;
        }
        return (int) Math.pow(2, count);
    }

    /*Enumerating GCD
Problem Description

You are given a number A and a number B. Greatest Common Divisor (GCD) of all numbers between A and B inclusive is taken (GCD(A, A+1, A+2 ... B)).
As this problem looks a bit easy, it is given that numbers A and B can be in the range of 10100.

You have to return the value of GCD found.

Greatest common divisor of 2 numbers A and B is the largest number D that divides both A and B perfectly.*/

    public String solve(String A, String B) {
        if (A.equals(B))
            return A;
        return "1";
    }

    /*Repeated Subtraction
You are given 2 numbers P and Q.

An operation on these 2 numbers is defined as follows: Take the smaller number of the 2 numbers and subtract it from the bigger number. Keep performing this operation till either of the following criterion is met:

Both numbers become equal.
Either of the number becomes 0.
Find the sum of the final values of P and Q.*/

    public int getFinal(int A, int B) {
        int t = 0;
        while (A > 0 && B > 0) {
            if (A == B)
                return (A + B);
            t = Math.max(A, B) - Math.min(A, B);
            if (A > B)
                A = t;
            else
                B = t;
        }
        return (A + B);
    }

    /*Count of divisors
Problem Description

Given an array of integers A, find and return the count of divisors of each element of the array.

NOTE: Order of the resultant array should be same as the input array.*/

    public ArrayList<Integer> cntDivisor(ArrayList<Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            if (max < A.get(i))
                max = A.get(i);
        }


        int fact[] = new int[max + 1];
        Arrays.fill(fact, 1);
        for (int i = 2; i <= max; i++) {
            for (int j = i; j <= max; j = j + i) {

                fact[j]++;
            }

        }

        for (int i = 0; i < A.size(); i++) {
            res.add(fact[A.get(i)]);
        }

        return res;
    }

    /*Divisor game
Problem Description

Scooby has 3 three integers A, B and C.

Scooby calls a positive integer special if it is divisible by B and it is divisible by C. You need to tell number of special integers less than or equal to A.*/

    public int divGame(int A, int B, int C) {
        long res = B * C;
        long g = gcd(B, C);
        res = res / g;


        return (int) (A / res);
    }

    /*Delete one
Problem Description

Given an integer array A of size N. You have to delete one element such that the GCD(Greatest common divisor) of the remaining array is maximum.

Find the maximum value of GCD.*/

    public int delOnegcd(ArrayList<Integer> A) {

        int size = A.size();
        int ans;
        int prefixGcd[] = new int[size + 2];
        int suffixGcd[] = new int[size + 2];

        prefixGcd[1] = A.get(0);
        for (int i = 2; i <= size; i += 1) {
            prefixGcd[i] = gcd(prefixGcd[i - 1], A.get(i - 1));
        }
        suffixGcd[size] = A.get(size - 1);
        for (int i = size - 1; i >= 1; i -= 1) {
            suffixGcd[i] = gcd(suffixGcd[i + 1], A.get(i - 1));
        }

        //remove first eleemnt or last element
        ans = Math.max(suffixGcd[2], prefixGcd[size - 1]);
        for (int i = 2; i < size; i += 1) {
            //excluding curr element and takding gcd of pref -1 and suff +1
            ans = Math.max(ans, gcd(prefixGcd[i - 1], suffixGcd[i + 1]));
        }
        return ans;


    }
    /*Delete Elements
Problem Description

Given an integer array A of size N.
Find the minimum number of elements that need to be removed such that the GCD of the resulting array becomes 1.

If not possible then return -1.*/

    public int solve(ArrayList<Integer> A) {
        int size = A.size();
        int g = 0;
        if (size >= 2)
            g = gcd(A.get(0), A.get(1));

        for (int i = 2; i < size; i++) {
            g = gcd(g, A.get(i));
        }

        if (g == 1)
            return 1;
        else
            return 0;
    }

    /*Greatest Common Divisor
Problem Description

Given 2 non negative integers A and B, find gcd(A, B)

GCD of 2 integers A and B is defined as the greatest integer g such that g is a divisor of both A and B. Both A and B fit in a 32 bit signed integer.

Note: DO NOT USE LIBRARY FUNCTIONS.*/

    public int gcd(int A, int B) {

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
}
