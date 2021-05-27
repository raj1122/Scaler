package com.company.Apr;


import java.util.*;

public class SegmentTree2 {

    private static ArrayList<ArrayList<Integer>> creatDbtArr1() {

        ArrayList<ArrayList<Integer>> dblArr=new ArrayList<>();
        Integer arr[][] = new Integer[][]
                {
                        {0, 3, 5},
                        {0, 3, 4},
                        {1, 2, -1},
                        {0, 1, 5}
                };

        for (int i = 0; i < arr.length; i++) {

            ArrayList<Integer> al1 = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                al1.add(arr[i][j]);

            }
            dblArr.add(al1);

        }

        return dblArr;
    }


    public void executeFn() {

        Integer[] a = {7, 15, 11};
        ArrayList<Integer> a1 = new ArrayList<>();
        Collections.addAll(a1, a);

        String[] b = { "C","A"};
        ArrayList<String> b1 = new ArrayList<>();
        Collections.addAll(b1, b);

        Integer[] c = {2, 2};
        ArrayList<Integer> c1 = new ArrayList<>();
        Collections.addAll(c1, c);

        Integer[] d = {9, 3};
        ArrayList<Integer> d1 = new ArrayList<>();
        Collections.addAll(d1, d);

        ArrayList<ArrayList<Integer>> dblArr= creatDbtArr1();
//        PrimeSegm(a1,b1,c1,d1);
        power3("10010",dblArr);



    }

    /*Power of 3
Problem Description

Given a binary string A of size N and an integer matrix B of size Q x 3.

Matrix B has Q queries:

For queries of type B[i][0] = 1, flip the value at index B[i][1] in A if and only if the value at that index is 0 and return -1.
For queries of type B[i][0] = 0, Return the value of the binary string from index B[i][1] to B[i][2] modulo 3.
Note: Rows are numbered from top to bottom and columns are numbered from left to right.*/

    class SegmentTreePower {
        int st[];

        public void constructTree(String a, int size) {
            int h = (int) Math.ceil(Math.log(size) / Math.log(2));
            int max_size = 2 * (int) Math.pow(2, h) - 1;
            st = new int[max_size];
            constructTreeUtil(a, 0, size - 1, 0);
        }

        private int constructTreeUtil(String  a, int ss, int se, int si) {
            if (ss == se) {
                st[si] = (a.charAt(ss)=='1')?1:0;
                return st[si];
            }
            int mid = ss + (se - ss) / 2;
            int left_side = si * 2 + 1;
            int right_side = si * 2 + 2;
            int left_tree = constructTreeUtil(a, ss, mid, left_side);
            int right_tree = constructTreeUtil(a, mid + 1, se, right_side);

//            st[si] = left_tree + right_tree;
            return st[si];
        }

        public String RMQUtil(int ss, int se, int si, int qs, int qe) {
            if (qs <= ss && qe >= se)
                return String.valueOf(st[si] );

            if (se < qs || ss > qe)
                return "";

            int mid = ss + (se - ss) / 2;
            int left_side = si * 2 + 1;
            int right_side = si * 2 + 2;
            String left_tree = RMQUtil(ss, mid, left_side, qs, qe);
            String right_tree = RMQUtil(mid + 1, se, right_side, qs, qe);
            return (left_tree+ right_tree);
        }


        public void updateTree(int ss, int se, int si, int qs,int elem) {
            //if idx becomes outer
            if (qs < ss || qs > se || ss > se) {
                return ;
            }

            //if index
            if (ss == se) {
                if(st[si]==0) {
                    st[si] = elem;
                }
                return;
            }

            int mid = (ss + se) / 2;

            int left_side = si * 2 + 1;
            int right_side = si * 2 + 2;
            updateTree(ss, mid, left_side, qs,elem);
            updateTree(mid + 1, se, right_side, qs,elem);


            //after making idex to 0,update  all parents to left and right

            st[si] = (st[left_side] + st[right_side]);
            return;
        }

    }

    public ArrayList<Integer> power3(String A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Integer> ans=new ArrayList<>();
        SegmentTreePower sgp=new SegmentTreePower();
        sgp.constructTree(A,A.length()-1);

        for (int i = 0; i < B.size(); i++) {
            int query=B.get(i).get(0);
            int qs=B.get(i).get(1);
            int qe=B.get(i).get(2);

            if(query==1)
            {
                sgp.updateTree(0,A.length()-1,0,qs-1,1);
            }
            else if(query==0)
            {
                String res=sgp.RMQUtil(0,A.length()-1,0,qs-1,qe-1);
                System.out.println(res);
            }

        }
        return ans;
    }







        /*Count the primes
Problem Description

Given an array A, containing positive integers. You need to perform some queries on it.

You will be given Q Queries. Each query will have one string and two integers. Each Query can be of two type :

"C" X Y - Here "C" says that we need to Change the integer at position X to integer Y.
"A" X Y - Here "A" say that we are Asked number of primes in the the range : [X, Y] (inclusive)
For each Query of type 2, you need to calculate an integer denoting the answer to it.

NOTE:

Assume 1-indexing for all queries.
Your code will be run on multiple test cases (< 10). Make sure to come up with an optimised solution and take care of clearing global variables.*/


    class SegmentTreePrime
    {
        int st[];
        public void constructTree(ArrayList<Integer> a, int size) {
            int h = (int) Math.ceil(Math.log(size) / Math.log(2));
            int max_size = 2 * (int) Math.pow(2, h) - 1;
            st = new int[max_size];
            constructTreeUtil(a, 0, size - 1, 0);
        }
        private int constructTreeUtil(ArrayList<Integer> a, int ss, int se, int si) {
            if (ss == se) {
                st[si] = a.get(ss);
                return a.get(ss);
            }
            int mid = ss + (se - ss) / 2;
            int left_side = si * 2 + 1;
            int right_side = si * 2 + 2;
            int left_tree = constructTreeUtil(a, ss, mid, left_side);
            int right_tree = constructTreeUtil(a, mid + 1, se, right_side);

            st[si] = left_tree + right_tree;
            return st[si];
        }

        public int RMQUtil(int ss, int se, int si, int qs, int qe) {
            if (qs <= ss && qe >= se)
                return st[si];

            if (se < qs || ss > qe)
                return 0;

            int mid = ss + (se - ss) / 2;
            int left_side = si * 2 + 1;
            int right_side = si * 2 + 2;
            int left_tree = RMQUtil(ss, mid, left_side, qs, qe);
            int right_tree = RMQUtil(mid + 1, se, right_side, qs, qe);

            return (left_tree+ right_tree);
        }

        public void updateTree(int ss, int se, int si, int qs,int elem) {
            //if idx becomes outer
            if (qs < ss || qs > se || ss > se) {
                return;
            }

            //if index
            if (ss == se) {
                st[si]=elem;
                return;
            }

            int mid = (ss + se) / 2;

            int left_side = si * 2 + 1;
            int right_side = si * 2 + 2;
            updateTree(ss, mid, left_side, qs,elem);
            updateTree(mid + 1, se, right_side, qs,elem);


            //after making idex to 0,update  all parents to left and right

            st[si] = (st[left_side] + st[right_side]);
            return;
        }

    }


    static boolean isPrime(int n)
    {
        // Corner cases
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }


    public ArrayList<Integer> PrimeSegm(ArrayList<Integer> A, ArrayList<String> B, ArrayList<Integer> C, ArrayList<Integer> D) {

        ArrayList<Integer> ans =new ArrayList<>();
        ArrayList<Integer> a1 =new ArrayList<>();
        SegmentTreePrime sgp=new SegmentTreePrime();
        for (int i = 0; i < A.size(); i++) {
            if(isPrime(A.get(i)))
            {
                a1.add(1);
            }
            else
            {
                a1.add(0);
            }
        }

        sgp.constructTree(a1,a1.size());

        for (int i = 0; i < B.size(); i++) {
            if(B.get(i).equals("A"))
            {
                int qs=C.get(i);
                int qe=D.get(i);
                int res=sgp.RMQUtil(0,a1.size()-1,0,qs-1,qe-1);
                ans.add(res);

            }
            else if(B.get(i).equals("C"))
            {

                int qi=C.get(i);
                if(isPrime(D.get(i)))
                    sgp.updateTree(0,a1.size()-1,0,qi-1,1);
                else
                    sgp.updateTree(0,a1.size()-1,0,qi-1,0);
            }

        }

        return ans;
    }
}
