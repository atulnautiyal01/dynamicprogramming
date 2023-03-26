package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given three types of tickets
 * 1 day ticket
 * 7 days ticket
 * 30 days ticket
 * <p>
 * we are travelling for n days
 * <p>
 * For given days we will use the train
 */
public class MinimumCostForTickets {
    public static void main(String[] args) {
        int[] trainDays = {1, 3, 8, 9, 22, 23, 28, 31};
        int[] costs = {4, 10, 25};
        int n = 32;
        Map<Integer, Integer> lookup = new HashMap<>();
        System.out.println("The minimum cost of tickets using memoization: " + costByMemoization(trainDays, costs, n, 0, lookup));
        System.out.println("The minimum cost of tickets using tabulation: " + costByTabulation(trainDays, costs, n));

    }

    public static int costByMemoization(int[] trainDays, int[] costs, int n, int day, Map<Integer, Integer> lookup) {
        if (lookup.get(day) != null) {
            return lookup.get(day);
        }
        if (day >= n) {
            return 0;
        } else if (!isDayInTrainDays(trainDays, day)) {
            int val = costByMemoization(trainDays, costs, n, day + 1, lookup);
            lookup.put(day, val);
            return val;
        } else {
            int val = Math.min(costs[0] + costByMemoization(trainDays, costs, n, day + 1, lookup),
                    Math.min(costs[1] + costByMemoization(trainDays, costs, n, day + 7, lookup), costs[2] + costByMemoization(trainDays, costs, n, day + 30, lookup)));
            lookup.put(day, val);
            return val;
        }
    }

    public static boolean isDayInTrainDays(int[] trainDays, int day) {
        for (int d : trainDays) {
            if (d == day) {
                return true;
            }
        }
        return false;
    }

    public static int costByTabulation(int[] trainDays, int[] costs, int n) {
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            if(!isDayInTrainDays(trainDays,i)){
                dp[i] = dp[i-1];
            }else {
                dp[i] = Math.min(costs[0] + (i - 1 >= 0 ? dp[i - 1] : 0),
                        Math.min(costs[1] + (i - 7 >= 0 ? dp[i - 7] : 0), costs[2] + (i - 30 >= 0 ? dp[i - 30] : 0)));
            }
        }
        return dp[n - 1];
    }
}
