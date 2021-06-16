package com.company;

import java.util.*;

public class amazon {


    public void executeFn()
    {
        Integer[] arr1 = {4,2,5,1,6};
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





        int [] arrm = {5,3,2,4,4,1,2};
//        majority(arrm);
        disp(levelTree);

    }

    public ArrayList<Integer> disp(ArrayList<Integer> data)
    {
        Collections.sort(data, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2)>0?-1:1;
            }
        });
        int len=data.size();
        int i=0;
        ArrayList<Integer> A=new ArrayList<>();
        HashMap<Integer,Integer> hm=new HashMap<>();
        int sumA=0;

        int sumB=0;
        for (int j = 0; j < len; j++) {
            sumB+=data.get(j);
            if(hm.containsKey(data.get(j)))
            {
                hm.put(data.get(j),hm.get(data.get(j))+1);

            }
            else
            {
                hm.put(data.get(j),1);
            }
        }




        while(i<len)
        {
            int t= data.get(i);
            int cal=0;
            if(hm.get(t)>1)
            {
                int freq= hm.get(t);
                for (int j = 0; j < freq; j++) {
                    A.add(t);
                }
                cal=(t*freq);
                i=i+freq;
            }
            else
            {
                cal=t;
                A.add(t);
                i++;
            }

            sumA+= cal;
            sumB-=cal;

            if(sumA>sumB && A.size()<=(len/2))
            {
                Collections.sort(A);
                return A;
            }
        }

        return A;
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
