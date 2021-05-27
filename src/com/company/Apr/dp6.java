package com.company.Apr;


import com.company.Mar.tree17;

import java.util.*;

public class dp6 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    public void executefn() {
        Integer[] arr1 = {10, 20, 30, 40};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);

        String s1[] = {"10", "sale", "jan"};
        String s2[] = {"200", "sale", "EMPTY"};

        ArrayList<String> ls1 = new ArrayList<>();
        ArrayList<String> ls2 = new ArrayList<>();
        Collections.addAll(ls1, s1);
        Collections.addAll(ls2, s2);


        int[][] dblarr = new int[][]{
                {5, 4},
                {6, 4},
                {6, 7},
                {2, 3}
        };


        int a1[] = new int[]{60, 100, 120};
        int a2[] = new int[]{10, 20, 30};

        TreeNode madeRoot = DeserializeTree(levelTree);

        maxPathSum(madeRoot);

    }


    /*Arithmetic Subsequences
Problem Description

A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequences:

 1, 3, 5, 7, 9
 7, 7, 7, 7
Given an integer array A of size N. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.

A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk](0-based indexing) is arithmetic. In particular, this means that k ≥ 2.

Return the number of arithmetic subsequences slices in the array A.*/

    static class Pair {
        int len3;
        int len2;
        public Pair(int len3, int len2) {
            this.len3 = len3;
            this.len2 = len2;
        }
    }
    public int solve(int[] A) {

        // dp[i][j] = gives no. of arithmetic sequences of len 3 and 2 with difference i ending at A[j]
        Map<Integer, Map<Integer, Pair>> dp = new HashMap<>();
        int n = A.length;
        int res = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int diff = A[i]-A[j];
                Map<Integer, Pair> seqCnts = dp.get(diff);
                if (seqCnts == null) {
                    seqCnts = new HashMap<>();
                    seqCnts.put(i, new Pair(0, 1));
                    dp.put(diff, seqCnts);
                } else {
                    Pair iSeqCnt = seqCnts.get(i);
                    Pair jSeqCnt = seqCnts.get(j);

                    if (iSeqCnt == null) {
                        iSeqCnt = new Pair(0, 0);
                        seqCnts.put(i, iSeqCnt);
                    }
                    iSeqCnt.len2 ++;
                    int len3 = jSeqCnt.len3 + jSeqCnt.len2;
                    iSeqCnt.len3 += len3;
                    res += len3;
                }
            }
        }
        return res;

    }


    class Res{
        long ans=0;
    }
    public int maxPathSum(TreeNode A) {

        Res res=new Res();
        res.ans=Integer.MIN_VALUE;
        maxPathSum1(A,res);
        return (int) res.ans;
    }

    private long  maxPathSum1(TreeNode a, Res r1) {
        if(a==null)
            return 0;

        long left= maxPathSum1(a.left,r1);
        long right = maxPathSum1(a.right,r1);

        long maxSingle= Math.max(a.val,Math.max(left,right)+a.val);

        long maxTop=Math.max(maxSingle,left+right+a.val);

        r1.ans=Math.max(r1.ans,maxTop);


        return maxSingle;
    }

    public TreeNode DeserializeTree(ArrayList<Integer> A) {


        Queue<TreeNode> q = new LinkedList<>();
        TreeNode head = null, temp = null;
        int i = 0;
        head = new TreeNode(A.get(i));
        q.add(head);
        while (!q.isEmpty()) {

            temp = q.peek();
            q.poll();

            int l = 2 * i + 1;
            int r = 2 * i + 2;
            i++;


            if ((l > A.size() - 1) || (r > A.size() - 1))
                break;

            if (A.get(l) != -1) {
                temp.left = new TreeNode(A.get(l));
                q.add(temp.left);
            }

            if (A.get(r) != -1) {
                temp.right = new TreeNode(A.get(r));
                q.add(temp.right);
            }

        }

        return head;

    }

}
