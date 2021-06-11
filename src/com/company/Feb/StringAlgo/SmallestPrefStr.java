package com.company.Feb.StringAlgo;


/*Smallest Prefix String
Problem Description

Given 2 strings A and B of size N and M respectively consisting of lowercase alphabets, find the lexicographically smallest string that can be formed by concatenating non empty prefixes of A and B (in that order).
Note: The answer string has to start with a non empty prefix of string A followed by a non empty prefix of string B.



Problem Constraints
1 <= N, M <= 100000



Input Format
First argument is a string A of size N.
Second argument is a string B of size M.



Output Format
Return a string denoting Lexicographically smallest string that can be formed by concatenating non empty prefixes of A and B (in that order).



Example Input
Input 1:

 A = "abba"
 B = "cdd"
Input 2:

 A = "acd"
 B = "bay"


Example Output
Output 1:

 "abbac"
Output 2:

 "ab"


Example Explanation
Explanation 1:

 We can concatenate prefix of A i.e "abba" and prefix of B i.e "c".
 The lexicographically smallest string will be "abbac".
Explanation 2:

 We can concatenate prefix of A i.e "a" and prefix of B i.e "b".
 The lexicographically smallest string will be "ab".*/


public class SmallestPrefStr {
    public String smallestPrefix(String A, String B) {

        int i=0;
        int j=0;
        int m=A.length();
        int n=B.length();
        String res="" +A.charAt(i);
        i++;
        while (i<m && j< n)
        {
            if(A.charAt(i)<= B.charAt(j))
            {
                res=res + A.charAt(i);
                i++;
            }
            else
            {
                break;
            }

        }
        res=res+B.charAt(j);
        return res;
    }
}
