package com.company.May;


import java.util.*;
import java.util.LinkedList;

public class graph1 {
    public void executeFn() {
        int a[][]=new int[][]
                {
                        {2, 0, 2, 2, 2, 0, 2, 1, 1, 0},
                        {0, 1, 2, 0, 2, 0, 0, 1, 0, 1},
                        {0, 1, 1, 1, 2, 0, 1, 1, 2, 1},
                        {2, 0, 2, 0, 1, 1, 2, 1, 0, 1},
                        {1, 0, 1, 1, 0, 1, 2, 0, 2, 2},
                        {0, 2, 1, 1, 2, 2, 0, 2, 1, 2},
                        {2, 1, 0, 2, 0, 0, 0, 0, 1, 1},
                        {2, 2, 0, 2, 2, 1, 1, 1, 2, 2}
                };
        int path[]=new int[]{1, 1, 2};

        Character c[][]=new Character[][]
                {
                        {'X', 'X', 'X', 'X'},
                        {'X', 'O', 'O', 'X'},
                        {'X', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'X'}
                };


        ArrayList<ArrayList<Character>> ac=new ArrayList<>();
        for (int i = 0; i < c.length; i++) {
            ArrayList<Character> a1=new ArrayList<>();
            for (int j = 0; j < c[0].length; j++) {
                a1.add(c[i][j]);
            }
            ac.add(a1);
        }
//        checkPath(5,a);
//        searchIsland(a);
//        RottenOrange(a);

        solve(path,2,1);

//        CaptureRegion(ac);


    }



    /*Capture Regions on Board
Problem Description

Given a 2-D board A of size N x M containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.*/

