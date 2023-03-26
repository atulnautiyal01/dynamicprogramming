package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of strictly positive integers
 * find subset whose sum is k
 */
public class SubsetSum {
    public static void main(String[] args) {
        int[] arr = {1,2,3,1};
        int k = 4;
        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("The number of subsets using memoization "+ subsetByMemoization(arr,k,0,lookup));
        System.out.println("The number of subsets using tabulation "+ subsetByTabulation(arr,k));

    }

    public static int subsetByMemoization(int[] arr, int sum, int i, Map<String, Integer> lookup){
       if(lookup.get(sum+","+i) != null){
           return lookup.get(sum+","+i);
       }

       if(sum==0){
           return 1;
       }else if(i == arr.length || sum < 0){
           return 0;
       }
       else {
           int value = subsetByMemoization(arr,sum-arr[i],i+1, lookup)+ subsetByMemoization(arr,sum,i+1, lookup);
           lookup.put(sum+","+i, value);
           return value;
       }
    }

    public static int subsetByTabulation(int[] arr, int sum){
        int dp[][] = new int[arr.length][sum+1];

        dp[0][0] = 1;

        if( arr[0]  <= sum){
            dp[0][arr[0]] = 1;
        }

        for(int i = 1; i < arr.length; i ++){
            for(int j = 0; j <= sum; j++ ){
                dp[i][j] = dp[i-1][j] + (j - arr[i] >=0 ? dp[i-1][j-arr[i]]: 0);
            }
        }

        return dp[arr.length -1][sum];

    }
}
