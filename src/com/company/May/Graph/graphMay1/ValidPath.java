package com.company.May.Graph.graphMay1;

/*Valid Path
Problem Description

There is a rectangle with left bottom as (0, 0) and right up as (x, y).

There are N circles such that their centers are inside the rectangle.

Radius of each circle is R. Now we need to find out if it is possible that we can move from (0, 0) to (x, y) without touching any circle.

Note : We can move from any cell to any of its 8 adjecent neighbours and we cannot move outside the boundary of the rectangle at any point of time.



Problem Constraints
0 <= x , y, R <= 100

1 <= N <= 1000

Center of each circle would lie within the grid



Input Format
1st argument given is an Integer x , denoted by A in input.

2nd argument given is an Integer y, denoted by B in input.

3rd argument given is an Integer N, number of circles, denoted by C in input.

4th argument given is an Integer R, radius of each circle, denoted by D in input.

5th argument given is an Array A of size N, denoted by E in input, where A[i] = x cordinate of ith circle

6th argument given is an Array B of size N, denoted by F in input, where B[i] = y cordinate of ith circle



Output Format
Return YES or NO depending on weather it is possible to reach cell (x,y) or not starting from (0,0).



Example Input
Input 1:

 x = 2
 y = 3
 N = 1
 R = 1
 A = [2]
 B = [3]
Input 2:

 x = 1
 y = 1
 N = 1
 R = 1
 A = [1]
 B = [1]


Example Output
Output 1:

 NO
Output 2:

 NO


Example Explanation
Explanation 1:

 There is NO valid path in this case
Explanation 2:

 There is NO valid path in this case*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class pair
{
    int x;
    int y;
    int d;

    public pair(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
public class ValidPath {
    public String solve(int A, int B, int C, int D, int[] E, int[] F) {

        int rowNbr[] = new int[] {  -1 , 0, 0, 1 };
        int colNbr[] = new int[] {   0, -1, 1, 0 };



        //creating a Array with all 1
        int arr[][]=new int[B+1][A+1];
        for (int[] a :arr) {
            Arrays.fill(a,1);
        }


        for (int i = 0; i < C; i++) {
            int ti=F[i];
            int tj=E[i];
            Queue<pair> q=new LinkedList<>();
            q.add(new pair(ti,tj,D) );
            //for every circle,calculating its distance and marking as zero
            while (!q.isEmpty())
            {
                pair p=q.peek();
                q.poll();

                //if at any time if end result or start index is 0
                //it means we cant reach from 0,0 to end,end
                if(arr[B][A]==0)
                    return "NO";
                if(arr[0][0]==0)
                    return "NO";

                if(p.d<0)
                    continue;
                if(isSafe(p.x,p.y,B,A))
                {
                    arr[p.x][p.y]=0;
                }

                for (int j = 0; j < rowNbr.length; j++) {
                    int tx=p.x+rowNbr[j];
                    int ty=p.y+colNbr[j];
                    if(isSafe(tx,ty,B,A) && p.d>0)
                    {
                        q.add(new pair(tx,ty,p.d-1));
                    }
                }
            }
        }

        if(arr[B][A]==0)
            return "NO";
        if(arr[0][0]==0)
            return "NO";
        String ans= isPathValid(arr,A,B );
        return ans;
    }


    //checking whether path exist
    private String isPathValid(int[][] arr, int A, int B) {
        int rowNbr[] = new int[] {  -1,-1 ,-1, 0, 0,  1,1,1 };
        int colNbr[] = new int[] {  -1, 0, 1,  -1, 1, -1,0,1 };
        boolean isVisited[][]=new boolean[B+1][A+1];
        Queue<pair> q=new LinkedList<>();
        q.add(new pair(0,0,-1));

        while (!q.isEmpty())
        {
            pair p=q.peek();
            q.poll();
            isVisited[p.x][p.y]=true;

            if(B==p.x && A==p.y)
            {
                return "YES";
            }
            for (int i = 0; i < rowNbr.length; i++) {
                int tx=p.x+rowNbr[i];
                int ty=p.y+colNbr[i];
                if(isSafe(tx,ty,B,A) )
                {
//                    System.out.println(tx+" "+ty);
                    if( isVisited[tx][ty]==false && arr[tx][ty]==1)
                    {
                        q.add(new pair(tx,ty, -1));
                    }

                }
            }
        }
        return "No";

    }

    private boolean isSafe(int x, int y, int a, int b) {
        return  (x>=0 && x<=a) &&( y>=0 && y<=b);
    }
}
