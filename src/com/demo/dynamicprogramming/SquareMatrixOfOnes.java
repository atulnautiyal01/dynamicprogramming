package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * given a matrix of 0's and 1's
 * find size of biggest square matrix of 1's
 */
public class SquareMatrixOfOnes {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 1, 1, 1, 0},
                {1, 0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0, 1},
                {0, 1, 0, 1, 1, 1}
        };

        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("max size of square matrix using memoization: " + maxSizeSquareMatrixByMemoization(matrix, lookup));
        System.out.println("max size of square matrix using tabulation: " + maxSizeSquareMatrixByTabulation(matrix));

    }

    public static int maxSizeSquareMatrixByMemoization(int[][] matrix, Map<String, Integer> lookup) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, rec(matrix, i, j, lookup));
            }
        }
        return max * max;
    }

    public static int rec(int[][] matrix, int i, int j, Map<String, Integer> lookup) {
        if (lookup.get(i + "," + j) != null) {
            return lookup.get(i + "," + j);
        }

        if (i < 0 || j < 0 || matrix[i][j] == 0) {
            return 0;
        } else {
            int val = 1 + Math.min(rec(matrix, i - 1, j - 1, lookup), Math.min(rec(matrix, i - 1, j, lookup), rec(matrix, i, j - 1, lookup)));
            lookup.put(i + "," + j, val);
            return val;
        }
    }

    public static int maxSizeSquareMatrixByTabulation(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = matrix[0][0];

        int max = 0;
        for (int row = 1; row < rows; row++) {
            dp[row][0] = matrix[row][0];
            max = Math.max(max, dp[row][0]);
        }

        for (int col = 1; col < columns; col++) {
            dp[0][col] = matrix[0][col];
            max = Math.max(max, dp[0][col]);
        }


        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < columns; col++) {
                if (matrix[row][col] == 1) {
                    dp[row][col] = 1 + Math.min(dp[row - 1][col - 1], Math.min(dp[row - 1][col], dp[row][col - 1]));
                    max = Math.max(max, dp[row][col]);
                }
            }
        }
        return max * max;
    }
}
