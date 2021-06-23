package com.company.May.Graph.graphMay2;

import java.util.ArrayList;
import java.util.Collections;

public class executeGraph2 {

    public void execute(){
        int[] arr1 = {2, 2, 4, 1, 2, 4, 2 };

        int[] arr2 = { 3, 3, 4, 5};

        int[] arr3 = {2,5,3};
        int C[][]=new int[][]
                {{1, 5, 6},
                    {10, 7, 2},
                    {3, 6, 9}
                };


        String s1[] = {"10", "sale", "jan"};
        String s2[] = {"200", "sale", "EMPTY"};

        ArrayList<String> ls1 = new ArrayList<>();
        ArrayList<String> ls2 = new ArrayList<>();
        Collections.addAll(ls1, s1);
        Collections.addAll(ls2, s2);

//        new DamagedRoads().solve(arr1,arr2);
//        new DamagedRoad2().solve(arr1,arr2);
//        new MatrixAbsDiff().solve(3,3,C);
        distributeCandies.solve();
    }
}
