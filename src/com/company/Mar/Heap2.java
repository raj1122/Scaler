package com.company.Mar;

import java.util.*;

public class Heap2 {


    public void executefn() {

        Integer[] arr1 = {2, 1, 17, 10, 21, 95};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);


        Integer[] arr2 = { 2, 4, 1, 1};
        ArrayList<Integer> levelTree2 = new ArrayList<>();
        Collections.addAll(levelTree2, arr2);
        int[] arr22 = new int[]{-2, -3, 2, 4};
//        nmaxpair(levelTree,levelTree2);
//        PlacesApart(levelTree,2);
        keepSwapping("raman",1,4);
//        solve(arr2);
    }

    /*KEEP SWAPPING
Given a string of lowercase alphabets A of size N and two integers B and C.

You have to perform B swaps, Where ith swap = swap(A[i%N], A[ (i + C)%N ]).

Return the final string A after B swaps.

NOTE: String A is 0 based indexed.

NOTE: Swaps are 1 based indexed.

*/
    public String keepSwapping(String A, int B, int C) {

        int i=1;
        StringBuilder sb = new StringBuilder(A);
        int N=A.length();
        while(B-->0)
        {
            char c=sb.charAt(i%N);
            char setChar=sb.charAt((i+C)%N);
            sb.setCharAt( (i%N) , setChar);
            sb.setCharAt( (i+C)%N, c);
            i++;
        }
        return sb.toString();

    }

    /*K Places Apart
Problem Description

Given N persons with different priorities standing in a queue.

Queue is following a property that Each person is standing atmost B places away from it's sorted position.

Your task is to sort the queue in the increasing order of priorities.

NOTE:

No two persons can have the same priority.
Use the property of the queue to sort the queue with complexity O(NlogB).*/

    public ArrayList<Integer> PlacesApart(ArrayList<Integer> A, int B) {
        ArrayList<Integer> ans =new ArrayList<>();
        PriorityQueue<pairTwo> pq=new PriorityQueue<>();
        for (int i = 0; i < A.size(); i++) {
            pq.add(new pairTwo(A.get(i),A.get(i),i));
        }
        while(!pq.isEmpty())
        {
            ans.add(pq.peek().sum);
            pq.poll();
        }


        return ans;
    }


    /*N max pair combinations
Problem Description

Given two integers arrays A and B of size N each.

Find the maximum N elements from the sum combinations (Ai + Bj) formed from elements in array A and B.*/

    class pairTwo implements Comparable<pairTwo>{
        int sum;
        int l;
        int r;

        public pairTwo(int sum, int l, int r) {
            this.sum = sum;
            this.l = l;
            this.r = r;
        }

        @Override
        public int hashCode() {
            return Objects.hash(l,r);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof pairTwo)) {
                return false;
            }
            pairTwo obj = (pairTwo)o;
            return (l == obj.l && r == obj.r);
        }
        @Override
        public int compareTo(pairTwo o) {
            return Integer.compare(this.sum,o.sum);
        }
    }

    public ArrayList<Integer> nmaxpair(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<pairTwo> pq=new PriorityQueue<>();
        Collections.sort(A);
        Collections.sort(B);

        HashSet<pairTwo> hs=new HashSet<>();
        int size=A.size();
        int l=size-1;
        int r=size-1;
        int t=A.get(l)+B.get(r);
        pq.add(new pairTwo(t,l,r));
        hs.add(new pairTwo(0,l,r));

        for (int i = 0; i < size; i++) {
            pairTwo it=pq.poll();
            ans.add(it.sum);
            l=it.l-1;
            r=it.r;
            if(l>=0 && r>=0 && !hs.contains(new pairTwo(0,l,r)) )
            {
                t=A.get(l)+B.get(r);
                pq.add(new pairTwo(t,l,r));
                hs.add(new pairTwo(0,l,r));
            }

            l=it.l;
            r=it.r-1;

            if(l>=0 && r>=0 && !hs.contains(new pairTwo(0,l,r)) )
            {
                t=A.get(l)+B.get(r);
                pq.add(new pairTwo(t,l,r));
                hs.add(new pairTwo(0,l,r));
            }
        }

        return ans;
    }

    static long  dp[]=new long[1005];     /* dp[i] = number of max heaps for i distinct integers */
    static long  dp1[]=new long[1005];    /*dp1[i]=number of max heaps for i-1 distinct integers */
    static long [][]combs=new long[1005][1005];
    static long MOD = 1000000007;
    static long[] log2 = new long[1005];

    public int solve(int[] A) {
        int size = A.length;


        for(int i=0;i<=size;i++)
            dp[i]=-1;

        for(int i=0;i<=size;i++)
            for(int j=0;j<=size;j++)
                combs[i][j] = -1;


        // for each power of two find logarithm
        int currLog2 = -1;
        int currPower2 = 1;
        for (int i = 1; i <= 47; i++) {
            if (currPower2 == i) {
                currLog2++;
                currPower2 *= 2;
            }
            log2[i] = currLog2;
        }

        int max = -1;
        int min = 10000000;
        int maxcount = 0;
        int mincount = 0;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            if (A[i] < min) {
                min = A[i];
                mincount = 1;
            } else if (A[i] == min) {
                mincount++;
            }
            if (A[i] > max) {
                max = A[i];
                maxcount = 1;
            } else if (A[i] == max) {
                maxcount++;
            }
        }

        long ans = 0;
        if (maxcount == 2)
            ans = getNumberOfMaxHeaps(size);
        else
            ans = getNumberOfMaxHeaps2(size);

        return (int) ans;
    }


    public  long  getNumberOfMaxHeaps2(long n)
    {
        if(n<2)
            return 0;
        if(n<4)
            return 1;
        if(n==4)
            return 2;
        if(n==5)
            return 4;
        if(dp1[(int)n]!=0)
            return dp1[(int)n];
        long l=getL(n);

        long r=n-l-1;
        long ans=(((choose((int)n-3,(int)l-2)*getNumberOfMaxHeaps2(l))%MOD)*getNumberOfMaxHeaps(r))%MOD;
        ans=(ans+ (((choose((int)n-3,(int)r-2)*getNumberOfMaxHeaps(l))%MOD)*getNumberOfMaxHeaps2(r))%MOD)%MOD;
        ans=(ans+ (((choose((int)n-3,(int)l-1)*getNumberOfMaxHeaps(l))%MOD)*getNumberOfMaxHeaps(r))%MOD)%MOD;
        dp1[(int)n]=ans;
        return ans;
    }

    public long getL(long n)
    {
        if(n==1)
            return 0;

        long h = log2[(int)n];
        long p = n - ((1<<(h)) - 1);
        int m = (1<<h);
        if(p>=(m/2))
            return (1<<(h)) - 1;
        else
            return (1<<(h)) - 1 - ((m/2) - p);
    }

    public long  getNumberOfMaxHeaps(long  n)
    {
        long MOD=1000000007;
        if(n<=1)
            return 1;

        if(dp[(int)n]!=-1)
            return dp[(int)n];

        long L = getL(n);
        long ans = (choose( (int)n-1, (int) L)*getNumberOfMaxHeaps(L))%MOD*(getNumberOfMaxHeaps(n-1-L));
        ans%=MOD;
        dp[(int)n] = ans;
        return ans;
    }


    public  long choose(long n,long k)
    {
        long MOD=1000000007;
        if(k>n)
            return 0;
        if(n<=1)
            return 1;
        if(k==0)
            return 1;

        if(combs[(int )n][(int)k]!=-1)
            return combs[(int )n][(int)k];
        long  answer = choose(n-1,k-1) + choose(n-1,k);
        answer%=MOD;
        combs[(int)n][(int)k] = answer;
        return answer;
    }

}
