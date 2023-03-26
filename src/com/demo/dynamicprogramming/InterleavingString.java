package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * given string s1 and s2
 * find if s3 can be formed by an interleaving of s1 and s2
 */
public class InterleavingString {
    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        Map<String, Boolean> lookup = new HashMap<>();
        System.out.println("s3 can be formed by interleaving s1 and s2: " + checkInterleavingByMemoization(s1, s2, s3, 0, 0, lookup));
        System.out.println("s3 can be formed by interleaving s1 and s2: " + checkInterleavingByTabulation(s1, s2, s3));
    }

    public static boolean checkInterleavingByMemoization(String s1, String s2, String s3, int i, int j, Map<String, Boolean> lookup) {
        if(s1.length()+s2.length() != s3.length()){
            return false;
        }
        if (lookup.get(i + "," + j) != null) {
            lookup.get(i + "," + j);
        }
        if (i == s1.length() && j == s2.length()) {
            return true;
        } else {
            boolean val = (i < s1.length() && s1.charAt(i) == s3.charAt(i + j) && checkInterleavingByMemoization(s1, s2, s3, i + 1, j, lookup))
                    || (j < s2.length() && s2.charAt(j) == s3.charAt(i + j) && checkInterleavingByMemoization(s1, s2, s3, i, j + 1, lookup));
            lookup.put(i + "," + j, val);
            return val;
        }
    }

    public static boolean checkInterleavingByTabulation(String s1, String s2, String s3){
        int length1= s1.length();
        int length2 = s2.length();
        boolean dp[][] = new boolean[length1+1][length2+1];

        dp[0][0] = true;

        for(int row=1; row <= length1 ; row++){
            dp[row][0] = s1.charAt(row-1) == s3.charAt(row-1) && dp[row-1][0];
        }

        for(int col=1; col <= length2 ; col++){
            dp[0][col] = s2.charAt(col-1) == s3.charAt(col-1) && dp[0][col-1];
        }

        for(int row = 1; row <= length1; row++){
            for(int col = 1; col <= length2; col++){
                dp[row][col] = s1.charAt(row-1) == s3.charAt(row+col-1) && dp[row-1][col]
                        || s2.charAt(col-1) == s3.charAt(row+col-1) && dp[row][col-1];
            }
        }
        return dp[length1][length2];
    }
}
