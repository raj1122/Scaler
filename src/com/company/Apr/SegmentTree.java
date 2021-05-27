package com.company.Apr;

import java.util.*;

class SegmentTreeNod {

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

        st[si] = Math.min(left_tree, right_tree);
        return st[si];
    }


    public void updateTree(int ss, int se, int si, int qs, int val) {
        if (qs < ss || qs > se || ss > se) {
            return;
        }

        if (ss == se) {
            st[si] = val;

            return;
        }

        int mid = (ss + se) / 2;

        int left_side = si * 2 + 1;
        int right_side = si * 2 + 2;
        updateTree(ss, mid, left_side, qs, val);
        updateTree(mid + 1, se, right_side, qs, val);


        st[si] = Math.min(st[left_side], st[right_side]);
        return;
    }

    public int RMQUtil(int ss, int se, int si, int qs, int qe) {
        if (qs <= ss && qe >= se)
            return st[si];

        if (se < qs || ss > qe)
            return Integer.MAX_VALUE;

        int mid = ss + (se - ss) / 2;
        int left_side = si * 2 + 1;
        int right_side = si * 2 + 2;
        int left_tree = RMQUtil(ss, mid, left_side, qs, qe);
        int right_tree = RMQUtil(mid + 1, se, right_side, qs, qe);

        return Math.min(left_tree, right_tree);
    }


}

class SegmentTreeNodBin {

    int st[];

    public void constructTree(int A,int elem) {
        //taking A+1 top handle large nodes
        int h = (int) Math.ceil(Math.log(A + 1) / Math.log(2));
        int max_size = 2 * (int) Math.pow(2, h) - 1;
        st = new int[max_size];
        constructTreeUtil(0, A, 0,elem);
    }

    private void constructTreeUtil(int ss, int se, int si,int elem) {
        //if index found ,directly make it 1
        if (ss == se) {
            st[si] = elem;
            return;
        }
        int mid = ss + (se - ss) / 2;
        int left_side = si * 2 + 1;
        int right_side = si * 2 + 2;
        //recursively call for left and mid
        //recursively call for mid+1 and right
        constructTreeUtil(ss, mid, left_side,elem);
        constructTreeUtil(mid + 1, se, right_side,elem);
        int left_tree = st[left_side];
        int right_tree = st[right_side];
        //sum of nodes of left and right side
        //and add it at curr idx
        st[si] = left_tree + right_tree;
    }


    public void updateTree(int ss, int se, int si, int qs,int elem,int query_type) {
        //if idx becomes outer
        if (qs < ss || qs > se || ss > se) {
            return;
        }

        //if index
        if (ss == se) {
            if(query_type==1){
                st[si]=st[si]+1;
            }
            else if(query_type==2){
                st[si]=st[si]-1;
                if(st[si]<0)
                    st[si]=0;
            }
            return;
        }

        int mid = (ss + se) / 2;

        int left_side = si * 2 + 1;
        int right_side = si * 2 + 2;
        updateTree(ss, mid, left_side, qs,elem,query_type);
        updateTree(mid + 1, se, right_side, qs,elem,query_type);


        //after making idex to 0,update  all parents to left and right

        st[si] = (st[left_side] + st[right_side]);
        return;
    }



    public int RMQUtilUpd(int ss, int se, int si, int qs) {
        while (ss != se) {
            //for very idx,i am taking left and right side and mid
            int lc = 2 * si + 1;
            int rc = 2 * si + 2;
            int mid = (ss + se) / 2;
            //if left side root is greater than qs then go to left side
            if (st[lc] >= qs) {
                si = lc;
                se = mid;
            } else {

                //if left side is smallelr it means all left value can be subtracted
                //and we can search for remaining nodes with same binary search
                si = rc;
                qs = qs - st[lc];
                ss = mid + 1;
            }
        }
        //if only one element is left we can send ss+1
        if (qs == 1)
            return ss + 1;
        return -1;
    }


}


class SegmentTreeBobQ{

    int st[];

    public void constructTree(int A,int elem) {
        //taking A+1 top handle large nodes
        int h = (int) Math.ceil(Math.log(A + 1) / Math.log(2));
        int max_size = 2 * (int) Math.pow(2, h) - 1;
        st = new int[max_size];
        constructTreeUtil(0, A, 0,elem);
    }

    private void constructTreeUtil(int ss, int se, int si,int elem) {
        //if index found ,directly make it 1
        if (ss == se) {
            st[si] = elem;
            return;
        }
        int mid = ss + (se - ss) / 2;
        int left_side = si * 2 + 1;
        int right_side = si * 2 + 2;
        //recursively call for left and mid
        //recursively call for mid+1 and right
        constructTreeUtil(ss, mid, left_side,elem);
        constructTreeUtil(mid + 1, se, right_side,elem);
        int left_tree = st[left_side];
        int right_tree = st[right_side];
        //sum of nodes of left and right side
        //and add it at curr idx
        st[si] = left_tree + right_tree;
    }


