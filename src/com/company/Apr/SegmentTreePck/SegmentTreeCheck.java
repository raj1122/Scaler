package com.company.Apr.SegmentTreePck;

import java.util.*;


/*Segment Tree?
Problem Description

Given an array A of size N and Q queries. Perform following queries:

1 V 0 append V in the back of array.
2 X V set A[X] = V.
3 X 0 delete A[X]. Note: All element at back of X move forward to occupy void.
4 X Y find sum in range [X, Y].
NOTE: For the query of type 4 X Y, output the sum % 109 + 7.*/

public class SegmentTreeCheck {

    private static ArrayList<ArrayList<Integer>> creatDbtArr1() {

        ArrayList<ArrayList<Integer>> dblArr=new ArrayList<>();
        Integer arr[][] = new Integer[][]
                {
                        {2, 2, 4},
                        {3, 3, 0},
                        {3, 3, 0},
                        {1, 6, 0},
                        {4, 1, 3},
                        {4, 1, 2}
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

    public void startProc()
    {
        Integer[] a = {2, 5, 8, 10 };
        ArrayList<Integer> a1 = new ArrayList<>();
        Collections.addAll(a1, a);
        ArrayList<ArrayList<Integer>> dblArr= creatDbtArr1();
        solve(a1,dblArr);


    }

    static int N = (int) 2e5 + 10;

    // To store the sum of
    // the array elements
    static int[] bit = new int[N];

    // To keep track of how many type 3
    // queries have been performed before
    // a particular index
    static int[] idx = new int[N];

    static void update(int idx, int val, int[] bitt) {
        while (idx < N) {
            bitt[idx] += val;
            idx += idx & (-idx);
        }
    }

    static int sum(int idx, int[] bitt) {

        int res = 0;
        while (idx > 0) {
            res += bitt[idx];
            idx -= idx & (-idx);
        }

        return res;
    }


    static Vector<Integer> peformQueries(Vector<Integer> A,ArrayList<ArrayList<Integer>> B) {

        // For 1 bases indexing
        // insert 0 in the vector
        A.insertElementAt(0, 0);

        // Updated size of the vector
        int n = (int) A.size();

        // Updating the bit tree
        for (int i = 1; i < n; ++i) {
            update(i, A.elementAt(i), bit);
        }

        // Vector to store the answers
        // of range sum
        Vector<Integer> ans = new Vector<>();

        // Iterating in the query
        // vector
        for (ArrayList<Integer> i : B) {

            int type = i.get(0);
            int x = i.get(1), v = i.get(2);

            // If the query is of
            // type 1
            if (type == 1) {

                // Updating the tree
                // with x in the last
                update(n, x, bit);

                // Pushing back the value
                // in the original vector
                A.add(x);

                // Incrementing the size
                // of the vector by 1
                n++;
            }

            // If the query is of type 2
            else if (type == 2) {

                // Getting the updated index
                // in case of any query of
                // type 3 occured before it
                int id = x + sum(x, idx);

                // Making the effect to that
                // value to 0 by subtracting
                // same value from the tree
                update(id, -A.elementAt(id), bit);

                // Updating the A[id] to v
                A.set(id, v);

                // Now update the
                // bit by v at x
                update(id, v, bit);
            }

            // If the query is of type 3
            else if (type == 3) {

                // Get the current index
                int id = x + sum(x, idx);

                // Remove the effect of that
                // index from the bit tree
                update(id, -A.elementAt(id), bit);

                // Update the idx tree
                // because one element has
                // been deleted
                update(x, 1, idx);

                // Update the idx val to 0
                A.set(id, 0);
            }

            // If the query is of type 4
            else {

                // Get the updated range
                int xx = x + sum(x, idx);
                int vv = v + sum(v, idx);

                // Push_back the value
                ans.add(sum(vv, bit) - sum(xx - 1, bit));
            }
        }

        return ans;
    }

    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {

        Vector<Integer> v1=new Vector<>(A);
        Vector<Integer> v = peformQueries(v1,B);
        ArrayList<Integer> ans =new ArrayList<>(v);
        return ans;
    }
}
