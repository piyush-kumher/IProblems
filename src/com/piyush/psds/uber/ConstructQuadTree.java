package com.piyush.psds.uber;

// https://leetcode.com/problems/construct-quad-tree/

// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}

public class ConstructQuadTree {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}
        };
        ConstructQuadTree cqt = new ConstructQuadTree();
        Node n = cqt.construct(grid);
        System.out.println(n);
    }

    public Node construct(int[][] grid) {
        if (grid.length != 0) {
            return construct(grid, 0, 0, grid.length - 1, grid.length - 1, new Node());
        }
        return new Node();
    }

    private Node construct(int[][] grid, int i, int j, int m, int n, Node curr) {
        if (i == m && j == n) {
            curr.val = grid[i][j] == 1;
            curr.isLeaf = true;
            return curr;
        }
        int x = (m - i) / 2;
        int y = (n - j) / 2;
        Node topLeft = construct(grid, i, j, i + x, j + y, new Node());
        Node topRight = construct(grid, i, j + y + 1, i + x, n, new Node());
        Node bottomLeft = construct(grid, i + x + 1, j, m, j + y, new Node());
        Node bottomRight = construct(grid, i + x + 1, j + y + 1, m, n, new Node());
        if ((topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf)
                && (topLeft.val == topRight.val && bottomLeft.val == bottomRight.val && topLeft.val == bottomLeft.val)) {
            curr.isLeaf = true;
            curr.val = topLeft.val;
        } else {
            curr.topLeft = topLeft;
            curr.topRight = topRight;
            curr.bottomLeft = bottomLeft;
            curr.bottomRight = bottomRight;
        }
        return curr;
    }
}
