package com.company.Apr;

import java.util.*;

public class dp5 {
    public void executefn() {
        Integer[] arr1 = {10, 20, 30, 40};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);

        String s1[] = {"trainer", "my", "interview","traineri"};
        String s2[] = {"aaa"};

        ArrayList<String> ls1 = new ArrayList<>();
        ArrayList<String> ls2 = new ArrayList<>();
        Collections.addAll(ls1, s1);
        Collections.addAll(ls2, s2);


        int [][]dblarr=new int[][]{
                {5, 4},
                {6, 4},
                {6, 7},
                {2, 3}
        };


        int a1[]=new int []{60, 100, 120};
        int a2[]=new int []{10, 20, 30};


        String t1="myinterviewtraineri";

//        knapsack2(a1,a2,50);
//        wordBreak(t1, s1);
        isInterleave("XXY", "XXZ", "XXXXZY");
    }




    /*Flip Array
Problem Description

Given an array A of positive elements, you have to flip the sign of some of its elements such that the resultant sum of the elements of array should be minimum non-negative(as close to zero as possible).

Return the minimum number of elements whose sign needs to be flipped such that the resultant sum is minimum non-negative.*/

    public int flipArr(final int[] A) {
        int n=A.length;
        int[][] dp = new int[2000][2000];

        // boolean variable used for
        // toggling between maps
        int flag = 1;

        // Calculate the sum of all
        // elements of the array
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += A[i];
// Initializing first map(dp[0])
        // with INT_MAX because for i=0,
        // there are no elements in the
        // array to flip
        for (int i = -sum; i <= sum; i++) {
            try {
                dp[0][i] = Integer.MAX_VALUE;
            }
            catch (Exception e) {
            }
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                try {
                    dp[flag][j] = Integer.MAX_VALUE;
                    if (j - A[i - 1] <= sum && j - A[i - 1] >= -sum)
                        dp[flag][j] = dp[flag ^ 1][j - A[i - 1]];
                    if (j + A[i - 1] <= sum
                            && j + A[i - 1] >= -sum
                            && dp[flag ^ 1][j + A[i - 1]]
                            != Integer.MAX_VALUE)
                        dp[flag][j] = Math.min(
                                dp[flag][j],
                                dp[flag ^ 1][j + A[i - 1]] + 1);
                } catch (Exception e) {
                }
            }
            flag = flag ^ 1;
        }

        // Required sum is minimum non-negative
        // So, we iterate from i=0 to sum and find
        // the first i where dp[flag ^ 1][i] != INT_MAX
        for (int i = 0; i <= sum; i++) {
            if (dp[flag ^ 1][i] != Integer.MAX_VALUE)
                return dp[flag ^ 1][i];
        }

        // In worst case we will flip max n-1 elements
        return n - 1;
    }

    /*Distinct Subsequences
Problem Description

Given two sequences A and B, count number of unique ways in sequence A, to form a subsequence that is identical to the sequence B.

Subsequence : A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).*/

    public int numDistinct(String A, String B) {
        int n=A.length();
        int m=B.length();
        if(m>n)
            return 0;

        int dp[][]=new int[m+1][n+1];

        for (int i = 1; i <= m; i++)
            dp[i][0] = 0;

        for (int j = 0; j <= n; j++)
            dp[0][j] = 1;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                if (A.charAt(j - 1) == B.charAt(i - 1)) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
    
    /*DISTINCT SUBSEQUENCES
Given a stringof lower case alphabets A of size N

Return the count of all distinct subsequences of A%10^9+7.

*/

    public int disSub(String A) {
        long dp[]=new long [A.length()+1];
        HashMap<Character,Integer> hm=new HashMap<>();
        dp[0]=1;
        long MOD=(int)1e9+7;
        for (int i = 1; i <= A.length(); i++) {
            dp[i]= ((2%MOD)*(dp[i-1]%MOD))%MOD;

            char ch=A.charAt(i-1);

            if(hm.containsKey(ch))
            {
                int lastOcrr=hm.get(ch);
                dp[i]=((dp[i]%MOD)-(dp[lastOcrr-1]%MOD))%MOD;
                dp[i]= ((dp[i]+MOD)%MOD);

            }
            hm.put(ch,i);
        }
        return (int) dp[A.length()]-1;
    }




    /*Interleaving Strings
Problem Description

Given A, B, C find whether C is formed by the interleaving of A and B.*/

    public boolean isInterleave(String A, String B, String C) {

        int M = A.length(), N = B.length();

        // Let us create a 2D table to store
        // solutions of subproblems. C[i][j]
        // will br true if C[0..i+j-1] is an
        // interleaving of A[0..i-1] and B[0..j-1].
        boolean IL[][] = new boolean[M + 1][N + 1];
        // IL is default initialised by false

        // C can be an interleaving of A and B
        // only if the sum of lengths of A and B
        // is equal to length of C
        if ((M + N) != C.length())
            return false;

        // Process all characters of A and B

        for(int i = 0; i <= M; i++)
        {
            for(int j = 0; j <= N; j++)
            {
                // Two empty strings have an
                // empty strings as interleaving
                if (i == 0 && j == 0)
                    IL[i][j] = true;

                    // A is empty
                else if (i == 0)
                {
                    if (B.charAt(j - 1) ==
                            C.charAt(j - 1))
                        IL[i][j] = IL[i][j - 1];
                }
                // B is empty
                else if (j == 0)
                {
                    if (A.charAt(i - 1) ==
                            C.charAt(i - 1))
                        IL[i][j] = IL[i - 1][j];
                }

                // Current character of C matches
                // with current character of A,
                // but doesn't match with current
                // character if B
                else if (A.charAt(i - 1) == C.charAt(i + j - 1) &&
                        B.charAt(j - 1) != C.charAt(i + j - 1))
                    IL[i][j] = IL[i - 1][j];

                    // Current character of C matches
                    // with current character of B, but
                    // doesn't match with current
                    // character if A
                else if (A.charAt(i - 1) !=
                        C.charAt(i + j - 1) &&
                        B.charAt(j - 1) ==
                                C.charAt(i + j - 1))
                    IL[i][j] = IL[i][j - 1];

                    // Current character of C matches
                    // with that of both A and B
                else if (A.charAt(i - 1) == C.charAt(i + j - 1) &&
                        B.charAt(j - 1) == C.charAt(i + j - 1))
                    IL[i][j] = (IL[i - 1][j] || IL[i][j - 1]);
            }
        }
        return IL[M][N];
    }

    /*Word Break
Given a string A and a dictionary of words B, determine if A can be segmented into a space-separated sequence of one or more dictionary words.

Input Format:

The first argument is a string, A.
The second argument is an array of strings, B.*/

    public int wordBreak(String A, String[] B) {

        HashSet<String> hs=new HashSet<>();
        for (int i = 0; i < B.length; i++) {
            hs.add(B[i]);
        }
        int ans =wordBreakO(A,hs);
        return ans;
    }

    public int wordBreakO(String A, HashSet<String> hs) {
        boolean[] t = new boolean[A.length() + 1];
        t[0] = true; //set first to be true, why?
        //Because we need initial state
        for (int i = 0; i < A.length(); i++) {
            //should continue from match position
            if (!t[i])
                continue;

            for (String a : hs) {
                int len = a.length();
                int end = i + len;

                if (end > A.length())
                    continue;

                if (t[end]) continue;

                if (A.substring(i, end).equals(a)) {
                    t[end] = true;
                }
            }
        }
        return t[A.length()] ? 1 : 0;
    }
    /*Unbounded Knapsack
Problem Description

Given a knapsack weight A and a set of items with certain value B[i] and weight C[i], we need to calculate maximum amount that could fit in this quantity.

This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.*/


    public int unboundKnapsack(int A, int[] B, int[] C) {
        int blen=B.length;
        int dp[]=new int[A+1];
        for (int i = 0; i <= A; i++) {
            for (int j = 0; j < blen ; j++) {
                if(C[j]<=i)
                {
                    dp[i]=Math.max(dp[i] , dp[i-C[j]] +B[j]);
                }
            }
        }
        return dp[A];
    }



    /*0-1 Knapsack
Problem Description

Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.

Also given an integer C which represents knapsack capacity.

Find out the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.

NOTE:

You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).*/

    public int knapsack(int[] A, int[] B, int C) {

        int vlen=A.length;
        int dp[][]=new int[vlen+1][C+1];

        for (int i = 0; i <= C; i++) {
            dp[0][i]=0;
        }

        for (int i = 0; i <=vlen ; i++) {
            dp[i][0]=0;
        }

        for (int i = 1; i <=vlen ; i++) {
            for (int j = 1; j <=C ; j++) {
                if(B[i-1]<=j) {
                    dp[i][j] = Math.max(A[i-1] + dp[i - 1][j - B[i-1]], dp[i - 1][j]);
                }
                else
                {
                    dp[i][j]=dp[i-1][j];
                }


            }

        }
        return dp[vlen][C];
    }


    public int knapsack2(int[] A, int[] B, int C) {

        // array to store final result
        //dp[i] stores the profit with KnapSack capacity "i"
        int dp[]=new int[C+1];


        int alen=A.length;
        // iterate through all items
        for(int i=0; i < alen; i++)
            //traverse dp array from right to left
            for(int j=C; j>=B[i]; j--)
                dp[j] = Math.max(dp[j] , A[i] + dp[j-B[i]]);
    /*above line finds out maximum of  dp[j](excluding ith element value)
      and val[i] + dp[j-wt[i]] (including ith element value and the
      profit with "KnapSack capacity - ith element weight") */
        return dp[C];
    }
}
