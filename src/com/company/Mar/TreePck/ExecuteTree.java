package com.company.Mar.TreePck;

import com.company.Mar.heap;

import java.util.ArrayList;
import java.util.Collections;

public class ExecuteTree {

    private static ArrayList<ArrayList<Integer>> creatDbtArr1() {
        ArrayList<ArrayList<Integer>> dblArr = new ArrayList<>();

        Integer arr[][] = new Integer[][]
                {   {10, 6}
                        ,  {3, 2}
                        ,  {12, 7}
                        ,  {9, 5}
                        ,  {2, 1}
                        ,  {8, 3}
                        ,  {7, 1}
                        ,  {4, 2}
                        ,  {6, 3}
                        ,  {11, 4}
                        ,  {5, 3}
                        ,  {13, 11}
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

    public void executeTree()
    {
        ArrayList<ArrayList<Integer>> dblArr=creatDbtArr1();
        Integer[] arr1 = {-59, -33, 34, 0, 69, 24, -22, 58, 62, -36, 5, 45, -19 };
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);
        new MaxDiffTree().solve(levelTree,dblArr);
    }

}
