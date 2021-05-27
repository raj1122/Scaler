package com.company.Apr.dynamicProg.dp5;

import com.company.Apr.dynamicProg.dp1.WaysDecode;

import java.util.ArrayList;
import java.util.Collections;

public class ExecuteDp5 {

    public void ExecuteFn()
    {
        int[] arr1 = {14, 10, 4};

        int[] arr2 = {2,1,3};

        int[] arr3 = {2,5,3};


        String s1[] = {"10", "sale", "jan"};
        String s2[] = {"200", "sale", "EMPTY"};

        ArrayList<String> ls1 = new ArrayList<>();
        ArrayList<String> ls2 = new ArrayList<>();
        Collections.addAll(ls1, s1);
        Collections.addAll(ls2, s2);

//        new TusharBirthday().solve(arr1,arr2,arr3);
        new FlipArray().solve(arr1);



    }
}
