package com.piyush.sahu.graph.leetcode;

/**
 * https://leetcode.com/problems/couples-holding-hands/
 */
public class CouplesholdingHands {

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Couples Holding Hands.
     * Memory Usage: 35.5 MB, less than 98.55% of Java online submissions for Couples Holding Hands.
     */
    public int minSwapsCouples(int[] row) {
        int[] ptn = new int[row.length];
        int[] pos = new int[row.length];
        for(int i=0; i < row.length; i++){
            ptn[i] = (i%2==0 ? i+1: i-1);
            pos[row[i]] = i;
        }
        int noOfSwaps = 0;
        for(int i=0; i < row.length; i++){
            for(int j=ptn[pos[ptn[row[i]]]]; i!=j; j=ptn[pos[ptn[row[i]]]]){
                swap(row, i, j);
                swap(pos, row[i], row[j]);
                noOfSwaps++;
            }
        }
        return noOfSwaps;
    }
    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Couples Holding Hands.
     * Memory Usage: 35.6 MB, less than 98.55% of Java online submissions for Couples Holding Hands.
     */
    public int usingUnionfind(int[] row) {
        int[] parent = new int[row.length/2];
        for(int i=0; i < row.length/2; i++) {
            parent[i] = i;
        }
        int count = 0;
        for(int i=0; i < row.length/2; i++){
            int a = row[2 * i];
            int b = row[2 * i + 1];
            if(union(parent, a/2, b/2)){
                count++;
            }
        }
        return count;
    }

    private boolean union(int[] parent, int p, int q){
        int rootP = find(parent, p);
        int rootQ = find(parent, q);
        if(rootP != rootQ){
            parent[rootP] = rootQ;
            return true;
        }
        return false;
    }

    private int find(int[] parent, int p){
        if(p == parent[p]){
            return p;
        }
        return find(parent, parent[p]);
    }

}
