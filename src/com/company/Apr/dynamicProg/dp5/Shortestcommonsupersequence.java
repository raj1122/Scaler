package com.company.Apr.dynamicProg.dp5;
/*bookmark-empty
Shortest common supersequence
Given two strings A and B, find the shortest string that has both A and B as subsequences.
If multiple answers exist, you may return any of them.

Note: A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.

Input Format:

First line contains a single integer T denoting the number of test cases.
T lines follow each containing two space separated strings A B
Output Format:

Print T lines each containing a single string.
Note: The string should only contain lower case english alphabets.
Constraints:

1 ≤ T ≤ 10000
1 ≤ |A|,|B| ≤ 1000
sum of |A| * |B| over all test cases does not exceed 10^7*/



public class Shortestcommonsupersequence {

    static void printSuperSeq(String a, String b)
    {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m+1][n+1];

        // Fill table in bottom up manner
        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                // Below steps follow above recurrence
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0 )
                    dp[i][j] = i;
                else if (a.charAt(i-1) == b.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }

        String res = "";

        // Start from the right-most-bottom-most corner and
        // one by one store characters in res[]
        int i = m, j = n;
        while (i > 0 && j > 0)
        {
            // If current character in a[] and b are same,
            // then current character is part of LCS
            if (a.charAt(i-1) == b.charAt(j-1))
            {
                // Put current character in result
                res = a.charAt(i-1) + res;

                // reduce values of i, j and indexs
                i--;
                j--;
            }

            // If not same, then find the larger of two and
            // go in the direction of larger value
            else if (dp[i-1][j] < dp[i][j-1])
            {
                res = a.charAt(i-1) + res;
                i--;
            }
            else
            {
                res = b.charAt(j-1) + res;
                j--;
            }
        }
        while (i > 0)
        {
            res = a.charAt(i-1) + res;
            i--;
        }

        while (j > 0)
        {
            res = b.charAt(j-1) + res;
            j--;
        }
        System.out.println(res);
    }

    static int lcs(String X, String Y, int m, int n)
    {
        int[][] L = new int[m + 1][n + 1];
        int i, j;

        // Following steps build L[m + 1][n + 1]
        // in bottom up fashion. Note that
        // L[i][j] contains length of LCS
        // of X[0..i - 1]and Y[0..j - 1]
        for (i = 0; i <= m; i++) {
            for (j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;

                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                    L[i][j] = L[i - 1][j - 1] + 1;

                else
                    L[i][j] = Math.max(L[i - 1][j],L[i][j - 1]);
            }
        }

        return L[m][n];
    }

    static int shortestSuperSequence(String X, String Y)
    {
        int m = X.length();
        int n = Y.length();

        // find lcs
        int l = lcs(X, Y, m, n);

        // Result is sum of input string
        // lengths - length of lcs
        return (m + n - l);
    }
}
