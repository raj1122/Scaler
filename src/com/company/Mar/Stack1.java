package com.company.Mar;

import javax.print.DocFlavor;
import java.util.*;

public class Stack1 {

    public void executeFn() {
        Chk2Bracket("-(a+b+c)", "-a-b-c");
    }


    /*Check two bracket expressions
Problem Description

Given two strings A and B. Each string represents an expression consisting of lowercase english alphabets, '+', '-', '(' and ')'.

The task is to compare them and check if they are similar. If they are similar return 1 else return 0.

NOTE: It may be assumed that there are at most 26 operands from ‘a’ to ‘z’ and every operand appears only once.*/

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

    /*All Subarrays
Problem Description

Given an integer array A of size N. You have to generate it's all subarrays having the size greater than 1.

Then for each subarray find Bitwise XOR of its maximum and second maximum element.

Find and return the maximum value of XOR among all subarrays.*/

    public int subArrayXor(ArrayList<Integer> A) {
        int ans = 0, n = A.size();
        // create a stack to store the maximum value of all subarrays
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < n; i += 1) {
            // while stack is not emepty take xor of its top and current element
            while (!s.empty()) {
                // strore the maximum value of xor
                ans = Math.max(ans, (A.get(s.peek()) ^ A.get(i)));
                // if top of the stack is greater than current element than break the loop
                if (A.get(s.peek()) > A.get(i)) break;
                // else pop out the top of the stack
                s.pop();
            }
            // push the current element into the stack
            s.push(i);
        }
        // return the answer
        return ans;
    }
    /*Balanced Paranthesis
Problem Description

Given an expression string A, examine whether the pairs and the orders of “{“,”}”, ”(“,”)”, ”[“,”]” are correct in A.

Refer to the examples for more clarity.*/

    public int BalancedParanthesis(String A) {

        Stack<Character> balan = new Stack<>();

        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);

            if (ch == ')') {
                if (balan.isEmpty())
                    return 0;

                char top = balan.peek();
                balan.pop();
                if (top != '(')
                    return 0;

            } else if (ch == '}') {
                if (balan.isEmpty())
                    return 0;

                char top = balan.peek();
                balan.pop();
                if (top != '{')
                    return 0;

            } else if (ch == ']') {
                if (balan.isEmpty())
                    return 0;

                char top = balan.peek();
                balan.pop();
                if (top != '[')
                    return 0;

            } else
                balan.push(ch);
        }
        if (balan.isEmpty())
            return 1;

        return 0;
    }


    /*Redundant Braces
Problem Description

Given a string A denoting an expression. It contains the following operators '+', '-', '*', '/'.

Chech whether A has redundant braces or not.

NOTE: A will be always a valid expression.*/

    public int braces(String A) {

        Stack<Character> Brac = new Stack<>();
        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);
            if (ch == ')') {
                ch = Brac.peek();
                Brac.pop();
                boolean isRedun = true;
                while (ch != '(') {
                    if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                        isRedun = false;
                    }

                    ch = Brac.peek();
                    Brac.pop();
                }

                if (isRedun == true) {
                    return 1;
                }

            } else
                Brac.push(ch);

        }

        return 0;
    }

    /*Infix to Postfix
Problem Description

Given string A denoting an infix expression. Convert the infix expression into postfix expression.

String A consists of ^, /, *, +, -, (, ) and lowercase english alphabets where lowercase english alphabets are operands and ^, /, *, +, - are operators.

Find and return the postfix expression of A.

NOTE:

^ has highest precedence.
/ and * have equal precedence but greater than + and -.
+ and - have equal precedence and lowest precedence among given operators.*/

    public String InfixPost(String A) {
        Stack<Character> stPost = new Stack<>();
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                sb.append(ch);
            } else if (ch == '(')
                stPost.push(ch);
            else if (ch == ')') {
                while (!stPost.isEmpty() && stPost.peek() != '(')
                    sb.append(stPost.pop());

                //remove open (
                stPost.pop();
            } else {
                while (!stPost.isEmpty() && Prec(ch) <= Prec(stPost.peek())) {
                    sb.append(stPost.pop());
                }
                stPost.push(ch);
            }


        }

        while (!stPost.isEmpty()) {
            sb.append(stPost.pop());
        }


        return sb.toString();
    }

    public int Prec(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    /*Largest Rectangle in Histogram
Problem Description

Given an array of integers A .

A represents a histogram i.e A[i] denotes height of the ith histogram's bar. Width of each bar is 1.

Find the area of the largest rectangle formed by the histogram.

*/

    public int largestRectangleArea(ArrayList<Integer> A) {

        Stack<Integer> stleft = new Stack<>();
        int size = A.size();
        Integer ls[] = new Integer[size];
        Integer rs[] = new Integer[size];
        Arrays.fill(ls, -1);
        Arrays.fill(rs, -1);
        for (int i = 0; i < size; i++) {
            //remove stack element if it becomes > than curr element
            while (!stleft.isEmpty() && A.get(stleft.peek()).intValue() >= A.get(i).intValue())
                stleft.pop();

            if (stleft.isEmpty())
                ls[i] = -1;
            else
                ls[i] = stleft.peek();

            stleft.push(i);

        }

        stleft = new Stack<>();
        for (int i = size - 1; i >= 0; i--) {
            //remove stack element if it becomes > than curr element
            while (!stleft.isEmpty() && A.get(stleft.peek()).intValue() >= A.get(i).intValue())
                stleft.pop();

            if (stleft.isEmpty())
                rs[i] = -1;
            else
                rs[i] = stleft.peek();

            stleft.push(i);
        }


        int lelm[] = new int[size];
        int relm[] = new int[size];
        for (int i = 0; i < size; i++) {
            if (ls[i] == -1)
                lelm[i] = -1;
            else
                lelm[i] = A.get(ls[i]);

            if (rs[i] == -1)
                relm[i] = -1;
            else
                relm[i] = A.get(rs[i]);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            //for left-> im finding which index is smaller, then taking index +1 .If no element is smaller start from first
            int lstart = Math.min(i, ls[i] + 1);
            //for right-> im finding which index is smaller in right,
            // then taking index - 1 .If no element is smaller take last index
            int rstart = Math.max(i, rs[i] - 1);
            if (rs[i] == -1)
                rstart = size - 1;
            max = Math.max(max, (rstart - lstart + 1) * A.get(i).intValue());

        }


        return max;
    }

    /*Next Greater
Problem Description

Given an array A, find the next greater element G[i] for every element A[i] in the array. The Next greater Element for an element A[i] is the first greater element on the right side of A[i] in array, A.

More formally:

G[i] for an element A[i] = an element A[j] such that
    j is minimum possible AND
    j > i AND
    A[j] > A[i]
Elements for which no greater element exists, consider the next greater element as -1.*/

    public ArrayList<Integer> nextGreater(ArrayList<Integer> A) {
        Stack<Integer> stleft = new Stack<>();
        int size = A.size();
        Integer ls[] = new Integer[size + 1];
        Arrays.fill(ls, -1);
        for (int i = size - 1; i >= 0; i--) {
            //remove stack element if it becomes > than curr element
            while (!stleft.isEmpty() && A.get(stleft.peek()).intValue() <= A.get(i).intValue())
                stleft.pop();
            if (stleft.isEmpty())
                ls[i] = -1;
            else
                ls[i] = stleft.peek();

            stleft.push(i);
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (ls[i] == -1)
                res.add(-1);
            else
                res.add(A.get(ls[i]));
        }
        return res;

    }

    /*Nearest Smaller Element
Problem Description

Given an array A, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.

More formally,

G[i] for an element A[i] = an element A[j] such that

j is maximum possible AND

j < i AND

A[j] < A[i]

Elements for which no smaller element exist, consider next smaller element as -1.*/

    public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {


        Stack<Integer> stleft = new Stack<>();
        int size = A.size();
        Integer ls[] = new Integer[size + 1];
        Arrays.fill(ls, -1);
        for (int i = 0; i < size; i++) {
            //remove stack element if it becomes > than curr element
            while (!stleft.isEmpty() && A.get(stleft.peek()).intValue() >= A.get(i).intValue())
                stleft.pop();

            if (stleft.isEmpty())
                ls[i] = -1;
            else
                ls[i] = stleft.peek();

            stleft.push(i);

        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (ls[i] == -1)
                res.add(-1);
            else
                res.add(A.get(ls[i]));
        }
        return res;
    }

    /*Double Character Trouble
Problem Description

You are given a string A.

An operation on the string is defined as follows:

Remove the first occurrence of same consecutive characters. eg for a string "abbcd", the first occurrence of same consecutive characters is "bb".

Therefore the string after this operation will be "acd".

Keep performing this operation on the string until there are no more occurrences of same consecutive characters and return the final string.*/

    public String DoubleCharacterTrouble(String A) {
        Stack<String> st = new Stack<>();
        for (int i = 0; i < A.length(); i++) {
            String t = A.charAt(i) + "";
            if (st.isEmpty()) {
                st.push(t);
            } else {
                String top = st.peek();
                if (t.equals(top)) {
                    st.pop();
                } else
                    st.push(t);
            }

        }

        StringBuilder res = new StringBuilder("");
        for (String item : st) {
            res.append(item);
        }
        return res.toString();
    }

    /*Evaluate Expression
Problem Description

An arithmetic expression is given by a charater array A of size N. Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each character may be an integer or an operator.*/

    public int evalRPN(ArrayList<String> A) {
        Stack<String> st = new Stack<>();
        for (int i = 0; i < A.size(); i++) {
            String t = A.get(i);

            int a = 0;
            int b = 0;
            int res = 0;
            switch (t) {
                case "*": {
                    a = Integer.parseInt(st.pop());
                    b = Integer.parseInt(st.pop());
                    res = a * b;
                    st.push(String.valueOf(res));
                    break;
                }
                case "/": {
                    b = Integer.parseInt(st.pop());
                    a = Integer.parseInt(st.pop());
                    res = a / b;
                    st.push(String.valueOf(res));
                    break;
                }
                case "+": {
                    b = Integer.parseInt(st.pop());
                    a = Integer.parseInt(st.pop());
                    res = a + b;
                    st.push(String.valueOf(res));
                    break;
                }
                case "-": {
                    b = Integer.parseInt(st.pop());
                    a = Integer.parseInt(st.pop());
                    res = a - b;
                    st.push(String.valueOf(res));
                    break;
                }
                default: {
                    st.push(t);
                }
            }


        }
        return Integer.parseInt(st.pop());
    }

    /*MAX and MIN
Problem Description

Given an array of integers A .

value of a array = max(array) - min(array).

Calculate and return the sum of values of all possible subarrays of A % 109+7.*/

    public int maxMin(ArrayList<Integer> A) {
        long mod = (long) 1e9 + 7;
        int size = A.size();
        int[] lg = new int[size];
        int[] rg = new int[size];
        int[] rs = new int[size];
        int[] ls = new int[size];
        Stack<Integer> stleft = new Stack<>();
        for (int i = 0; i < size; i++) {
            //remove stack element if it becomes > than curr element
            while (!stleft.isEmpty() && A.get(stleft.peek()).intValue() >= A.get(i).intValue())
                stleft.pop();

            if (stleft.isEmpty())
                ls[i] = -1;
            else
                ls[i] = stleft.peek();

            stleft.push(i);

        }
        stleft = new Stack<>();
        for (int i = size - 1; i >= 0; i--) {
            //remove stack element if it becomes > than curr element
            while (!stleft.isEmpty() && A.get(stleft.peek()).intValue() >= A.get(i).intValue())
                stleft.pop();
            if (stleft.isEmpty())
                rs[i] = size;
            else
                rs[i] = stleft.peek();
            stleft.push(i);
        }
        stleft = new Stack<>();
        for (int i = 0; i < size; i++) {
            //remove stack element if it becomes > than curr element
            while (!stleft.isEmpty() && A.get(stleft.peek()).intValue() <= A.get(i).intValue())
                stleft.pop();

            if (stleft.isEmpty())
                lg[i] = -1;
            else
                lg[i] = stleft.peek();

            stleft.push(i);

        }
        stleft = new Stack<>();
        for (int i = size - 1; i >= 0; i--) {
            //remove stack element if it becomes > than curr element
            while (!stleft.isEmpty() && A.get(stleft.peek()).intValue() <= A.get(i).intValue())
                stleft.pop();
            if (stleft.isEmpty())
                rg[i] = size;
            else
                rg[i] = stleft.peek();
            stleft.push(i);
        }


        long ans = 0;
        for (int i = 0; i < size; i++) {

            long left = (i - lg[i]);
            long right = (rg[i] - i);
            ans += (((left * right) % mod) * A.get(i)) % mod;
            ans = ans % mod;

            left = (i - ls[i]);
            right = (rs[i] - i);
            ans -= (((left * right) % mod) * A.get(i)) % mod;
            ans += mod;
            ans %= mod;
        }

        return (int) (ans % mod);

    }

    /*Passing game
Problem Description

There is a football event going on in your city. In this event, you are given A passes and players having ids between 1 and 106.

Initially some player with a given id had the ball in his possession. You have to make a program to display the id of the player who possessed the ball after exactly A passes.

There are two kinds of passes:

1) ID

2) 0

For the first kind of pass, the player in possession of the ball passes the ball "forward" to player with id = ID.

For the second kind of a pass, the player in possession of the ball passes the ball back to the player who had forwarded the ball to him.

In the second kind of pass "0" just means Back Pass.

Return the ID of the player who currently posseses the ball.*/

    public int PassingGame(int A, int B, ArrayList<Integer> C) {
        Stack<Integer> st = new Stack<>();
        st.push(B);

        for (int i = 0; i < C.size(); i++) {
            if (C.get(i) == 0) {
                if (!st.isEmpty())
                    st.pop();
            } else
                st.push(C.get(i));

        }
        return st.peek();
    }

    /*Maximum Frequency stack
Problem Description

You are given a matrix A which represent operations of size N x 2. Assume initially you have a stack-like data structure you have to perform operations on it.

Operations are of two types:

1 x: push an integer x onto the stack and return -1

2 0: remove and return the most frequent element in the stack.

If there is a tie for the most frequent element, the element closest to the top of the stack is removed and returned.

A[i][0] describes the type of operation to be performed. A[i][1] describe the element x or 0 corresponding to the operation performed.


*/
    HashMap<Integer, Integer> freq = new HashMap<>();
    HashMap<Integer, Stack<Integer>> hm = new HashMap<>();

    int mxFreq = 0;

    public ArrayList<Integer> freqStack(ArrayList<ArrayList<Integer>> A) {

        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < A.size(); i++) {
            int t = A.get(i).get(0);
            int t1 = A.get(i).get(1);
            if (t == 1) {
                res.add(freqPush(t1));
            } else
                res.add(freqPop(t1));

        }

        return res;
    }


    public int freqPush(int t1) {
        int freq1 = freq.getOrDefault(t1, 0) + 1;
        //element occuring for first time
        freq.put(t1, freq1);
        //getting max
        mxFreq = Math.max(mxFreq, freq1);
        //if record is not preent then insert stack else already stack with push
        hm.computeIfAbsent(freq1, x -> new Stack<>()).push(t1);
        return -1;
    }

    public int freqPop(int t1) {
        int top = hm.get(mxFreq).pop();

        freq.put(top, freq.get(top) - 1);
        if (hm.get(top).size() == 0)
            mxFreq--;

        return top;
    }

    /*Sort stack using another stack
Problem Description

Given a stack of integers A, sort it using another stack.

Return the array of integers after sorting the stack using another stack.*/

    public ArrayList<Integer> sortStack(ArrayList<Integer> A) {

        Stack<Integer> temp = new Stack<Integer>();
        while (!A.isEmpty()) {
            int t = A.remove(A.size() - 1);
            //remove all element until we find smallest element
            while (!temp.isEmpty() && temp.peek() > t) {
                A.add(temp.pop());
            }
            temp.push(t);

        }
        return new ArrayList<>(temp);

    }
    /*Min Stack
Problem Description

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
NOTE:

All the operations have to be constant time operations.
getMin() should return -1 if the stack is empty.
pop() should return nothing if the stack is empty.
top() should return -1 if the stack is empty.*/

    public void callFunc() {

        String a = "586 g t t g P 593848644 t p t t t t p P 680232627 P 383101258 P 240914318 g t t g g g p t P 213391515 p g p P 742850435 t t p P 140398572 t p P 223343895 g p P 781329724 p g P 957992928 g t p t g t p t t P 285167635 g g g g P 406164147 t P 758145620 p p p P 124285491 P 562495437 g t p P 944055491 p t P 52710853 p t P 300724037 t g t p p P 261181367 p t p t p g p P 878174480 g P 212362489 p p g p t p g t t t g t p p P 31729170 P 114379646 p g g g t t t g P 940773816 t t t t t p g t g t P 951862306 g P 881407410 P 353039583 P 878631052 t p p g g t P 463882617 p t g p t P 471748835 g p g g t g g t t g g g P 842420509 P 945537631 p g p p p t t t g p p P 731260864 p P 32317538 g g t t P 32484018 t t p g p P 804019134 t P 711633790 p P 800701823 g p p t P 407672563 p t g P 224962756 g g p p g P 574232670 t p p p t P 701785843 g P 444445859 g P 47850275 p t g P 501076238 g t t t g p g t p p t g p g P 309787254 t P 869566183 P 71770367 p t P 464272346 g g p P 397900315 g t t p p p g t t P 937096341 p P 436863539 t t P 542632465 P 781555871 p p t p P 254349373 P 178643603 g g P 178735710 P 52131937 g P 952629390 p P 350915931 g P 228975548 P 263688213 t p t g t P 676902702 t t P 63194601 g g P 19217890 P 596393544 p g t p t g p g P 508824208 P 36903639 p t p P 719030567 t p P 719389474 P 479824726 p p t p p g t P 725153581 P 541171427 P 103507999 p P 277436428 g t p t g t t t P 701795326 t t p t p p g p P 324304199 p P 82265427 p g p t p g p g p p p P 525849167 P 696013291 P 753175403 p p P 870235711 t g g p g p p t p g P 974721906 P 531365097 P 501145234 t p t g g t p t P 137243011 g p P 34998402 P 447313462 p p P 603194026 t t t g t p g p g P 215762626 t P 151859662 P 409772254 p p t t g g t p p P 517631790 g t g P 359027788 p p g t t P 66861044 p p t t g t P 230291592 P 681081810 t P 462993916 p g p P 100520658 p g t P 192955504 t P 651898157 g t P 357537677 g P 764119050 P 843021351 p g P 661902663 g g P 350513129 P 818846798 P 675467354 t g g t p P 393353030 t p g g P 538842245 t t g P 464133893 p t t p g p p t t p p g g P 812231214 p g P 809370817 p P 49863929 p P 133917999 g P 98397249 p p t p g t p P 565077104 t P 500269843 P 337772871 t g P 648755277 t g g g P 945760233 g t P 195829325 t p g g g g g t p g t t P 279641729 p g p p t g t t t t p p P 623929613 t t p p t P 404667209 t t p t p t p P 360716847 t g t g p p p g P 596790133 p g p g P 997831569 t t g t g t g P 619805999 g p t p g p g t P 272317483 P 830076034 t g g t g P 962873120";

        String[] str = a.split(" ");
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals("P")) {
                push(Integer.parseInt(str[i + 1]));
            } else if (str[i].equals("p"))
                pop();
            else if (str[i].equals("t"))
                System.out.println(top());
            else if (str[i].equals("g"))
                System.out.println(getMin());


        }
    }


    static Stack<Integer> st1 = new Stack<>();
    static int min = -1;

    public void push(int x) {
        if (st1.isEmpty()) {
            min = x;
            st1.push(x);
            return;
        }

        if (x < min) {
            //store previous element
            long t = 2 * x - min;
            st1.push((int) t);
            min = x;
        } else
            st1.push(x);
    }

    public void pop() {
        if (!st1.isEmpty()) {
            int t = st1.pop();

            if (t < min) {
                //get previous element
                min = 2 * min - t;
            }
        }
    }

    public int top() {
        if (st1.isEmpty())
            return -1;
        int t = st1.peek();

        if (t < min)
            return min;
        else
            return t;
    }

    public int getMin() {
        if (st1.isEmpty())
            return -1;
        return min;

    }


}


