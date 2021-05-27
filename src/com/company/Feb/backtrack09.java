package com.company.Feb;

import java.util.*;

public class backtrack09 {


    public void executeFn() {
        int[][] arr =
                {
                        {7},
                        {-4},
                        {-5},
                        {-6}
                };
        verHorSum(4, arr, 0);
    }

    /*Vertical and Horizontal Sums
Problem Description

Given a matrix B, of size N x M, you are allowed to do at most A special operations on this grid such that there is no vertical or horizontal contiguous subarray that has a sum greater than C.

A special operation involves multiplying any element by -1 i.e. changing its sign.

Return 1 if it is possible to achieve the answer, otherwise 0.*/
    public int verHorSum(int A, int[][] B, int C) {
        int ans = verHorSum1(A, B, C);
        return ans;
    }

    private int verHorSum1(int flips, int[][] mat, int maxSum) {

        int ans = 1;

        if (flips == -1)
            return 0;

        int row = mat.length;
        int col = mat[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int sum = 0;
                for (int k = j; k < col; k++) {
                    sum += mat[i][k];

                    if (sum > maxSum) {
                        ans = 0;
                        for (int l = j; l <= k; l++) {
                            if (mat[i][l] > 0) {
                                mat[i][l] = mat[i][l] * -1;
                                ans = ans | verHorSum1(flips - 1, mat, maxSum);
                                mat[i][l] = mat[i][l] * -1;
                            }
                        }
                        return ans;
                    }

                }
            }

        }

        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                int sum = 0;
                for (int k = i; k < row; k++) {
                    sum += mat[k][j];
                    if (sum > maxSum) {
                        ans = 0;
                        for (int l = i; l <= k; l++) {
                            if (mat[l][j] > 0) {
                                mat[l][j] = mat[l][j] * -1;
                                ans = ans | verHorSum1(flips - 1, mat, maxSum);
                                mat[l][j] = mat[l][j] * -1;
                            }
                        }
                        return ans;
                    }
                }
            }
        }

        return ans;

    }

    private boolean checkwholeArr(ArrayList<ArrayList<Integer>> b, int C) {

        int sum = 0;
        for (int i = 0; i < b.size(); i++) {
            sum = 0;
            for (int j = 0; j < b.get(i).size(); j++) {
                sum = sum + b.get(i).get(j);
            }

            if (sum > C)
                return false;
        }

        for (int i = 0; i < b.get(0).size(); i++) {
            sum = 0;
            for (int j = 0; j < b.size(); j++) {
                sum = sum + b.get(j).get(i);
            }

            if (sum > C)
                return false;

        }


        return true;
    }

    private boolean check(ArrayList<ArrayList<Integer>> b, int C, int row, int col) {

        int sum = 0;
        //rowcheck  or vertical check
        for (int i = 0; i < b.size(); i++) {
            sum = sum + b.get(i).get(col);
        }

        if (sum > C)
            return false;
        //horizontal sum check
        sum = 0;
        for (int i = 0; i < b.size(); i++) {
            sum = sum + b.get(row).get(i);
        }
        if (sum > C)
            return false;


        return true;
    }

    /*Palindrome Partitioning
Problem Description

Given a string A, partition A such that every string of the partition is a palindrome.

Return all possible palindrome partitioning of A.

Ordering the results in the answer : Entry i will come before Entry j if :
len(Entryi[0]) < len(Entryj[0]) OR
(len(Entryi[0]) == len(Entryj[0]) AND len(Entryi[1]) < len(Entryj[1])) OR * * *
(len(Entryi[0]) == len(Entryj[0]) AND ... len(Entryi[k] < len(Entryj[k]))*/

    public ArrayList<ArrayList<String>> partition(String a) {
        Stack<String> ans = new Stack<>();
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        partition1(a, "", res, ans);
        return res;
    }

    private void partition1(String a, String asf, ArrayList<ArrayList<String>> res, Stack<String> ans) {
        if (a.length() == 0) {
//            System.out.println(ans.toString());
            res.add(new ArrayList<>(ans));
            return;
        }
        for (int i = 0; i < a.length(); i++) {
            String prefix = a.substring(0, i + 1);
            String retOfString = a.substring(i + 1);
            if (isPalindrome(prefix)) {
                ans.push(prefix);
                partition1(retOfString, asf + prefix, res, ans);
                ans.pop();
            }

        }

    }

    public boolean isPalindrome(String input) {
        int start = 0;
        int end = input.length() - 1;
        while (start < end) {
            if (input.charAt(start++) != input.charAt(end--))
                return false;
        }
        return true;
    }

    /*Remove Invalid Parentheses
Problem Description

Given a string A consisting of lowercase English alphabets and parentheses '(' and ')'. Remove the minimum number of invalid parentheses in order to make the input string valid.

Return all possible results.

You can return the results in any order.*/

    public ArrayList<String> InvalidParentheses(String A) {


        ArrayList<String> res = new ArrayList<>();
        InvalidParentheses1(A, 0, 0, res, new char[]{'(', ')'});
        return res;

    }

    private void InvalidParentheses1(String s, int left, int right, List<String> res, char[] pars) {
        int stack = 0;
        int n = s.length();
        for (; right < n; right++) {
            char c = s.charAt(right);
            if (c == pars[0]) {
                stack++;
            } else if (c == pars[1]) {
                stack--;
            }

            if (stack < 0) break;
        }

        if (stack < 0) {
            for (; left <= right; left++) {
                char c = s.charAt(left);
                if (c != pars[1]) continue;
                if (left > 1 && s.charAt(left) == s.charAt(left - 1)) continue;
                InvalidParentheses1(s.substring(0, left) + s.substring(left + 1), left, right, res, pars);
            }
        } else if (stack > 0) {
            InvalidParentheses1(new StringBuilder(s).reverse().toString(), 0, 0, res, new char[]{')', '('});
        } else {
            res.add(pars[0] == '(' ? s : new StringBuilder(s).reverse().toString());
        }
    }


    /*Unique Paths III
Problem Description

Given a matrix of integers A of size N x M . There are 4 types of squares in it:

1. 1 represents the starting square.  There is exactly one starting square.
2. 2 represents the ending square.  There is exactly one ending square.
3. 0 represents empty squares we can walk over.
4. -1 represents obstacles that we cannot walk over.
Find and return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

Note: Rows are numbered from top to bottom and columns are numbered from left to right.*/

    public int UniquePaths(ArrayList<ArrayList<Integer>> A) {
        int zero = 0;
        int sx = 0;
        int sy = 0;

        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(i).size(); j++) {
                if (A.get(i).get(j) == 0)
                    zero++;
                else if (A.get(i).get(j) == 1) {
                    sx = i;
                    sy = j;
                }
            }

        }
        return UniquePaths1(A, sx, sy, zero);
    }


    private int UniquePaths1(ArrayList<ArrayList<Integer>> a, int x, int y, int zero) {

        if (x < 0 || x >= a.size() || y < 0 || y >= a.get(0).size() || (a.get(x).get(y) == -1))
            return 0;
        if (a.get(x).get(y) == 2) {
            return zero == -1 ? 1 : 0;
        }

        a.get(x).set(y, -1);
        zero--;


        int totPath = (
                UniquePaths1(a, x + 1, y, zero) + UniquePaths1(a, x - 1, y, zero)
                        + UniquePaths1(a, x, y + 1, zero) +
                        UniquePaths1(a, x, y - 1, zero)
        );
        a.get(x).set(y, 0);
        zero++;

        return totPath;

    }



    /*Combination Sum II
Problem Description

Given an array of size N of candidate numbers A and a target number B. Return all unique combinations in A where the candidate numbers sums to B.

Each number in A may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.*/

    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Collections.sort(a);
        Stack<Integer> st = new Stack<>();
        HashSet<ArrayList<Integer>> hs = new HashSet<>();
        combinationSum1(res, hs, st, a, b, 0, 0);

        return res;
    }

    private void combinationSum1(ArrayList<ArrayList<Integer>> res, HashSet<ArrayList<Integer>> hs, Stack<Integer> st, ArrayList<Integer> a, int b, int idx, int sum) {

        if (sum == b && (!hs.contains(st))) {
            res.add(new ArrayList<>(st));
            hs.add(new ArrayList<>(st));
            return;
        }


        for (int i = idx; i < a.size(); i++) {

            if (sum + a.get(i) <= b) {

                swap(a, idx, i);
                st.push(a.get(idx));
                combinationSum1(res, hs, st, a, b, i + 1, sum + a.get(idx));
                swap(a, idx, i);
                st.pop();

            }

        }
    }

    public void swap(ArrayList<Integer> a, int i, int j) {
        int temp = a.get(i);
        int t1 = a.get(j);
        a.set(i, t1);
        a.set(j, temp);
    }

    /*NQueens
Problem Description

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.*/

    public ArrayList<ArrayList<String>> solveNQueens(int a) {
        ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
        ArrayList<String> ans = new ArrayList<>();

        if (a >= 2 && a <= 3)
            return board;

        for (int j = 0; j < a; j++) {
            if (a == 1)
                ans.add("Q");
            else
                ans.add(".");
        }

        for (int i = 0; i < a; i++) {
            board.add(new ArrayList<>(ans));
        }


        if (a == 1)
            return board;

        ArrayList<ArrayList<String>> disSol = new ArrayList<>();
        solveNQueens1(board, disSol, 0, a);

        return disSol;
    }

    boolean solveNQueens1(ArrayList<ArrayList<String>> board, ArrayList<ArrayList<String>> disSol, int col, int N) {
        if (col >= N) {
            ArrayList<String> board1 = new ArrayList<String>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {


                for (int j = 0; j < N; j++) {
                    String t = board.get(i).get(j);
                    sb.append(t);
                }
                sb.append(" ");

            }
//            sb.append(" ");
            board1.add(sb.toString().substring(0, sb.length()));
//            System.out.println(sb.toString());
            disSol.add(board1);
            return true;
        }

        for (int i = 0; i < N; i++) {

            if (isSafe(board, i, col, N)) {
                board.get(i).set(col, "Q");

                solveNQueens1(board, disSol, col + 1, N);
                // if (solveNQueens1(board, disSol, col + 1,N) == true) {

                // }

                board.get(i).set(col, ".");// BACKTRACK
            }
        }

        /* If the queen can not be placed in any row in
           this colum col, then return false */
        return false;
    }


    boolean isSafe(ArrayList<ArrayList<String>> board, int row, int col, int N) {
        int i, j;

        /* Check this row on left side */
        for (i = 0; i < col; i++) {
            if (board.get(row).get(i).equals("Q"))
                return false;
        }

        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).get(j).equals("Q"))
                return false;
        }

        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (board.get(i).get(j).equals("Q"))
                return false;
        }

        return true;
    }












    /*Sudoku
Problem Description

Write a program to solve a Sudoku puzzle by filling the empty cells. Empty cells are indicated by the character '.' You may assume that there will be only one unique solution.*/

    public void solveSudoku(ArrayList<ArrayList<Character>> a) {
        solveSudoku1(a, 0, 0);

//        System.out.println("a");
    }

    private boolean solveSudoku1(ArrayList<ArrayList<Character>> a, int r, int c) {

        if (c >= a.size()) {
            c = 0;
            r++;
        }
        if (r >= a.size())
            return true;

        char chNum = a.get(r).get(c);
        int n = (int) chNum - '0';
        if (n > 0)
            return solveSudoku1(a, r, c + 1);

        for (int i = 1; i <= 9; i++) {
            if (SudokuCheck(a, r, c, i)) {
                char ch = (char) (i + '0');
                a.get(r).set(c, ch);
                if (solveSudoku1(a, r, c + 1))
                    return true;
            }

            a.get(r).set(c, '.');


        }


        return false;
    }

    public boolean SudokuCheck(ArrayList<ArrayList<Character>> a, int r, int c, int num) {
        char ch = (char) (num + '0');
        int size = a.size();
        for (int i = 0; i < a.size(); i++) {
            if ((a.get(r).get(i) == ch) || (a.get(i).get(c) == ch))
                return false;
        }
        int x = (r / 3) * 3;
        int y = (c / 3) * 3;
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (a.get(x + j).get(y + k) == ch)
                    return false;
            }
        }

        return true;
    }

}
