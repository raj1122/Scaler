package com.company.Feb;


import java.util.*;

public class stringAlgo {

    public void executeFn() {
//        makeStrPalindrome("AACECAAAA");
        PeriodStr("AAACAAAA");
    }


    /*Smallest Prefix String
Problem Description

Given 2 strings A and B of size N and M respectively consisting of lowercase alphabets, find the lexicographically smallest string that can be formed by concatenating non empty prefixes of A and B (in that order).
Note: The answer string has to start with a non empty prefix of string A followed by a non empty prefix of string B.
    */

    static void sort(String a[], int n)
    {

        //sort the array
        for(int i = 0;i < n;i++)
        {
            for(int j = i + 1;j < n;j++)
            {

                // comparing which of the
                // two concatenation causes
                // lexiographically smaller
                // string
                if((a[i] + a[j]).compareTo(a[j] + a[i]) > 0)
                {
                    String s = a[i];
                    a[i] = a[j];
                    a[j] = s;
                }
            }
        }
    }


    public String smallestPrefix(String A, String B) {

        return "";
    }

    /*Cyclic Permutations
Problem Description

Given two binary strings A and B, count how many cyclic permutations of B when taken XOR with A give 0.

NOTE: If there is a string, S0, S1, ... Sn-1 , then it's cyclic permutation is of the form Sk, Sk+1, ... Sn-1, S0, S1, ... Sk-1 where k can be any integer from 0 to N-1.*/

    public  void compute_z(String s, int z[])
    {
        int l = 0, r = 0;
        int n = s.length();
        for (int i = 1; i <= n - 1; i++) {
            if (i > r) {
                l = i;
                r = i;
                while (r < n && s.charAt(r - l) == s.charAt(r))
                    r++;
                z[i] = r - l;
                r--;
            }
            else {
                int k = i - l;
                if (z[k] < r - i + 1) {
                    z[i] = z[k];
                }
                else {
                    l = i;
                    while (r < n && s.charAt(r - l) == s.charAt(r))
                        r++;
                    z[i] = r - l;
                    r--;
                }
            }
        }
    }

    public int solve(String A, String B) {
        B=B+B;
        // new b now contains all the cyclic
        // permutations of old b as it's sub-strings
        B = B.substring(0, B.length() - 1);

        // concatenate pattern with text
        int ans = 0;
        String s = A + "$" + B;
        int n = s.length();

        // Fill z array used in Z algorithm
        int z[] = new int[n];
        compute_z(s, z);

        for (int i = 1; i <= n - 1; i++) {

            // pattern occurs at index i since
            // z value of i equals pattern length
            if (z[i] == A.length())
                ans++;
        }
        return ans;
    }

    /*Make String Pallindrome
Problem Description

Given a string A of size N consisting only of lowercase alphabets. The only operation allowed is to insert characters in the beginning of the string.

Find and return how many minimum characters are needed to be inserted to make the string a palindrome string.*/

    public int makeStrPalindrome(String A) {
        StringBuilder sb = new StringBuilder();
        sb.append(A);

        // Get concatenation of string, special character
        // and reverse string
        String rev = sb.reverse().toString();
        sb.reverse().append("$").append(rev);

        // Get LPS array of this concatenated string
        int lps[] = computeLPSArray(sb.toString());
        return A.length() - lps[sb.length() - 1];
    }

