package com.company.Feb;

import javafx.util.Pair;

import java.util.*;

class PairP {
    int x;
    int y;

    public PairP(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {

        return x;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof PairP)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        PairP c = (PairP) o;

        // Compare the data members and return accordingly
        return Double.compare(x, c.x) == 0
                && Double.compare(y, c.y) == 0;
    }
}

public class hashing1 {

    public void executeFn(ArrayList<Integer> al1, ArrayList<Integer> al2) {
        Integer[] arr1 = {1, 7, 11, 8, 11, 7, 1};
        ArrayList<Integer> iarr = new ArrayList<>();
        Collections.addAll(iarr, arr1);
        ArrayList<ArrayList<Integer>> dblArr = creatDbtArr();
        CompareSubarrays(iarr, dblArr);
//        minWindow("ADOBECODEBANC","ABC");
    }


    /*Compare Sorted Subarrays
Problem Description

Given an array A of length N. You have to answer Q queires.

Each query will contain 4 integers l1, r1, l2 and r2. If sorted segment from [l1, r1] is same as sorted segment from [l2 r2] then answer is 1 else 0.

NOTE The queries are 0-indexed.*/

    public int rangeSum(int i, int j, int pre[]) {
        if (i == 0)
            return pre[j];

        return pre[j] - pre[i - 1];
    }

