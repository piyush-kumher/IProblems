package com.piyush.psds.uber;

import java.util.*;

// https://leetcode.com/problems/sliding-puzzle/submissions/
public class SlidingPuzzle {

    // shortest distance : BFS
    public int slidingPuzzle(int[][] board) {
        int rows = board.length;
        int columns = board[0].length;
        int zeroX = 0;
        int zeroY = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                    break;
                }
            }
        }
        Node zero = new Node(zeroX, zeroY, board, 0);
        Queue<Node> q = new ArrayDeque();
        q.offer(zero);

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Set<String> seen = new HashSet<>();
        String target = Arrays.deepToString(new int[][]{{1, 2, 3}, {4, 5, 0}});
        seen.add(zero.node);

        while (!q.isEmpty()) {
            Node node = q.remove();
            if (node.node.equals(target)) {
                return node.dist;
            }
            for (int[] direction : directions) {
                int x = direction[0] + node.zeroX;
                int y = direction[1] + node.zeroY;
                if (x < 0 || x >= rows || y < 0 || y >= columns) {
                    continue;
                }
                int[][] newBoard = new int[rows][columns];
                int t = 0;
                for (int[] row : node.board) {
                    newBoard[t++] = row.clone();
                }
                newBoard[node.zeroX][node.zeroY] = newBoard[x][y];
                newBoard[x][y] = 0;
                Node newNode = new Node(x, y, newBoard, node.dist + 1);
                if (seen.contains(newNode.node)) {
                    continue;
                }
                q.add(newNode);
                seen.add(newNode.node);
            }
        }
        return -1;
    }

    // A* algo: Dijsktra with heuristic
    public int slidingPuzzle_HNODE(int[][] board) {
        int R = board.length, C = board[0].length;
        int sr = 0, sc = 0;

        //Find sr, sc
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 0) {
                    sr = i;
                    sc = j;
                    break;
                }
            }
        }

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        PriorityQueue<HNode> heap = new PriorityQueue<>((a, b) ->
                (a.heuristic + a.depth) - (b.heuristic + b.depth));
        HNode start = new HNode(board, sr, sc, 0);
        heap.add(start);

        Map<String, Integer> cost = new HashMap<>();
        cost.put(start.boardstring, 9999999);

        String target = Arrays.deepToString(new int[][]{{1, 2, 3}, {4, 5, 0}});
        String targetWrong = Arrays.deepToString(new int[][]{{1, 2, 3}, {5, 4, 0}});

        while (!heap.isEmpty()) {
            HNode node = heap.poll();
            if (node.boardstring.equals(target))
                return node.depth;
            if (node.boardstring.equals(targetWrong))
                return -1;
            if (node.depth + node.heuristic > cost.get(node.boardstring))
                continue;

            for (int[] di : directions) {
                int nei_r = di[0] + node.zero_r;
                int nei_c = di[1] + node.zero_c;

                // If the neighbor is not on the board or wraps incorrectly around rows/cols
                if ((Math.abs(nei_r - node.zero_r) + Math.abs(nei_c - node.zero_c) != 1) ||
                        nei_r < 0 || nei_r >= R || nei_c < 0 || nei_c >= C)
                    continue;

                int[][] newboard = new int[R][C];
                int t = 0;
                for (int[] row : node.board)
                    newboard[t++] = row.clone();

                // Swap the elements on the new board
                newboard[node.zero_r][node.zero_c] = newboard[nei_r][nei_c];
                newboard[nei_r][nei_c] = 0;

                HNode nei = new HNode(newboard, nei_r, nei_c, node.depth + 1);
                if (nei.depth + nei.heuristic >= cost.getOrDefault(nei.boardstring, 9999999))
                    continue;
                heap.add(nei);
                cost.put(nei.boardstring, nei.depth + nei.heuristic);
            }
        }

        return -1;
    }

    class Node {
        int zeroX;
        int zeroY;
        int dist;
        int[][] board;
        String node;

        public Node(int i, int j, int[][] board, int dist) {
            this.dist = dist;
            this.zeroX = i;
            this.zeroY = j;
            this.board = board;
            this.node = Arrays.deepToString(board);
        }
    }

    class HNode {
        int[][] board;
        String boardstring;
        int heuristic;
        int zero_r;
        int zero_c;
        int depth;

        HNode(int[][] B, int zr, int zc, int d) {
            board = B;
            boardstring = Arrays.deepToString(board);

            //Calculate heuristic
            heuristic = 0;
            int R = B.length, C = B[0].length;
            for (int r = 0; r < R; ++r)
                for (int c = 0; c < C; ++c) {
                    if (board[r][c] == 0) continue;
                    int v = (board[r][c] + R * C - 1) % (R * C);
                    // v/C, v%C: where board[r][c] should go in a solved puzzle
                    heuristic += Math.abs(r - v / C) + Math.abs(c - v % C);
                }
            heuristic /= 2;
            zero_r = zr;
            zero_c = zc;
            depth = d;
        }
    }
}
