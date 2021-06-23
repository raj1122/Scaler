package com.company.May.Graph.graphMay3;

import com.company.May.Graph.graphMay2.GymTrainer;

public class ExecuteGraph3 {
    public void executeFn()
    {
        int B[][]=new int[][]
                {
                        {1, 5},
                        {4, 6},
                        {18, 3},
                        {4, 5},
                        {15, 9},
                        {15, 4}
                };
        int C[][]=new int[][]
                {
                        {1, 2},
                        {2, 3} ,
                       {5, 6},
                        {5, 7}
                };
        int A[]=new int[]{1, 6, 7, 2, 9, 4, 5};
        int D[]=new int[]{ 1, 2, 3, 2, 4, 3 };
        int E[]=new int[]{ 2, 3, 4, 4, 1, 1 };
        int F[]=new int[]{ 4, 1, 1, 1, 1, 1 };
        int G[]=new int[]{ 1, 1 };
        int H[]=new int[]{ 2, 3 };
//        new EdgeInMst().solve(3,B);
//        new GoodGraph().solve(A);
//        new SheldonCity().solve(4,6,2,D,E,F,G,H);
//        new GymTrainer().solve(20,B,C);
        new Batches().solve(7,A,C,12);
    }
}
