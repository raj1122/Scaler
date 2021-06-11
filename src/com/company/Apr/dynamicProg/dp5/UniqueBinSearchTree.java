package com.company.Apr.dynamicProg.dp5;

/*Unique Binary Search Trees II
Problem Description

Given an integer A, how many structurally unique BST's (binary search trees) exist that can store values 1...A?



Problem Constraints
1 <= A <=18



Input Format
First and only argument is the integer A



Output Format
Return a single integer, the answer to the problem



Example Input
Input 1:

 1
Input 2:

 2


Example Output
Output 1:

 1
Output 2:

 2


Example Explanation
Explanation 1:

 Only single node tree is possible.
Explanation 2:

 2 trees are possible, one rooted at 1 and other rooted at 2.*/

import java.util.Arrays;

public class UniqueBinSearchTree {

    static long  dp[]=new long[1005];     /* dp[i] = number of max heaps for i distinct integers */
    static long  dp1[]=new long[1005];    /*dp1[i]=number of max heaps for i-1 distinct integers */
    static long [][]combs=new long[1005][1005];
    static long MOD = 1000000007;
    static long[] log2 = new long[1005];


    public int numTrees(int A) {
        int size = A+1;

        Arrays.fill(dp,-1);

        for(int i=0;i<=size;i++)
            for(int j=0;j<=size;j++)
                combs[i][j] = -1;


        // for each power of two find logarithm
        int currLog2 = -1;
        int currPower2 = 1;
        for (int i = 1; i <= size; i++) {
            if (currPower2 == i) {
                currLog2++;
                currPower2 *= 2;
            }
            log2[i] = currLog2;
        }


        return 0;
    }



}
