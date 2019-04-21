package com.piyush.sahu.DP.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Perfect Squares
 * https://leetcode.com/problems/perfect-squares/submissions/
 * https://www.geeksforgeeks.org/minimum-number-of-squares-whose-sum-equals-to-given-number-n/
 */
public class MinNoOfSquareSumsToANumber {

    /**
     * Runtime: 330 ms, faster than 9.80% of Java online submissions for Perfect Squares.
     * Memory Usage: 40.2 MB, less than 13.34% of Java online submissions for Perfect Squares.
     */
    public int numSquares(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        return find(n, map);
    }

    private int find(int n, Map<Integer, Integer> map){
        if(map.containsKey(n)){
            return map.get(n);
        }
        if(n == 3 || n == 0){
            return n;
        }
        int res = n;
        for(int i=1; i <=n; i++){
            int temp = i * i;
            if(temp > n){
                break;
            }else{
                res = Math.min(res, 1 + find(n -temp, map));
            }
        }
        map.put(n, res);
        return res;
    }


    /**
     * Runtime: 21 ms, faster than 71.83% of Java online submissions for Perfect Squares.
     * Memory Usage: 33.9 MB, less than 100.00% of Java online submissions for Perfect Squares.
     */
    public int numSquaresDP(int n) {
        int[] mem = new int[n+1];
        mem[0] = 0;
        for(int i=1; i <= n ; i++){
            mem[i] = i;
            for(int j=1; j <= i; j++){
                int temp = j * j;
                if(temp > i){
                    break;
                }
                else{
                    mem[i] = Math.min(mem[i], 1 + mem[i-temp]);
                }
            }
        }
        return mem[n];
    }

    public static void main(String[] args) {
        MinNoOfSquareSumsToANumber m = new MinNoOfSquareSumsToANumber();
        System.out.println(m.numSquaresDP(1));
    }

}
