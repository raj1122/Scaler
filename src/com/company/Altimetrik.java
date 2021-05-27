package com.company;

import java.util.*;
/*
* list of number acesnding order
*
* 1,2,3,4,5
*
*
* 4
* l=3
* h=4
* mid=3
* */


public class Altimetrik {

    public int findBin(int a[],int num)
    {
        int l=0;
        int h=a.length-1;
        while(l<h)
        {
            int mid= (l+h)/2;
            if(a[mid]==num)
            {
                return mid;
            }
            else  if(a[mid]>num)
            {
                h=mid-1;
            }
            else
            {
                l=mid+1;
            }
        }
        return -1;
    }


    public boolean fn(int[] arr, int n, int sum, HashSet<ArrayList<Integer>> hs, ArrayList<Integer> al)
    {
        if(sum==0)
        {
            for (int a:al) {
                System.out.print(a);
            }

            System.out.println();
            return true;
        }

        if(n==0)
        {
            return false;
        }

        if((sum-arr[n-1])>=0) {
            al.add(arr[n-1]);
            fn(arr, n, sum - arr[n - 1], hs, al);
            al.remove(al.size()-1);
        }
        fn(arr,n-1,sum, hs, al);
        return false;
    }


    /*
    * 4,5,6
    * 4,6,5
    * 564
    * 546
    * 645
    * 654
    *
    * */

    public void permutate(ArrayList<Integer> A)
    {
        HashSet<Integer> hs=new HashSet<>();//which number not yet traversed
        ArrayList<Integer> ans=new ArrayList<>();//permutation store
        permutate1(A,hs,ans,0);

    }

    private void permutate1(ArrayList<Integer> a, HashSet<Integer> hs, ArrayList<Integer> ans, int idx) {

        if(ans.size()==a.size())  //exit
        {
            for (int i = 0; i < ans.size(); i++) {
                System.out.print(ans.get(i));
            }

            System.out.println();

            return;
        }
        for (int i = 0; i < a.size(); i++) {
            int t=a.get(i);  //4,5,
            if(!hs.contains(t) )
            {
                //4,
                hs.add(t);
                ans.add(t);
                //i=1
                permutate1(a,hs,ans,i+1);
                //backtrack
                hs.remove(t);  //4,
                ans.remove(ans.size()-1);
            }
        }
    }


}
