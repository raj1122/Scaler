package com.company.May.Graph.graphMay2;


/*Damaged Roads
Problem Description

You are the Prime Minister of a country and once you went for a world tour.
After 5 years, when you returned to your country, you were shocked to see the condition of the roads between the cities. So, you plan to repair them, but you cannot afford to spend a lot of money.

The country can be represented as a (N+1) x (M+1) grid, where Country(i, j) is a city.

The cost of repairing a road between (i, j) and (i + 1, j) is A[i]. The cost of repairing a road between (i, j) and (i, j + 1) is B[j].

Return the minimum cost of repairing the roads such that all cities can be visited from city indexed (0, 0).

As the cost can be large, return the cost modulo 109+7.



Problem Constraints
1 <= N, M <= 105

1 <= A[i], B[i] <= 103


Input Format
The first argument will be an integer array, A, of size N.
The second argument will be an integer array, B, of size M.



Output Format
Return an integer representing the minimum possible cost.



Example Input
Input 1:

 A = [1, 1, 1]
 B = [1, 1, 2]
Input 2:

 A = [1, 2, 3]
 B = [4, 5, 6]


Example Output
Output 1:

 16
Output 2:

 39


Example Explanation
Explanation 1:

 The minimum cost will be 16 if we repair the roads in the following way:
 Repair the roads from the all cities in row 0 to row 1 i.e. (0, j) to (1, j) (0 <= j <= 3), so that the cost will be 4 (A[0] * 4).
 Repair the roads from the all cities in row 1 to row 2 i.e. (1, j) to (2, j) (0 <= j <= 3), so that the cost will be 4 (A[1] * 4).
 Repair the roads from the all cities in row 2 to row 3 i.e. (2, j) to (3, j) (0 <= j <= 3), so that the cost will be 4 (A[2] * 4).
 Repair the roads (0, 0) to (0, 1), (0, 1) to (0, 2), (0, 2) to (0, 3), so that the cost will be B[0] + B[1] + B[2] = 4.
 Total cost will be 16.

Explanation 2:

 The minimum possible cost will be 39.*/


import java.util.PriorityQueue;

public class DamagedRoads {

    class node implements Comparable<node>
    {
        int x;
        int y;
        int dist;

        public node(int dist, int x, int y ) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(node o) {

            if(this.dist!=o.dist)
                return this.dist-o.dist;
            if(this.x!=o.x)
                return this.x-o.x;
            return this.y-o.y;
        }
    }
    public int solve(int[] A, int[] B) {
        int dirx[]={0,1};
        int diry[]={1,0};
        long MOD=(int)1e9+7;
        int rows=A.length+1;
        int cols=B.length+1;
        boolean visited[][]=new boolean[rows][cols];
        PriorityQueue<node> pq=new PriorityQueue<>();
        pq.add(new node(0,0,0));
        long res=0;

        while(!pq.isEmpty())
        {
            node temp=pq.peek();
            pq.poll();
            if(!visited[temp.x][temp.y])
            {
                visited[temp.x][temp.y]=true;
                res=(res+temp.dist)%MOD;
                for(int i=0;i<2;i++)
                {
                    int tempx=temp.x+dirx[i];
                    int tempy=temp.y+diry[i];
                    if(tempx<rows && tempy<cols && visited[tempx][tempy]==false)
                    {
                        if(i==0) {
                            pq.add(new node(B[temp.y], tempx, tempy));
                        }
                        else {
                            pq.add(new node(A[temp.x],tempx,tempy) );
                        }
                    }
                }
            }
        }

        return (int)res;

    }
}
