package com.company.jan.BasicMath;

/*Sorted Permutation Rank
Problem Description

Given a string A. Find the rank of the string amongst its permutations sorted lexicographically.
Assume that no characters are repeated.

Note: The answer might not fit in an integer, so return your answer % 1000003



Problem Constraints
1 <= |A| <= 1000



Input Format
First argument is a string A.



Output Format
Return an integer denoting the rank of the given string.



Example Input
Input 1:

A = "acb"
Input 2:

A = "a"


Example Output
Output 1:

2
Output 2:

1


Example Explanation
Explanation 1:

Given A = "acd".
The order permutations with letters 'a', 'c', and 'b' :
abc
acb
bac
bca
cab
cba
So, the rank of A is 2.
Explanation 2:

Given A = "a".
Rank is clearly 1.*/

public class SortedPermuteRank {

    static void fact(int n,int fact[]) {
        fact[0]=1;
        fact[1]=1;
        for (int i = 2; i < n; i++) {
            fact[i]=fact[i-1]*i;
        }
    }

    static void prefsumwithcnt(int[] count,String str)
    {
        for (int i = 0; i < str.length(); ++i)
            ++count[str.charAt(i)];
        for (int i = 1; i < 256; ++i)
            count[i] += count[i - 1];
    }

    static void updatecountwithchar(int[] count, char ch)
    {
        int i;
        for (i = ch; i < 256; ++i)
            if(count[i]!=0)
                --count[i];
    }

    public int findRank(String A) {
        long mod=1000003;
        int len = A.length();
        int fact[]=new int[len+1];
        fact(len,fact);
        long rank = 1;
        int count[] = new int[256];
        //init array with prefix sum so that we can easily find char smaller than curr chr
        prefsumwithcnt(count, A);
        for (int i = 0; i < len; ++i) {
            // count number of chars smaller than str[i]
            // fron str[i+1] to str[len-1]
            long t=(count[A.charAt(i) - 1]%mod);
            long t1=( fact[len-i-1]%mod);
            rank =((rank%mod)+  ( (t *t1  )%mod))%mod;
            // Reduce count of characters greater than str[i]
            updatecountwithchar(count,A.charAt(i));
        }
        return (int) rank;
    }
}
