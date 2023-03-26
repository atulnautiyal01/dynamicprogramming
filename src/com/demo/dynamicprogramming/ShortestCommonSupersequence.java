package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s1 and s2
 * find the shortest string that has both s1 and s2
 * A string 'A' is supersequence of a String 'B' if 'B' is a subsequence of 'A'
 */
public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        String s1 = "abdacbab";
        String s2 = "acebfca";
        Map<String, Integer> lookup = new HashMap<>();
        System.out.println("The shortest common supersequence using memoization: "+ scsByMemoization(s1,s2,0,0,lookup));
        System.out.println("The shortest common supersequence using tabulation: "+ scsByTabulation(s1,s2));
    }

    public static int scsByMemoization(String s1, String s2, int i, int j, Map<String, Integer> lookup){
        if(lookup.get(i+","+j)!= null){
            return lookup.get(i+","+j);
        }
        if( i == s1.length()){
            return s2.length() - j;
        }else if( j == s2.length()) {
            return s1.length() - i;
        }else if(s1.charAt(i) == s2.charAt(j)){
            int val = 1 + scsByMemoization(s1,s2,i+1,j+1, lookup);
            lookup.put(i+","+j, val);
            return val;
        }else{
            int val = 1 + Math.min(scsByMemoization(s1,s2,i,j+1, lookup), scsByMemoization(s1,s2,i+1,j, lookup));
            lookup.put(i+","+j, val);
            return val;
        }
    }

    public static int scsByTabulation(String s1, String s2){

        final int rows = s1.length();
        final int columns = s2.length();
        int dp[][] = new int[rows +1][columns +1];
        for(int col = 1 ; col < columns ; col++){
            dp[0][col] = col;
        }

        for(int row = 1; row < rows; row++){
            dp[row][0] = row;
        }

        for(int i = 1; i <= rows ; i ++){
            for(int j = 1; j <= columns; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[rows][columns];
    }
}
