package com.piyush.psds.google.interview_process;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/fruit-into-baskets/
public class FruitsIntoBaskets {

    public int totalFruit(int[] tree) {
        Map<Integer, Integer> counter = new HashMap<>();
        int j=0;
        int ans = 0;
        for(int i=0; i < tree.length; i++) {
            counter.put(tree[i], counter.getOrDefault(tree[i], 0) + 1);
            while(counter.size() >= 3) {
                counter.put(tree[j], counter.get(tree[j]) - 1);
                if(counter.get(tree[j]) == 0) {
                    counter.remove(tree[j]);
                }
                j++;
            }
            ans = Math.max(ans, i-j+1);
        }
        return ans;
    }

    public static void main(String[] args) {
        FruitsIntoBaskets fib = new FruitsIntoBaskets();
        int[] tree = {3,3,3,1,2,1,1,2,3,3,4};
        System.out.println(fib.totalFruit(tree));
    }

}
