package com.company.Apr.dynamicProg.dp5;

/*Evaluate Expression To True
Given an expression, A, with operands and operators (OR , AND , XOR), in how many ways can you evaluate the expression to true, by grouping in different ways?

Operands are only true and false.

Return the number of ways to evaluate the expression modulo 103 + 3.



Input Format:

The first and the only argument of input will contain a string, A.

The string A, may contain these characters:
    '|' will represent or operator
    '&' will represent and operator
    '^' will represent xor operator
    'T' will represent true operand
    'F' will false
Output:

Return an integer, representing the number of ways to evaluate the string.
Constraints:

1 <= length(A) <= 150
Example:

Input 1:
    A = "T|F"

Output 1:
    1

Explanation 1:
    The only way to evaluate the expression is:
        => (T|F) = T

Input 2:
    A = "T^T^F"

Output 2:
    0

Explanation 2:
    There is no way to evaluate A to a true statement.*/

public class EvaluateExpression {

    public int cnttrue(String A) {
        String oprtr="";
        String oprnd="";

        for (int i = 0; i < A.length(); i++) {
            char ch=A.charAt(i);
            if(ch =='T' || ch=='F')
            {
                oprnd+=ch;
            }
            else
                oprtr+=ch;
        }

        return countParenth(oprnd.toCharArray(),oprtr.toCharArray(),oprnd.length());
    }

    public int countParenth(char symb[],
                     char oper[],
                     int n)
    {
        long MOD=(int)1e3+3;
        long F[][] = new long[n][n];
        long T[][] = new long[n][n];

        // Fill diaginal entries first
        // All diagonal entries in T[i][i]
        // are 1 if symbol[i] is T (true).
        // Similarly, all F[i][i] entries
        // are 1 if symbol[i] is F (False)
        for (int i = 0; i < n; i++) {
            F[i][i] = (symb[i] == 'F') ? 1 : 0;
            T[i][i] = (symb[i] == 'T') ? 1 : 0;
        }

        // Now fill T[i][i+1], T[i][i+2],
        // T[i][i+3]... in order And F[i][i+1],
        // F[i][i+2], F[i][i+3]... in order
        for (int gap = 1; gap < n; ++gap)
        {
            for (int i = 0,
                 j = gap; j < n;
                 ++i, ++j)
            {
                T[i][j] = F[i][j] = 0;
                for (int g = 0; g < gap; g++)

                {
                    // Find place of parenthesization
                    // using current value of gap
                    int k = i + g;

                    // Store Total[i][k]
                    // and Total[k+1][j]
                    long tik = ((T[i][k]%MOD)+ (F[i][k]%MOD))%MOD;
                    long tkj = ((T[k + 1][j]%MOD)+ (F[k + 1][j]%MOD));

                    // Follow the recursive formulas
                    // according to the current operator
                    if (oper[k] == '&')
                    {
                        long t1=((T[i][k]%MOD)* (T[k + 1][j]%MOD))%MOD;
                        T[i][j] =((T[i][j] %MOD) + (t1%MOD))%MOD;
                        long t2=((T[i][k]%MOD)* (T[k + 1][j]%MOD))%MOD;
                        long t3=((tik%MOD) *(tkj%MOD))%MOD;
                        long t4= ((t3%MOD) - (t2%MOD))%MOD;
                        t4=(t4+MOD)%MOD;
                        F[i][j] = ((F[i][j]%MOD) + (t4+MOD))%MOD;
                    }
                    if (oper[k] == '|')
                    {
                        long t1= ((F[i][k]%MOD)* (F[k + 1][j]%MOD));
                        F[i][j] = ((F[i][j]%MOD)+ (t1%MOD))%MOD;
                        long t2= ((F[i][k]%MOD)* (F[k + 1][j]%MOD))%MOD;
                        long t3=((tik%MOD) *(tkj%MOD))%MOD;
                        long t4= ((t3%MOD) - (t2%MOD))%MOD;
                        t4=(t4+MOD)%MOD;
                        T[i][j] = ((T[i][j]%MOD) + (t4+MOD))%MOD;
                    }
                    if (oper[k] == '^')
                    {
                        long t1=((F[i][k]%MOD)* (T[k + 1][j]%MOD))%MOD;
                        long t2= ((T[i][k]%MOD)* (F[k + 1][j]%MOD))%MOD;
                        long t3= ((t1%MOD) +(t2%MOD))%MOD;
                        T[i][j] = ((T[i][j]%MOD)  +(t3%MOD))%MOD;
                        long t4=((T[i][k]%MOD)* (T[k + 1][j]%MOD))%MOD;
                        long t5=((F[i][k]%MOD)* (F[k + 1][j]%MOD))%MOD;
                        long t6= ((t4%MOD)+ (t5%MOD))%MOD;
                        F[i][j] =((F[i][j] %MOD) +(t6%MOD))%MOD;

                    }
                }
            }
        }
        return (int)T[0][n - 1];
    }
}
