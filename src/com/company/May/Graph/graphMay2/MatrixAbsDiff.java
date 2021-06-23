package com.company.May.Graph.graphMay2;


/*Matrix and Absolute Difference
Problem Description

Given a matrix C of integers, of dimension A x B.

For any given K, you are not allowed to travel between cells that have an absolute difference greater than K.

Return the minimum value of K such that it is possible to travel between any pair of cells in the grid through a path of adjacent cells.

NOTE:

Adjacent cells are those cells that share a side with the current cell.


Problem Constraints
1 <= A, B <= 102

1 <= C[i][j] <= 109



Input Format
The first argument given is A.

The second argument give is B.

The third argument given is the integer matrix C.



Output Format
Return a single integer, the minimum value of K.



Example Input
Input 1:

 A = 3
 B = 3
 C = [  [1, 5, 6]
        [10, 7, 2]
        [3, 6, 9]   ]


Example Output
Output 1:

 4


Example Explanation
Explanation 1:


 It is possible to travel between any pair of cells through a path of adjacent cells that do not have an absolute
 difference in value greater than 4. e.g. : A path from (0, 0) to (2, 2) may look like this:
 => (0, 0) -> (0, 1) -> (1, 1) -> (2, 1) -> (2, 2)*/

import com.company.May.graph2;

import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;

class primDataDiff {

    // Stores destination vertex in adjacency list
    int dest;

    // Stores weight of a vertex in the adjacency list
    int weight;
    // Constructor
    primDataDiff(int a, int b)
    {
        dest = a;
        weight = b;
    }
}

class Graph {
    // Number of vertices in the graph
    int V;

    // List of adjacent nodes of a given vertex
    LinkedList<primDataDiff>[] adj;

    // Constructor
    Graph(int e)
    {
        V = e;
        adj = new LinkedList[V];
        for (int o = 0; o < V; o++)
            adj[o] = new LinkedList<>();
    }
    void addEdge( int src, int dest, int weight)
    {
        primDataDiff node0 = new primDataDiff(dest, weight);
        adj[src].addLast(node0);

        primDataDiff node = new primDataDiff(src, weight);
        adj[dest].addLast(node);
    }
}

class nodeDiff  implements Comparable<nodeDiff>{
    int vertex;
    int key;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        nodeDiff node = (nodeDiff) o;
        return vertex == node.vertex && key == node.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex, key);
    }

    @Override
    public int compareTo(nodeDiff o) {
        return this.key-o.key;
    }
}

public class MatrixAbsDiff {

    public int solve(int A, int B, int[][] C) {


        int rowNbr[] = new int[] { -1 , 0, 0, 1 };
        int colNbr[] = new int[] {  0, -1, 1, 0 };

        Graph g =new Graph((A*B)+1);
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                for (int k = 0; k < rowNbr.length; k++) {
                    int tempx= i+ rowNbr[k];
                    int tempy= j+ colNbr[k];

                    if( isafe( tempx,tempy,A,B))
                    {

                        int src= (i *B) +(j+1);
                        int dest=(tempx *B) +(tempy+1);
                        int diff = Math.abs(C[tempx][tempy] - C[i][j]);
                        g.addEdge(src,dest,diff);
                    }
                }

            }

        }

        return primMst(g,C);

    }

    private boolean isafe(int x, int y, int A, int B) {
        return ( x>=0 &&x<A) &&(y>=0 && y<B);
    }

    int primMst(Graph g ,int[][] b) {

        long MOD =(int)1e9+7;
        boolean[] mstset = new boolean[g.V];
        nodeDiff[] e = new nodeDiff[g.V];

        int[] parent = new int[g.V];
        for (int o = 0; o < g.V; o++)
            e[o] = new nodeDiff();

        for (int o = 0; o < g.V; o++) {
            // Initialize to false
            mstset[o] = false;

            // Initialize key values to infinity
            e[o].key = Integer.MAX_VALUE;
            e[o].vertex = o;
            parent[o] = -1;
        }


        PriorityQueue<nodeDiff> queue = new PriorityQueue<nodeDiff>();



        e[1].key = 0;
        nodeDiff t1=new nodeDiff();
        t1.vertex=e[1].vertex;
        t1.key=e[1].key;
        queue.add(t1);
        long cost=0;
        int cnt=0;
        int max=Integer.MIN_VALUE;
        while(!queue.isEmpty())
        {
            nodeDiff nd=queue.poll();

            if(mstset[nd.vertex]==true)
                continue;


            mstset[nd.vertex]=true;
            if(nd.key!=Integer.MAX_VALUE) {
                cnt++;
                max=Math.max(nd.key,max);
                cost = ((cost % MOD) + (nd.key % MOD)) % MOD;

            }

            //neighbour data
            for (primDataDiff iterator : g.adj[nd.vertex]) {
                if(mstset[iterator.dest] ==false)
                {
                    if (e[iterator.dest].key > iterator.weight) {
//                        queue.remove(e[iterator.dest]);
                        e[iterator.dest].key = iterator.weight;
                        int v=iterator.dest;
                        int w= iterator.weight;
                        nodeDiff t=new nodeDiff();
                        t.vertex=v;
                        t.key=w;
                        queue.add(t);
                        parent[iterator.dest] = nd.vertex;
                    }
                }
            }
        }



        return max;

    }



}
