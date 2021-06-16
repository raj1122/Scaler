package com.company.May.Graph.graphMay3;

public class ExecuteGraph3 {
    public void executeFn()
    {
        int B[][]=new int[][]
                {
                        {1, 2, 468},
                        {2, 3, 335},
                        {3, 1, 501},
                        {2, 4, 170},
                        {2, 5, 725},
                        {2, 7, 479},
                        {4, 6, 359},
                        {5, 6, 963}
                };
        int A[]=new int[]{ 1,1,1,1};
//        new EdgeInMst().solve(3,B);
        new GoodGraph().solve(A);
    }
}
