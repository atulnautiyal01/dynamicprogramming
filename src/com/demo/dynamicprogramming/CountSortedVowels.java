package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * find count of sorted vowels word count
 */
public class CountSortedVowels {
    public static void main(String[] args) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        int n = 9;
        char last = Character.MIN_VALUE;
        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("sorted vowel words count using memoization: " + countByMemoization(vowels, n, last, lookup));
        System.out.println("sorted vowel words count using tabulation: " + countByTabulation(vowels, n));
    }

    public static int countByMemoization(char[] vowels, int n, char last, Map<String, Integer> lookup) {
        if (lookup.get(n + "," + last) != null) {
            return lookup.get(n + "," + last);
        }
        if (n == 0) {
            return 1;
        } else {
            int count = 0;
            for (char vowel : vowels) {
                if (last <= vowel) {
                    count += countByMemoization(vowels, n - 1, vowel, lookup);
                }
            }
            lookup.put(n + "," + last, count);
            return count;
        }
    }

    public static int countByTabulation(char[] vowels, int n) {
        int[][] dp = new int[n][5];
        for (int col = 0; col < 5; col++) {
            dp[0][col] = 1;
        }

        for (int row = 1; row < n; row++) {
            dp[row][0] = dp[row - 1][0] + dp[row - 1][1] + dp[row - 1][2] + dp[row - 1][3] + dp[row - 1][4];
            dp[row][1] = dp[row - 1][1] + dp[row - 1][2] + dp[row - 1][3] + dp[row - 1][4];
            dp[row][2] = dp[row - 1][2] + dp[row - 1][3] + dp[row - 1][4];
            dp[row][3] = dp[row - 1][3] + dp[row - 1][4];
            dp[row][4] = dp[row - 1][4];
        }

        return dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2] + dp[n - 1][3] + dp[n - 1][4];

    }
}
