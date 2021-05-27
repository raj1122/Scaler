package com.company.Apr;

import java.util.*;
public class dp4 {
    public void executefn() {
        Integer[] arr1 = {1, 2, 3};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);

        String s1[] = {"10", "sale", "jan"};
        String s2[] = {"200", "sale", "EMPTY"};

        ArrayList<String> ls1 = new ArrayList<>();
        ArrayList<String> ls2 = new ArrayList<>();
        Collections.addAll(ls1, s1);
        Collections.addAll(ls2, s2);
        ArrayList<ArrayList<Integer>> dblArr = creatDbtArr1();

        int [][]dblarr=new int[][]
                {
                        {1, 1, 1},
                        {0, 1, 1},
                        {1, 0, 0}
                };

        int a[]={40,20,30,10,30};
        matrixMul(a);
//        RussianDoll(dblarr);
//        NDigitNumber1(2,2);
//        numTrees(3);
//        anytwo("aabb");

//        maximalRectangle(dblarr);

    }



    /*Matrix Chain Multiplication
Problem Description

Given an array of integers A representing chain of 2-D matices such that the dimensions of ith matrix is A[i-1] x A[i].

Find the most efficient way to multiply these matrices together. The problem is not actually to perform the multiplications, but merely to decide in which order to perform the multiplications.

Return the minimum number of multiplications needed to multiply the chain.*/

    public int matrixMul(int[] A) {
        int len=A.length;
        int dp[][]=new int[len-1][len-1];
        for (int gap = 0; gap < len-1; gap++) {
            for (int i = 0,j=gap; j < len-1; i++,j++) {
                if(gap==0)
                {
                    dp[i][j]=0;
                }
                else if(gap==1)
                {
                    dp[i][j]=A[i]*A[j]*A[j+1];
                }
                else
                {
                    int min=Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        int lc=dp[i][k];
                        int rc=dp[k+1][j];
                        int mc=A[i]*A[k+1]*A[j+1];
                        int totalCost=lc+rc+mc;
                        if(totalCost<min)
                            min=totalCost;
                    }
                    dp[i][j]=min;
                }
            }
        }
        return dp[0][len-2];
    }

    /*Max Rectangle in Binary Matrix
Problem Description

Given a 2-D binary matrix A of size N x M filled with 0's and 1's, find the largest rectangle containing all ones and return its area.*/

    public int maximalRectangle(int[][] A) {

        int temp[] = new int[A[0].length];

        int maxArea = 0;
        int area = 0;
        for(int i=0; i < A.length; i++){
            for(int j=0; j < temp.length; j++){
                if(A[i][j] == 0){
                    temp[j] = 0;
                }else{
                    temp[j] += A[i][j];
                }
            }
            area = largestRectangleArea(temp);
            if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }


    public int largestRectangleArea(int A[]) {

        Stack<Integer> stleft = new Stack<>();
        int size = A.length;
        Integer ls[] = new Integer[size];
        Integer rs[] = new Integer[size];
        Arrays.fill(ls, -1);
        Arrays.fill(rs, -1);
        for (int i = 0; i < size; i++) {
            //remove stack element if it becomes > than curr element
            while (!stleft.isEmpty() && A[stleft.peek()]>= A[i])
                stleft.pop();

            if (stleft.isEmpty())
                ls[i] = -1;
            else
                ls[i] = stleft.peek();

            stleft.push(i);

        }

        stleft = new Stack<>();
        for (int i = size - 1; i >= 0; i--) {
            //remove stack element if it becomes > than curr element
            while (!stleft.isEmpty() && A[stleft.peek()] >= A[i])
                stleft.pop();

            if (stleft.isEmpty())
                rs[i] = -1;
            else
                rs[i] = stleft.peek();

            stleft.push(i);
        }


        int lelm[] = new int[size];
        int relm[] = new int[size];
        for (int i = 0; i < size; i++) {
            if (ls[i] == -1)
                lelm[i] = -1;
            else
                lelm[i] = A[ls[i]];

            if (rs[i] == -1)
                relm[i] = -1;
            else
                relm[i] = A[rs[i]];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            //for left-> im finding which index is smaller, then taking index +1 .If no element is smaller start from first
            int lstart = Math.min(i, ls[i] + 1);
            //for right-> im finding which index is smaller in right,
            // then taking index - 1 .If no element is smaller take last index
            int rstart = Math.max(i, rs[i] - 1);
            if (rs[i] == -1)
                rstart = size - 1;
            max = Math.max(max, (rstart - lstart + 1) * A[i]);

        }


        return max;
    }

    /*Intersecting Chords in a Circle
Problem Description

Given a number A, return number of ways you can draw A chords in a circle with 2 x A points such that no 2 chords intersect.

Two ways are different if there exists a chord which is present in one way and not in other.
Return the answer modulo 109 + 7.*/

    public int chordCnt(int A) {
        int n = 2 * A;

        long MOD=(int)1e9+7;
        // dp array containing the sum
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[2] = 1;
        for (int i = 4; i <= n; i += 2) {
            for (int j = 0; j < i - 1; j += 2)
            {
                long t= (dp[j]%MOD *dp[i - 2 - j]%MOD)%MOD;
                dp[i] = (dp[i]%MOD + t%MOD)%MOD;
            }
        }

        // returning the required number
        return (int) dp[n];
    }
    /*
Problem Description

Given a string A, find if there is any subsequence that repeats itself.

A subsequence of a string is defined as a sequence of characters generated by deleting some characters in the string without changing the order of the remaining characters.

NOTE: Subsequence length should be greater than or equal to 2.
*/

    public int anytwo(String A) {
        int alen=A.length();
        int dp[][]=new int[alen+1][alen+1];

        for (int i = 1; i <=alen ; i++) {
            for (int j = 1; j <=alen ; j++) {
                int len=Math.abs(j-i);
                if(A.charAt(i-1)==A.charAt(j-1) && i!=j  )
                {
                    dp[i][j]=1+dp[i-1][j-1];
                }
                else
                {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        if(dp[alen][alen]>1)
            return 1;
        else return 0;
    }

    /*Unique Binary Search Trees II
Problem Description

Given an integer A, how many structurally unique BST's (binary search trees) exist that can store values 1...A?*/

    public int numTrees(int A) {
        int dp[]=new int[A+1];
        dp[0]=1;
        dp[1]=1;
        for (int i =2; i <=A ; i++) {
            for (int j = 1; j <=i ; j++) {
                int left=dp[i-j];
                int right= dp[j-1];
                dp[i]=dp[i]+(left*right);
            }
        }
        return dp[A];

    }

    /*Russian Doll Envelopes
Problem Description

Given a matrix of integers A of size N x 2 describing dimensions of N envelopes, where A[i][0] denotes the height of the ith envelope and A[i][1] denotes the width of the ith envelope.

One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

Find the maximum number of envelopes you can put one inside other.*/

    public int RussianDoll(int[][] A) {
        int n = A.length;
        // sort by ascending height, and sort by descending width if the height are the same
        Arrays.sort(A, new Comparator<int[]>()
        {
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ?
                        b[1] - a[1] : a[0] - b[0];
            }
        });
        // find LIS on the height array
        int[] width = new int[n];
        for (int i = 0; i < n; i++)
            width[i] = A[i][1];

        return lengthOfLIS(width);
    }

    private int lengthOfLIS(int[] width) {

        int alen = width.length;
        int lis[] = new int[alen];
        int res = Integer.MIN_VALUE;

        lis[0] = 1;
        res = Math.max(res, lis[0]);
        for (int i = 1; i < alen; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (width[j] < width[i] && lis[j] + 1 > lis[i]) {
                    lis[i] = lis[j] + 1;

                }
            }
            res = Math.max(res, lis[i]);
        }
        return res;
    }


    /*N digit numbers
Problem Description

Find out the number of A digit positive numbers, whose digits on being added equals to a given number B.

Note that a valid number starts from digits 1-9 except the number 0 itself. i.e. leading zeroes are not allowed.

Since the answer can be large, output answer modulo 1000000007*/
    static long lookup[][] = new long[1001][10001];
    public int NDigitNumber1(int A, int B) {
        long mod=1000000007;
        for (int i = 0; i < lookup.length; i++) {
            for (int j = 0; j < lookup[0].length; j++) {
                lookup[i][j]=-1;
            }
        }

        long ans=0;
        for (int i = 1; i <=9; i++) {
            long t = countWays(A-1,B-i);
            ans=((ans%mod) + (t%mod))%mod;
        }
        return (int) ans;
    }

    private long countWays(int n, int sum) {
        long mod=1000000007;

        if(sum<0)
            return 0;
        if(n==0)
        {
            if(sum==0)
                return 1;
            else
                return 0;
        }
        if(lookup[n][sum]!=-1)
            return lookup[n][sum];

        long ans=0;

        for (int i = 0; i <=9 ; i++) {
            if(sum-i>=0)
            {
                long t = countWays(n-1,sum-i);
                ans=((ans%mod) + (t%mod))%mod;
            }
        }
        lookup[n][sum] = ans;
        return lookup[n][sum];
    }


    public int NDigitNumber(int A, int B) {
        long mod=1000000007;
        long start=(long)Math.pow(10,A-1);
        long end= (long)Math.pow(10,A)-1;
        long ans=0;
        long s=start;
        while(s<end)
        {
            //checking sum of digit
            long t=s;
            long curr=0;
            while(t!=0)
            {
                curr= ((curr%mod) + (t%10)%mod)%mod;
                t=t/10;
            }

            if(curr==B) {
                ans=((ans%mod) + (1%mod))%mod;
                s=s+9;
            }
            else
            {
                s++;
            }
        }

        return (int) ans;
    }



    private static ArrayList<ArrayList<Integer>> creatDbtArr1() {
        ArrayList<ArrayList<Integer>> dblArr = new ArrayList<>();

        Integer arr[][] = new Integer[][]
                {
                        {1, 0}
                };

        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> al1 = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                al1.add(arr[i][j]);

            }
            dblArr.add(al1);

        }
        return dblArr;
    }
}
