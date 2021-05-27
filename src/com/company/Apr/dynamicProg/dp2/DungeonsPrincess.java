package com.company.Apr.dynamicProg.dp2;


/*Dungeon Princess
Problem Description

The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

Given a 2D array of integers A of size M x N.
Find and return the knight's minimum initial health so that he is able to rescue the princess.*/
public class DungeonsPrincess {

    public int calculateMinimumHP(int[][] A) {

        int rlen=A.length;
        int clen=A[0].length;
        int dp[][]=new int[rlen][clen];
        if(A[rlen-1][clen-1] <  0)
        {
            dp[rlen-1][clen-1]=1+ Math.abs(A[rlen-1][clen-1]);
        }
        else
        {
            //as number are positive so take floor value
            dp[rlen-1][clen-1]=1;
        }

        //fill row sparatelt
        for (int col = clen-2; col >=0; col--) {
            dp[rlen-1][col]= Math.max(dp[rlen-1][col+1] - A[rlen-1][col] , 1);
        }
        //fill col separately
        for (int row = rlen-2; row >=0 ; row--) {
            dp[row][clen-1]= Math.max(dp[row+1][clen-1] - A[row][clen-1] , 1);
        }

        for (int row = rlen-2; row >=0 ; row--) {
            for (int col = clen-2; col >=0 ; col--) {
                int minPathPoints= Math.min(dp[row+1][col],dp[row][col+1]);
                dp[row][col] = Math.max(minPathPoints-A[row][col],1);

            }

        }


        return dp[0][0];
    }
}
