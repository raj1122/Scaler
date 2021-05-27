package com.company.Feb;


import java.util.*;





//Technologies: - common in


//Tehnologies in Project from academic -Distinguish
public class twopointer {

    public void executeFn(ArrayList<Integer> al1, ArrayList<Integer> al2) {
//        CountSubarrays1(al1);
        ClosestPair(al1, al2, 13);
    }

    /*Closest pair from sorted arrays
Problem Description

Given two sorted arrays of distinct integers, A and B, and an integer C, find and return the pair whose sum is closest to C and the pair has one element from each array.

More formally, find A[i] and B[j] such that abs((A[i] + B[j]) - C) has minimum value.

If there are multiple solutions find the one with minimum i and even if there are multiple values of j for the same i then return the one with minimum j.

Return an array with two elements {A[i], B[j]}.*/

    public ArrayList<Integer> ClosestPair(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        ArrayList<Integer> ans = new ArrayList<>();

        int i = 0;
        int j = B.size() - 1;
        int max = Integer.MAX_VALUE;
        int maxi = -1;
        int maxj = -1;
        boolean isFirst = false;
        while (i < A.size() && j >= 0) {
            long sum = A.get(i) + B.get(j);
            if (Math.abs(sum - C) < max || (Math.abs(sum - C) == max && (isFirst == false || maxi == i))) {
                if (isFirst == false)
                    isFirst = true;
                maxi = i;
                maxj = j;
                max = (int) Math.abs(sum - C);
            }


            if (sum > C)
                j--;
            else
                i++;
        }

        ans.add(A.get(maxi));
        ans.add(B.get(maxj));
        return ans;
    }

    /*Count Subarrays
Problem Description

Misha likes finding all Subarrays of an Array. Now she gives you an array A of N elements and told you to find the number of subarrays of A, that have unique elements.

Since the number of subarrays could be large, return value % 109 +7.*/

    public int CountSubarrays1(ArrayList<Integer> A) {
        HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
        int l = 0, r = 0, mod = (int) (1e9 + 7);
        long ans = 0;
        // l and r represent the window which we are checking
        for (Integer x : A) {
            if (mp.get(x) != null) {
                // if we already have x in window, start removing elements from the left
                while (mp.get(x) != 0) {
                    mp.put(A.get(l), 0);
                    l++;
                }
            }

            mp.put(x, 1);
            // number of subarrays ending at index r
            ans += r - l + 1;
            ans %= mod;
            r++;
        }


        return (int) ans;
    }

    public int CountSubarrays(ArrayList<Integer> A) {

        long ans = 0;
        long mod = (int) 1e9 + 7;
        int l = 0;
        int r = 0;
        int prev_r = 0;
        //HashMap to store value
        HashMap<Integer, Integer> hm = new HashMap<>();
        while (r < A.size()) {
            //If index is already occurs
            if (hm.containsKey(A.get(r))) {

                //calculate subarray with l and r
                long t1 = (1L * (r - l) * (r - l + 1)) / 2;
                //prev_r-l  use to avoid duplicate count of subarray
                long t2 = (1L * (prev_r - l) * (prev_r - l + 1)) / 2;
                long t3 = ((t1 % mod) - ((t2) % mod)) % mod;
                t3 = t3 + mod;
                t3 = t3 % mod;

                ans = ((ans % mod) + (t3 % mod)) % mod;

                //use of hasmap to consider subarray with distinct elemetn
                //increase left side with 1
                int lim = hm.get(A.get(r));
                while (l <= lim) {
                    hm.remove(A.get(l));
                    l++;

                }
                hm.put(A.get(r), r);
                prev_r = r;
            } else
                hm.put(A.get(r), r);


            //if right poointer has become the size of array
            if (r == A.size() - 1) {

                long t1 = (1L * (r - l + 1) * (r - l + 2)) / 2;
                long t2 = (1L * (prev_r - l) * (prev_r - l + 1)) / 2;
                long t3 = ((t1 % mod) - ((t2) % mod)) % mod;
                t3 = t3 + mod;
                t3 = t3 % mod;

                ans = ((ans % mod) + (t3 % mod)) % mod;
            }
            r++;

        }

        return (int) (ans % mod);
    }

    /*Array 3 Pointers
Problem Description

You are given 3 sorted arrays A, B and C.

Find i, j, k such that : max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.

Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])).*/


