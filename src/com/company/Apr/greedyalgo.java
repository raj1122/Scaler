package com.company.Apr;

import java.lang.reflect.Array;
import java.util.*;

public class greedyalgo {

    public void executeFn() {
//        Integer[] arr1 = {1, 7, 6, 2, 8, 4, 4, 6, 8, 2};
//        Integer[] arr2 = {8, 11, 7, 7, 10, 8, 7, 5, 4, 9};
        Integer[] arr1 = {1, 3, 2, 3, 3};
        Integer[] arr2 = {5, 6, 1, 3, 9};
//        Integer[] arr1 = {3, 8, 7, 5};
//        Integer[] arr2 = {3, 1, 7, 19};
        ArrayList<Integer> al1 = new ArrayList<>();
        ArrayList<Integer> al2 = new ArrayList<>();
        Collections.addAll(al1, arr1);
        Collections.addAll(al2, arr2);
//        buyCar(al1, al2);
//        coinProblem(35);
//        BinString("00010110",3);
        buyCar(al1,al2);
    }


    /*The ship company
Problem Description

The local ship renting service has a special rate plan:

It is up to a passenger to choose a ship.
If the chosen ship has X (X > 0) vacant places at the given moment, then the ticket for such a ship costs X.
The passengers buy tickets in turn, the first person in the queue goes first, then goes the second one, and so on up to A-th person.

You need to tell the maximum and the minimum money that the ship company can earn if all A passengers buy tickets.



Problem Constraints
1 ≤ A ≤ 3000
1 ≤ B ≤ 1000
1 ≤ C[i] ≤ 1000
It is guaranteed that there are at least A empty seats in total.



Input Format
First argument is a integer A denoting the number of passengers in the queue.
Second arugument is a integer B deonting the number of ships.
Third argument is an integer array C of size B where C[i] denotes the number of empty seats in the i-th ship before the ticket office starts selling tickets.*/

    public ArrayList<Integer> ShipCompany(int A, int B, ArrayList<Integer> C) {
        PriorityQueue<Integer> max=new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min=new PriorityQueue<>();
        int max_val=0, min_val=0;

        for (int i = 0; i < C.size(); i++) {
            max.add(C.get(i));
            min.add(C.get(i));
        }

        int temp_min=0, temp_max=0, temp1, temp2;
        while(A-- >0){
            temp1=max.peek();
            max.poll();
            max_val+=temp1;
            //if(temp_max>max_val)
            //    max_val=temp_max;
            if(temp1>1)
                max.add(temp1-1);

            temp2=min.peek();
            min.poll();
            min_val+=temp2;
            //if(temp_min<min_val)
            //    min_val=temp_min;
            if(temp2>1)
                min.add(temp2-1);
        }

        ArrayList<Integer> res=new ArrayList<>();
        res.add(max_val);
        res.add(min_val);
        return res;
    }

    class FJob implements Comparable<FJob> {
        int s;
        int f;

        public FJob(int s, int f) {
            this.s = s;
            this.f = f;
        }

        @Override
        public int compareTo(FJob o) {
            int t = this.f - o.f;
            if (t > 0)
                return 1;
            else if (t < 0)
                return -1;
            else {
                int t1 = this.s - o.s;
                if (t1 > 0)
                    return 1;
                else if (t1 < 0)
                    return -1;

                return 0;
            }


        }
    }



    /*Binary Strings
Problem Description

You are given a string A consisting of 1's and 0's. Now the task is to make the string consisting of only 1's. But you are allowed to perform only the following operation:

Take exactly B consecutive elements of string and change 1 to 0 and 0 to 1.
Each operation takes 1 unit time so you have to regulardetermine the minimum time required to make the string of 1's only. If not possible return -1.*/


    public int BinString(String A, int B) {
        int ans=0;
        int len=A.length();
        int pf[]=new int[len];
        int cnt=0;
        for (int i = 0; i < len; i++) {

            if(A.charAt(i)=='0')
            {
                cnt=pf[i];
                if( cnt%2==0)
                {
                    if((i+B)>len)
                        return -1;
                    ans++;
                    for (int j = 0; j<B &&  (i+j)<len; j++) {
                        pf[i+j]=pf[i+j]+1;
                    }
                }
            }
            else if(A.charAt(i)=='1')
            {

                cnt=pf[i];
                if( cnt%2!=0)
                {
                    if((i+B)>len)
                        return -1;
                    ans++;
                    for (int j = 0; j<B && (i+j)<len; j++) {
                        pf[i+j]=pf[i+j]+1;
                    }
                }
            }

        }

        return ans;
    }


    public int solve(String A, int B) {
        int n=A.length();
        int count = 0;
        for (int i = 0; i < n - 1; i++)
        {

            // Increment count when consecutive
            // characters are different
            if (A.charAt(i) != A.charAt(i + 1))
                count++;
        }

        // Answer is rounding off the
        // (count / 2) to lower
        return (count + 1) / 2;
    }

