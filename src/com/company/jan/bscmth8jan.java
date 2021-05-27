package com.company.jan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class bscmth8jan {





    /*
    * The Prime Game
Problem Description

Two players are playing a game. The rules are as follows:

Player 1 always moves first, and both players always play optimally.
Initially there are A piles, where each pile has B number of stones.
The players move in alternating turns. In each turn, a player can choose a pile of size x and reduce the number of stones to y, where 1 <= y < x, and x and y are coprime.
If the current player is unable to make a move, they lose the game.
Determine the winner of the game.*/

    public int primeGame(int A, int B) {
        if (B == 1)
            return 2;

        if ((A % 2) == 0) {
            return 2;
        } else
            return 1;
    }




    /*Find nth Magic Number
Problem Description

Given an integer A, find and return the Ath magic number.

A magic number is defined as a number which can be expressed as a power of 5 or sum of unique powers of 5.

First few magic numbers are 5, 25, 30(5 + 25), 125, 130(125 + 5),

101  5^3 + 5^1
010  5^2
100 5^3….*/

    public int magicNum(int A) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            if ((A & (1 << i)) > 0) {
                ans += Math.pow(5, i + 1);
            }

        }
        return ans;
    }




    /*
    * Monkeys and Doors
There are 100 doors, all closed. In a nearby cage are 100 monkeys.

The first monkey is let out, and runs along the doors opening every one. The second monkey is then let out, and runs along the doors closing the 2nd, 4th, 6th,… - all the even-numbered doors. The third monkey is let out. He attends only to the 3rd, 6th, 9th,… doors (every third door, in other words), closing any that is open and opening any that is closed, and so on. After all 100 monkeys have done their work in this way, what state are the doors in after the last pass?

Answer with the number of open doors.*/

    public int closeDoor(int B) {
        int cnt = 0;


        for (int i = 1; i <= B; i++) {
            System.out.println(factorial(i));
            cnt += factorial(i) % 2 == 0 ? 0 : 1;

        }


        return cnt;

    }

    static int factorial(int num) {
        int ctr = 0;
        for (int i = 1; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0 && i * i != num) {
                ctr += 2;
            } else if (i * i == num) {
                ctr++;
            }
        }
        return ctr;
    }

    /*
    * Pair Sum divisible by M
Problem Description

Given an array of integers A and an integer B, find and return the number of pairs in A whose sum is divisible by B.

Since the answer may be large, return the answer modulo (109 + 7).*/
    public int pairSum(ArrayList<Integer> A, int B) {

        int rem[] = new int[B];


        /*getting freq in array*/
        for (int i = 0; i < A.size(); i++)
            ++rem[A.get(i) % B];


        /*tking rem as 0.Considering rem as 0 only*/
        long sum = ((rem[0] % 1000000007) * ((rem[0] - 1) % 1000000007)) / 2;


        for (int i = 1; i <= B / 2 && i != (B - i); i++)
            /*considering number with i and B-i   (1,3) = 4*/
            sum += (1l * (rem[i] % 1000000007) * (rem[B - i] % 1000000007));

        /*considering same number 2,2, =4*/
        if (B % 2 == 0)
            sum += ((1l * (rem[B / 2] % 1000000007) * ((rem[B / 2] - 1) % 1000000007)) / 2);


        return (int) (sum % 1000000007);


    }
}
