package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array
 * value indicates the amount of money in the house
 * tow adjacent houses cannot be robbed as this wil trigger alarm to call police
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] arr = {4, 8, 12, 1, 2, 10, 3, 6, 8};

        Map<Integer, Integer> lookup = new HashMap<>();
        System.out.println("The max amount a house robber can rob using memoization: " + maxAmountByMemoization(arr, 0, lookup));
        System.out.println("The max amount a house robber can rob using tabulation: " + maxAmountByTabulation(arr));

    }

    public static int maxAmountByMemoization(int[] arr, int i, Map<Integer, Integer> lookup) {
        if (i > arr.length - 1) {
            return 0;
        }

        if (lookup.get(i) != null) {
            return lookup.get(i);
        }

        int max = Math.max(arr[i] + maxAmountByMemoization(arr, i + 2, lookup), maxAmountByMemoization(arr, i + 1, lookup));
        lookup.put(i, max);
        return max;
    }

    public static int maxAmountByTabulation(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < arr.length; i++) {
            dp[i] = Math.max(arr[i]+ dp[i-2], dp[i-1]);
        }

        return dp[arr.length-1];
    }
}
