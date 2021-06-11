package com.company.May;

import java.lang.reflect.Array;
import java.util.*;
import java.util.LinkedList;

public class graph2 {

    public void executeFn() {
        int a[][] = new int[][]
                {
                        {0, 0, 0, 1},
                        {0, 0, 1, 1},
                        {0, 1, 1, 0}
                };


//        checkPath(5,a);
//        searchIsland(a);
//        cycleUndirected(5,a);
//        cycleDirected(5,a);
//        ConstructionCost(11,a);
        disCell(a);

    }


    /*Black Shapes
Problem Description

Given character matrix A of O's and X's, where O = white, X = black.

Return the number of black shapes. A black shape consists of one or more adjacent X's (diagonals not included)*/



    public int black(String[] A) {

        ArrayList<ArrayList<String>> al=new ArrayList<>();
        for(int i=0;i<A.length;i++) {
            ArrayList<String> a1 = new ArrayList<>();
            for (int j = 0; j < A[i].length(); j++) {
                a1.add(A[i].charAt(j) +"" );
            }
            al.add(a1);
        }

        int ans=0;

        int rlen=al.size();
        int clen=al.get(0).size();
        boolean visited[][]=new boolean[rlen][clen];
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if(al.get(i).get(j).equals("X")  && visited[i][j]!=true)
                {
                    searchBFS(al,visited,i,j);
                    ans++;
                }

            }

        }
        return ans;
    }

    class PairTwo {
        int si;
        int sj;


        public PairTwo(int si, int sj) {
            this.si = si;
            this.sj = sj;
        }
    }
    private boolean isSsafe(int si, int sj, boolean[][] visited, ArrayList<ArrayList<String>> al) {
        int ROW=al.size();
        int COL=al.get(0).size();
        if( (si>=0) && (si<ROW)  && (sj>=0) && (sj<COL) && (al.get(si).get(sj).equals("X"))&&(visited[si][sj]==false) )
            return true;
        return false;
    }

    private void searchBFS(ArrayList<ArrayList<String>> a, boolean[][] visited, int i, int j) {

        //first upper row --  middle row -- bottom row

        int rowNmbr[] = {-1, 0, 1, 0};
        int colNmbr[] = {0, -1, 0, 1};
        Queue<PairTwo> queue=new LinkedList<>();
        queue.add(new PairTwo(i,j));
        while(!queue.isEmpty())
        {
            int si=queue.peek().si;
            int sj=queue.peek().sj;
            queue.poll();


            for (int k = 0; k < rowNmbr.length; k++) {

                int nsi=si+rowNmbr[k];
                int nsj=sj+colNmbr[k];
                boolean check = isSsafe(nsi,nsj,visited,a);
                if(check==true)
                {
                    queue.add(new PairTwo(si+rowNmbr[k],sj+colNmbr[k])  );
                    visited[nsi][nsj]=true;
                }
            }
        }
    }


    /**/

    /*Distance of nearest cell
Problem Description

Given a matrix of integers A of size N x M consisting of 0 or 1.

For each cell of the matrix find the distance of nearest 1 in the matrix.

Distance between two cells (x1, y1) and (x2, y2) is defined as |x1 - x2| + |y1 - y2|.

Find and return a matrix B of size N x M which defines for each cell in A distance of nearest 1 in the matrix A.

NOTE: There is atleast one 1 is present in the matrix.*/

    class paird
    {
        int si;
        int sj;

        public paird(int si, int sj) {
            this.si = si;
            this.sj = sj;
        }
    }
    public int[][] disCell(int[][] A) {
        int rowNmbr[] = {-1, 0, 1, 0};
        int colNmbr[] = {0, -1, 0, 1};


        int rowlen=A.length;
        int collen=A[0].length;
        int dist[][]=new int[rowlen][collen];
        Queue<paird> q=new LinkedList<>();
        for (int i=0; i<rowlen; i++)
        {
            for (int j=0; j<collen; j++)
            {
                dist[i][j] = Integer.MAX_VALUE;
                if (A[i][j] == 1)
                {
                    // distance of '1' from itself is always 0
                    dist[i][j] = 0;
                    // make pair and push it in queue
                    q.add(new paird(i,j));
                }
            }
        }

        while(!q.isEmpty())
        {
            paird pairQ=q.poll();
            int si=pairQ.si;
            int sj=pairQ.sj;
            for (int i = 0; i < 4; i++) {
                int nsi=si+rowNmbr[i];
                int nsj=sj+colNmbr[i];
                if (nsi>=0 && nsi<rowlen && nsj>=0 && nsj<collen &&  dist[nsi][nsj] > dist[si][sj] + 1)
                {
                    // update distance
                    dist[nsi][nsj] = dist[si][sj] + 1;
                    q.add(new paird(nsi,nsj));
                }
            }
        }

        return dist;
    }

    /*Construction Cost
Problem Description

Given a graph with A nodes and C weighted edges. Cost of constructing the graph is the sum of weights of all the edges in the graph.

Find the minimum cost of constructing the graph by selecting some given edges such that we can reach every other node from the 1st node.

NOTE: Return the answer modulo 109+7 as the answer can be large.*/

    public int ConstructionCost(int A, int[][] B) {
        Graph g =new Graph(A);
        for (int i = 0; i < B.length; i++) {
            g.addEdge(B[i][0]-1,B[i][1]-1,B[i][2]);
        }

        return primMst(g,A,B);

    }

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
        LinkedList<primData>[] adj;

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
            primData node0 = new primData(dest, weight);
            primData node = new primData(src, weight);
            adj[src].addLast(node0);
            adj[dest].addLast(node);
        }
    }
    class node  implements Comparable<node>{
        int vertex;
        int key;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            node node = (node) o;
            return vertex == node.vertex && key == node.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex, key);
        }

        @Override
        public int compareTo(node o) {

            return this.key-o.key;
        }
    }





    private int primMst(Graph g,int a, int[][] b) {

        long MOD =(int)1e9+7;
        boolean[] mstset = new boolean[g.V];
        node[] e = new node[g.V];

        int[] parent = new int[g.V];

        for (int o = 0; o < g.V; o++)
            e[o] = new node();

        for (int o = 0; o < g.V; o++) {
            // Initialize to false
            mstset[o] = false;

            // Initialize key values to infinity
            e[o].key = Integer.MAX_VALUE;
            e[o].vertex = o;
            parent[o] = -1;
        }

//        mstset[0] = true;
        e[0].key = 0;
        PriorityQueue<node> queue = new PriorityQueue<node>();

//        TreeSet<node> queue = new TreeSet<node>(new comparator());
        //adding all adjacent vertices
//        for (int o = 0; o <g.V; o++)
//            queue.add(e[o]);

        node t1=new node();
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
            for (primData iterator : g.adj[nd.vertex]) {
                if(mstset[iterator.dest] ==false)
                {
                    if (e[iterator.dest].key > iterator.weight) {
//                        queue.remove(e[iterator.dest]);
                        e[iterator.dest].key = iterator.weight;
                        int v=iterator.dest;
                        int w= iterator.weight;
                        node t=new node();
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


    /*Cycle in Directed Graph
Problem Description

Given an directed graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].

Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.

NOTE:

The cycle must contain atleast two nodes.
There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make sure to clear them.*/


    class graphDataD
    {
        private int V;
        private LinkedList<Integer> adj[];
        graphDataD(int v)
        {
            V = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i)
                adj[i] = new LinkedList();
        }

        void addEdge(int v,int w)  {
            adj[v].add(w) ;
        }

        private boolean isCyclic(int s, boolean[] visited) {
            visited[s]=true;

            ListIterator<Integer> itr =  adj[s].listIterator();
            while(itr.hasNext())
            {
                int t=itr.next();
                if(visited[t]==true)
                    return true;
                else if (visited[t]==false && isCyclic(t,visited))
                {
                    return true;
                }
            }
            
            visited[s]=false;
            return false;
        }

    }

    public int cycleDirected(int A, int[][] B) {

        int rlen=B.length;
        int clen=B[0].length;

        graphDataD g = new graphDataD(A+1);
        for (int i = 0; i < B.length; i++) {
            g.addEdge(B[i][0],B[i][1]);
        }

        boolean  visited[] = new boolean [g.V];
        for (int i = 0; i < g.V; i++) {
            visited[i]=false;
        }
        for (int i = 0; i < g.V; i++) {
            boolean check = g.isCyclic(i, visited);
                if(check==true)
                    return 1;
        }

        return 0;
    }

    /*Cycle in Undirected Graph
Problem Description

Given an undirected graph having A nodes labelled from 1 to A with M edges given in a form of matrix B of size M x 2 where (B[i][0], B[i][1]) represents two nodes B[i][0] and B[i][1] connected by an edge.

Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.

NOTE:

The cycle must contain atleast three nodes.
There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make sure to clear them.*/

    class graphData
    {
        private int V;
        private LinkedList<Integer> adj[];
        graphData(int v)
        {
            V = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i)
                adj[i] = new LinkedList();
        }

        void addEdge(int v,int w)  {
            adj[v].add(w) ;
            adj[w].add(v);
        }

        private boolean isCyclic(int s, Boolean[] visited, int parent) {
            visited[s]=true;

            ListIterator<Integer> itr =  adj[s].listIterator();
            while(itr.hasNext())
            {
                int t=itr.next();
                if(visited[t]==true && t!=parent)
                    return true;
                else if (visited[t]==false && isCyclic(t,visited,s))
                {
                    return true;
                }
            }
            return false;
        }

    }

    public int cycleUndirected(int A, int[][] B) {

        int rlen=B.length;
        int clen=B[0].length;

        graphData g = new graphData(A+1);
        for (int i = 0; i < B.length; i++) {
               g.addEdge(B[i][0],B[i][1]);
        }

        Boolean  visited[] = new Boolean [g.V];
        for (int i = 0; i < g.V; i++) {
            visited[i]=false;
        }
        for (int i = 0; i < g.V; i++) {
            if(visited[i]!=true)
            {
                boolean check = g.isCyclic(i,visited,-1);
                if(check==true)
                    return 1;
            }
        }

        return 0;
    }


}
