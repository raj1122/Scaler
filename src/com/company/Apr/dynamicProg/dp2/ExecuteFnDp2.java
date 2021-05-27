package com.company.Apr.dynamicProg.dp2;

import java.util.*;

public class ExecuteFnDp2 {
    private static ArrayList<ArrayList<Integer>> creatDbtArr1() {
        ArrayList<ArrayList<Integer>> dblArr = new ArrayList<>();

        Integer arr[][] = new Integer[][]
                {
                        {0, 1}
                };

        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> al1 = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                al1.add(arr[i][j]);

            }
            dblArr.add(al1);

        }
        return dblArr;
    }

    public void ececuteFn(){
        ArrayList<ArrayList<Integer>> dblArr = creatDbtArr1();
        new UniqueGrid().uniquePathsWithObstacles(dblArr);
    }
}
