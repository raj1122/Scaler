package com.company.Apr.dynamicProg.dp1;

/*Max Product Subarray
Problem Description

Given an integer array A of size N. Find the contiguous subarray within the given array (containing at least one number) which has the largest product.

Return an integer corresponding to the maximum product possible.

NOTE: Answer will fit in 32-bit integer value.



Problem Constraints
1 <= N <= 5 * 105

-100 <= A[i] <= 100



Input Format
First and only argument is an integer array A.



Output Format
Return an integer corresponding to the maximum product possible.



Example Input
Input 1:

 A = [4, 2, -5, 1]
Input 2:

 A = [-3, 0, -5, 0]


Example Output
Output 1:

 8
Output 2:

 0


Example Explanation
Explanation 1:

 We can choose the subarray [4, 2] such that the maximum product is 8.
Explanation 2:

 0 will be the maximum product possible.*/

import java.util.List;

public class MxProdSubArr {
    public int maxProduct(final List<Integer> A) {
        // Variables to store maximum and minimum
        // product till ith index.
        int minVal = A.get(0);
        int maxVal = A.get(0);

        int maxProduct = A.get(0);

        for (int i = 1; i < A.size(); i++) {

            // When multiplied by -ve number,
            // maxVal becomes minVal
            // and minVal becomes maxVal.
            if (A.get(i) < 0) {
                int temp = maxVal;
                maxVal = minVal;
                minVal = temp;

            }

            // maxVal and minVal stores the
            // product of subarray ending at A.get(i).
            maxVal = Math.max(A.get(i), maxVal * A.get(i));
            minVal = Math.min(A.get(i), minVal * A.get(i));

            // Max Product of array.
            maxProduct = Math.max(maxProduct, maxVal);
        }

        // Return maximum product found in array.
        return maxProduct;

    }
}
