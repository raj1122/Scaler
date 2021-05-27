package com.company.Apr.dynamicProg.dp2;


/*Edit Distance
Problem Description

Given two strings A and B, find the minimum number of steps required to convert A to B. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character


Problem Constraints
1 <= length(A), length(B) <= 450



Input Format
The first argument of input contains a string, A.
The second argument of input contains a string, B.



Output Format
Return an integer, representing the minimum number of steps required.



Example Input
Input 1:

 A = "abad"
 B = "abac"
Input 2:

 A = "Anshuman"
 B = "Antihuman


Example Output
Output 1:

 1
Output 2:

 2


Example Explanation
Exlanation 1:

 A = "abad" and B = "abac"
 After applying operation: Replace d with c. We get A = B.

Explanation 2:

 A = "Anshuman" and B = "Antihuman"
 After applying operations: Replace s with t and insert i before h. We get A = B.*/


public class EditDistance {
    public int minDistance(String A, String B) {

        int alen=A.length();
        int blen=B.length();
        int dp[][]=new int[alen+1][blen+1];
        for (int i = 0; i <=alen ; i++) {
            for (int j = 0; j <= blen ; j++) {

                if(i==0)
                    dp[i][j]=j;
                else if (j==0)
                    dp[i][j]=i;
                else
                {
                    if(A.charAt(i-1) == B.charAt(j-1))
                    {
                        dp[i][j]=dp[i-1][j-1];
                    }
                    else
                    {
                        int t=Math.min(dp[i-1][j],dp[i][j-1]);
                        t= Math.min(t,dp[i-1][j-1]);
                        dp[i][j]=1+t;
                    }
                }
            }

        }

        return dp[alen][blen];
    }
}
