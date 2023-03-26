package com.demo.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * given an array of strictly positive integers
 * find if we can split the array int two subsets and both subsets have same sum
 */
public class Partition {
    public static void main(String[] args) {
        int arr[] = {4,5,3,2,5,1};
        Map<String, Boolean> lookupBase = new HashMap<>();
        Map<String,Integer> lookup = new HashMap<>();
        int sum = Arrays.stream(arr).sum();
        System.out.println("we can partition array into two parts using memoization: "+ partitionByMemoization(arr,sum,lookup));
        System.out.println("we can partition array into two parts using tabulation: "+ partitionByTabulation(arr,sum));
    }

    public static boolean partitionByMemoization(int[] arr, int sum, Map<String,Integer> lookup ){
        if(sum%2 == 1){
            return false;
        }else{
            return subsetByMemoization(arr,sum/2,0,lookup) > 0;
        }
    }

    public static boolean partitionByTabulation(int[] arr, int sum){
        if(sum%2 == 1){
            return false;
        }else{
            return subsetByTabulation(arr,sum/2) > 0;
        }
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
