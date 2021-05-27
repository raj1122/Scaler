package com.company;

import java.util.ArrayList;

public class basicMath29 {

    /*Sorted Permutation Rank
Problem Description

Given a string A. Find the rank of the string amongst its permutations sorted lexicographically.
Assume that no characters are repeated.

Note: The answer might not fit in an integer, so return your answer % 1000003*/

    public int findRank(String A) {
        int ans = 0;

        permute(A, 0, A.length() - 1);
        return ans;
    }

    private void permute(String str, int l, int r) {
        if (l == r)
            System.out.println(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    /*Trailing Zeros in Factorial
Problem Description

Given an integer A, return the number of trailing zeroes in A! i.e. factorial of A.

Note: Your solution should run in logarithmic time complexity.*/
    public int trailingZeroes(int A) {
        int ans = 0;
        while (A > 0) {
            ans = ans + (A / 5);
            A = A / 5;
        }
        return ans;
    }

    /*
    * Reverse integer
Problem Description

You are given an integer N and the task is to reverse the digits of the given integer. Return 0 if the result overflows and does not fit in a 32 bit signed integer

Look at the example for clarification.



Problem Constraints
N belongs to the Integer limits.*/

    public int reverse(int A) {

        long max = (long) Math.pow(2, 31) - 1;
        long min = (long) Math.pow(2, 31) * -1;
        long sum = 0;
        int temp = A;
        boolean isNeg = false;
        if (A < 0)
            return 0;

        //1146467285
        while (A != 0) {
            int d = A % 10;
            sum = (sum * 10) + d;
//            if(sum>max ||  sum<min)
//            {
//                return 0;
//            }
            A = A / 10;
        }
        if (temp == sum)
            return 1;
        else
            return 0;
    }

    /*Excel Column Title
Problem Description

Given a positive integer A, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB */

    public String convertToTitle(int A) {
        StringBuilder res = new StringBuilder("");

        int t;
        char c;
        while (A > 0) {
            t = A % 26;
            if (t == 0) {

                res.insert(0, 'Z');
                A = (A / 26) - 1;
            } else {

                c = (char) ((t - 1) + 'A');
                res.insert(0, c);

                A = A / 26;
            }


        }


        return res.toString();
    }

    /*Reverse integer
Problem Description

You are given an integer N and the task is to reverse the digits of the given integer. Return 0 if the result overflows and does not fit in a 32 bit signed integer

Look at the example for clarification.



Problem Constraints
N belongs to the Integer limits.



Input Format
Input an Integer.



Output Format
Return a single integer denoting the reverse of the given integer.



Example Input
Input 1:

 x = 123

Input 2:

 x = -123*/


    /*
    * Excel Column Number
Problem Description

Given a column title as appears in an Excel sheet, return its corresponding column number.*/

    public int titleToNumber(String A) {


        int p = 0;
        int sum = 0;
        for (int i = A.length() - 1; i >= 0; i--) {
            int t = (int) A.charAt(i) - 64;
            int d = (int) Math.pow(26, p);
            sum = (int) (sum + (d * t));
            p++;
        }
        return sum;
    }
    /*
    * Rearrange Array
Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.

Example:

Input : [1, 0]
Return : [0, 1]*/

    public void arrange(ArrayList<Integer> a) {
        int n = a.size();

        //4, 0, 2, 1, 3
        //3 4 2 0 1
        int sum, t;
        for (int i = 0; i < n; i++) {
            if (a.get(i) < i) {
                t = a.get(a.get(i)) / n;
            } else {
                t = a.get(a.get(i));
            }
            sum = t + (a.get(i) * n);
            a.set(i, sum);
        }

        for (int i = 0; i < n; i++) {
            a.set(i, a.get(i) % n);
        }

    }
}
