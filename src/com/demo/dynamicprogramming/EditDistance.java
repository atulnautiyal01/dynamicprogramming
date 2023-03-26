package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * given two words word1 and word2
 * find the edit distance to go from word1 to word2 by using
 * a) insertion b) deletion c) substitution
 */
public class EditDistance {
    public static void main(String[] args) {
        String word1 = "inside";
        String word2 = "index";

        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("The edit distance using memoization: " + distanceByMemoization(word1, word2, 0, 0, lookup));
        System.out.println("The edit distance using tabulation: " + distanceByTabulation(word1, word2));

    }

    public static int distanceByMemoization(String word1, String word2, int i, int j, Map<String, Integer> lookup) {
        if (lookup.get(i + "," + j) != null) {
            return lookup.get(i + "," + j);
        }
        if (i == word1.length()) {
            return word2.length() - j;
        } else if (j == word2.length()) {
            return word1.length() - i;
        } else if (word1.charAt(i) == word2.charAt(j)) {
            int distance = distanceByMemoization(word1, word2, i + 1, j + 1, lookup);
            lookup.put(i + "," + j, distance);
            return distance;
        } else {
            int deleteOperation = distanceByMemoization(word1, word2, i + 1, j, lookup);
            int insertOperation = distanceByMemoization(word1, word2, i, j + 1, lookup);
            int substitutionOperation = distanceByMemoization(word1, word2, i + 1, j + 1, lookup);
            int distance = 1 + Math.min(deleteOperation, Math.min(insertOperation, substitutionOperation));
            lookup.put(i + "," + j, distance);
            return distance;
        }
    }

    public static int distanceByTabulation(String word1, String word2) {
        final int rows = word1.length();
        final int columns = word2.length();
        int[][] dp = new int[rows + 1][columns + 1];
        for (int col = 1; col < columns; col++) {
            dp[0][col] = col;
        }
        for (int row = 1; row < rows; row++) {
            dp[row][0] = row;
        }
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[rows][columns];
    }
}
