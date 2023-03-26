package com.demo.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Give values and their weights
 * Maximise the value by not exceeding K
 */
public class KnapSack {
    public static void main(String[] args) {
        int values[] = {20,30,15,25,10};
        int weights[] = {6,13,5,10,3};
        int k = 20;
        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("The maximum value using memoization: "+ maximizeValueByMemoization(values,weights,k,0,lookup));
        System.out.println("The maximum value using tabulation: "+ maximizeValueByTabulation(values,weights,k));
    }

    public static int maximizeValueByMemoization(int values[], int weights[], int k, int i,Map<String, Integer> lookup){
        if(lookup.get(k+","+ i) != null ){
            return lookup.get(k+","+ i);
        }
        if(i == values.length){
            return 0;
        }else if(weights[i] > k){
            int value = maximizeValueByMemoization(values,weights, k,i+1, lookup);
            lookup.put(k+","+i, value);
            return value;
        }else{
            int value = Math.max(values[i] + maximizeValueByMemoization(values,weights,k-weights[i], i+1, lookup),
                    maximizeValueByMemoization(values,weights,k,i+1, lookup));
            lookup.put(k+","+i, value);
            return value;
        }
    }

    public static int maximizeValueByTabulation(int values[], int weights[], int k){
        if(k==0){
            return 0;
        }else if( k >= Arrays.stream(weights).sum()){
            return Arrays.stream(values).sum();
        }
        int dp[][] = new int[values.length][k+1];

        for(int col = 0; col <= k ; col++){
            dp[0][col] = values[0];
        }

        for(int row = 1; row < values.length; row++){
            for(int col = 0; col <= k; col++){
                if(weights[row] > col){
                    dp[row][col] = dp[row-1][col];
                }else{
                    dp[row][col] = Math.max(values[row]+ dp[row-1][col-weights[row]], dp[row-1][col]);
                }
            }
        }
        return dp[values.length-1][k];
    }
}

