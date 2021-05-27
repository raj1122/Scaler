package com.company;

import com.company.Apr.*;
import com.company.Apr.dynamicProg.ExecuteDpMain;
import com.company.Apr.dynamicProg.dp1.ExecuteDp1;
import com.company.Apr.dynamicProg.dp2.ExecuteFnDp2;
import com.company.Feb.*;
import com.company.Mar.*;
import com.company.May.LinkedList;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here



        Integer[] arr1 = {4,5,6};
        Integer[] arr2 = {1, 2, 30};
        int[] arrs = new int[]{10, 12};
        Integer[] arr3 = {10, 12};
        Integer[] arr4 = {2, 3};
        ArrayList<ArrayList<Integer>> dblArr = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> dblArr1 = new ArrayList<ArrayList<Integer>>();
        creatDbtArr(dblArr);
        creatDbtArr1(dblArr1);

        Character arrChr[] = {'S', 'c', 'a', 'l', 'e', 'r', 'A', 'c', 'a', 'd', 'e', 'm', 'y'};
        String s1[] = {"4", "13", "5", "/", "+"};

        ArrayList<Integer> al1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();
        ArrayList<Integer> a3 = new ArrayList<>();
        ArrayList<Integer> a4 = new ArrayList<>();
        ArrayList<Integer> E = new ArrayList<>();
        ArrayList<String> al2 = new ArrayList<>();

        ArrayList<Integer> num1 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> dblArray = new ArrayList<>();

        Collections.addAll(al1, arr1);
        Collections.addAll(a2, arr2);
        Collections.addAll(a3, arr3);
        Collections.addAll(a4, arr4);
        dblArray.add(a2);
        dblArray.add(a3);
        dblArray.add(a4);


        int negPos[]=new int[]{1,2,3};

        Collections.addAll(al2, s1);

        HashSet<ArrayList<Integer>> hs=new HashSet<>();
        ArrayList<Integer> al=new ArrayList<>();
        new Altimetrik().fn(negPos,negPos.length,5,hs,al);
//        new Altimetrik().permutate(al1);
        String s = "qxkpvo  f   w vdg t wqxy ln mbqmtwwbaegx   mskgtlenfnipsl bddjk znhksoewu zwh bd fqecoskmo";


        Collections.addAll(num1, arr1);

//        s4.SmallestBase("4681");




        String sudoko[]=new String[]{ "53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79" };



