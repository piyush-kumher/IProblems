package com.piyush.psds.aug_6_2020_streak.leetcode.unionFind;

import java.util.*;

public class LargestComponentSizeByCommonFactor {

    public static void main(String[] args) {
        LargestComponentSizeByCommonFactor l = new LargestComponentSizeByCommonFactor();
        int[] A = {4,6,15,35};
        System.out.println(l.largestComponentSize(A));
    }

    public int largestComponentSize(int[] A) {
        int maxValue = Arrays.stream(A).reduce(A[0], (x, y) -> x > y ? x : y);
        DUS dsu = new DUS(maxValue+1);
        HashMap<Integer, Integer> numFactorMap = new HashMap<>();
        for(int num : A) {
            List<Integer> primeFactors = findPrimeNumberTill(num);
            numFactorMap.put(num, primeFactors.get(0));
            for(int i=0; i < primeFactors.size()-1 ; i++) {
                dsu.union(primeFactors.get(i), primeFactors.get(i+1));
            }
        }
        int maxGroup = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for(int num : A) {
            int root = dsu.find(numFactorMap.get(num));
            int c = count.getOrDefault(root, 0);
            count.put(root, c+1);
            maxGroup = Math.max(maxGroup, c+1);
        }
        return maxGroup;
    }

    public int largestComponentSize_normal_factor(int[] A) {
        int maxValue = Arrays.stream(A).reduce(A[0], (x, y) -> x > y ? x : y);
        DUS dsu = new DUS(maxValue+1);
        for(int num : A) {
            for(int factor = 2; factor < (int)(Math.sqrt(num)) + 1; factor++) {
                if(num % factor == 0) {
                    dsu.union(num, factor);
                    dsu.union(num, num/factor);
                }
            }
        }
        int maxGroup = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for(int num : A) {
            int root = dsu.find(num);
            int c = count.getOrDefault(root, 0);
            count.put(root, c+1);
            maxGroup = Math.max(maxGroup, c+1);
        }
        return maxGroup;
    }


    private List<Integer> findPrimeNumberTill(int num) {
        Set<Integer> set = new HashSet<>();
        int factor = 2;
        while(num >= factor * factor) {
            if(num % factor == 0) {
                num /= factor;
                set.add(factor);
            } else {
                factor++;
            }
        }
        set.add(num);
        return new ArrayList<>(set);
    }

    class DUS {
        int[] parents;
        int[] ranks;

        public DUS(int size) {
            parents = new int[size];
            ranks = new int[size];
            for(int i=0; i < size; i++) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        public int union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);
            if(parentA == parentB) {
                return parentA;
            }
            if(ranks[parentA] > ranks[parentB]) {
                parents[parentB] = parentA;
            } else if(ranks[parentA] < ranks[parentB]) {
                parents[parentA] = parentB;
                return parentB;
            } else {
                parents[parentB] = parentA;
                ranks[parentA]++;
            }
            return parentA;
        }

        public int find(int a) {
            if(parents[a] == a) {
                return a;
            }
            return find(parents[a]);
        }

    }
}
