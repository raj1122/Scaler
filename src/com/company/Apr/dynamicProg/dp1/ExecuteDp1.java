package com.company.Apr.dynamicProg.dp1;

import java.util.ArrayList;
import java.util.Collections;

public class ExecuteDp1 {

    public void ExecuteFn()
    {
        Integer[] arr1 = {-3, 0, -5, 0};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);

        String s1[] = {"10", "sale", "jan"};
        String s2[] = {"200", "sale", "EMPTY"};

        ArrayList<String> ls1 = new ArrayList<>();
        ArrayList<String> ls2 = new ArrayList<>();
        Collections.addAll(ls1, s1);
        Collections.addAll(ls2, s2);

        new WaysDecode().numDecodings("123");


        
    }
}
