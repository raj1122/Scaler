package com.company.May;

import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;

public class graph3 {

    public void executeFn() {
        int a[][] = new int[][]
                {
                        {1, 2, 1},
                        {2, 3, 4},
                        {1, 4, 3},
                        {4, 3, 2},
                        {1, 3, 10}
                };
        commIsland(4,a);


    }
    /*Commutable Islands
Problem Description

There are A islands and there are M bridges connecting them. Each bridge has some cost attached to it.

We need to find bridges with minimal cost such that all islands are connected.

It is guaranteed that input data will contain at least one possible scenario in which all islands are connected with each other.*/



    class primData {

        // Stores destination vertex in adjacency list
        int dest;

        // Stores weight of a vertex in the adjacency list
        int weight;
        // Constructor
        primData(int a, int b)
        {
            dest = a;
            weight = b;
        }
    }
    class Graph {

        // Number of vertices in the graph
        int V;

        // List of adjacent nodes of a given vertex
        LinkedList< primData>[] adj;

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

             primData node0 = new  primData(dest, weight);
             primData node = new  primData(src, weight);
            adj[src].addLast(node0);
            adj[dest].addLast(node);
        }
    }
    class node  implements Comparable< node>{
        int vertex;
        int key;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
             node node = ( node) o;
            return vertex == node.vertex && key == node.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex, key);
        }

        @Override
        public int compareTo( node o) {

            return this.key-o.key;
        }
    }





    private int primMst( Graph g, int a, int[][] b) {

        long MOD =(int)1e9+7;
        boolean[] mstset = new boolean[g.V];
         node[] e = new  node[g.V];

        int[] parent = new int[g.V];

        for (int o = 0; o < g.V; o++)
            e[o] = new  node();

        for (int o = 0; o < g.V; o++) {
            // Initialize to false
            mstset[o] = false;

            // Initialize key values to infinity
            e[o].key = Integer.MAX_VALUE;
            e[o].vertex = o;
            parent[o] = -1;
        }

        e[0].key = 0;
        PriorityQueue< node> queue = new PriorityQueue< node>();



         node t1=new  node();
        t1.vertex=e[0].vertex;
        t1.key=e[0].key;
        queue.add(t1);
        long cost=0;
        int cnt=0;
        while(!queue.isEmpty())
        {
             node nd=queue.poll();
            while(!queue.isEmpty() && mstset[nd.vertex]==true)
                nd=queue.poll();


            mstset[nd.vertex]=true;
            if(nd.key!=Integer.MAX_VALUE) {
                cnt++;
                cost = ((cost % MOD) + (nd.key % MOD)) % MOD;
                if(cnt==g.V)
                    break;
            }

            //neighbour data
            for ( primData iterator : g.adj[nd.vertex]) {
                if(mstset[iterator.dest] ==false)
                {
                    if (e[iterator.dest].key > iterator.weight) {
//                        queue.remove(e[iterator.dest]);
                        e[iterator.dest].key = iterator.weight;
                        int v=iterator.dest;
                        int w= iterator.weight;
                         node t=new  node();
                        t.vertex=v;
                        t.key=w;
                        queue.add(t);
                        parent[iterator.dest] = nd.vertex;
                    }
                }
            }
        }


        return (int)cost;

    }

    
    public int commIsland(int A, int[][] B) {

        Graph g =new Graph(A);
        for (int i = 0; i < B.length; i++) {
            g.addEdge(B[i][0]-1,B[i][1]-1,B[i][2]);
        }

        return primMst(g,A,B);
    }
}
