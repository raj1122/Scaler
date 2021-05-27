package com.company.Feb;

import java.util.*;

public class backtrack08 {

    /*Number of Squareful Arrays
Problem Description

Given an array of integers A, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.

Find and return the number of permutations of A that are squareful. Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].*/

    public int SquarefulArrays(ArrayList<Integer> A) {

        if (A.size() < 2)
            return 0;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        HashSet<ArrayList<Integer>> hs = new HashSet<>();
        SquarefulArrays1(A, res, hs, 0);

        return res.size();

    }

    private void SquarefulArrays1(ArrayList<Integer> a, ArrayList<ArrayList<Integer>> res, HashSet<ArrayList<Integer>> hs, int idx) {

        if ((idx == a.size()) && (!hs.contains(a))) {
            res.add(new ArrayList<>(a));
            hs.add(a);
        }

        for (int i = idx; i < a.size(); i++) {

            int t = 0;
            if (idx > 0) {
                t = a.get(idx - 1);
            }
            int t1 = a.get(i);
            if (idx == 0 || isPerfectSquare(t + t1)) {

                swap(a, idx, i);
                SquarefulArrays1(a, res, hs, idx + 1);
                swap(a, idx, i);
            }
        }
    }

    public void swap(ArrayList<Integer> a, int i, int j) {
        int temp = a.get(i);
        int t1 = a.get(j);
        a.set(i, t1);
        a.set(j, temp);
    }

    public boolean isPerfectSquare(int x) {
        if (x == 0 || x == 1)
            return true;
        if (x >= 2) {
            int sr = (int) Math.sqrt(x);
            return ((sr * sr) == x);
        }
        return false;
    }

    /*Subsets II
Problem Description

Given a collection of integers denoted by array A of size N that might contain duplicates, return all possible subsets.

NOTE:

Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
The subsets must be sorted lexicographically.*/

    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> A) {

        Collections.sort(A);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Stack<Integer> ans = new Stack<>();
        HashSet<ArrayList<Integer>> hs = new HashSet<>();
        subsetsWithDup1(A, ans, res, hs, 0);
        return res;
    }

    private void subsetsWithDup1(ArrayList<Integer> a, Stack<Integer> ans, ArrayList<ArrayList<Integer>> res, HashSet<ArrayList<Integer>> hs, int i) {

        if (!hs.contains(ans)) {
            res.add(new ArrayList<>(ans));
            hs.add(new ArrayList<>(ans));

        }

        for (int j = i; j < a.size(); j++) {
            int t = a.get(j);
            //checking duplicate with index and element as index willlbe unique
            ans.push(a.get(j));

            subsetsWithDup1(a, ans, res, hs, j + 1);
            ans.pop();
        }
    }


    /*Combinations
Problem Description

Given two integers A and B, return all possible combinations of B numbers out of 1 2 3 ... A .

Make sure the combinations are sorted.

To elaborate,

Within every entry, elements should be sorted. [1, 4] is a valid entry while [4, 1] is not.
Entries should be sorted within themselves.
WARNING: DO NOT USE LIBRARY FUNCTION FOR GENERATING COMBINATIONS.*/
    public ArrayList<ArrayList<Integer>> combine(int A, int B) {

        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Stack<Integer> ans = new Stack<>();
        combine1(A, B, ans, res, 1);
        return res;
    }

    private void combine1(int a, int b, Stack<Integer> ans, ArrayList<ArrayList<Integer>> res, int i) {

        if (ans.size() == b) {
            res.add(new ArrayList<>(ans));
            return;
        }

        for (int j = i; j <= a; j++) {
            ans.push(j);
            combine1(a, b, ans, res, j + 1);
            ans.pop();

        }
    }



    /*Subset
Problem Description

Given a set of distinct integers, A, return all possible subsets.

NOTE:

Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
Also, the subsets should be sorted in ascending ( lexicographic ) order.
The list is not necessarily sorted.*/

    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {

        HashSet<ArrayList<Integer>> hs = new HashSet<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Stack<Integer> ans = new Stack<>();
        Collections.sort(A);
        HashMap<Integer, Integer> hm = new HashMap<>();
        subsets1(A, res, ans, hm, hs, 0);
        return res;
    }

    private void subsets1(ArrayList<Integer> a, ArrayList<ArrayList<Integer>> res, Stack<Integer> ans, HashMap<Integer, Integer> hm, HashSet<ArrayList<Integer>> hs, int i) {

        res.add(new ArrayList<>(ans));
        for (int j = i; j < a.size(); j++) {
            int t = a.get(j);
            //checking duplicate with index and element as index willlbe unique
            if (hm.containsKey(j) && hm.containsValue(t))
                continue;
            if (!ans.isEmpty() && ans.peek().intValue() > a.get(j).intValue())
                continue;
            ans.push(a.get(j));
            hm.put(j, t);
            subsets1(a, res, ans, hm, hs, j + 1);
            ans.pop();
            hm.remove(j, t);
        }
    }

    /*Permutations
Problem Description

Given an integer array A of size N denoting collection of numbers , return all possible permutations.

NOTE:

No two entries in the permutation sequence should be the same.
For the purpose of this problem, assume that all the numbers in the collection are unique.
WARNING: DO NOT USE LIBRARY FUNCTION FOR GENERATING PERMUTATIONS. Example : next_permutations in C++ / itertools.permutations in python.
If you do, we will disqualify your submission retroactively and give you penalty points.*/
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {

        HashMap<Integer, Integer> hm = new HashMap<>();
        HashSet<ArrayList<Integer>> hs = new HashSet<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Stack<Integer> ans = new Stack<>();
        permute1(A, res, ans, hm, hs);

        return res;
    }


    private void permute1(ArrayList<Integer> a, ArrayList<ArrayList<Integer>> res, Stack<Integer> ans, HashMap<Integer, Integer> hm, HashSet<ArrayList<Integer>> hs) {

        if (ans.size() == a.size()) {
            //checking whether arraylist alreay present then ignore
            if (!hs.contains(ans.clone())) {
                res.add(new ArrayList<>(ans));
                hs.add(new ArrayList<>(ans));
            }
            return;
        }

        for (int j = 0; j < a.size(); j++) {
            int t = a.get(j);
            //checking duplicate with index and element as index willlbe unique
            if (hm.containsKey(j) && hm.containsValue(t))
                continue;
            ans.push(a.get(j));
            hm.put(j, t);
            permute1(a, res, ans, hm, hs);
            ans.pop();
            hm.remove(j, t);
        }
    }



    /*SIXLETS
Problem Description

Given a array of integers A of size N and an integer B.

Return number of non-empty subsequences of A of size B having sum <= 1000.*/

    public int SIXLETS(ArrayList<Integer> A, int B) {


        return SIXLETS1(A, B, 0, 0, 0);
    }

    public int SIXLETS1(ArrayList<Integer> A, int B, int idx, int sum, int size) {
        if (size == B)
            return 1;

        int ans = 0;
        for (int i = idx; i < A.size(); i++) {
            if ((sum + A.get(i)) > 1000)
                continue;
            ans += SIXLETS1(A, B, i + 1, sum + A.get(i), size + 1);
        }


        return ans;
    }

}
