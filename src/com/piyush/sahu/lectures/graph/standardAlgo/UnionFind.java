package com.piyush.sahu.lectures.graph.standardAlgo;



import java.util.Arrays;

public class UnionFind {

    int[] rank;
    int[] parent;
    int count;

    public UnionFind(int disjointElements){
        parent = new int[disjointElements];
        rank = new int[disjointElements];
        for(int i=0; i < disjointElements; i++){
            parent[i] = i; // parent can also be set to -1 initially
        }
        this.count = disjointElements;
    }

    public int find(int p){
        if(p == parent[p]){ // if initially parent is set to -1, condition will change to parent[p] == -1
            return p;
        }
        return find(parent[p]);
    }

    public boolean union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ){
            return true;
        }
        if(rank[p] < rank[q]){
            parent[rootP] = rootQ;
        }else if(rank[rootQ] < rank[rootP]){
            parent[rootQ] = rootP;
        }else{
            parent[rootP] = rootQ;
            rank[rootP] = rank[rootP]+1;
        }
        count--;
        return false;
    }

    @Override
    public String toString() {
        return "Sets: " + count + "\nParent: " + Arrays.toString(parent) + "\nRank: " + Arrays.toString(rank);
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(5);
        System.out.println(unionFind);

        System.out.println("union 1 2");
        unionFind.union(1, 2);
        System.out.println(unionFind);

        System.out.println("union 1 2");
        unionFind.union(1, 2);
        System.out.println(unionFind);

        System.out.println("union 3 4");
        unionFind.union(3, 4);
        System.out.println(unionFind);

        System.out.println("union 1 0");
        unionFind.union(1, 0);
        System.out.println(unionFind);

        System.out.println("union 1 3");
        unionFind.union(1, 3);
        System.out.println(unionFind);

        System.out.println("find 4");
        System.out.println(unionFind.find(4));
        System.out.println(unionFind);
    }

}
