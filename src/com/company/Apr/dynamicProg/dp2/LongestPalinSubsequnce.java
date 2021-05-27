package com.company.Apr.dynamicProg.dp2;


/*Longest Palindromic Subsequence
Problem Description

Given a string A. Find the longest palindromic subsequence (A subsequence which does not need to be contiguous and is a palindrome).

You need to return the length of longest palindromic subsequence*/

public class LongestPalinSubsequnce {

    public int PalindromicSubsequence(String A) {
        int alen=A.length();
        int dp[][]=new int[alen][alen];

        for (int i = 0; i < alen; i++) {
            dp[i][i]=1;
        }

        for (int len = 2; len <=alen ; len++) {
            for (int i = 0; i < alen-len+1 ; i++) {
                int j=i+len-1;
                if(A.charAt(i)==A.charAt(j)  && len==2)
                    dp[i][j]=2;
                else if(A.charAt(i)==A.charAt(j))
                {
                    dp[i][j]=dp[i+1][j-1]+2;
                }
                else
                    dp[i][j]=Math.max(dp[i][j-1],dp[i+1][j]);
            }
        }
        return dp[0][alen-1];
    }
}
