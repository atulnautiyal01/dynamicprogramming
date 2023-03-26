package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * find the maximum price that wew can earn by cutting rod into pieces
 * prices[i] represents price of rod for length i
 * n is size of rod n
 */
public class RodCutting {
    public static void main(String[] args) {
        int[] prices = {0, 1, 3, 5, 6, 7, 9, 10, 11};
        int n = 8;
        Map<Integer, Integer> lookup = new HashMap<>();
        System.out.println("max price to cut rod into pieces using memoization: " + maxPriceByMemoization(prices, n, lookup));
        System.out.println("max price to cut rod into pieces using tabulation: " + maxPriceByTabulation(prices, n));
    }

    public static int maxPriceByMemoization(int[] prices, int n, Map<Integer, Integer> lookup) {
        if (lookup.get(n) != null) {
            return lookup.get(n);
        }
        int max = 0;

        for (int i = 1; i <= n; i++) {
            max = Math.max(max, prices[i] + maxPriceByMemoization(prices, n - i, lookup));
        }
        lookup.put(n, max);
        return max;
    }

    public static int maxPriceByTabulation(int[] prices, int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int length = 1; length <= i; length++) {
                dp[i] = Math.max(dp[i], prices[length] + dp[i - length]);
            }
        }
        return dp[n];
    }
}
