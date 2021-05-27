package com.company.Apr.dynamicProg.dp1;


import java.util.*;

public class MaxSumThreeDig {

    /*Maximum Sum
Problem Description

You are given an array A of N integers and three integers B, C, and D.

You have to find the maximum value of A[i]*B + A[j]*C + A[k]*D, where 1 <= i <= j <= k <= N.



Problem Constraints
1 <= N <= 105

-10000 <= A[i], B, C, D <= 10000



Input Format
First argument is an array A
Second argument is an integer B
Third argument is an integer C
Fourth argument is an integer D



Output Format
Return an Integer S, i.e maximum value of (A[i] * B + A[j] * C + A[k] * D), where 1 <= i <= j <= k <= N.



Example Input
Input 1:

 A = [1, 5, -3, 4, -2]
 B = 2
 C = 1
 D = -1
Input 2:

 A = [3, 2, 1]
 B = 1
 C = -10
 D = 3


Example Output
Output 1:

 18
Output 2:

 -4*/


    public int solve(int[] A, int B, int C, int D) {

        int ans = Integer.MIN_VALUE;
        int size=A.length;
        int []left = new int[size];
        left[0] = B *A[0];
        for (int i = 1; i < size; i++)
            left[i] = Math.max(left[i - 1], B * A[i]);

        int right[] = new int[size];
        right[size - 1] = D * A[size - 1];
        for (int i = size - 2; i >= 0; i--)
            right[i] = Math.max(right[i + 1], D * A[i]);



        for (int i = 0; i < size; i++)
            ans = Math.max(ans, left[i] + C * A[i] + right[i]);


        return ans;
    }
}
