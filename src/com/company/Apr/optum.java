package com.company.Apr;

import java.util.*;

public class optum {
    private static ArrayList<ArrayList<Integer>> creatDbtArr1() {
        ArrayList<ArrayList<Integer>> dblArr = new ArrayList<>();

        Integer arr[][] = new Integer[][]
                {
                        {1, 0}
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

    public void executefn() {
        Integer[] arr1 = {7};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);

        String s1[] = {"10", "sale", "jan"};
        String s2[] = {"200", "sale", "EMPTY"};

        ArrayList<String> ls1 = new ArrayList<>();
        ArrayList<String> ls2 = new ArrayList<>();
        Collections.addAll(ls1, s1);
        Collections.addAll(ls2, s2);
        ArrayList<ArrayList<Integer>> dblArr = creatDbtArr1();
        int[] arr2 = new int[]{7, 5, 2, 7, 2, 7, 4, 7, 5, 2, 4};
        solution(arr2);

    }

    public int solution(int[] A) {
        // write your code in Java SE 8

        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < A.length; i++)
            hm.put(A[i], 0);


        int maxCnt = hm.size();
        int ans = Integer.MAX_VALUE;
        int len = A.length;
        int i, j;
        for (i = 0; i < len; i++) {
            HashMap<Integer, Integer> hm1 = new HashMap<>(hm);
            hm1.put(A[i], 1);
            int currCnt = 1;
            for (j = i + 1; j < len; j++) {
                if (hm1.get(A[j]) != 1) {
                    currCnt++;
                    hm1.put(A[j], 1);
                }

                if (currCnt == maxCnt)
                    break;
            }
            int currLen = j - i + 1;
            if (i == 0)
                currLen = currLen - 1;
            if (currCnt == maxCnt)
                ans = Math.min(ans, currLen);
        }


        return ans;
    }
}
