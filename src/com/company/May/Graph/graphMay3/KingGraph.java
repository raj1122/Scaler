package com.company.May.Graph.graphMay3;


/*King Graph
Kingdom JenaRajya is a well planned kingdom. They have N houses numbered [0,1,..,N-1] in the city and some bidirectional roads connecting the houses. King Jena has decided to meet his people and so he will visit one of the house in the kingdom where others can gather and meet him. King Jena is very kind and do not want anyone to travel far to meet him. So, he has come up with the following criteria to decide the house he will be visiting:

Assuming that the people from other houses will take the shortest possible path to reach the house the king is visiting, King Jena wants to minimize the maximum distance one has to travel to meet him. In other words, he will choose the house where the shortest distance to the farthest house is minimum possible.

Output the house number which King Jena will visit.

Note:

In case there is a tie, he will visit the house with minimum house number.
You can assume that the graph is connected and so everyone will be able to visit.
Constraints: 1 <= N <= 500 1 <= Length of road <= 1000000

Input format: Adjacency matrix representation of the graph. A[i][j] = distance between house i and j. (A[i][i] = 0 and A[i][j] = -1, if house i and house j have no road between them) As the roads are bidirectional, A[i][j] = A[j][i]

Example:

Input:
A = [[0, 6, 8, -1],
     [6, 0, 9, -1],
     [8, 9, 0, 4],
     [-1, -1, 4, 0]]

Output:
2*/

import java.util.Arrays;

public class KingGraph {

    public int solve(int[][] A) {

        int V=A.length;
        int dist[][] = new int[V][V];
        int d[]=new int[V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if(A[i][j]== -1)
                    dist[i][j] =1000000000;
                else
                    dist[i][j] = A[i][j];
            }
        }


        for (int l = 0; l < V; l++) {
            dist[l][l]=0;
        }



        for (int k = 0; k < V; k++)
        {
            for (int i = 0; i < V; i++)
            {
                for (int j = 0; j < V; j++)
                {

                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        d[i] = dist[i][j];
                    }
                }
            }
        }

        int maxDist = -1, ans = -1, temp = 1000000000;
        for(int i=0; i<V; i++){
            maxDist = -1;
            for(int j=0; j<V; j++) {
                maxDist = Math.max(dist[i][j], maxDist);
            }

            if(maxDist < temp){
                temp = maxDist;	ans = i;
            }
        }
        return ans;
    }

}
