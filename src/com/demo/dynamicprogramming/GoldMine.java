package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * given a mine of m rows and n columns
 * [i][j] represents amount of gold
 * we can enter from top anywhere from the first row
 * we can only go in three direction (a) bottom (b) bottom-left (c) bottom-right
 * we can exist from anywhere from the last row
 * find out the maximum gold we can take
 */
public class GoldMine {
    public static void main(String[] args) {
        int[][] matrix = {
                {3, 2, 12, 15, 10},
                {6, 19, 7, 11, 17},
                {8, 5, 12, 32, 21},
                {3, 20, 2, 9, 7}
        };

        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("The maximum amount of Gold using memoization: " + maxGoldByMemoization(matrix, lookup));
        System.out.println("The maximum amount of Gold using tabulation: " + maxGoldByTabulation(matrix));


    }

    public static int maxGoldByMemoization(int[][] matrix, Map<String, Integer> lookup) {
        int max = Integer.MIN_VALUE;
        for (int col = 0; col < matrix[0].length; col++) {
            max = Math.max(max, rec(matrix, 0, col, lookup));
        }
        return max;
    }

    public static int rec(int[][] matrix, int i, int j, Map<String, Integer> lookup) {
        if (lookup.get(i + "," + j) != null) {
            return lookup.get(i + "," + j);
        }
        final int rows = matrix.length;
        final int columns = matrix[0].length;
        if (j < 0 || j == columns || i == rows) {
            return 0;
        }
        int maxGold = matrix[i][j] + Math.max(rec(matrix, i + 1, j - 1, lookup), Math.max(rec(matrix, i + 1, j, lookup), rec(matrix, i + 1, j + 1, lookup)));
        lookup.put(i + "," + j, maxGold);
        return maxGold;
    }

    public static int maxGoldByTabulation(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int[][] dp = new int[rows][columns];

        for (int col = 0; col < columns; col++) {
            dp[0][col] = matrix[0][col];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final int topLeft = j > 0 ? dp[i - 1][j - 1] : 0;
                final int top = dp[i - 1][j];
                final int topRight = j < columns - 1 ? dp[i - 1][j + 1] : 0;
                dp[i][j] = matrix[i][j] + Math.max(topLeft, Math.max(top, topRight));
            }
        }

        for (int col = 0; col < columns; col++) {
            max = Math.max(max, dp[rows - 1][col]);
        }
        return max;
    }

}