    public int minimize1(final List<Integer> A, final List<Integer> B, final List<Integer> C) {

        int ans = Integer.MAX_VALUE;
        int p1 = 0, p2 = 0, p3 = 0;
        while (p1 < A.size() && p2 < B.size() && p3 < C.size()) {
            ans = Math.min(ans, Math.max(Math.abs(A.get(p1).intValue() - B.get(p2).intValue()), Math.max(Math.abs(B.get(p2).intValue() - C.get(p3).intValue()), Math.abs(C.get(p3).intValue() - A.get(p1).intValue()))));
            if (A.get(p1).intValue() <= B.get(p2).intValue() && A.get(p1) <= C.get(p3).intValue()) {
                p1++;
            } else if (B.get(p2).intValue() <= A.get(p1).intValue() && B.get(p2).intValue() <= C.get(p3).intValue()) {
                p2++;
            } else {
                p3++;
            }
        }
        return ans;
    }

    public int minimize(final List<Integer> A, final List<Integer> B, final List<Integer> C) {

        int ans = Integer.MIN_VALUE;
        int ans1 = Integer.MAX_VALUE;

        int p = A.size(), q = B.size(), r = C.size();

        int i = 0, j = 0, k = 0;
        while (i < p && j < q && k < r) {
            int min = Math.min(A.get(i), Math.min(B.get(j), C.get(k)));
            int max = Math.max(A.get(i), Math.max(B.get(j), C.get(k)));
            ans = Math.max(Math.abs(A.get(i) - B.get(j)), ans);
            ans = Math.max(Math.abs(B.get(j) - C.get(k)), ans);
            ans = Math.max(Math.abs(C.get(k) - A.get(i)), ans);

            ans1 = Math.min(ans, ans1);
            if (A.get(i) != max && A.get(i) != min)
                i++;
            else if (B.get(j) != max && B.get(j) != min)
                j++;
            else
                k++;
        }
        return ans;
    }


    /*Max Continuous Series of 1s
Problem Description

Given a binary array A, find the maximum sequence of continuous 1's that can be formed by replacing at-most B ones.

For this problem, return the indices of maximum continuous series of 1s in order.

If there are multiple possible solutions, return the sequence which has the minimum start index.*/

    public ArrayList<Integer> maxone(ArrayList<Integer> A, int B) {
        ArrayList<Integer> res = new ArrayList<>();

        int wl = 0, wr = 0;
        int bestL = 0, bestW = 0;
        int zeroCnt = 0;

        while (wr < A.size()) {
            if (zeroCnt <= B) {
                if (A.get(wr) == 0)
                    zeroCnt++;
                wr++;
            }

            if (zeroCnt > B) {
                if (A.get(wl) == 0)
                    zeroCnt--;
                wl++;
            }
            if ((wr - wl > bestW) && (zeroCnt <= B)) {
                bestW = wr - wl;
                bestL = wl;
            }

        }

        for (int i = 0; i < bestW; i++) {
            res.add((i + bestL));

        }
        return res;
    }

    /*Another Count Rectangles
Problem Description

Given a sorted array of distinct integers A and an integer B, find and return how many rectangles with distinct configurations can be created using elements of this array as length and breadth whose area is lesser than B.

(Note that a rectangle of 2 x 3 is different from 3 x 2 if we take configuration into view)*/

    public int cntRect(ArrayList<Integer> A, int B) {
        long ans = 0;
        long mod = 1000000007;
        int l = 0;
        int r = A.size() - 1;
        while (l <= r) {
            long area = ((A.get(l).intValue() % mod) * (A.get(r).intValue() % mod)) % mod;
            if (area < B) {
                //if indexes are different then taking different pair with mul(* )as 2
                //If indx are same then taking only one pair as t will be 0
                long t = (2 * (r - l)) % mod;
                ans = (ans + (t) % mod + 1) % mod;
                l++;
            } else
                r--;
        }

        return (int) (ans % mod);
    }

    /*3 Sum
Problem Description

Given an array A of N integers, find three integers in A such that the sum is closest to a given number B. Return the sum of those three integers.

Assume that there will only be one solution.*/

    public int threeSumClosest(ArrayList<Integer> A, int B) {
        Collections.sort(A);

        int sum = 0;
        int mindiff = Integer.MAX_VALUE;
        for (int i = 0; i < A.size() - 2; i++) {
            int l = i + 1;
            int r = A.size() - 1;
            while (l < r) {
                int t = A.get(i) + A.get(l) + A.get(r);

                if (t == B) {
                    return B;
                }
                int diff = Math.abs(t - B);
                if (diff == 0) {
                    return B;
                }
                if (diff < mindiff) {
                    mindiff = diff;
                    sum = t;
                } else if (t < B) {
                    l++;
                } else {
                    r--;
                }


            }

        }
        return sum;
    }

