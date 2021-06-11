package com.company.jan.BasicMath;

public class LexicographicallyPermute {

    final static int MAX_CHAR = 26;
    final static int MAX_FACT = 20;
    static long fact[] = new long[MAX_FACT];

    // Utility for calculating factorial
    static void precomputeFactorirals()
    {
        fact[0] = 1;
        for (int i = 1; i < MAX_FACT; i++)
            fact[i] = fact[i - 1] * i;
    }

    // Function for nth permutation
    static void nPermute(String str, int n)
    {
        precomputeFactorirals();

        // length of given string
        int len = str.length();

        // Count frequencies of all
        // characters
        int freq[] = new int[MAX_CHAR];
        for (int i = 0; i < len; i++)
            freq[str.charAt(i) - 'a']++;

        // out string for output string
        String out = "";

        // Iterate till sum equals n
        int sum = 10;
        int k = 0;

        // We update both n and sum
        // in this loop.
        while (sum >= n) {

            // Check for characters
            // present in freq[]
            sum = 0;
            for (int i = 0; i < MAX_CHAR; i++) {
                if (freq[i] == 0)
                    continue;

                // Remove character
                freq[i]--;

                // calculate sum after fixing
                // a particular char

                int xsum = (int)fact[len - 1 - k];
                for (int j = 0; j < MAX_CHAR; j++)
                    xsum /= fact[freq[j]];
                sum += xsum;

                // if sum > n fix that char as
                // present char and update sum
                // and required nth after fixing
                // char at that position
                if (sum >= n) {
                    out += (char)(i + 'a');
                    k++;
                    n -= (sum - xsum);
                    break;
                }

                // if sum < n, add character back
                if (sum < n)
                    freq[i]++;
            }
        }

        // append string termination
        // character and print result
        System.out.println(out);
    }

}
