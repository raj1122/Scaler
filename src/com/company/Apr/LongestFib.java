package com.company.Apr;


import java.util.*;

/*Length of Longest Fibonacci Subsequence
Problem Description

Given a strictly increasing array A of positive integers forming a sequence.

A sequence X1, X2, X3, ..., XN is fibonacci like if


N > =3
Xi + Xi+1 = Xi+2 for all i+2 <= N
Find and return the length of the longest Fibonacci-like subsequence of A.

If one does not exist, return 0.

NOTE: A subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the order of the remaining elements.*/

public class LongestFib {
    public int solve(int[] A) {
        HashMap<Integer, Integer> hm=new HashMap<>();
        int N = A.length, res = 0;

        // Initialize dp table
        int dp[][]=new int[N][N];

        for (int i = 0; i < N; i++) {

            hm.put(A[i],i);
            for (int j = i+1; j < N; j++) {
                dp[i][j]=2;
            }
        }

        for (int i = 0; i < N; i++) {

            hm.put(A[i],i);
            for (int j = i+1; j < N; j++) {
                int k=A[j]+A[i];
                if(hm.containsKey(k))
                {
                    int idx=hm.get(k);
                    dp[j][idx]=dp[i][j]+1;
                }
            }
        }

        for (int i = 0; i < N; i++) {

            hm.put(A[i], i);
            for (int j = i + 1; j < N; j++) {
                res=Math.max(res,dp[i][j]);
            }
        }
        // Return the answer
        return res > 2 ? res : 0;
    }


}
