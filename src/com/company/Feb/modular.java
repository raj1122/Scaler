package com.company.Feb;

import java.util.*;

public class modular {

    public void executeFn(ArrayList<Integer> al1, ArrayList<Integer> a2) {
//        ABModulo(8259010,2201509);
        veryLargePower(2, 27);
    }


    /*Very Large Power
Problem Description

Given two Integers A, B. You have to calculate (A ^ (B!)) % (1e9 + 7).

"^" means power ,

"%" means "mod", and

"!" means factorial.*/

    public int veryLargePower(int A, int B) {
        int factB = calFactB(B);
        long mod = (int) 1e9 + 7;
        long ans = calApower(A, factB, mod);
        return (int) ans;
    }

    private long calApower(long a, long factB, long mod) {
        long Mod = (int) 1e9 + 7;
        long result = 1;
        while (factB > 0) {
            if (factB % 2 == 1) {
                result = result * a % Mod;
            }
            factB = factB / 2;
            a = a * a % Mod;
        }
        return result;

    }


    private int calFactB(int n) {
        long mod = (long) 1e9 + 7;
        if (n == 0)
            return 1;
        long fact = 1;

        for (long i = 2; i <= n; i++)
            fact = (fact * i) % (mod - 1);

        return (int) fact;
    }

    /*A, B and Modulo
Problem Description

Given two integers A and B, find the greatest possible positive M, such that A % M = B % M.*/
    public int ABModulo(int A, int B) {

        int a = Math.max(A, B);
        int b = Math.min(A, B);
        int num = a - b;
        double t = Math.ceil(Math.sqrt(a - b));
        long ans = 0;
        for (int i = 1; i <= t; i++) {
            if (num % i == 0) {
                ans = i;
                if ((num / i) > b) {
                    ans = i;

                }
            }

        }

        if ((a % num) == (b % num)) {
            ans = num;
        }
        return (int) ans;

    }


    /*Mod Sum
Problem Description

Given an array of integers A, calculate the sum of A [ i ] % A [ j ] for all possible i, j pairs. Return sum % (109 + 7) as an output.*/

    public int ModSumFreq(ArrayList<Integer> A) {
        long ans = 0;
        long mod = (long) (1e9 + 7);
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < A.size(); i++) {
            hm.computeIfPresent(A.get(i), (Integer, count) -> (count + 1));
            hm.computeIfAbsent(A.get(i), (Integer) -> 1);
        }

        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            for (Map.Entry<Integer, Integer> entryj : hm.entrySet()) {
                //freq multiplied
                int t = entry.getValue() * entryj.getValue();
                //actual number ->handle duplicate number
                int t1 = entry.getKey() % entryj.getKey();
                ans = ans + (t * t1);
                ans = ans % mod;

            }
        }
        return (int) ans;
    }


}
