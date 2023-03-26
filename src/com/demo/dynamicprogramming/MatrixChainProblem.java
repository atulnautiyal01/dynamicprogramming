package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * given a set of matrices
 * find the least number of operations to compute the product of these matrices
 */
public class MatrixChainProblem {
    public static void main(String[] args) {
        int[][] matrices = {
                {40, 20},
                {20, 30},
                {30, 10},
                {10, 30},
                {30, 50}
        };
        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("minimum number of operation needed to compute matrices multiplication using memoization: "+ productByMemoization(matrices,0,matrices.length-1,lookup));
        System.out.println("minimum number of operation needed to compute matrices multiplication using tabulation: "+ productByTabulation(matrices));
    }

    public static int productByMemoization(int[][] matrices, int i, int j, Map<String, Integer> lookup) {
        if (lookup.get(i + "," + j) != null) {
            return lookup.get(i + "," + j);
        }
        if (i == j) {
            return 0;
        } else {
            int minCost = Integer.MAX_VALUE;
            for (int k = i; k < j; k++) {
                int lCost = productByMemoization(matrices, i, k, lookup);
                int rCost = productByMemoization(matrices, k + 1, j, lookup);
                int pCost = matrices[i][0] * matrices[k][1] * matrices[j][1];
                minCost = Math.min(minCost, lCost + rCost + pCost);
            }
            lookup.put(i + "," + j, minCost);
            return minCost;
        }
    }

    public static int productByTabulation(int[][] matrices){
        int length = matrices.length;
        int dp[][] = new int[length][length];
        for(int dis =1; dis < length; dis++){
            for(int row = 0; row < length - dis; row++){
                int col = row + dis;
                int minCost = Integer.MAX_VALUE;
                for(int k = row; k < col; k ++){
                    int lCost = dp[row][k];
                    int rCost = dp[k+1][col];
                    int pCost = matrices[row][0]*matrices[k][1]*matrices[col][1];
                    minCost = Math.min(minCost, lCost+rCost+pCost);
                }
                dp[row][col] = minCost;
            }
        }
        return dp[0][length-1];
    }
}
