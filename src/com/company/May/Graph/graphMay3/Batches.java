package com.company.May.Graph.graphMay3;

import java.util.HashMap;
import java.util.Map;


/*Batches
Problem Description

A students applied for admission in IB Academy. An array of integers B is given representing the strengths of A people i.e. B[i] represents the strength of ith student.

Among the A students some of them knew each other. A matrix C of size M x 2 is given which represents relations where ith relations depicts that C[i][0] and C[i][1] knew each other.

All students who know each other are placed in one batch.

Strength of a batch is equal to sum of the strength of all the students in it.

Now the number of batches are formed are very much, it is impossible for IB to handle them. So IB set criteria for selection: All those batches having strength at least D are selected.

Find the number of batches selected.

NOTE: If student x and student y know each other, student y and z know each other then student x and student z will also know each other.



Problem Constraints
1 <= A <= 105

1 <= M <= 2*105

1 <= B[i] <= 104

1 <= C[i][0], C[i][1] <= A

1 <= D <= 109



Input Format
The first argument given is an integer A.
The second argument given is an integer array B.
The third argument given is a matrix C.
The fourth argument given is an integer D.



Output Format
Return the number of batches selected in IB.



Example Input
Input 1:

 A = 7
 B = [1, 6, 7, 2, 9, 4, 5]
 C = [  [1, 2]
        [2, 3]
       `[5, 6]
        [5, 7]  ]
 D = 12
Input 2:

 A = 5
 B = [1, 2, 3, 4, 5]
 C = [  [1, 5]
        [2, 3]  ]
 D = 6


Example Output
Output 1:

 2
Output 2:

 1


Example Explanation
Explanation 1:

 Initial Batches :
    Batch 1 = {1, 2, 3} Batch Strength = 1 + 6 + 7 = 14
    Batch 2 = {4} Batch Strength = 2
    Batch 3 = {5, 6, 7} Batch Strength = 9 + 4 + 5 = 18
    Selected Batches are Batch 1 and Batch 2.
Explanation 2:

 Initial Batches :
    Batch 1 = {1, 5} Batch Strength = 1 + 5  = 6
    Batch 2 = {2, 3} Batch Strength = 5
    Batch 3 = {4} Batch Strength = 4
    Selected Batch is only Batch 1.*/


public class Batches {

    class Subset
    {
        int parent;
        int rank;
        boolean isFound;
    }
    public int find(Subset arr[], int x)
    {
        if(arr[x].parent!=x)
        {
            arr[x].parent=find(arr,arr[x].parent);
        }
        return arr[x].parent;
    }


    public void union(Subset subset[], int x, int y)
    {
        int tx=find(subset,x);
        int ty=find(subset,y);
        if(subset[tx].rank>subset[ty].rank)
        {
            subset[ty].parent=tx;
        }
        else if(subset[tx].rank<subset[ty].rank)
        {
            subset[tx].parent=ty;
        }
        else
        {
            subset[ty].parent=tx;
            subset[tx].rank++;
        }
    }

    public int solve(int A, int[] B, int[][] C, int D) {
        Subset subset[]=new Subset[A+1];
        for (int i = 1; i <=A ; i++) {
            subset[i]=new Subset();
            subset[i].parent=i;
            subset[i].rank=0;
        }

        for (int i = 0; i < C.length; i++) {
            int x=C[i][0];
            int y=C[i][1];

            int tx=find(subset,x);
            int ty=find(subset,y);
            if(tx!=ty)
            {
                union(subset,tx,ty);
            }
        }

        HashMap<Integer,Integer> hm=new HashMap<>();
        for (int i = 1; i <=A ; i++) {
            int x= find(subset,subset[i].parent);
            int t=B[i-1];
            if(hm.containsKey(x))
            {
                hm.put(x,hm.get(x)+t);
            }
            else
            {

                hm.put(x,t);
            }
        }

        int ans=0;
        for (Map.Entry<Integer,Integer> itr:hm.entrySet()) {
                if(itr.getValue() >= D)
                {
                    ans++;
                }
        }

        return ans;

    }
}