    public ArrayList<Integer> CompareSubarrays(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            int l1 = B.get(i).get(0);
            int r1 = B.get(i).get(1);
            int l2 = B.get(i).get(2);
            int r2 = B.get(i).get(3);
            int prefixSum[] = new int[A.size()];
            prefixSum[0] = A.get(0);


            for (int k = 1; k < A.size(); k++)
                prefixSum[k] = prefixSum[k - 1] + A.get(k);
            int suml = rangeSum(l1, r1, prefixSum);
            int sumr = rangeSum(l2, r2, prefixSum);

            if (suml != sumr) {
                ans.add(0);
                continue;
            }
            HashMap<Integer, Integer> hml = new HashMap<>();
            HashMap<Integer, Integer> hmr = new HashMap<>();
            int reqCnt = 0;
            for (int j = l1; j <= r1; j++) {
                hml.put(A.get(j), hml.getOrDefault(A.get(j), 0) + 1);
                reqCnt++;
            }

            int cnt = 0;
            for (int j = l2; j <= r2; j++) {
                hmr.put(A.get(j), hmr.getOrDefault(A.get(j), 0) + 1);

                int t = A.get(j);
                if (hml.containsKey(t) && hmr.get(t) <= hml.get(t)) {
                    cnt++;
                }
            }

            if (reqCnt == cnt)
                ans.add(1);
            else
                ans.add(0);
        }
        return ans;
    }

    public String minWindow(String A, String B) {

        if (A.length() < B.length())
            return "";
        HashMap<Character, Integer> pattern = new HashMap<>();
        HashMap<Character, Integer> match = new HashMap<>();
        for (int i = 0; i < B.length(); i++) {
            pattern.put(B.charAt(i), pattern.getOrDefault(B.charAt(i), 0) + 1);
        }
        int l = 0, r = 0;
        int reqCnt = B.length();
        int curr = 0;
        StringBuilder ans = new StringBuilder("");
        int maxlen = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;
            //acquire the string until curr==reqCnt
            while (r < A.length() && curr < reqCnt) {
                char ch = A.charAt(r);
                match.put(ch, match.getOrDefault(ch, 0) + 1);
                if (pattern.containsKey(ch) && match.getOrDefault(ch, 0) <= pattern.getOrDefault(ch, 0)) {
                    curr++;
                }
                r++;
                flag1 = true;
            }
            //release the string until curr!reqCnt
            while (l < r && curr == reqCnt) {
                //collect answer
                int len = r - l;
                //take min answer
                if (len < maxlen) {
                    start = l;
                    end = r;
                    maxlen = len;
                }
                char ch = A.charAt(l);
                l++;
                //remove the char in  match
                if (match.get(ch) == 1)
                    match.remove(ch);
                else
                    match.put(ch, match.get(ch) - 1);

                //if patern is not present in String B then dont compare
                if (pattern.containsKey(ch) && match.getOrDefault(ch, 0) < pattern.getOrDefault(ch, 0)) {
                    curr--;

                }
                flag2 = true;
            }

            //if it doesn't pass any loop exit
            if (flag1 == false && flag2 == false)
                break;
        }


        if (start == -1)
            return "";
        return A.substring(start, end);
    }

    /*Longest Substring Without Repeat
Problem Description

Given a string A, find the length of the longest substring without repeating characters.

Note: Users are expected to solve in O(N) time complexity.*/
    public int lengthOfLongestSubstring(String A) {
        HashMap<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0;
        int n = A.length();
        int len = 0;
        while (r < n) {
            if (map.containsKey(A.charAt(r))) {
                l = Math.max(l, map.get(A.charAt(r)) + 1);
            }
            map.put(A.charAt(r), r);
            len = Math.max(len, r - l + 1);
            r++;
        }
        return len;
    }

    /*Colorful Number
Problem Description

For Given Number A find if its COLORFUL number or not.

If number A is a COLORFUL number return 1 else return 0.

What is a COLORFUL Number:

A number can be broken into different contiguous sub-subsequence parts.
Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245.
And this number is a COLORFUL number, since product of every digit of a contiguous subsequence is different.*/

    public int colorful(int A) {
        HashSet<Integer> hs = new HashSet<Integer>();
        String str = Integer.toString(A);
        for (int i = 0; i < str.length(); i++) {
            int prod = 1;
            for (int j = i; j < str.length(); j++) {
                int num = str.charAt(j) - '0';
                prod *= num;
                if (hs.contains(prod)) return 0;
                hs.add(prod);
            }
        }
        return 1;
    }

    /*Count Rectangles
Problem Description

Given two arrays of integers A and B of size N each, where each pair (A[i], B[i]) for 0 <= i < N represents a unique point (x, y) in 2-D Cartesian plane.

Find and return the number of unordered quadruplet (i, j, k, l) such that (A[i], B[i]), (A[j], B[j]), (A[k], B[k]) and (A[l], B[l]) form a rectangle with the rectangle having all the sides parallel to either x-axis or y-axis.*/
    public int CountRectangles(ArrayList<Integer> A, ArrayList<Integer> B) {
        int ans = 0;

        HashSet<PairP> hs = new HashSet<>();
        for (int i = 0; i < A.size(); i++) {
            //storing  every point in hashset
            PairP t = new PairP(A.get(i), B.get(i));
            hs.add(t);

        }
        for (int i = 0; i < A.size(); i++) {

            for (int j = i + 1; j < A.size(); j++) {

                if ((A.get(i).intValue() == A.get(j).intValue()) || (B.get(i).intValue() == B.get(j).intValue()))
                    continue;
                else {
                    //checking other diagonal points if present
                    PairP p2 = new PairP(A.get(i), B.get(j));
                    PairP p1 = new PairP(A.get(j), B.get(i));
                    if (hs.contains(p1) && hs.contains(p2))
                        ans++;
                }
            }
        }
        //returning ans/2
        return (ans / 2);
    }



    /*Count Right Triangles
Problem Description

Given two arrays of integers A and B of size N each, where each pair (A[i], B[i]) for 0 <= i < N represents a unique point (x, y) in 2D Cartesian plane.

Find and return the number of unordered triplets (i, j, k) such that (A[i], B[i]), (A[j], B[j]) and (A[k], B[k]) form a right angled triangle with the triangle having one side parallel to the x-axis and one side parallel to the y-axis.

NOTE: The answer may be large so return the answer modulo (109 + 7).*/

    public int cntTriangles(ArrayList<Integer> A, ArrayList<Integer> B) {
        int ans = 0;
        HashMap<Integer, Integer> xmap = new HashMap<>();
        HashMap<Integer, Integer> ymap = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            if (xmap.containsKey(A.get(i)))
                xmap.put(A.get(i), xmap.get(A.get(i)) + 1);
            else
                xmap.put(A.get(i), 1);

            if (ymap.containsKey(B.get(i)))
                ymap.put(B.get(i), ymap.get(B.get(i)) + 1);
            else
                ymap.put(B.get(i), 1);
        }

        for (int i = 0; i < A.size(); i++) {
            //we have to exclude current point
            if (xmap.get(A.get(i)) >= 1 && ymap.get(B.get(i)) >= 1) {

//               multiply x axis point with y axis point
                ans += (xmap.get(A.get(i)) - 1) * (ymap.get(B.get(i)) - 1);
            }
        }

        return ans;
    }

    /*Points on same line
Problem Description

Given two array of integers A and B describing a pair of (A[i], B[i]) coordinates in 2D plane. A[i] describe x coordinates of the ith point in 2D plane whereas B[i] describes the y-coordinate of the ith point in 2D plane.

Find and return the maximum number of points which lie on the same line.*/
    /*Replicating Substring
Problem Description

Given a string B, find if it is possible to re-order the characters of the string B so that it can be represented as a concatenation of A similar strings.

Eg: B = aabb and A = 2, then it is possible to re-arrange the string as "abab" which is a concatenation of 2 similar strings "ab".

If it is possible, return 1, else return -1.*/

    public int pointLine(ArrayList<Integer> A, ArrayList<Integer> B) {
        int currMax = 0;
        int samePoint = 0;
        int horizontalSame = 0;
        int ans = Integer.MIN_VALUE;
        HashMap<PairP, Integer> hm = new HashMap<PairP, Integer>();
        for (int i = 0; i < A.size(); i++) {
            int x1 = A.get(i);
            int y1 = B.get(i);
            PairP p1 = new PairP(x1, y1);
            samePoint = 0;
            horizontalSame = 0;
            currMax = 0;
            for (int j = i + 1; j < A.size(); j++) {

                int x2 = A.get(j);
                int y2 = B.get(j);
                PairP p2 = new PairP(x2, y2);
                int x = p2.x - p1.x;
                int y = p2.y - p1.y;


                int g = gcd(x, y);
                System.out.println("x" + x + "y" + y + "g" + g);


                if (p1.equals(p2))
                    samePoint++;
                else if (p1.x == p2.x)
                    horizontalSame++;
                else {
                    x = x / g;
                    y = y / g;
                    PairP makePair = new PairP(x, y);
                    if (hm.containsKey(makePair)) {
                        hm.put(makePair, hm.get(makePair) + 1);
                    } else
                        hm.put(makePair, 1);

//                    if(i==1)
                    System.out.println("currMax" + currMax + "hash" + hm.get(makePair));

                    currMax = Math.max(currMax, hm.get(makePair));
                }
                currMax = Math.max(currMax, horizontalSame);
            }
            //end of inner loop


            ans = Math.max(ans, currMax + samePoint + 1);
//            System.out.println("anx"+ans);
            hm.clear();
        }


        return ans;
    }

    public int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }


    public int solve(int A, String B) {

        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < B.length(); i++) {
            char ch = B.charAt(i);
            if (hm.containsKey(ch)) {
                hm.put(ch, hm.get(ch) + 1);
            } else {
                hm.put(ch, 1);
            }
        }
        for (Map.Entry<Character, Integer> hs : hm.entrySet()) {
            int t = hs.getValue();
            if ((t % A) != 0)
                return -1;


        }
        return 1;
    }

    /*Shaggy and distances
Problem Description

Shaggy has an array A consisting of N elements. We call a pair of distinct indices in that array as a special pair if elements at that index in the array are equal.

Shaggy wants you to find a special pair such that distance between that pair is minimum. Distance between two indices is defined as |i-j|. If there is no special pair in the array then return -1.*/

    public int shaggyDist(ArrayList<Integer> A) {
        int ans = Integer.MAX_VALUE;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            if (hm.containsKey(A.get(i))) {
                int t = hm.get(A.get(i));
                int diff = i - t;
                hm.put(A.get(i), i);
                ans = Math.min(ans, diff);
            } else {
                hm.put(A.get(i), i);
            }

        }
        if (ans == Integer.MAX_VALUE)
            return -1;
        return ans;
    }

    /*Sort Array in given Order
Problem Description

Given two array of integers A and B, Sort A in such a way that the relative order among the elements will be the same as those are in B. For the elements not present in B, append them at last in sorted order.

Return the array A after sorting from the above method.

NOTE: Elements of B are unique.*/
    public ArrayList<Integer> sortarryOrder(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            if (hm.containsKey(A.get(i)))
                hm.put(A.get(i), hm.get(A.get(i)) + 1);
            else
                hm.put(A.get(i), 1);
        }

        for (int i = 0; i < B.size(); i++) {
            if (hm.containsKey(B.get(i))) {
                int size = hm.get(B.get(i));
                for (int j = 0; j < size; j++) {
                    res.add(B.get(i));
                }
                hm.remove(B.get(i));
            }
        }

        ArrayList<Integer> keys = new ArrayList<Integer>(hm.keySet());
        Collections.sort(keys);
        for (Integer x : keys) {
            int size1 = hm.get(x);
            for (int j = 0; j < size1; j++) {
                res.add(x);
            }
        }
        return res;
    }


    private static ArrayList<ArrayList<Integer>> creatDbtArr() {
        ArrayList<ArrayList<Integer>> dblArr = new ArrayList<>();

        Integer arr[][] = new Integer[][]
                {
                        {0, 2, 4, 6}
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

}
