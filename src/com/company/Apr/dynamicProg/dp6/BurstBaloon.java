package com.company.Apr.dynamicProg.dp6;


/*Burst Balloons
Problem Description

You are given N balloons each with a number of coins associated with them. An array of integers A represents the coins associated with the ith balloon.
You are asked to burst all the balloons. If the you burst balloon ith you will get A[left] * A[i] * A[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
Find the maximum coins you can collect by bursting the balloons wisely.

NOTE: You may imagine A[-1] = A[N] = 1. They are not real therefore you can not burst them.



Problem Constraints
1 <= N <= 100
1 <= A[i] <= 100



Input Format
The only argument given is the integer array A.



Output Format
Return the maximum coins you can collect by bursting the balloons wisely.



Example Input
Input 1:

 A = [3, 1, 5, 8]
Input 2:

 A = [3, 1, 2]


Example Output
Output 1:

 167
Output 2:

 15*/

import java.util.ArrayList;

public class BurstBaloon {

    public int solve(int[] A) {

    int len=A.length;
        // Declaring DP array
        int[][] dp = new int[len][len];

        for (int gap = 0; gap < dp.length; gap++) {
            for (int i = 0,j=gap; j<dp.length ; i++,j++) {
                int max=Integer.MIN_VALUE;
                for (int k = i; k <=j ; k++) {
                    int left=(k==i)?0:dp[i][k-1];
                    int right=(k==j)?0:dp[k+1][j];
                    int val=A[k];
                    if(i>0)
                        val=val*A[i-1];

                    if(j<len-1)
                        val=val*A[j+1];

                    int total=val+left+right;
                    if(total>max)
                    {
                        max=total;
                    }
                }

                dp[i][j]=max;
            }
        }

        return dp[0][len-1];
    }
}
