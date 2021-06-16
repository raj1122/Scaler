package com.company.Mar.StackPackage;


/*Check two bracket expressions
Problem Description

Given two strings A and B. Each string represents an expression consisting of lowercase english alphabets, '+', '-', '(' and ')'.

The task is to compare them and check if they are similar. If they are similar return 1 else return 0.

NOTE: It may be assumed that there are at most 26 operands from ‘a’ to ‘z’ and every operand appears only once.



Problem Constraints
1 <= length of the each String <= 100



Input Format
The arguments given are string A and String B.



Output Format
Return 1 if they represent the same expression else return 0.



Example Input
Input 1:

 A = "-(a+b+c)"
 B = "-a-b-c"
Input 2:

 A = "a-b-(c-d)"
 B = "a-b-c-d"


Example Output
Output 1:

 1
Output 2:

 0


Example Explanation
Explanation 1:

 The expression "-(a+b+c)" can be written as "-a-b-c" which is equal as B.
Explanation 2:

 Both the expression are different*/


import java.util.Stack;

public class Check2Bracket {

    public int Chk2Bracket(String A, String B) {

        String strA = SimplifyStr(A);
        String strB = SimplifyStr(B);

        int[] v = new int[26];
        for (int i = 0; i < strA.length(); i++) {
            char ch = strA.charAt(i);
            if (ch == '+' || ch == '-')
                continue;
            else {
                char ch1 = strA.charAt(i - 1);
                int t = (ch1 == '+') ? 1 : -1;
                int chrAscii = ch - 'a';
                v[chrAscii] += t;
            }
        }


        for (int i = 0; i < strB.length(); i++) {
            char ch = strB.charAt(i);
            if (ch == '+' || ch == '-')
                continue;
            else {
                char ch1 = strB.charAt(i - 1);
                int t = (ch1 == '+') ? -1 : +1;
                int chrAscii = ch - 'a';
                v[chrAscii] += t;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (v[i] != 0)
                return 0;
        }

        return 1;
    }

    private String SimplifyStr(String a) {
        Stack<Boolean> st = new Stack<>();

        StringBuilder an = new StringBuilder();
        st.push(true);
        int i = 0;
        while (i < a.length()) {
            char ch = a.charAt(i);
            if (ch == '+' || ch == '-') {
                i++;
                continue;
            } else if (ch == '(') {
                boolean top = st.peek();
                if (getSignBoolean(a, i))
                    st.push(top);
                else
                    st.push(!top);
            } else if (ch == ')')
                st.pop();
            else {
                boolean top = st.peek();
                boolean sign = getSignBoolean(a, i);
                if (top == true) {
                    String t = (sign == true) ? "+" : "-";
                    an.append(t + "" + ch);

                } else {

                    String t = (sign == true) ? "-" : "+";
                    an.append(t + "" + ch);

                }
            }
            i++;

        }


        return an.toString();
    }

    public boolean getSignBoolean(String s, int i) {
        if (i == 0)
            return true;
        if (s.charAt(i - 1) == '-')
            return false;
        return true;
    }

}