//        FebClass();
//        backtrack08fn(al1,arrs,dblArr,a2,al2,a3);
//        MarchFuncCall(al1,arrs,dblArr,a2,al2,a3,al2);
//        AprfuncCall();
        MayFuncCall();
    }

    private static void MayFuncCall() {
//        new stringAlgo().solve("1001","0011");
        new ExecuteDpMain().ExecuteDpMainFn();
//        new dp4().executefn();
//        new amazon().executeFn();
//        new graph3().executeFn();
//        new graph2().executeFn();
//        new circleQueue().executeFn();
//        new graph1().executeFn();
    }

    private static void FebClass() {
        backtrack09 b9 = new backtrack09();
        b9.executeFn();
    }

    private static void AprfuncCall() {

        new ExecuteDp1().ExecuteFn();

//        new dp1().executefn();
//        d2.executefn();
//        d3.executefn();
//        d4.executefn();

//        new dp5().executefn();

//        g.executeFn();
//            new SegmentTree().executeFn();
//        new SegmentTree2().executeFn();
//        new SegmentExecuteFn().executeFn();
//        new greedyalgo().executeFn();
    }

    private static void MarchFuncCall(ArrayList<Integer> al1, int[] arrs, ArrayList<ArrayList<Integer>> dblArr, ArrayList<Integer> a2, ArrayList<String> al2, ArrayList<Integer> a3, ArrayList<String> strings) {
        Stack1 st1 = new Stack1();

        greedyalgo gred = new greedyalgo();
//        gred.executeFn();
        modular mod = new modular();
        Queue8 q8 = new Queue8();
        LinkedList10 ll = new LinkedList10();
        tree17 tr = new tree17();
        LCA lca = new LCA();
        triee tri = new triee();
//        tri.executeF();
        heap hp = new heap();
//        hp.executefn();
        Heap2 hp2=new Heap2();
        hp2.executefn();
//        lca.executeFn();
//        ll.startFunction();
//            st1.executeFn();
//        mod.executeFn(al1,a2);
//        q8.execueTfn();
//        tr.executeFn(al1,a2);

    }

    private static void backtrack08fn(ArrayList<Integer> al1, int[] arr1, ArrayList<ArrayList<Integer>> dblArr, ArrayList<Integer> al2, ArrayList<String> strings, ArrayList<Integer> a3) {
        backtrack08 b8 = new backtrack08();
        backtrack09 b9 = new backtrack09();
        sort13 s3 = new sort13();
        hashing1 hash = new hashing1();
//        hash.executeFn(al1,al2);
        stringAlgo sl = new stringAlgo();
        sl.executeFn();

        twopointer tp = new twopointer();
//        tp.executeFn(al1,al2);


        ArrayList<ArrayList<Character>> a = new ArrayList<ArrayList<Character>>();//
//        b9.InvalidParentheses("))())()))(()((");
        ArrayList<Character> a1 = new ArrayList<>();
        String s[][] = new String[][]
                {
                        {"53..7...."},
                        {"6..195..."},
                        {".98....6."},
                        {"8...6...3"},
                        {"4..8.3..1"},
                        {"7...2...6"},
                        {".6....28."},
                        {"...419..5"},
                        {"....8..79"}
                };

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                a1 = new ArrayList<>();
                String temp = s[i][j];
                for (int k = 0; k < temp.length(); k++) {
//                    System.out.println(temp.charAt(k));
                    a1.add(temp.charAt(k));
                }
                a.add(new ArrayList<Character>(a1));
            }
        }
        new backtrack09().solveSudoku(a);

    }

    private static void creatDbtArr(ArrayList<ArrayList<Integer>> dblArr) {

        Integer arr[][] = new Integer[][]
                {
                        {1, 1, 1},
                        {1, 1, 1},
                        {1, 1, 1}
                };

        for (int i = 0; i < arr.length; i++) {

            ArrayList<Integer> al1 = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                al1.add(arr[i][j]);

            }
            dblArr.add(al1);

        }
    }



    private static void creatDbtArr1(ArrayList<ArrayList<Integer>> dblArr) {

        Integer arr[][] = new Integer[][]
                {
                        {1, 1, 1},
                        {1, 1, 1},
                        {1, 1, 1}
                };

        for (int i = 0; i < arr.length; i++) {

            ArrayList<Integer> al1 = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                al1.add(arr[i][j]);

            }
            dblArr.add(al1);

        }
    }


    /*
    * Square granites
Problem Description

A city is of rectangular shape with the size A * B meters. On the occasion of the city's anniversary, a decision was taken to pave the city with square granite flagstones. Each flagstone is of the size C * C. What is the least number of flagstones needed to pave the city?

NOTE: It's allowed to cover the surface larger than the city, but the city has to be covered. It's not allowed to break the flagstones. The sides of flagstones should be parallel to the sides of the city.*/

    public int Squaregranites(int A, int B, int C) {
        int ans = 0;
        Double l = new Double(A);
        Double b = new Double(B);
        Double t = new Double(C);
        ans = ((int) Math.ceil(l / t)) * ((int) Math.ceil(b / t));
        return ans;

    }

