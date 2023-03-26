package com.demo.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * given a string s and arrays of words
 * find out if it is possible to break s into words
 */
public class WordBreak {
    public static void main(String[] args) {
        String s = "captsandogsareanimals";
        String arr[]= {"cats","dog","sand","and", "cat","mals", "san", "dogs", "are", "animals", "ani", "og", "sar"};
        final Set<String> words = Arrays.stream(arr).collect(Collectors.toSet());
        Map<Integer, Boolean> lookup = new HashMap<>();
        System.out.println("String s can be broken down into words using memoization: "+ wordBreakByMemoization(s,words,0,lookup));
        System.out.println("String s can be broken down into words using tabulation: "+ wordBreakByTabulation(s,words));
    }

    public static boolean wordBreakByMemoization(String s, Set<String> words, int i, Map<Integer, Boolean> lookup){
        if(lookup.get(i) != null){
            return lookup.get(i);
        }
        if( i == s.length()){
            return true;
        }else{
            for(int j = i+1; j <= s.length() ; j ++){
                if(words.contains(s.substring(i,j)) && wordBreakByMemoization(s,words,j,lookup)){
                    lookup.put(i,true);
                    return true;
                }
            }
            lookup.put(i,false);
            return false;
        }
    }

    public static boolean wordBreakByTabulation(String s, Set<String> words){
        boolean dp[] = new boolean[s.length()+1];
        dp[0] = true;
        for(int i = 1 ;i <= s.length() ; i ++){
            for(int j = 0; j < i; j++){
                if(words.contains(s.substring(j,i)) && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