    /*Assign Mice to Holes
Problem Description

There are N Mice and N holes that are placed in a straight line. Each hole can accomodate only 1 mouse.

The positions of Mice are denoted by array A and the position of holes are denoted by array B.

A mouse can stay at his position, move one step right from x to x + 1, or move one step left from x to x − 1. Any of these moves consumes 1 minute.

Assign mice to holes so that the time when the last mouse gets inside a hole is minimized.*/

    public int mice(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A);
        Collections.sort(B);

        int max = 0;
        for (int i=0; i<A.size(); i++) {
            if (max < Math.abs(A.get(i) - B.get(i)))
                max = Math.abs(A.get(i) - B.get(i));
        }
        return max;
    }

    /*Distribute Candy
Problem Description

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?*/

    public int candy(int[] A) {
        int n=A.length;
        int candies[] = new int[n];
        candies[0] = 1;
        // First loop for up trends
        for(int i = 1; i<n; i++) {
            if(candies[i] == 0) {
                candies[i]=1;
            }
            if(A[i] > A[i-1]) {
                candies[i] = candies[i-1]+1;
            }
        }

        // Second loop for down trends
        for(int i = n-1; i > 0; i--) {
            if(A[i-1] > A[i] && candies[i-1] <= candies[i]) {
                candies[i-1] = candies[i]+1;
            }
        }

        int count = 0;
        for (int i=0; i<candies.length; i++) {
            count += candies[i];
        }

        return (int)count;

    }

    /*Another Coin Problem
Problem Description

The monetary system in DarkLand is really simple and systematic. The locals only use coins. The coins come in different values. The values used are:

 1, 5, 25, 125, 625, 3125, 15625, ...
Formally, for each K >= 0 there are coins worth 5K.

Given an integer A denoting the cost of an item, find and return the smallest number of coins necessary to pay exactly the cost of the item (assuming you have a sufficient supply of coins of each of the types you will need).

*/

    public int coinProblem(int A) {

        int ans = 0;

        while (A > 0) {
            if (A == 1) {
                ans++;
                break;
            }

            int fp = 0;
            long powf = (long) Math.pow(5, fp);

            while (powf < A) {
                fp++;
                powf = (long) Math.pow(5, fp);
            }
            fp--;
            powf = (long) Math.pow(5, fp);
            ans++;
            A = (int) (A - powf);

        }


        return ans;
    }

    /*Free Cars
Problem Description

Given two arrays A and B of size N. A[i] represents the time by which you can buy ith car without paying any money.

B[i] represents the profit you can earn by buying ith car. It takes 1 minute to buy a car so, you can only buy the ith car when the current time <= A[i] - 1.

Your task is to find maximum profit one can earn by buying cars considering that you can only buy one car at a time.

NOTE:

You can stary buying from time = 0.
Return you answer modulo 109 + 7.*/


    class FreeCar {
        int id;
        int deadline;
        int profit;

        public FreeCar(int deadline, int profit,int id) {
            this.deadline = deadline;
            this.profit = profit;
            this.id=id;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FreeCar freeCar = (FreeCar) o;
            return id == freeCar.id && deadline == freeCar.deadline && profit == freeCar.profit;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, deadline, profit);
        }

    }

    class Sortby implements Comparator<FreeCar> {

        @Override
        public int compare(FreeCar o1, FreeCar o2) {
            if (o1.deadline == o2.deadline)
                return o2.profit - o1.profit;
            return o1.deadline - o2.deadline;
        }
    }

    public int buyCar(ArrayList<Integer> A, ArrayList<Integer> B) {
        int ans = 0;
        ArrayList<FreeCar> list = new ArrayList<>();
        int size = A.size();
        for (int i = 0; i < size; i++) {
            list.add(new FreeCar(A.get(i), B.get(i),i));
        }
        Collections.sort(list,new Sortby());


        PriorityQueue<FreeCar> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.profit - o2.profit;
        });


        long profit = 0;
        int mod = 1000000007;
        for (int i = 0; i < list.size(); i++) {
            FreeCar temp=list.get(i);
            if (pq.size() == 0 || temp.deadline > pq.size()) {

                profit = (profit % mod + temp.profit % mod) % mod;

                pq.add(new FreeCar(A.get(i), B.get(i),i));

            } else {
                FreeCar tt = pq.peek();
                if (temp.profit > tt.profit) {
                    pq.poll();
                    pq.add(tt);
                    profit = (profit % mod + temp.profit % mod) % mod;
                    profit=(profit%mod-tt.profit %mod)%mod;
                }

            }
        }



        return (int)profit;
    }

    /*Finish Maximum Jobs
Problem Description

There are N jobs to be done but you can do only one job at a time.

Given an array A denoting the start time of the jobs and an array B denoting the finish time of the jobs.

Your aim is to select jobs in such a way so that you can finish maximum number of jobs. Return the maximum number of jobs you can finish.*/



    public int maxJob(ArrayList<Integer> A, ArrayList<Integer> B) {
        int ans = 0;
        PriorityQueue<FJob> jobs = new PriorityQueue<>();
        for (int i = 0; i < A.size(); i++) {
            jobs.add(new FJob(A.get(i), B.get(i)));

        }




        return ans;
    }
}
