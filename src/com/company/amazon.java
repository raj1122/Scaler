package com.company;

import java.util.*;

public class amazon {


    public void executeFn()
    {
        Integer[] arr1 = {7,7,9,9,9,2,7};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);

        Integer[] arr2 = {2,3};
        ArrayList<Integer> levelTree2 = new ArrayList<>();
        Collections.addAll(levelTree2, arr2);

        Integer[] arr3 = {2,3};
        ArrayList<Integer> levelTree3 = new ArrayList<>();
        Collections.addAll(levelTree3, arr3);

        Integer[] arr4 = {1,2};
        ArrayList<Integer> levelTree4 = new ArrayList<>();
        Collections.addAll(levelTree4, arr4);

        ArrayList<ArrayList<Integer>> al=new ArrayList<>();

        al.add(levelTree);
        al.add(levelTree2);
        al.add(levelTree3);
        al.add(levelTree4);

        int dp[][]=new int[al.size()+1][11];
        for (int[] row : dp)
            Arrays.fill(row, -1);

//        recursion(al,0,10,dp);

        for (int i = 0; i < 30; i++) {
            getTopTen(new amazon.LeaderBrd("A"+i,i));
        }


        PriorityQueue<LeaderBrd> ans  =  getTopTen(new LeaderBrd("C",30));
        ArrayList<LeaderBrd> res=new ArrayList<>(ans);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));

        }





        int [] arrm = {9,9,7,7,9,2,2,9,7,3,5,7,9,3,5,9,4,5,7,9,3,4,3,5,7,8,7,11,9,7};
        majority(arrm);
        System.out.println("hi");

    }

    static int cnt = 0;
    static ArrayList<Integer> ans = new ArrayList<>();

    private int recursion(ArrayList<ArrayList<Integer>> al, int idx, int sum,int dp[][]) {

        if (idx == al.size()) {
            if (sum >= 0) {
                System.out.println(Arrays.toString(ans.toArray()));
                cnt++;
                return 1;

            } else
                return 0;
        }

        if(dp[idx][sum] !=-1) {
            return dp[idx][sum];
        }

        ArrayList<Integer> prod = al.get(idx);
        int check=0;
        int len = prod.size();
        for (int i = 0; i < len; i++) {

            if (sum - prod.get(i) >= 0) {
                ans.add(prod.get(i));
                  check += recursion(al, idx + 1, (sum - prod.get(i)),dp);

                ans.remove(prod.get(i));
            }
        }
        dp[idx][sum]= check;
        return dp[idx][sum];
    }


    class LeaderBrd implements Comparable<LeaderBrd>{

        private String name;
        private long points;

        public LeaderBrd(String name, long points) {
            this.name = name;
            this.points = points;
        }


        @Override
        public int compareTo(LeaderBrd o) {
            return (int) (this.points-o.points);
        }
    }

    static PriorityQueue<LeaderBrd> pq=new PriorityQueue<>();

    public PriorityQueue<LeaderBrd> getTopTen(LeaderBrd leaderBrd)
    {

        if(pq.size()>=10)
        {
            LeaderBrd temp=pq.peek();
            if(temp.points <leaderBrd.points)
            {
                pq.poll();
                pq.add(leaderBrd);
            }
        }
        else
        {
            pq.add(leaderBrd);
        }

        return pq;

    }


    public int majority(int arr[])
    {

        int cnt=0;
        int elem=-1;
        int temp=0;
        boolean isFirst=false;
        for (int i = 0; i < arr.length; i++) {
            if(cnt==0)
            {
                elem=arr[i];
                cnt++;

            }
            else if(elem==arr[i])
            {
                cnt++;
            }
            else
            {
                cnt--;
            }
        }

        return elem;
    }


}

// [4][sum]  == -1
//
