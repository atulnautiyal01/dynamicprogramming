package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * There is matrix of 1s and 0s, where 1 represents the wall.
 * Find the number of ways to go from top-left cell to bottom-right cell.
 * We cannot pass through wall.
 * We can only go to right or bottom
 */
public class FindPathInMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0}
        };

        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("Total paths using memoization: " + pathsByMemoization(matrix, 0, 0, lookup));
        System.out.println("Total paths using Tabulation: " + pathsByTabulation(matrix));

    }

    public static int pathsByMemoization(int[][] matrix, int i, int j, Map<String, Integer> lookup) {
        final int rows = matrix.length;
        final int columns = matrix[0].length;

        if (lookup.get(i + "," + j) != null) {
            return lookup.get(i + "," + j);
        }
        if (i == rows || j == columns || matrix[i][j] == 1) {        //either out of bounds or there is a wall
            return 0;
        } else if (i == rows - 1 && j == columns - 1) {//bottom right position
            return 1;
        } else if (i == rows - 1) {
            final int paths = pathsByMemoization(matrix, i, j + 1, lookup);//last row
            lookup.put(i + "," + j, paths);
            return paths;
        } else if (j == columns - 1) {//last column
            final int paths = pathsByMemoization(matrix, i + 1, j, lookup);
            lookup.put(i + "," + j, paths);
            return paths;
        } else {//otherwise
            final int paths = pathsByMemoization(matrix, i + 1, j, lookup) + pathsByMemoization(matrix, i, j + 1, lookup);
            lookup.put(i + "," + j, paths);
            return paths;
        }
    }

    public static int pathsByTabulation(int[][] matrix){
        int rows = matrix.length;
        int columns = matrix[0].length;
        int dp[][] = new int[rows][columns];

        if(matrix[0][0] == 0){
            dp[0][0] = 1;
        }else{
            dp[0][0] = 0;
        }

        for(int col = 1 ; col< columns; col ++){
            if(matrix[0][col] == 0){
                dp[0][col] = dp[0][col-1];
            }else{
                dp[0][col] = 0;
            }
        }

        for(int row = 1 ; row< rows; row ++){
            if(matrix[row][0] == 0){
                dp[row][0] = dp[row-1][0];
            }else{
                dp[row][0] = 0;
            }
        }

        for(int row = 1; row < rows; row++){
            for(int col=1; col<columns; col++){
                if(matrix[row][col] == 0){
                    dp[row][col] = dp[row-1][col] + dp[row][col-1];
                }else{
                    dp[row][col] = 0;
                }
            }
        }

        return dp[rows-1][columns-1];
    }
}
