package com.company.Apr;

import java.util.*;

public class dp3 {

    public void executefn() {
        Integer[] arr1 = {1, 2, 3};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);

        String s1[] = {"10", "sale", "jan"};
        String s2[] = {"200", "sale", "EMPTY"};

        ArrayList<String> ls1 = new ArrayList<>();
        ArrayList<String> ls2 = new ArrayList<>();
        Collections.addAll(ls1, s1);
        Collections.addAll(ls2, s2);
        ArrayList<ArrayList<Integer>> dblArr = creatDbtArr1();


            int mat= IsMatch2("a","a*");
//        minCut("ababb");
//        coinchange21(levelTree,4);
    }




    /*Coin Sum Infinite
Problem Description

You are given a set of coins A. In how many ways can you make sum B assuming you have infinite amount of each coin in the set.

NOTE:

Coins in set A will be unique. Expected space complexity of this problem is O(B).
The answer can overflow. So, return the answer % (106 + 7).*/

    public int coinchange2(ArrayList<Integer> A, int B) {
        int alen=A.size();
        long mod=(int)1e6+7;
        long dp[][]=new long[alen+1][B+1];

        //money
        for (int i = 0; i <=B ; i++) {
            dp[0][i]=0;
        }

        //coins
        for (int i = 0; i <=alen ; i++) {
            dp[i][0]=1;
        }

        //for every coin finding the sum
        for (int coin = 1; coin <=alen ; coin++) {
            //cal sum for coin
            for (int money = 1; money <=B ; money++) {
                if(money>=A.get(coin-1))
                {
                    dp[coin][money]=((dp[coin][money-A.get(coin-1)]%mod)  + (dp[coin-1][money]%mod))%mod;
                }
                else {
                    //take previous coin as current coin is greter than amount
                    dp[coin][money]=dp[coin-1][money];
                }
            }
        }
        return  (int)(dp[alen][B]%mod);
    }

    public int coinchange21(ArrayList<Integer> A, int B) {
        int alen=A.size();
        long mod=(int)1e6+7;
        long dp[]=new long[B+1];

        if(alen==0)
        {
            if(B==0)
                return 1;
            else
                return 0;
        }
        dp[0]=1;
        //for every coin finding the sum
        for (int coin = 0; coin < alen ; coin++) {
            //cal sum for coin
            for (int money = A.get(coin); money <=B ; money++) {
                dp[money]=((dp[money]%mod)+(dp[money-A.get(coin)]%mod))%mod;

            }
        }
        return  (int)(dp[B]%mod);

    }
    
    /*Palindrome Partitioning II
Problem Description

Given a string A, partition A such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of A.

*/

    public int minCut(String A) {
        int alen = A.length();
        int cut[][] = new int[alen][alen];
        boolean pal[][] = new boolean[alen][alen];
        for (int i = 0; i < alen; i++) {
            pal[i][i] = true;
            cut[i][i] = 0;
        }

        for (int len = 2; len <= alen; len++) {
            for (int i = 0; i < alen - len + 1; i++) {

                int j = i + len - 1;
                if (len == 2)
                    pal[i][j] = (A.charAt(i) == A.charAt(j));
                else {
                    //pal[i+1][j-1] to comsider for middle part
                    pal[i][j] = ((A.charAt(i) == A.charAt(j)) && (pal[i + 1][j - 1]));
                }

                if (pal[i][j] == true)
                    cut[i][j] = 0;
                else {
                    cut[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k <= j - 1; k++) {
                        int t = cut[i][k] + 1 + cut[k + 1][j];
                        cut[i][j] = Math.min(cut[i][j], t);
                    }
                }
            }
        }
        return cut[0][alen - 1];
    }

    /*Regular Expression Match
Problem Description

Implement wildcard pattern matching with support for ' ? ' and ' * ' for strings A and B.

' ? ' : Matches any single character.
' * ' : Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).*/

    public int isMatch(final String A, final String B) {
        int alen = A.length();
        int blen = B.length();

        if (alen == 0) {
            return ((blen == 0) == true) ? 1 : 0;
        }
        boolean dp[][] = new boolean[alen + 1][blen + 1];
        dp[0][0] = true;


        for (int j = 1; j <= blen; j++) {
            if (B.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }


        for (int i = 1; i <= alen; i++) {
            for (int j = 1; j <= blen; j++) {
                //if character matches
                if ((A.charAt(i - 1) == B.charAt(j - 1)) || (B.charAt(j - 1) == '?')) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (B.charAt(j - 1) == '*') {
                    boolean itr = dp[i][j - 1] || dp[i - 1][j];
                    dp[i][j] = itr;
                } else
                    dp[i][j] = false;
            }

        }
        return (dp[alen][blen] == true) ? 1 : 0;
    }

    public int  IsMatch2(String A, String B) {

        if (A.length() == 1 && B.charAt(0) == '*')
            return 1;

        if (A.length() > 1 && B.charAt(0) == '*')
            return 0;
        StringBuilder s1 = new StringBuilder(A);
        StringBuilder p1 = new StringBuilder(B);
        s1.insert(0, " ");
        p1.insert(0, " ");
        boolean dp[][] = new boolean[p1.length()][s1.length()];

        dp[0][0] = true;
        //string is empty and pattern exist.
        //if pattern is " a* and string is " " then match should be true.
        for (int i = 2; i < p1.length(); i++) {
            if (p1.charAt(i) == '*')
                dp[i][0] = dp[i-2][0];
        }
        for (int i = 1; i < p1.length(); i++) {
            for (int j = 1; j < s1.length(); j++) {
                //if dot then match occured
                if (s1.charAt(j) == p1.charAt(i) || p1.charAt(i) == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p1.charAt(i) == '*') {
                    //if pattern is ab* then pattern now becomes a ie b* is ignored,
                    dp[i][j] = dp[i-2][j];  //for zero charatertfor mis* and mis
                    if (s1.charAt(j) == p1.charAt(i - 1) || p1.charAt(i - 1) == '.')
                        //if pattern is ab* and string is ab then b and b* matches, so now check if a and ab* matches
                        dp[i][j] = dp[i][j-1] || dp[i - 1][j];
                    //dp[i][j-1]  mis* and mis if s* is ss then we
                    //look for mis* for mi..use table
                    //dp[i-1][j]  mis* and mis .if s* is s then problem
                    //is search for mis and mis
                } else dp[i][j] = false;
            }
        }
        //mis*   mis
        return (dp[p1.length() - 1][s1.length() - 1]==true)?1:0;
    }


    private static ArrayList<ArrayList<Integer>> creatDbtArr1() {
        ArrayList<ArrayList<Integer>> dblArr = new ArrayList<>();

        Integer arr[][] = new Integer[][]
                {
                        {1, 0}
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
