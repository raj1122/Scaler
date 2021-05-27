package com.company.Apr.dynamicProg.dp5;


/*Flip Array
Problem Description

Given an array A of positive elements, you have to flip the sign of some of its elements such that the resultant sum of the elements of array should be minimum non-negative(as close to zero as possible).

Return the minimum number of elements whose sign needs to be flipped such that the resultant sum is minimum non-negative.



Problem Constraints
1 <= length of(A) <= 100

Sum of all the elements will not exceed 10,000.



Input Format
First and only argument is an integer array A.



Output Format
Return an integer denoting the minimum number of elements whose sign needs to be flipped.



Example Input
Input 1:

 A = [15, 10, 6]
Input 2:

 A = [14, 10, 4]


Example Output
Output 1:

 1
Output 2:

 1


Example Explanation
Explanation 1:

 Here, we will flip the sign of 15 and the resultant sum will be 1.
Explanation 2:

 Here, we will flip the sign of 14 and the resultant sum will be 0.
 Note that flipping the sign of 10 and 4 also gives the resultant sum 0 but flippings there sign are not minimum.*/




//can you hear me

public class FlipArray {
    public int solve(final int[] A) {
        int sum=0;
        for(int i=0;i<A.length;i++)
        {
            sum+=A[i];
        }
        int dp[][]=new int [sum+1][A.length+1];

        for(int s=0;s<=sum;s++)
        {
            for(int i=0;i<=A.length;i++)
            {
                dp[s][i] = Integer.MAX_VALUE;
            }
        }

        dp[0][0]=0;
        for(int s =0;s<=sum;s++)
        {
            for(int i=1;i<= A.length;i++)
            {
                if(s-A[i-1]>=0)
                {
                    int t1;
                    if(dp[s-A[i-1]][i-1]==Integer.MAX_VALUE)
                    {
                        t1=Integer.MAX_VALUE;
                    }
                    else
                    {
                        t1=1+dp[s-A[i-1]][i-1];
                    }

                    dp[s][i] = Math.min(t1,dp[s][i-1]);
                }
                else
                {
                    dp[s][i]= dp[s][i-1];
                }
            }
        }

        for(int s=sum;s>=0;s--)
        {
            if(sum-2*s>=0 && dp[s][A.length]!=Integer.MAX_VALUE)
            {
                return dp[s][A.length];
            }
        }

        return A.length-1;

    }
}
