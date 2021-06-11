package com.company.Mar.TreePck;


/*Maximum Difference on Tree
Problem Description

Given a tree with N nodes labeled from 1 to N.

Each node has a certain weight assigned to it given by an integer array A of size N.

Your task is to find the maximum difference in weights of two nodes where one node is a descendant of the other.

NOTE:

The tree is rooted at node labeled with 1.


Problem Constraints
2 <= N <= 105

-103 <= A[i] <= 103



Input Format
First argument is an integer array A of size N denoting the weight of each node.

Second argument is a 2-D array B of size (N-1) x 2 denoting the edge of the tree.



Output Format
Return an single integer denoting the maximum difference in weights of two nodes where one node is a descendant of the other.



Example Input
Input 1:

 A = [10, 5, 12, 6]
 B = [  [1, 2]
        [1, 4]
        [4, 3]
     ]
Input 2:

 A = [11, 12]
 B = [  [1, 2]
     ]


Example Output
Output 1:

 6
Output 2:

 1


Example Explanation
Explanation 1:

 The maximum difference occurs between the 3rd and 4th nodes. A[3] − A[4] = 12 - 6 = 6 .
Explanation 2:

 The maximum difference occurs between the 2nd and 1st nodes. A[2] − A[1] = 12 - 11 = 1 .*/


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

class data
{
    int min;
    int max;

    public data(int min, int max) {
        this.min = min;
        this.max = max;
    }
}



class TreeData
{
    LinkedList<Integer> root[];
    public TreeData(int V)
    {
        root=new LinkedList[V+1];
        for (int i = 0; i <= V; i++) {
            root[i]=new LinkedList<>();
        }
    }
    public void addEdge(int u,int v)
    {
        root[u].add(v);
    }

    int ans=Integer.MIN_VALUE;
    public data findMaxWeight(int u, ArrayList<Integer> A) {

        //cal max and min and sending it to root;
        int nval=A.get(u-1);
        data drt = new data(nval, nval);
        for (int n : root[u]) {
            data rt = findMaxWeight(n, A);
            drt.min=Math.min(drt.min,rt.min);
            drt.max=Math.max(drt.max,rt.max);
        }
        //cal ans for every max and min
        ans=Math.max(ans,Math.abs(drt.max - nval));
        ans=Math.max(ans,Math.abs(drt.min - nval));
        return drt;
    }
}

public class MaxDiffTree {

    public int solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        TreeData g=new TreeData(A.size());
        g.ans=Integer.MIN_VALUE;

        HashSet<Integer> hs=new HashSet<>();
        for (int i = 0; i < B.size(); i++) {
            int u= B.get(i).get(0);
            int v= B.get(i).get(1);
            hs.add(v);
            g.addEdge(u,v);
        }

        for (int i = 1; i <=A.size() ; i++) {
            if(!hs.contains(i))
            {
                g.findMaxWeight(i,A);
            }
        }

        return g.ans;
    }
}
