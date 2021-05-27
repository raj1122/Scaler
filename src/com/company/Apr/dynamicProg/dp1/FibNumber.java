package com.company.Apr.dynamicProg.dp1;

/*Fibonacci Number
Problem Description

Given a positive integer A, write a program to find the Ath Fibonacci number.

In a Fibonacci series, each term is the sum of the previous two terms and the first two terms of the series are 0 and 1. i.e. f(0) = 0 and f(1) = 1. Hence, f(2) = 1, f(3) = 2, f(4) = 3 and so on.

NOTE: 0th term is 0. 1th term is 1 and so on.*/

public class FibNumber {

    public int findFib(int n)
    {
        int dp[]=new int[n+1];

        if(n<2)
            return 1;

        dp[0]=0;
        dp[1]=1;

        for (int i = 2; i < n; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];

    }
}
