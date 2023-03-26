package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an amount and list of possible coins
 * find the minimum number of coins to make the amount
 */
public class CoinChange {
    public static final int INT_BIG_VALUE=1000000000;
    public static void main(String[] args) {
        int amount = 15;
        int[] possibleCoins = {2, 3, 7};
        Map<Integer, Integer> lookup = new HashMap<>();
        System.out.println("The minimum number of coins using memoization: " + minNumberOfCoinsByMemoization(amount, possibleCoins, lookup));
        System.out.println("The minimum number of coins using tabulation: " + minNumberOfCoinsByTabulation(amount, possibleCoins));

    }

    public static int minNumberOfCoinsByMemoization(int amount, int[] possibleCoins, Map<Integer, Integer> lookup) {
        if (lookup.get(amount) != null) {
            return lookup.get(amount);
        }
        if (amount == 0) {
            return 0;
        } else {
            int numberOfCoins = INT_BIG_VALUE;
            for (int coin : possibleCoins) {
                if (amount - coin >= 0) {
                    numberOfCoins = Math.min(numberOfCoins, 1 + minNumberOfCoinsByMemoization(amount - coin, possibleCoins, lookup));
                }
            }
            lookup.put(amount, numberOfCoins);
            return numberOfCoins;
        }
    }

    public static int minNumberOfCoinsByTabulation(int amount, int[] possibleCoins){
        int dp[] = new int[amount+1];
        for(int i = 1 ; i <= amount ; i ++){
            dp[i] = INT_BIG_VALUE;
        }
        dp[0] = 0;

        for(int amt = 1; amt <= amount; amt++){
            for (int coin: possibleCoins) {
                if(amt-coin >= 0) {
                    dp[amt] = Math.min(dp[amt], 1 + dp[amt - coin]);
                }
            }
        }
        return dp[amount];
    }
}
