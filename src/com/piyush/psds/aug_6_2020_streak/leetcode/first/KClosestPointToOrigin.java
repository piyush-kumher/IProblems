package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 */
public class KClosestPointToOrigin {

    public static void main(String[] args) {
        KClosestPointToOrigin k = new KClosestPointToOrigin();
        int[][] arr = {{1, 3}, {-2, 2}};
        System.out.println(k.kClosest(arr, 1));
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> q = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.d, p1.d));
        for (int[] point : points) {
            Point p = new Point(point[0], point[1]);
            q.offer(p);
            if (q.size() > K) {
                q.poll();
            }
        }
        int[][] ans = new int[K][2];
        for (int i = 0; i < K; i++) {
            if (!q.isEmpty()) {
                Point p = q.poll();
                ans[i][0] = p.x;
                ans[i][1] = p.y;
            }
        }
        return ans;
    }

    static class Point {
        int x;
        int y;
        int d;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.d = x * x + y * y;
        }
    }

}
