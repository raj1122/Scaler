package com.company.Apr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class graph {
    int src;
    int des;
    LinkedList<graph> ll[];

    public graph(int src, int des, int size) {
        this.src = src;
        this.des = des;
        this.ll = new LinkedList[size + 1];
        //creating linked list for all element
        for (int i = 0; i <= size; i++) {
            ll[i] = new LinkedList<>();
        }
    }

    public graph() {

    }

    //start of the program
    public void executeFn() {
        Integer[] arr1 = {4, 4, 1, 4, 13, 8, 8, 8, 0, 8, 14, 9, 15, 11, -1, 10, 15, 22, 22, 22, 22, 22, 21};

        int size = arr1.length;
        graph g = new graph(0, 0, size);

        for (int i = 0; i < size; i++) {
            int src = arr1[i];
            if (src == -1)
                continue;
            int des = arr1[src];
            g.insert(src, des, size);
        }

        for (int i = 0; i <= size; i++) {
            g.checkloop(i, size);

        }

    }

    private void insert(int src, int des, int size) {
        //adding source and destination
        LinkedList<graph> g = ll[src];
        g.add(new graph(src, des, size));

    }

    private void checkloop(int src, int size) {

        LinkedList<graph> g = ll[src];
        //if no elemenet return
        if (g.size() == 0)
            return;

        graph s1 = g.get(0);
        int slow = s1.src;

        //maintaining start so that we can check the loop
        graph start = s1;
        if (s1.des == -1)
            return;
        int v[] = new int[size + 1];
        for (int i = 0; i <= size; i++)
            v[i] = 0;
        boolean found = false;
        //iterating over vertex
        while (s1.des != -1) {
            v[s1.src] = 1;
            LinkedList<graph> g1 = ll[s1.des];
            s1 = g1.get(0);
            //if vertex has already been traversed
            if (v[s1.src] == 1) {
                found = true;
                break;
            }
        }
        long ans = 0;
        // if loop found and start and curr is same then loop sum
        if (found == true && start.src == s1.src) {
            ans = +start.src;
            graph temp = ll[start.des].get(0);
            while (temp.src != start.src) {
                ans += temp.src;
                temp = ll[temp.des].get(0);
            }
            System.out.println(ans);
        }
    }


}
