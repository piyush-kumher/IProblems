package com.piyush.psds.facebook.recursion;

import java.util.*;

public class Permutations2 {


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        // count the occurrence of each number
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (!counter.containsKey(num))
                counter.put(num, 0);
            counter.put(num, counter.get(num) + 1);
        }

        LinkedList<Integer> comb = new LinkedList<>();
        this.backtrack(comb, nums.length, counter, results);
        return results;
    }

    protected void backtrack(
            LinkedList<Integer> comb,
            Integer N,
            HashMap<Integer, Integer> counter,
            List<List<Integer>> results) {

        if (comb.size() == N) {
            // make a deep copy of the resulting permutation,
            // since the permutation would be backtracked later.
            results.add(new ArrayList<>(comb));
            return;
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            if (count == 0)
                continue;
            // add this number into the current combination
            comb.addLast(num);
            counter.put(num, count - 1);

            // continue the exploration
            backtrack(comb, N, counter, results);

            // revert the choice for the next exploration
            comb.removeLast();
            counter.put(num, count);
        }
    }

    public List<List<Integer>> permuteUnique_(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Set<List<Integer>> list = new HashSet<>();
        List<Integer> numbers = new ArrayList<>();
        for(int num: nums) {
            numbers.add(num);
        }
        recurse(numbers, list, 0);
        return new ArrayList(list);
    }

    private void recurse(List<Integer> numbers, Set<List<Integer>> list, int start) {
        if(start == numbers.size()) {
            list.add(new ArrayList<>(numbers));
        }
        for(int i=start; i < numbers.size(); i++) {
            Collections.swap(numbers, start, i);
            recurse(numbers, list, start+1);
            Collections.swap(numbers, i, start);
        }
    }

}
