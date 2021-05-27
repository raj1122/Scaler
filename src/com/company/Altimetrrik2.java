package com.company;


import java.util.*;

public class Altimetrrik2 {


    /*Problem Statement :-
Steps :-
1. Create the int array and store 10 int value to array.
2. Define the start and end index input value from keyboard at run time.
3. Based on index value short the array elements dynamically.


Ex :-
int[] arr = {5,3,7,-4,0,8,3,-8,8,5}
input :- Start index = 7
input :- End index = 10
O/p = {5,3,7,-4,0,8,-8,3,5,8}


input :- Start index = 2
input :- End index = 5
O/p = {5,-4,0,3,  7,8,3,-8,8,5}*/


    public int[] sortGroupdWise(int a[],int start,int end)
    {


        int len=a.length;

//        if(!(start >=0  && end<=len))
//        {
//            System.out.println("query is not coreect");
//            return a;
//        }



        PriorityQueue<Integer> pq=new PriorityQueue<>();

        for (int i = start; i <=end ; i++) {
            pq.add(a[i]);
        }

        for (int i = start; i <=end ; i++) {
            a[i] = pq.peek();
            pq.poll();
        }

        for (int i = 0; i < len; i++) {
            System.out.print(a[i]+" ");
        }
        return a;
    }


    /*Problem Statement :-
How to print all permutation of a String? (No duplicate permutation should exist and the input may contain one or more white space.)
Write an efficient program to print all permutations of a given String . For example, if given input is "123" then your program should print all 6 permutations
e.g. "123", "132", "213", "231", "312" and "321".
*/
    public void printPermutae(String s)
    {
        ArrayList<Character> al=new ArrayList<>();
        HashSet<Character> hs=new HashSet<>();
        printPermutae1(s,hs,0,al);
    }

    public void printPermutae1(String s,HashSet<Character> hs,int idx,ArrayList<Character> al)
    {
        int len=s.length();

        //if all string traverse
        if(hs.size()==len)
        {

            for(int i=0;i<len;i++)
            {
                System.out.print(al.get(i));
            }
            System.out.println();
        }

        //iterate throught other string
        for(int i=0;i<len;i++)
        {
            char curr=s.charAt(i);
            if(!hs.contains(curr))
            {
                al.add(curr);
                hs.add(curr);
                printPermutae1(s,hs,i+1,al);
                hs.remove(curr);
                al.remove(al.size()-1);
            }
        }
    }
}