    public void updateTree(int ss, int se, int si, int qs,int elem,int query_type) {
        //if idx becomes outer
        if (qs < ss || qs > se || ss > se) {
            return;
        }

        //if index
        if (ss == se) {
            if(query_type==1){
                st[si]=st[si]+1;
            }
            else if(query_type==2){
                st[si]=st[si]-1;
                if(st[si]<0)
                    st[si]=0;
            }
            return;
        }

        int mid = (ss + se) / 2;

        int left_side = si * 2 + 1;
        int right_side = si * 2 + 2;
        updateTree(ss, mid, left_side, qs,elem,query_type);
        updateTree(mid + 1, se, right_side, qs,elem,query_type);


        //after making idex to 0,update  all parents to left and right

        st[si] = (st[left_side] + st[right_side]);
        return;
    }

    public  int range(int qL, int qR, int start, int end,  int pos) {

        if (qL <= start && qR >= end) {
            return st[pos];
        }

        if (end < qL || start > qR) {
            return 0;
        }

        int mid = (start + end)/2;
        int lcnt=range(qL, qR, start, mid, 2*pos+1);
        int rcnt=range(qL, qR, mid +1, end, 2*pos+2);
        return lcnt + rcnt;
    }

}
public class SegmentTree {

    /*Bob and Queries
Problem Description

Bob has an array A of N integers. Initially, all the elements of the array are zero. Bob asks you to perform Q operations on this array.

You have to perform three types of query, in each query you are given three integers x, y and z.

if x = 1: Update the value of A[y] to 2 * A[y] + 1.
if x = 2: Update the value A[y] to ⌊A[y]/2⌋ , where ⌊⌋ is Greatest Integer Function.
if x = 3: Take all the A[i] such that y <= i <= z and convert them into their corresponding binary strings. Now concatenate all the binary strings and find the total no. of '1' in the resulting string.
Queries are denoted by a 2-D array B of size M x 3 where B[i][0] denotes x, B[i][1] denotes y, B[i][2] denotes z.*/



    public ArrayList<Integer> BobQuery(int A, ArrayList<ArrayList<Integer>> B) {

        ArrayList<Integer> ans = new ArrayList<>();
        SegmentTreeBobQ sgm = new SegmentTreeBobQ();
        sgm.constructTree(A - 1,0);

        for (int i = 0; i < B.size(); i++) {
            int x=B.get(i).get(0);
            int y=B.get(i).get(1);
            int z=B.get(i).get(2);
            if(x==1)
            {
                sgm.updateTree(0, A - 1, 0, y - 1,1,1);
            }
            else if (x==2)
            {
                sgm.updateTree(0, A - 1, 0, y - 1,-1,2);
            }
            else if(x==3)
            {
                int t=sgm.range( y-1,  z-1,  0,  A-1,   0);
//                System.out.println(t);
                ans.add(t);
            }

        }
        return ans;
    }

    /*Binary updates
Problem Description

Given an integer A denoting the size of the array consisting all ones.

You are given Q queries, for each query there are two integer x and y:

If x is 0, then update the value of array at index y to 0.
If x is 1, then output the index of yth one in the array. If there is no such index then output -1.
NOTE 1: There will atleast 1 query where value of x is 1.*/
    public ArrayList<Integer> BinaryUpdates(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        SegmentTreeNodBin sgm = new SegmentTreeNodBin();
        sgm.constructTree(A - 1,1);

        for (int i = 0; i < B.size(); i++) {

            int opr = B.get(i).get(0);
            int qs = B.get(i).get(1);
            if (opr == 0) {
                sgm.updateTree(0, A - 1, 0, qs - 1,0,1);
            } else {
                int disp = sgm.RMQUtilUpd(0, A - 1, 0, qs);
                ans.add(disp);
            }
        }
        return ans;
    }

    /*Range Minimum Query

Problem Description

Given an integer array A of size N.

You have to perform two types of query, in each query you are given three integers x,y,z.

If x = 0, then update A[y] = z.
If x = 1, then output the minimum element in the array A between index y and z inclusive.
Queries are denoted by a 2-D array B of size M x 3 where B[i][0] denotes x, B[i][1] denotes y, B[i][2] denotes z.*/


    public ArrayList<Integer> RangeMinQuery(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        int size = A.size();
        SegmentTreeNod sgm = new SegmentTreeNod();
        sgm.constructTree(A, size);
        for (int i = 0; i < B.size(); i++) {

            int opr = B.get(i).get(0);
            int qs = B.get(i).get(1);
            int qe = B.get(i).get(2);
            if (opr == 0) {
                sgm.updateTree(0, A.size() - 1, 0, qs - 1, qe);
            } else {
                int disp = sgm.RMQUtil(0, size - 1, 0, qs - 1, qe - 1);
                ans.add(disp);
            }
        }
        return ans;

    }


    public void executeFn() {
        Integer[] arr1 = {7, 2, 19, 24, 5, 24};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);
        ArrayList<ArrayList<Integer>> dblArr = creatDbtArr1();
//        RangeMinQuery(levelTree,dblArr);
//        BinaryUpdates(13, dblArr);

        BobQuery(5,dblArr);

    }

    private static ArrayList<ArrayList<Integer>> creatDbtArr1() {
        ArrayList<ArrayList<Integer>> dblArr = new ArrayList<>();

        Integer arr[][] = new Integer[][]
                {
                        {1, 1, -1},
                        {1, 2, -1},
                        {1, 3, -1},
                        {3, 1, 3},
                        {3, 2, 4}
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
}