/*
* Pairs with Given Difference
Problem Description

Given an one-dimensional integer array A of size N and an integer B.

Count all distinct pairs with difference equal to B.

Here a pair is defined as an integer pair (x, y), where x and y are both numbers in the array and their absolute difference is B.
* */

    public int pairDiff(ArrayList<Integer> A, int B) {
        int cnt = 0;


        Collections.sort(A);
        int size = A.size();
        int t1;
        Set<Integer> hs = new HashSet<>();
        for (int i = 0; i < size; i++) {
            int t = A.get(i);

            while (i < size - 1 && t == A.get(i + 1)) {
                i++;
            }
            if (hs.contains(t + B)) {
                cnt++;
            }
            if (hs.contains(t - B)) {
                cnt++;
            }
            hs.add(t);
        }
        if (hs.size() == 1) {
            return 1;
        }
        return cnt;
    }
    /*
    Find pairs in array whose sum is already present
Given an array A of N distinct and positive elements, the task is to find number of unordered pairs whose sum already exists in given array.*/

    public int sumPair(ArrayList<Integer> A) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int cnt = 0;
        int size = A.size() - 1;
        for (int i = 0; i < A.size(); i++) {
            hm.put(A.get(i), i);
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int sum = A.get(i) + A.get(j);
                if (hm.containsKey(sum))
                    cnt++;

            }

        }
        return cnt;

    }


    /*
    * Sum of adjacent elements is always even
Given an array of integers A, find and return the minimum elements to be removed such that in the resulting array the sum of any two adjacent values is even.

*/
    public int adjSum(ArrayList<Integer> A) {
        int odd = 0;
        int even = 0;
        for (int i = 0; i < A.size(); i++) {
            if ((A.get(i) % 2) == 0)
                even++;
            else
                odd++;


        }
        return Math.min(odd, even);
    }

    /**
     * Arithmetic Progression?
     * Problem Description
     * <p>
     * Given an integer array A of size N. Return 1 if the array can be rearranged to form an arithmetic progression, otherwise, return 0.
     * <p>
     * A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.
     */
    public int APSum(ArrayList<Integer> A) {
        HashMap<Integer, Integer> hm = new HashMap<>();

        int min = Integer.MAX_VALUE;
        int smin = Integer.MAX_VALUE;
        for (int i = 0; i < A.size(); i++) {
            if (hm.containsKey(A.get(i)))
                return 0;

            if (A.get(i) < min) {
                smin = min;
                min = A.get(i);

            } else if (A.get(i) < smin && A.get(i) != min) {
                smin = A.get(i);
            }
            hm.put(A.get(i), 1);
        }

        int d = smin - min;

        int cnt = 0;
        for (int i = 0; i < A.size(); i++) {
            int t = A.get(i) + d;
            if (hm.containsKey(t))
                continue;

            if (cnt == 1)
                return 0;
            cnt++;

        }

        return 1;
    }

    /*
    * Array with consecutive elements
Problem Description

Given an array of positive integers A, check and return whether the array elements are consecutive or not.
NOTE: Try this with O(1) extra space.
* */

    public int consecutiveElem(ArrayList<Integer> A) {
        int res = 0;
        Collections.sort(A);

        for (int i = 0; i < A.size() - 1; i++) {

            int t = A.get(i + 1) - 1;
            int t1 = A.get(i);
            if (t1 != t)
                return 0;
        }
        return 1;


    }

    /*
    Given a set of distinct integers, A, return all possible subsets.

NOTE:

Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
Also, the subsets should be sorted in ascending ( lexicographic ) order.
The list is not necessarily sorted.
*/
    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> t1 = new ArrayList<>();
        Collections.sort(A);
        int num = 0;
        subsetsRec(res, A, -1, t1, "");
        return res;
    }

    private void subsetsRec(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> A, int index, ArrayList<Integer> t1, String curr) {
        int size = A.size();

        if (index == size) {
            return;
        }


        if (t1.size() == 0) {
            res.add(new ArrayList<>());
            t1.clear();
        } else {
            ArrayList<Integer> t2 = (ArrayList<Integer>) t1.clone();
            if (!res.contains(t2))
                res.add(t2);
        }

        for (int i = index + 1; i < size; i++) {
            curr += A.get(i);
            t1.add(A.get(i));
            subsetsRec(res, A, i, t1, curr);
            t1.remove(A.get(i));
            curr = curr.substring(0, curr.length() - 1);
        }
    }

    /*
    * Subsets II
        Problem Description

        Given a collection of integers denoted by array A of size N that might contain duplicates, return all possible subsets.

        NOTE:

        Elements in a subset must be in non-descending order.
        The solution set must not contain duplicate subsets.
        The subsets must be sorted lexicographically.*/
    public int solve(int A, int B) {

        return KthRec(A, B);

    }

    public int KthRec(int A, int B) {
        if ((A == 1) || (B == 1)) {
            return 0;

        }
        int mid = (int) (Math.pow(2, A - 1) / 2);
        if (B <= mid) {
            return KthRec(A - 1, B);
        } else {
            return KthRec(A - 1, B - mid) == 1 ? 0 : 1;


        }
    }

    public void printRec(int A) {
        if (A < 1) {
            return;

        }

        System.out.println(A + " ");
        printRec(A--);


    }

    public int solveRec(int A) {
        if ((A == 0) || (A == 1))
            return 1;
        else if (A == 2) {
            return A;
        }

        return (solveRec(A - 1) + solveRec(A - 2) + solveRec(A - 3) + A);
    }

    public ArrayList<String> letterCombinations(String A) {
        //A=23
        ArrayList<String> res = new ArrayList<>();
        String mobile[] = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        lettRec(res, mobile, A, 0, "");

        return res;
    }

    public void lettRec(ArrayList<String> res, String mobile[], String A, int idx, String curr) {

        if (idx == A.length()) {
            res.add(curr);
            return;
        }

        String ltr = mobile[Integer.parseInt(A.charAt(idx) + "")];
        for (int i = 0; i < ltr.length(); i++) {
            lettRec(res, mobile, A, idx + 1, curr + ltr.charAt(i));
        }
    }


    public int sumDigitRec(int A) {
        if (A == 0)
            return A;
        return ((A % 10) + sumDigitRec(A / 10));
    }

    public ArrayList<Character> to_upper(ArrayList<Character> A) {

        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) >= 65 && A.get(i) <= 90) {
                char c = (char) (A.get(i) + 32);
                A.set(i, c);
            }

        }
        return A;
    }

    public String rotateString(String A, int B) {

        String res = "";
        if (B > A.length())
            B = B % A.length();
        int start = A.length() - B;
        A = A.substring(start) + A.substring(0, start);

        return A;
    }

    public String getLargest(String A) {
        String res = "";
        A = A.toLowerCase();
        String arr[] = A.split("_");

        int t1;
        StringBuilder S = new StringBuilder(arr[0]);
        String P = arr[1];
        int t[] = new int[26];
        Arrays.fill(t, 0);
        for (int i = 0; i < P.length(); i++) {
            t1 = arr[1].charAt(i);
            t[t1 - 97] = t[t1 - 97] + 1;
        }


        int S1 = S.length();
        int incr = 25;
        String temp = "";
        for (int i = 0; i < S1; i++) {
            while (incr >= 0 && t[incr] == 0) {
                incr--;
            }
            if (incr < 0) {
                return S.toString();

            }
            char c = (char) (incr + 97);


            if (c > S.charAt(i)) {
                S.setCharAt(i, c);
                if (t[incr] == 1)
                    t[incr] = 0;
                else
                    t[incr] = t[incr] - 1;
            }
        }

        return S.toString();
    }


    public int amazeStr(String A) {
        ArrayList<Character> al = new ArrayList<>();
        Character vow[] = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        Collections.addAll(al, vow);
        int res = 0;
        char c;
        int n = A.length();
        for (int i = 0; i < A.length(); i++) {

            c = A.charAt(i);
            if (al.contains(c)) {
                res = res + (n - i);
            }

        }
        return (res % 10003);
    }

    public int occ(String A) {
        int res = 0;
        for (int i = 0; i < A.length(); i++) {
            if ((i + 2) < A.length()) {
                if (A.substring(i, i + 3).equals("bob")) {
                    res++;
                }
            } else {
                break;
            }

        }
        return res;
    }

    public String oprStr(String A) {
        StringBuilder res = new StringBuilder(A);
        int t, idx = 0;
        int i;
        ArrayList<Character> al = new ArrayList<>();
        Character vow[] = {'a', 'e', 'i', 'o', 'u'};
        Collections.addAll(al, vow);
        i = 0;
        while (i < res.length()) {
            t = res.charAt(i);
            if (t >= 65 && t <= 90) {
                res.deleteCharAt(i);
            } else if (al.contains(res.charAt(i))) {

                res.setCharAt(i, '#');
                i++;
            } else {
                i++;
            }
        }
        res = new StringBuilder(res.toString() + res.toString());

        return res.toString();

    }

    public int distinctStr(String A, int B) {
        int res = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        int min = Integer.MAX_VALUE;
        Character minChr = null;
        Character maxchr = null;
        int max = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder(A);

        if (B == 0) {
            A.length();
        }
        for (int i = 0; i < A.length(); i++) {

            if (hm.containsKey(A.charAt(i))) {
                hm.put(A.charAt(i), hm.get(A.charAt(i)) + 1);
            } else {
                hm.put(A.charAt(i), 1);
            }
        }
        while (B > 0) {

            min = Integer.MAX_VALUE;
            for (Map.Entry<Character, Integer> al : hm.entrySet()) {
                if (al.getValue() < min) {

                    minChr = al.getKey();
                    min = al.getValue();
                }

                if (al.getValue() > max) {
                    max = al.getValue();
                    maxchr = al.getKey();
                }
            }
            if (minChr == maxchr) {
                return 1;
            }


            if (min != Integer.MAX_VALUE && (B - min) > 0) {

                hm.remove(minChr);
                hm.put(maxchr, hm.get(maxchr) + min);
                B = B - min;

            } else if (min != Integer.MAX_VALUE && (B - min) < 0) {
                return hm.size();
            } else {
                hm.remove(minChr);
                hm.put(maxchr, hm.get(maxchr) + min);
                return hm.size();
            }

        }
        return res;
    }

    public String clospalind(String A) {
        int k = 0, l = 0, h = A.length() - 1;
        while (l < h) {
            if (A.charAt(l) == A.charAt(h)) {

            } else {
                if (k == 1) {
                    return "NO";
                }
                k++;
            }
            l++;
            h--;
        }
        if (k == 0) {
            if ((A.length() % 2) == 0) {
                return "NO";
            } else {
                return "YES";
            }
        }
        return "YES";
    }

    public String longestCommonPrefix(ArrayList<String> A) {

        String ans = "";
        boolean isTrue;
        int min = Integer.MAX_VALUE;
        int maxIdx = 0;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).length() < min) {
                min = A.get(i).length();
                maxIdx = i;
            }
        }
        String res = A.get(maxIdx);
        for (int i = 0; i < res.length(); i++) {
            isTrue = true;
            for (int j = 0; j < A.size(); j++) {

                if (j == maxIdx) {
                    continue;
                }
                if (res.charAt(i) != A.get(j).charAt(i)) {
                    isTrue = false;
                    break;
                }
            }
            if (isTrue) {
                ans = ans + res.charAt(i);
            } else {
                break;
            }
        }
        return ans;

    }

    private static String reverseStr(String A) {
        StringBuilder sb = new StringBuilder();
        int l = 0;
        int space = 0;

        A = reverseFn(A.substring(0).trim());
        int start = 0;
        int h = A.length();
        String temp;
        while (start < h) {
            if (A.charAt(start) == ' ') {

                temp = reverseFn(A.substring(l, start).trim());
                sb.append(temp + " ");

                if ((start + 1) < h && A.charAt(start + 1) == ' ') {
                    while (A.charAt(start) == ' ') {
                        start++;
                    }
                    l = start;

                } else {
                    l = start + 1;
                    start++;
                }

            } else {
                start++;
            }

        }
        temp = reverseFn(A.substring(l, start));
        sb.append(temp);
        return sb.toString();
    }


    private static String reverseFn(String s) {
        char t;
        int l = 0;
        int h = s.length() - 1;
        StringBuilder sb = new StringBuilder(s);
        while (l < h) {
            t = sb.charAt(l);
            sb.setCharAt(l, sb.charAt(h));
            sb.setCharAt(h, t);
            l++;
            h--;
        }
        return sb.toString();
    }
}
