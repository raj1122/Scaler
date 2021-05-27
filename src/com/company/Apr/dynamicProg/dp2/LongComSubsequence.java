package com.company.Apr.dynamicProg.dp2;


import java.util.*;
/*Longest Common Subsequence
Problem Description

Given two strings A and B. Find the longest common subsequence ( A sequence which does not need to be contiguous), which is common in both the strings.

You need to return the length of such longest common subsequence.



Problem Constraints
1 <= Length of A, B <= 1005



Input Format
First argument is a string A.
Second argument is a string B.



Output Format
Return an integer denoting the length of the longest common subsequence.



Example Input
Input 1:

 A = "abbcdgf"
 B = "bbadcgf"
Input 2:

 A = "aaaaaa"
 B = "ababab"


Example Output
Output 1:

 5
Output 2:

 3


Example Explanation
Explanation 1:

 The longest common subsequence is "bbcgf", which has a length of 5.
Explanation 2:

 The longest common subsequence is "aaa", which has a length of 3.*/

public class LongComSubsequence {
    public int solve(String A, String B) {
        int alen=A.length();
        int blen=B.length();
        int lcs[][]= new int [alen+1][blen+1];
        for (int i = 0; i <=alen ; i++) {
            for (int j = 0; j <= blen ; j++) {
//                String t=A.substring(0,i);
//                String t1=B.substring(0,j);
                if(i==0 || j==0)
                {
                    lcs[i][j]=0;
                }
                else if( A.charAt(i-1)==B.charAt(j-1))
                {
                    //if both char matches get previou answer
                    lcs[i][j]=lcs[i-1][j-1]+1;

                }
                else
                {
                    lcs[i][j]= Math.max(lcs[i-1][j] , lcs[i][j-1]);
                }
            }
        }
        return lcs[alen][blen];




    }
}