    private boolean isSafeCapture(int si, int sj, ArrayList<ArrayList<Character>> a, boolean[][] visited) {
        int ROW=a.size();
        int COL =a.get(0).size();
        if( (si>=0) && (si<ROW)  && (sj>=0) && (sj<COL) && (a.get(si).get(sj)=='O')&&(visited[si][sj]==false) )
            return true;
        return false;
    }
    private int captureRegion(ArrayList<ArrayList<Character>> a, boolean[][] visited) {
        //top -- left--right  --bottom

        Queue<PairTwoO> queue=new LinkedList<PairTwoO>();
        int rlen=a.size();
        int clen=a.get(0).size();
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen ; j++) {
                if( i==0 || j==0 || i==rlen-1  || j==clen-1)
                {
                    if(a.get(i).get(j).compareTo('O')==0 )
                        queue.add(new PairTwoO(i,j,0));
                }
            }
        }

        int rowNbr[] = new int[] {  -1,       0, 0,        1 };
        int colNbr[] = new int[] {   0,       -1,1,        0 };

        int val=0;
        while (!queue.isEmpty())
        {
            int si=queue.peek().si;
            int sj=queue.peek().sj;
            val=queue.peek().value;
            queue.poll();
            visited[si][sj]=true;
            for (int k = 0; k < rowNbr.length; k++) {
                int nsi=si+rowNbr[k];
                int nsj=sj+colNbr[k];
                boolean check=isSafeCapture(nsi,nsj,a,visited);
                if(check==true)
                {
                    visited[nsi][nsj]=true;
                    queue.add(new PairTwoO(nsi,nsj,val+1));
                }
            }
        }
        return val;

    }
    public void CaptureRegion(ArrayList<ArrayList<Character>> a) {
        int rlen=a.size();
        int clen=a.get(0).size();
        boolean visited[][]=new boolean[rlen][clen];
        captureRegion(a,visited);

        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen ;j++) {
                if(visited[i][j]!=true && a.get(i).get(j)=='O')
                {
                    a.get(i).set(j,'X');
                }

            }
        }
        System.out.println("hi");
    }
    /*First Depth First Search
Problem Description

You are given N towns (1 to N). All towns are connected via unique directed path as mentioned in the input.

Given 2 towns find whether you can reach the first town from the second without repeating any edge.

B C : query to find whether B is reachable from C.

Input contains an integer array A of size N and 2 integers B and C ( 1 <= B, C <= N ).

There exist a directed edge from A[i] to i+1 for every 1 <= i < N. Also, it's guaranteed that A[i] <= i.

NOTE: Array A is 0-indexed.*/

    public int solve(int[] A, final int B, final int C) {
        graphData g=new graphData(A.length+1);
        for (int i = 0; i < A.length; i++) {
            g.addEdge(i+1,A[i]);
        }
        boolean visited[]=new boolean[A.length+1];

        int ans= g.checkReachPath(visited,B,C);
        return 0;
    }
    /*Rotten Oranges
Problem Description

Given a matrix of integers A of size N x M consisting of 0, 1 or 2.

Each cell can have three values:

The value 0 representing an empty cell.

The value 1 representing a fresh orange.

The value 2 representing a rotten orange.

Every minute, any fresh orange that is adjacent (Left, Right, Top, or Bottom) to a rotten orange becomes rotten. Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1 instead.

Note: Your solution will run on multiple test cases. If you are using global variables, make sure to clear them.


*/

    class PairTwoO {
        int si;
        int sj;
        int value;

        public PairTwoO(int si, int sj, int value) {
            this.si = si;
            this.sj = sj;
            this.value = value;
        }
    }
    public int RottenOrange(int[][] A) {
        int ans=0;

        int rlen=A.length;
        int clen=A[0].length;
        boolean visited[][]=new boolean[rlen][clen];

        ans+=searchBFSOrange(A,visited);
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if (A[i][j]==1 && visited[i][j]==false)
                    return -1;

            }

        }

        return ans;
    }

    private int searchBFSOrange(int[][] A, boolean[][] visited) {
        //top -- left--right  --bottom

        Queue<PairTwoO> queue=new LinkedList<PairTwoO>();
        int rlen=A.length;
        int clen=A[0].length;
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen ; j++) {
                if(A[i][j]==2)
                    queue.add(new PairTwoO(i,j,0));
            }

        }

        int rowNbr[] = new int[] {  -1,       0, 0,        1 };
        int colNbr[] = new int[] {   0,       -1,1,        0 };
        int prev=0;
        int val=0;
        while (!queue.isEmpty())
        {
            int si=queue.peek().si;
            int sj=queue.peek().sj;
            val=queue.peek().value;
            queue.poll();
            for (int k = 0; k < rowNbr.length; k++) {
                int nsi=si+rowNbr[k];
                int nsj=sj+colNbr[k];
                boolean check=isSafeOraneg(nsi,nsj,A,visited);
                if(check==true)
                {
                    visited[nsi][nsj]=true;
                    queue.add(new PairTwoO(nsi,nsj,val+1));
                }
            }
        }
        return val;

    }

    private boolean isSafeOraneg(int si, int sj, int[][] a, boolean[][] visited) {
        int ROW=a.length;
        int COL =a[0].length;
        if( (si>=0) && (si<ROW)  && (sj>=0) && (sj<COL) && (a[si][sj]==1)&&(visited[si][sj]==false) )
            return true;
        return false;
    }

    /*Number of islands
Problem Description

Given a matrix of integers A of size N x M consisting of 0 and 1. A group of connected 1's forms an island. From a cell (i, j) such that A[i][j] = 1 you can visit any cell that shares a corner with (i, j) and value in that cell is 1.

More formally, from any cell (i, j) if A[i][j] = 1 you can visit:

(i-1, j) if (i-1, j) is inside the matrix and A[i-1][j] = 1.
(i, j-1) if (i, j-1) is inside the matrix and A[i][j-1] = 1.
(i+1, j) if (i+1, j) is inside the matrix and A[i+1][j] = 1.
(i, j+1) if (i, j+1) is inside the matrix and A[i][j+1] = 1.
(i-1, j-1) if (i-1, j-1) is inside the matrix and A[i-1][j-1] = 1.
(i+1, j+1) if (i+1, j+1) is inside the matrix and A[i+1][j+1] = 1.
(i-1, j+1) if (i-1, j+1) is inside the matrix and A[i-1][j+1] = 1.
(i+1, j-1) if (i+1, j-1) is inside the matrix and A[i+1][j-1] = 1.
Return the number of islands.

NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.*/



    class PairTwo {
        int si;
        int sj;


        public PairTwo(int si, int sj) {
            this.si = si;
            this.sj = sj;
        }
    }
    public int searchIsland(int[][] A) {
        int ans=0;

        int rlen=A.length;
        int clen=A[0].length;
        boolean visited[][]=new boolean[rlen][clen];
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if(A[i][j]==1  && visited[i][j]!=true)
                {
                    searchBFS(A,visited,i,j);
                    ans++;
                }

            }

        }
        return ans;
    }

    private void searchBFS(int[][] a, boolean[][] visited, int i, int j) {

        //first upper row --  middle row -- bottom row

        int rowNbr[] = new int[] { -1, -1, -1,      0, 0,       1, 1, 1 };
        int colNbr[] = new int[] { -1,  0,  1,      -1,1,       -1, 0, 1 };
        Queue<PairTwo> queue=new LinkedList<>();
        queue.add(new PairTwo(i,j));
        while(!queue.isEmpty())
        {
            int si=queue.peek().si;
            int sj=queue.peek().sj;
            queue.poll();


            for (int k = 0; k < rowNbr.length; k++) {

                int nsi=si+rowNbr[k];
                int nsj=sj+colNbr[k];
                boolean check = isSsafe(nsi,nsj,visited,a);
                if(check==true)
                {
                    queue.add(new PairTwo(si+rowNbr[k],sj+colNbr[k])  );
                    visited[nsi][nsj]=true;
                }
            }
        }
    }

    private boolean isSsafe(int si, int sj, boolean[][] visited, int[][] a) {
        int ROW=a.length;
        int COL =a[0].length;
        if( (si>=0) && (si<ROW)  && (sj>=0) && (sj<COL) && (a[si][sj]==1)&&(visited[si][sj]==false) )
            return true;
        return false;
    }
    /*Path in Directed Graph
Problem Description

Given an directed graph having A nodes labelled from 1 to A containing M edges given by matrix B of size M x 2such that there is a edge directed from node

B[i][0] to node B[i][1].

Find whether a path exists from node 1 to node A.

Return 1 if path exists else return 0.

NOTE:

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

        void addEdge(int v,int w)  {   adj[v].add(w) ;  }

        public int checkReachPath(boolean[] visited, int src, int des) {

            if(src==des)
            {
                return 1;
            }
            visited[src]=true;

            ListIterator<Integer> itr =  adj[src].listIterator();
            while(itr.hasNext())
            {
                int t=itr.next();



                if( visited[t]!=true &&checkReachPath(visited,t,des)==1)
                    return 1;
            }
            return 0;

        }
    }

    public int checkPath(int A, int[][] B) {

        graphData g = new graphData(A+1);
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length; j++) {

                g.addEdge(B[i][0],B[i][1]);
            }
        }


        boolean visited[] = new boolean[A+1];
        Queue<Integer> queue = new LinkedList<>();

        visited[1]=true;
        queue.add(1);
        while(!queue.isEmpty())
        {
            int s=queue.poll();
            ListIterator<Integer> itr =  g.adj[s].listIterator();
            while(itr.hasNext())
            {
                int t=itr.next();
                if(t==A)
                {
                    return 1;
                }
                if(!visited[t])
                {
                    visited[t]=true;
                    queue.add(t);
                }
            }
        }

        return 0;

    }
}
