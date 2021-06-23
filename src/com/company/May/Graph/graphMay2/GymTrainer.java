package com.company.May.Graph.graphMay2;

import java.util.HashSet;

/**/
public class GymTrainer {

    class Subset
    {
        int parent;
        int rank;
        boolean isFound;
    }
    public int find(Subset arr[],int x)
    {
        if(arr[x].parent!=x)
        {
            arr[x].parent=find(arr,arr[x].parent);
        }
        return arr[x].parent;
    }


    public void union(Subset subset[],int x,int y)
    {
        int tx=find(subset,x);
        int ty=find(subset,y);
        if(subset[tx].rank>subset[ty].rank)
        {
            subset[ty].parent=tx;
        }
        else if(subset[tx].rank<subset[ty].rank)
        {
            subset[tx].parent=ty;
        }
        else
        {
            subset[ty].parent=tx;
            subset[tx].rank++;
        }
    }


    public int solve(int A, int[][] B, int[][] C) {

        Subset [] subset=new Subset[A+1];
        HashSet<Integer> walk=new HashSet<>();

        for (int i = 0; i <= A; i++) {
            subset[i]=new Subset();
            subset[i].parent=i;
            subset[i].rank=0;
            subset[i].isFound=false;
        }

        for (int i = 0; i < B.length; i++) {
            int x=B[i][0];
            int y=B[i][1];

            subset[x].isFound=true;
            subset[y].isFound=true;
            walk.add(x);
            walk.add(y);
            int tx= find(subset,x);
            int ty= find(subset,y);
            if(tx!=ty)
            {
                union(subset,tx,ty);
            }
        }

        for (int i = 0; i < C.length; i++) {

            int x=C[i][0];
            int y=C[i][1];
            if(walk.contains(x))
                return 0;

            if(walk.contains(y))
                return 0;

            x= find(subset,x);
            y= find(subset,y);


            if(x!=y)
            {
                union(subset,x,y);
            }
        }

        HashSet<Integer> hs=new HashSet<>();
        for (int i = 1; i <= A; i++) {
            int f=find(subset,subset[i].parent);
            hs.add(f);
        }
        int ans=hs.size();
        long MOD=(int)1e9+7;
        long t= (long) power(2,ans,MOD);
        return (int) t ;
    }

    static long power(long x,long y, long p)
    {
        long res = 1; // Initialize result

        // Update x if it is more
        // than or equal to p
        x = x % p;

        while (y > 0)
        {
            // If y is odd, multiply
            // x with the result
            if ((y & 1) > 0)
                res = (res * x) % p;

            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
        return res;
    }


}
