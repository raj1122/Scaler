package com.company.May.Graph.graphMay3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*Good Graph
Problem Description

Given a directed graph of N nodes where each node is pointing to exactly one of the N nodes (can possibly point to itself). Ishu, the coder, is bored and he has discovered a problem out of it to keep himself busy. Problem is as follows:

A node is 'good' if it satisfies one of the following:

1. It is the special node (marked as node 1)
2. It is pointing to the special node (node 1)
3. It is pointing to a good node.
Ishu is going to change pointers of some nodes to make them all 'good'. You have to find the minimum number of pointers to change in order to make all the nodes good (Thus, a Good Graph).

NOTE: Resultant Graph should hold the property that all nodes are good and each node must point to exactly one node.



Problem Constraints
1 <= N <= 105



Input Format
First and only argument is an integer array A containing N numbers all between 1 to N, where i-th number is the number of node that i-th node is pointing to.



Output Format
An Integer denoting minimum number of pointer changes.



Example Input
Input 1:

 [1, 2, 1, 2]
Input 2:

 [3, 1, 3, 1]


Example Output
Output 1:

 1
Output 2:

 1


Example Explanation
Explanation 1:

 Pointer of node 2 is made to point to node 1.
Explanation 2:

 Pointer of node 3 is made to point to node 1.*/
public class GoodGraph {

    class subset
    {
        int parent, rank;
    };

    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root
        // of high rank tree (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
            // If ranks are same, then make one as
            // root and increment its rank by one
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    int find(subset subsets[], int i)
    {
        // find root and make root as parent of i
        // (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }


    public int solve(int[] A) {
        int n=A.length;

        subset subsets[] = new subset[n+1];
        for (int i = 1; i <=n; ++i) {
            subsets[i] = new subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        HashSet<Integer> hs = new HashSet<Integer>();
        for (int i = 1; i <= n; i++) {
            if(  i == A[i-1])
                continue;
            int x=find(subsets,i);
            int y=find(subsets,A[i-1]);


            if (x != y) {
                Union(subsets, x, y);
            }
        }

        boolean isFound=false;
        for (int i = 1; i <=n ; i++) {

            hs.add(subsets[i].parent);
        }
        if(isFound==false)
        {
            return hs.size();
        }
        return hs.size()-1;



    }
}
