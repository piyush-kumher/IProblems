package com.piyush.psds.google.array_string;

import java.util.Arrays;

public class MaximumDistanceToClosestPerson {

    public static void main(String[] args) {
        MaximumDistanceToClosestPerson m = new MaximumDistanceToClosestPerson();
        int[] seats = {0, 0, 0, 0, 1, 0, 1};
        System.out.println(m.maxDistToClosest(seats));
    }

    public int maxDistToClosest1(int[] seats) {
        int n = seats.length;
        int[] dist = new int[n];
        int t = n;
        for(int i=0; i < n; i++) {
            t = (seats[i] == 0) ? (i == 0 ? t : dist[i-1]+1) : 0;
            dist[i] = t;
        }
        int ans = 0;
        t = n;
        for(int i=n-1; i>=0; i--) {
            t = (seats[i] == 0) ? (i == n-1 ? t : dist[i+1]+1) : 0;
            if(seats[i] == 0) {
                dist[i] = Math.min(dist[i], t);
                ans = Math.max(ans, dist[i]);
            }
        }
        return ans;
    }

    public int maxDistToClosest(int[] seats) {
        int N = seats.length;
        int[] left = new int[N], right = new int[N];
        Arrays.fill(left, N);
        Arrays.fill(right, N);

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) left[i] = 0;
            else if (i > 0) left[i] = left[i-1] + 1;
        }

        for (int i = N-1; i >= 0; --i) {
            if (seats[i] == 1) right[i] = 0;
            else if (i < N-1) right[i] = right[i+1] + 1;
        }

        int ans = 0;
        for (int i = 0; i < N; ++i)
            if (seats[i] == 0)
                ans = Math.max(ans, Math.min(left[i], right[i]));
        return ans;
    }
}
