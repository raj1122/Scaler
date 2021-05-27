package com.company.Apr;


import java.util.*;

class dataD{
    String name;
    int data;

    public dataD(String name, int data) {
        this.name = name;
        this.data = data;
    }
}
public class HmUser {


     LinkedList<dataD>[] hm1=new LinkedList[10];

    public HmUser() {

        for (int i = 0; i < 10; i++) {
            hm1[i]=new LinkedList();
        }
    }

    public int getHm(String key)
    {
        int findIdx=0;

        for (int i = 0; i < key.length(); i++) {
            int t=(int) (key.charAt(i));
            findIdx = findIdx + t;
        }

        int resIdx=(findIdx%10);

        int ans=-1;
        synchronized (hm1[resIdx])
        {
            for (int i = 0; i < hm1[resIdx].size(); i++) {

                dataD itr=hm1[resIdx].get(i);
                if(itr.name.equals(key))
                {
                    ans=itr.data;
                    break;
                }
            }
        }
        return ans;
    }


    public boolean putObject(String key,int value)
    {
        int findIdx=0;

        for (int i = 0; i < key.length(); i++) {
            int t=(int) (key.charAt(i));
            findIdx = findIdx + t;
        }

        int resIdx=(findIdx%10);

        int ans=-1;
        synchronized (hm1[resIdx])
        {
            for (int i = 0; i < hm1[resIdx].size(); i++) {

                dataD itr=hm1[resIdx].get(i);
                if(itr.name.equals(key))
                {
                   hm1[resIdx].set(i , new dataD(key,value));
                   ans=value;
                   break;
                }
            }
        }
        if(ans!=-1)
        {
            hm1[resIdx].add(new dataD(key,value));
            return true;
        }
        else
        {
            return false;
        }
    }


}
