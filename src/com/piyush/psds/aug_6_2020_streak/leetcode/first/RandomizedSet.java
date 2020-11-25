package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> list;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public static void main(String[] args) {
        RandomizedSet s = new RandomizedSet();
        System.out.println(s.remove(0));
        System.out.println(s.remove(0));
        System.out.println(s.insert(0));
        System.out.println(s.getRandom());
        System.out.println(s.remove(0));
        System.out.println(s.insert(0));
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int indexGettingRemoved = map.get(val);
        if (indexGettingRemoved != list.size() - 1) {
            int endEle = list.get(list.size() - 1);
            list.set(indexGettingRemoved, endEle);
            map.put(endEle, indexGettingRemoved);
        }
        map.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
    }
}
