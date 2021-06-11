package com.company.Apr.SegmentTreePck;

import java.util.ArrayList;

public class SegmentExecuteFn {

    private static ArrayList<ArrayList<Integer>> creatDbtArr1() {

        ArrayList<ArrayList<Integer>> dblArr=new ArrayList<>();
        Integer arr[][] = new Integer[][]
                {
                        {0, 3, 5},
                        {0, 3, 4},
                        {1, 2, -1},
                        {0, 1, 5},
                        {1, 2, -1},
                        {0, 1, 4}
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

    public void executeFn(){
        ArrayList<ArrayList<Integer>> dblArr= creatDbtArr1();
        new Power3().solve("10010",dblArr);
//        new SegmentTreeCheck().startProc();

    }
}