    /*Container With Most Water
Problem Description

Given n non-negative integers A[0], A[1], ..., A[n-1] , where each represents a point at coordinate (i, A[i]).

N vertical lines are drawn such that the two endpoints of line i is at (i, A[i]) and (i, 0).

Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.*/

    public int maxArea(ArrayList<Integer> A) {

        long area = 0;
        int l = 0;
        int r = A.size() - 1;
        area = Math.min(A.get(l), A.get(r)) * Math.abs(r - l);
        while (l < r) {
            long temp = Math.min(A.get(l).intValue(), A.get(r).intValue()) * Math.abs(r - l);
            area = Math.max(area, temp);
            if (A.get(l).intValue() < A.get(r).intValue())
                l++;
            else
                r--;
        }
        return (int) area;
    }

    /*MaxMod
Problem Description

Given an array A of size N, groot wants you to pick 2 indices i and j such that

1 <= i, j <= N
A[i] % A[j] is maximum among all possible pairs of (i, j).
Help Groot in finding the maximum value of A[i] % A[j] for some i, j.*/

    public int maxMod(ArrayList<Integer> A) {
        int i, first, second;


        first = second = Integer.MIN_VALUE;
        for (i = 0; i < A.size(); i++) {
            if (A.get(i) > first) {
                second = first;
                first = A.get(i);
            } else if (A.get(i) > second && A.get(i) != first)
                second = A.get(i);
        }
        if (second == Integer.MIN_VALUE)
            return 0;

        return (second % first);
    }

    /*Unique Elements
Problem Description

You are given an array A of N elements. You have to make all elements unique, to do so in one step you can increase any number by one.

Find the minimum number of steps.*/

    public int UniqueElements(ArrayList<Integer> A) {

        int n = A.size();
        // sort the array
        Collections.sort(A);
        // initialize variables
        int steps = 0, i = 1, j = 0;
        // loop unitil you reach the end
        while (j < n) {
            // make current element unique
            if (i >= A.get(j).intValue()) {
                steps += (i - A.get(j++).intValue());
                i += 1;
            } else {
                i = A.get(j) + 1;
                j += 1;
            }
        }

        // return the answer
        return steps;
    }

    /*
Pairs with given sum II
Problem Description

Given a sorted array of integers (not necessarily distinct) A and an integer B, find and return how many pair of integers ( A[i], A[j] ) such that i != j have sum equal to B.

Since the number of such pairs can be very large, return number of such pairs modulo (109 + 7).*/
    public int givenSumII(ArrayList<Integer> A, int B) {
        long cnt = 0;
        long mod = 1000000007;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            if (hm.containsKey(A.get(i))) {
                hm.put(A.get(i), hm.get(A.get(i)) + 1);

            } else
                hm.put(A.get(i), 1);
        }

        //getting every combination pair like 1 with 1,1 i.e 3 and decrmenting 1 so that current num
        // is not taken for pair consideration
        for (int i = 0; i < A.size(); i++) {
            int cumSum = B - A.get(i);
            if (hm.containsKey(cumSum))
                cnt = cnt + hm.get(cumSum);
            cnt = cnt % mod;
            if (cumSum == A.get(i).intValue())
                cnt--;
            cnt = cnt % mod;
        }


        cnt = cnt / 2;
        //every pair is counted twice so taking one pair only
        return (int) (cnt % mod);
    }

    public int givenSumIIextra(ArrayList<Integer> A, int B) {
        long cnt = 0;
        long mod = 1000000007;

        Collections.sort(A);
        int l = 0;
        int h = A.size() - 1;
        while (l < h) {
            if (A.get(l).intValue() + A.get(h).intValue() < B)
                l++;
            else if (A.get(l).intValue() + A.get(h).intValue() > B)
                h--;
            else {
                //count pairs
                int currl = A.get(l), lIdx = l;
                int currh = A.get(h), hIdx = h;
                while ((l < h) && (currl == A.get(l)))
                    l++;

                while ((h >= l) && (currh == A.get(h)))
                    h--;

                long t = 0;
                if (currl == currh) {
                    //number are same then take only one pair
                    //converting these equation->l-lIdx+hIdx-h-1
                    long t1 = (l - lIdx + hIdx - h - 1);

                    t = (t1 * (t1 + 1) / 2) % mod;
                } else {
                    //number are diff then taken combination a*(b-sum)
                    long calL = (l - lIdx) % mod;
                    long calH = (hIdx - h) % mod;
                    t = (calL * calH) % mod;
                }
                cnt = ((cnt % mod) + (t % mod)) % mod;

            }

        }
        return (int) (((cnt % mod) + mod) % mod);
    }


}




