package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * given two strings s1 and s2
 * find longest common subsequence
 * subsequence of a string s is a subset of its characters that are not necessarily adjacent
 * however have to be in right order
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String s1 = "abdacbab";
        String s2 = "acebfca";
        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("The longest common subsequence using memoization: " + lcsByMemoization(s1, s2, 0, 0, lookup));
        System.out.println("The longest common subsequence using Tabulation: " + lcsByTabulation(s1, s2));
    }

    public static int lcsByMemoization(String s1, String s2, int i, int j, Map<String, Integer> lookup) {
        if (lookup.get(i + "," + j) != null) {
            return lookup.get(i + "," + j);
        }
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            int longest = 1 + lcsByMemoization(s1, s2, i + 1, j + 1, lookup);
            lookup.put(i + "," + j, longest);
            return longest;
        } else {
            int longest = Math.max(lcsByMemoization(s1, s2, i, j + 1, lookup), lcsByMemoization(s1, s2, i + 1, j, lookup));
            lookup.put(i + "," + j, longest);
            return longest;
        }
    }

    public static int lcsByTabulation(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }

        }
        return dp[s1.length()][s2.length()];
    }
}
