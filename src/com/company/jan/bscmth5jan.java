package com.company.jan;

import java.util.*;

public class bscmth5jan {
    /*Grid Unique Paths
Problem Description

A robot is located at the top-left corner of an A x B grid (marked 'Start' in the diagram below).*/

    public int uniquePaths(int A, int B) {
        /*10!/3!6!
        * we should not start with 6!*
        in that way 9*8*7*6! will not cut.
        * thats why we use 6!
        * */

        int N = A + B - 2;
        int R = Math.max(A - 1, B - 1);
        double res = 1;

        int j = 1;
        for (int i = R + 1; i <= (A + B - 2); i++) {
            res = res * i / j;
            j++;
        }

        return (int) res;

    }
}
