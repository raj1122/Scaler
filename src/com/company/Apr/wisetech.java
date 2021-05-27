package com.company.Apr;

import java.util.*;

public class wisetech {

    public static int finalInstances(int instances, List<Integer> averageUtil) {
        long ins = instances;
        int i = 0;
        int size = averageUtil.size();
        long max = (long) Math.pow(10, 8) * 2;
        while (i < size) {
            if (averageUtil.get(i) >= 25 && averageUtil.get(i) <= 60) {
                i++;
                continue;
            } else if (averageUtil.get(i) < 25) {
                if (ins != 1) {
                    long res1 = ins / 2;
                    ins = (int) Math.ceil((double) ins / 2);
                    if ((i + 11) >= size)
                        break;
                    else
                        i = i + 11;
                } else
                    i++;
            } else if (averageUtil.get(i) > 60) {

                if ((ins * 2) <= max) {
                    ins = ins * 2;
                    if ((i + 11) >= size)
                        break;
                    else
                        i = i + 11;
                } else
                    i++;
            }

        }
        return (int) ins;


    }

    public static void executefn() {
        Integer[] arr1 = {4, 30, 5, 4, 8, 19, 89, 7, 6, 7, 8};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);

        String s1[] = {"10", "sale", "jan"};
        String s2[] = {"200", "sale", "EMPTY"};

        ArrayList<String> ls1 = new ArrayList<>();
        ArrayList<String> ls2 = new ArrayList<>();
        Collections.addAll(ls1, s1);
        Collections.addAll(ls2, s2);

        finalInstances(5, levelTree);
    }
}
