package com.company.jan;

import java.util.*;

public class prblmsolving18jan {


    /*Simplify Directory Path
Given a string A representing an absolute path for a file (Unix-style).

Return the string A after simplifying the absolute path.*/

    public String simplifyPath(String A) {

        StringBuilder sb = new StringBuilder("");
        A = (String) A.subSequence(1, A.length());
        String[] arr = A.split("[/]");
        Deque<String> st = new LinkedList<String>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("..")) {
                if (!st.isEmpty())
                    st.pop();
            } else if (arr[i].matches("[a-zA-Z]+")) {
                st.push("/" + arr[i]);
            }
        }

        if (st.isEmpty())
            return "/";

        while (!st.isEmpty()) {
            sb.append(st.removeLast());
        }
        return sb.toString();
    }

    /*Bulbs
Problem Description

N light bulbs are connected by a wire.

Each bulb has a switch associated with it, however due to faulty wiring, a switch also changes the state of all the bulbs to the right of current bulb.

Given an initial state of all bulbs, find the minimum number of switches you have to press to turn on all the bulbs.

You can press the same switch multiple times.

Note: 0 represents the bulb is off and 1 represents the bulb is on.*/

    //0  1  0 1

    public int bulbs(ArrayList<Integer> A) {
        int ans = 0;

        for (int i = 0; i < A.size(); i++) {

            if ((ans % 2) != 0) {
                A.set(i, 1 - A.get(i));
            }
            if (A.get(i) == 0) {

                ans++;
            }
        }

        return ans;
    }


    /*SUBARRAY OR
Problem Description

Given an array of integers A of size N.

Value of a subarray is defined as BITWISE OR of all elements in it.

Return the sum of Value of all subarrays of A % 109 + 7.*/

    public int subArrayOR(ArrayList<Integer> A) {
        long sum = 0;
        ArrayList<Integer> unsetBits = new ArrayList<>();

        long size = A.size();
        //contribution of 1 in subarray
        int max = Collections.max(A);
        int maxBit = (int) Math.ceil(Math.log(max) + 1);
        long n = (size * (size + 1)) / 2;
        for (int i = 0; i < 30; i++) {
            unsetBits = new ArrayList<>();
            for (int j = 0; j < size; j++) {

                if ((A.get(j) & (1 << i)) <= 0) {
                    unsetBits.add(j);

                }
            }

            long cntContinousArr = 0;
            long cnt = 1;

            for (int j = 1; j < unsetBits.size(); j++) {
                //If difference is 1 then continous
                if (unsetBits.get(j) - unsetBits.get(j - 1) == 1) {
                    cnt++;
                } else {
                    //on this line it is giving me error
                    cntContinousArr += cnt * (cnt + 1) / 2;
                    cnt = 1;
                }
            }

            cntContinousArr += cnt * (cnt + 1) / 2;
            if (unsetBits.size() == 0)
                cntContinousArr = 0;

            long cntSubarrIthSet = n - cntContinousArr;
            sum += ((cntSubarrIthSet % 1000000007) * (Math.pow(2, i) % 1000000007)) % 1000000007;

        }

        return (int) (sum % 1000000007);
    }




    /*Single Number II
Problem Description

Given an array of integers, every element appears thrice except for one which occurs once.

Find that element which does not appear thrice.

NOTE: Your algorithm should have a linear runtime complexity.

Could you implement it without using extra memory?*/

    public int singleNumber(final List<Integer> A) {


        int setBits = 0;
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            setBits = 0;
            for (int j = 0; j < A.size(); j++) {
                if ((A.get(j) & 1 << i) > 0) {
                    setBits++;

                }
            }

            ans = ans | (setBits % 3) << i;

        }

        return ans;
    }


}
