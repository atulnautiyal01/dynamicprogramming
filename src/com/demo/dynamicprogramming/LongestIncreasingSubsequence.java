package com.demo.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * find longest increasing subsequence
 * which is longest subsequence where each element is strictly greater than the previous one.
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] arr = {7, 5, 2, 4, 7, 2, 3, 6, 4, 5, 12, 1, 7};
        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("longest increasing subsequence using memoization: " + lisByMemoization(arr, 0, Integer.MIN_VALUE, lookup));
        System.out.println("longest increasing subsequence using tabulation: " + lisByTabulation(arr));
    }

    public static int lisByMemoization(int[] arr, int i, int prev, Map<String, Integer> lookup) {
        if (lookup.get(i + "," + prev) != null) {
            return lookup.get(i + "," + prev);
        }

        if (i == arr.length) {
            return 0;
        } else if (arr[i] <= prev) {
            int value = lisByMemoization(arr, i + 1, prev, lookup);
            lookup.put(i + "," + prev, value);
            return value;
        } else {
            int value = Math.max(1 + lisByMemoization(arr, i + 1, arr[i], lookup), lisByMemoization(arr, i + 1, prev, lookup));
            lookup.put(i + "," + prev, value);
            return value;
        }
    }

    public static int lisByTabulation(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            int maxlength = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] > maxlength) {
                    maxlength = dp[j];
                }
            }
            dp[i] = 1 + maxlength;
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