    private int[] computeLPSArray(String str) {

        int n = str.length();
        int lps[] = new int[n];
        int i = 1, len = 0;
        lps[0] = 0; // lps[0] is always 0

        while (i < n) {
            if (str.charAt(i) == str.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    /*Closest Palindrome
Problem Description

Groot has a profound love for palindrome which is why he keeps fooling around with strings.
A palindrome string is one that reads the same backward as well as forward.

Given a string A of size N consisting of lowercase alphabets, he wants to know if it is possible to make the given string a palindrome by changing exactly one of its character.*/

    public String ClosestPalindrome(String A) {

        int l = 0;
        int r = A.length() - 1;
        int k = 0;
        while (l < r) {
            if (A.charAt(l) != A.charAt(r)) {
                k++;
                if (k > 1)
                    return "NO";
            }
            l++;
            r--;
        }

        if ((A.length() % 2) == 0) {
            if (k == 0)
                return "NO";
            return "YES";
        } else {
            return "YES";
        }
    }

    /*Count A
Problem Description

You are given a string A. Find the number of substrings that start and end with 'a'.

*/
    public int CountA(String A) {
        int cnt = 0;

        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == 'a')
                cnt++;
        }
        cnt = cnt * (cnt + 1);

        cnt /= 2;
        return cnt;


    }

    /*Period of a string
Problem Description

Given a string A of length N consisting of lowercase alphabets. Find the period of the string.

Period of the string is the minimum value of k (k >= 1), that satisfies A[i] = A[i % k] for all valid i.*/

    public int PeriodStr(String A) {

        int p = 1;
        int lps[] = computeLPSArray(A);
        for (int i = 0; i < lps.length; i++) {
            if (i + lps[i] == A.length() + 1)
                return lps[i];

        }


        return 0;
    }

    /*Boring substring
Problem Description

Given a string A of lowercase English alphabets. Rearrange the characters of the given string A such that there is no boring substring in A.

A boring substring has the following properties:

Its length is 2.
Both the characters are consecutive, for example - "ab", "cd", "dc", "zy" etc.(If the first character is C then the next character can be either (C+1) or (C-1)).
Return 1 if it possible to rearrange the letters of A such that there are no boring substring in A, else return 0.*/

    public int boringSubString(String A) {
        StringBuilder odd = new StringBuilder("");
        StringBuilder even = new StringBuilder("");
        char minf = 0;
        char maxf = 0;
        char mins = 0;
        char maxs = 0;
        boolean isFirst = true;
        boolean isSecond = true;
        for (int i = 0; i < A.length(); i++) {
            int t = A.charAt(i) - 97;
            if ((t % 2) == 0) {
                if (isFirst == true) {
                    maxf = A.charAt(i);
                    minf = A.charAt(i);
                    isFirst = false;
                }
//                even += A.charAt(i);
                even.append(A.charAt(i));
                minf = (A.charAt(i) > minf) ? minf : A.charAt(i);
                maxf = (A.charAt(i) > maxf) ? A.charAt(i) : maxf;
            } else {
                if (isSecond == true) {
                    isSecond = false;
                    mins = A.charAt(i);
                    maxs = A.charAt(i);
                }
//                odd += A.charAt(i);
                odd.append(A.charAt(i));
                mins = (A.charAt(i) > mins) ? mins : A.charAt(i);
                maxs = (A.charAt(i) > maxs) ? A.charAt(i) : maxs;
            }
        }
        even = new StringBuilder(sortString(even.toString()));
        odd = new StringBuilder(sortString(odd.toString()));

        //if either even or odd string not found then just 1 because boring string will not be found
        if (isFirst == true || isSecond == true)
            return 1;

        //checking if max of even str and min of odd string diff is  greater than 1
        if ((maxf - mins) > 1)
            return 1;
        if (Math.abs(minf - maxs) > 1)
            return 1;


        return 0;
    }

    static String sortString(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return (String.valueOf(arr));
    }

    /*Longest Common Prefix
Problem Description

Given the array of strings A, you need to find the longest string S which is the prefix of ALL the strings in the array.

Longest common prefix for a pair of strings S1 and S2 is the longest string S which is the prefix of both S1 and S2.

For Example: longest common prefix of "abcdefgh" and "abcefgh" is "abc".*/

    public String longestCommonPrefix(ArrayList<String> A) {
        String ans = "";
        String minStr = "";
        boolean isSame = true;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).length() < min) {
                min = A.get(i).length();
                minStr = A.get(i);
            }

        }

        for (int i = 0; i < minStr.length(); i++) {
            isSame = true;
            for (int j = 0; j < A.size(); j++) {
                if (A.get(j).equals(minStr))
                    continue;
                if (minStr.charAt(i) != A.get(j).charAt(i)) {
                    isSame = false;
                    break;
                }
            }
            if (isSame == false)
                break;
            ans += minStr.charAt(i);
        }
        return ans;
    }


}
