package com.company.May.Graph.graphMay3;


/*Sheldon and Pair of Cities
Problem Description

Sheldon lives in a country with A cities (numbered from 1 to A) and B bidirectional roads.

Roads are denoted by integer array D, E and F of size M, where D[i] and E[i] denotes the cities and F[i] denotes the distance between the cities.

Now he has many lectures to give in the city and is running short of time, so he asked you C queries, for each query i, find the shortest distance between city G[i] and H[i].

If the two cities are not connected then the distance between them is assumed to be -1.



Problem Constraints
1 <= A <= 200

1 <= B <= 200000

1 <= C <= 100000

1 <= F[i] <= 1000000

1 <= D[i], E[i], G[i], H[i] <= A



Input Format
First argument is an integer A.
Seocnd argument is an integer B.
Third argument is an integer C.
Fourth argument is an integer array D.
Fifth argument is an integer array E.
Sixth argument is an integer array F.
Seventh argument is an integer array G.
Eight argument is an integer array H.



Output Format
Return an integer array of size C, for each query denoting the shortest distance between the given two vertices.
If the two vertices are not connected then output -1.



Example Input
Input 1:

 A = 4
 B = 6
 C = 2
 D = [1, 2, 3, 2, 4, 3]
 E = [2, 3, 4, 4, 1, 1]
 F = [4, 1, 1, 1, 1, 1]
 G = [1, 1]
 H = [2, 3]
Input 2:

 A = 3
 B = 3
 C = 2
 D = [1, 2, 1]
 E = [2, 3, 3]
 F = [3, 1, 1]
 G = [2, 1]
 H = [3, 2]


Example Output
Output 1:

 [2, 1]
Output 2:

 [1, 2]


Example Explanation
Explanation 1:

 Distance between (1,2) will 2 if we take path 1->4->2.
 Distance between (1,3) will 1 if we take path 1->3.
Explanation 2:

 Distance between (2,3) will 1 if we take path 1->3.
 Distance between (1,2) will 2 if we take path 1->3->2.*/

import java.util.Arrays;

public class SheldonCity {

    public int[] solve(int A, int B, int C, int[] D, int[] E, int[] F, int[] G, int[] H) {
        int dist[][]=new int[A][A];


        //initialize to MAx/2
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE/2);


        //for 00,11,22,33  dist is 0
        for (int i = 0; i < A; i++) {
            dist[i][i]=0;

        }

        //using input to create 2d
        for (int i = 0; i < B; i++) {
            int x=D[i];
            int y=E[i];
            int d=F[i];
            dist[x-1][y-1]= Math.min(d,dist[x-1][y-1]);
        }



        //using Floyd Algo to cal distance
        int i, j, k;
        for (k = 0; k < A; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < A; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < A; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }


        //using query to get the answer
        int ans[]=new int[C];
        int a1=0;
        for (int l = 0; l < C; l++) {
            int x=G[l];
            int y=H[l];

            ans[a1]=dist[x-1][y-1];
            a1++;
        }
        return ans;
    }
}
