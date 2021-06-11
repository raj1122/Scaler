package com.company.Apr.dynamicProg.dp6;


/*Valuable Nodes
Problem Description

Given a tree T containing N nodes numbered [1,2, ..., N] rooted at node 1.

Each node has a value associated with it. You need to choose some of the nodes from the tree such that the sum of values of the chosen nodes is maximum possible.

Moreover, if you have chosen a node V you cannot choose any of its children or grand children.

In simple words, you have to choose a subset of nodes such that no two nodes in the chosen set have a parent-child relation or grandfather-grandchild relation between them.



Problem Constraints
1 <= N <= 500000

1 <= val <= 10000



Input Format
First argument is the vector A, where A[i] denotes parent of i+1

Second argument is the vector B, where B[i] if the value associated with node i+1



Output Format
Return an integer containing the maximum possible sum. (As the answer can be large, output the answer modulo 1000000007)



Example Input
Input 1:

A = [0]
B = [1]
Input 2:

A = [0, 1, 2, 3]
B = [1, 50, 3, 4]


Example Output
Output 1:

 1
Output 2:

 50


Example Explanation
Explanation 1:

 Only node 1 is taken.
Explanation 2:

 Only node 2 is taken.*/

import java.util.ArrayList;
import java.util.Arrays;

public class ValuableNodes {
    public int solve(int[] A, int[] B) {

        int dp[]=new int[A.length+1];
        ArrayList<Integer> child[]=new ArrayList[A.length+1];
        Arrays.fill(dp,-1);
        for (int i = 1; i <= A.length; i++) {
            int par=A[i-1];
            if(child[par]==null)
            {
                child[par]=new ArrayList<>();
            }
            child[par].add(i);
        }
        return (int) solve(child,B,1,dp);
    }

    private long solve(ArrayList<Integer>[] child, int[] b, int root, int[] dp) {
        long option1=b[root-1];
        long MOD=(int)1e9+7;
        if(child[root] !=null)
        {
            for (int i = 0; i < child[root].size(); i++) {
                int cchild=child[root].get(i);
                if(child[cchild]!=null)
                {
                    for (int j = 0; j < child[cchild].size(); j++) {
                        int gchild=child[cchild].get(j);
                        if(child[gchild]!=null)
                        {
                            for (int k = 0; k < child[gchild].size(); k++) {
                                option1=((option1%MOD)+(solve(child,b,child[gchild].get(k),dp)%MOD))%MOD;
                            }
                        }

                    }
                }
            }
        }
        long option2=0;
        if(child[root]!=null)
        {
            for (int i = 0; i < child[root].size(); i++) {
                option2=((option2%MOD) + (solve(child,b,child[root].get(i),dp)%MOD))%MOD;
            }
        }
        return Math.max(option1,option2);

    }
}
