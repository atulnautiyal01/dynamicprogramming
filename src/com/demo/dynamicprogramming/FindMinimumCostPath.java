package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * find minimum cost path to go from top-left to top-right cell
 */
public class FindMinimumCostPath {
    public static void main(String[] args) {
        int[][] matrix = {
                {3, 2, 12, 15, 18},
                {6, 19, 7, 11, 17},
                {8, 5, 12, 32, 31},
                {3, 20, 2, 9, 7}
        };
        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("The minimum cost path using memoization: " + minCostPathByMemoization(matrix, 0, 0, lookup));
        System.out.println("The minimum cost path using tabulation: " + minCostPathByTabulation(matrix));
    }

    public static int minCostPathByMemoization(int[][] matrix, int i, int j, Map<String, Integer> lookup) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        if (lookup.get(i + "," + j) != null) {
            return lookup.get(i + "," + j);
        }
        if (i == rows || j == columns) {//out of bound
            return 0;
        } else if (i == rows - 1) {// last row
            int minCost = matrix[i][j] + minCostPathByMemoization(matrix, i, j + 1, lookup);
            lookup.put(i + "," + j, minCost);
            return minCost;
        } else if (j == columns - 1) {//last column
            int minCost = matrix[i][j] + minCostPathByMemoization(matrix, i + 1, j, lookup);
            lookup.put(i + "," + j, minCost);
            return minCost;
        } else {//otherwise
            int minCost = matrix[i][j] + Math.min(minCostPathByMemoization(matrix, i, j + 1, lookup), minCostPathByMemoization(matrix, i + 1, j, lookup));
            lookup.put(i + "," + j, minCost);
            return minCost;
        }
    }

    public static int minCostPathByTabulation(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int[][] dp = new int[rows][columns];

        dp[0][0] = matrix[0][0];

        //for first column
        for (int row = 1; row < rows; row++) {
            dp[row][0] = matrix[row][0] + dp[row - 1][0];
        }

        //for last column
        for (int col = 1; col < columns; col++) {
            dp[0][col] = matrix[0][col] + dp[0][col - 1];
        }

        //for remaining rows and columns
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < columns; col++) {
                dp[row][col] = matrix[row][col] + Math.min(dp[row - 1][col], dp[row][col - 1]);
            }
        }

        return dp[rows - 1][columns - 1];

    }
}
