package com.company.Apr.SegmentTreePck;

/*Power of 3
Problem Description

Given a binary string A of size N and an integer matrix B of size Q x 3.

Matrix B has Q queries:

For queries of type B[i][0] = 1, flip the value at index B[i][1] in A if and only if the value at that index is 0 and return -1.
For queries of type B[i][0] = 0, Return the value of the binary string from index B[i][1] to B[i][2] modulo 3.
Note: Rows are numbered from top to bottom and columns are numbered from left to right.



Problem Constraints
1 <= N <= 100000
1 <= Q <= 200000
1 <= B[i][1], B[i][2] <= N
B[i][1] <= B[i][2]



Input Format
The first argument given is the string A.
The second argument given is the integer matrix B of size Q * 3.



Output Format
Return an array of size Q where ith value is answer to ith query.



Example Input
Input 1:

 A = 10010
 B = [  [0, 3, 5]
        [0, 3, 4]
        [1, 2, -1]
        [0, 1, 5]
     ]
Input 2:

 A = 11111
 B = [
        [0, 2, 4]
        [1, 2, -1
        [0, 2, 4]]
     ]


Example Output
Output 1:

 [2, 1, -1, 2]
Output 2:

 [1, -1, 1]


Example Explanation
Explanation 1:

 For query 1, binary string from index 3 to 5 is 010 = 2. So 2 mod 3 = 2.
 For query 2, binary string from index 3 to 4 is 01 = 1. So 1 mod 3 = 1.
 After query 3, given string changes to 11010.
 For query 4, binary string from index 1 to 5 is 11010 = 26. So 26 mod 3 = 2.
 So, output array is [2, 1, -1, 2].
Explanation 2:

 For query 1, binary string from index 2 to 4 is 111 = 7. So 7 od 3 = 1.
 After query 2, string remains same as there is already 1 at index 2.
 For query 3, binary string from index 2 to 4 is 111 = 7. So 7 od 3 = 1.
 So, output array is [1, -1, 1].
 */

import com.company.Apr.SegmentTree2;

import java.util.ArrayList;

class SegmentTreePower {
    String st[];

    public void constructTree(String a, int size) {
        int h = (int) Math.ceil(Math.log(size+1) / Math.log(2));
        int max_size = 2 * (int) Math.pow(2, h) - 1;
        st = new String[max_size];
        constructTreeUtil(a, 0, size , 0);
    }

    private String constructTreeUtil(String  a, int ss, int se, int si) {
        if (ss == se) {
            st[si] = a.charAt(ss)+"";
            return st[si];
        }
        int mid = ss + (se - ss) / 2;
        int left_side = si * 2 + 1;
        int right_side = si * 2 + 2;
        String left_tree = constructTreeUtil(a, ss, mid, left_side);
        String right_tree = constructTreeUtil(a, mid + 1, se, right_side);
        st[si] = left_tree + right_tree;
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
            if(st[si].equals("0")) {
                st[si] = String.valueOf(elem);
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

public class Power3 {

    public ArrayList<Integer> solve(String A, ArrayList<ArrayList<Integer>> B) {
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
                long decimal=Long.parseLong(res,2);
//                System.out.println(decimal%3);
                ans.add( (int)decimal%3);
            }

        }
        return ans;
    }
}
