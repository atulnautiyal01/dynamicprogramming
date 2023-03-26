package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n steps to climb
 * Given jumps of different steps
 * find number of ways to climb
 */
public class WaysToClimb {
    public static void main(String[] args) {
        int n = 10;
        int[] jumps = {2,4,5,8};
        Map<Integer,Integer> lookup = new HashMap<>();
        System.out.println("The ways to climb using memoization: "+ waysToClimbByMemoization(n,jumps, lookup));
        System.out.println("The ways to climb using tabulation: "+ waysToClimbByTabulation(n,jumps));
    }


    public static int waysToClimbByMemoization(int n, int[] jumps, Map<Integer,Integer> lookup){
        if(lookup.get(n)!= null){
            return lookup.get(n);
        }
        if(n== 0){//found a way to reach nth step
            return 1;
        }
        else {
            int ways = 0;
            for (int jump: jumps) {
                if(n-jump >= 0) {
                    ways += waysToClimbByMemoization(n - jump, jumps, lookup);
                }
            }
            lookup.put(n,ways);
            return ways;
        }
    }

    public static int waysToClimbByTabulation(int n,int[] jumps){
        int dp[] = new int[n+1];
        dp[0]=1;
        for(int i = 1; i <= n; i++){
            for (int jump: jumps) {
                if(i - jump >= 0) {
                    dp[i] += dp[i - jump];
                }
            }
        }
        return dp[n];
    }
}
