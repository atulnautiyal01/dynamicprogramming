package com.demo.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * given a string of number find the ways to decode the number
 * decoding map
 * 1-A,2-B,3-C ... 24-X,25-Y,26-Z
 */
public class WaysToDecode {
    public static void main(String[] args) {
        String s = "512810120129";
        Map<Integer, Integer> lookup = new HashMap<>();
        System.out.println("ways to decode using memoization: " + wtdByMemoization(s, 0, lookup));
        System.out.println("ways to decode using tabulation: " + wtdByTabulation(s));
    }

    public static int wtdByMemoization(String s, int i, Map<Integer, Integer> lookup) {
        if (lookup.get(i) != null) {
            return lookup.get(i);
        }
        if (i == s.length()) {
            return 1;
        }
        if (s.charAt(i) == '0') {
            return 0;
        } else if (i < s.length() - 1 && isValueBetween10And26(s.charAt(i), s.charAt(i + 1))) {
            int value = wtdByMemoization(s, i + 1, lookup) + wtdByMemoization(s, i + 2, lookup);
            lookup.put(i, value);
            return value;
        } else {
            int value = wtdByMemoization(s, i + 1, lookup);
            lookup.put(i, value);
            return value;
        }
    }

    public static int wtdByTabulation(String s) {
        int[] dp = new int[s.length()];
        if (s.charAt(0) == '0') {
            return 0;
        }
        dp[0] = 1;
        dp[1] = (s.charAt(1) != 0 ? 1 : 0) + (isValueBetween10And26(s.charAt(0), s.charAt(1)) ? 1 : 0);

        for (int i = 2; i < s.length(); i++) {
            dp[i] = s.charAt(i) != '0' ? dp[i - 1] : 0;
            dp[i] += isValueBetween10And26(s.charAt(i - 1), s.charAt(i)) ? dp[i - 2] : 0;
        }
        return dp[s.length() - 1];
    }

    public static boolean isValueBetween10And26(char i, char j) {
        int value = Integer.valueOf(String.valueOf(i) + j);
        return value >= 10 && value <= 26;
    }
}
