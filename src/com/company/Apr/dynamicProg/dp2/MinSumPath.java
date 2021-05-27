package com.company.Apr.dynamicProg.dp2;


import java.util.*;

/*Min Sum Path in Matrix
Problem Description

Given a M x N grid A of integers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Return the minimum sum of the path.

NOTE: You can only move either down or right at any point in time.*/
public class MinSumPath {
    public int minPathSum(int[][] A) {
        int row=A.length;
        int col=A[0].length;
        int dp[][]=new int[row][col];

        for (int r = 0; r <row ; r++) {
            for (int c = 0; c < col; c++) {
                if(r==c && r==0)
                {
                    dp[r][c]=A[r][c];
                }
                else if(r==0)
                {
                    dp[r][c]=dp[r][c-1] +A[r][c];
                }
                else if (c==0)
                {
                    dp[r][c]=dp[r-1][c] + A[r][c];
                }
                else
                {
                    dp[r][c] = Math.min(dp[r-1][c],dp[r][c-1]) + A[r][c];
                }
            }
        }

        return dp[row-1][col-1];
    }
}
