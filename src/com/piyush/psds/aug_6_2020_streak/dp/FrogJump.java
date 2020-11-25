package com.piyush.psds.aug_6_2020_streak.dp;

public class FrogJump {

    public static void main(String[] args) {
        FrogJump f = new FrogJump();
        int[] arr = {0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println(f.canCross(arr));
    }

    public boolean canCross(int[] stones) {
        if (stones[0] + 1 != stones[1]) {
            return false;
        }
        return jump(stones, 1, 1);
    }

    private boolean jump(int[] stones, int lastIndex, int k) {
        if (lastIndex == stones.length - 1) {
            return true;
        }
        int minusjump = k > 1 ? search(stones, lastIndex + 1, stones[lastIndex] + k - 1) : -1;
        if (minusjump >= 0 && jump(stones, minusjump, k - 1)) {
            return true;
        }
        int nextIndex = (minusjump != -1) ? (minusjump) : (lastIndex);
        int kjump = search(stones, nextIndex + 1, stones[lastIndex] + k);
        if (kjump >= 0 && jump(stones, kjump, k)) {
            return true;
        }
        nextIndex = kjump != -1 ? kjump : nextIndex;
        int plusjump = search(stones, nextIndex + 1, stones[lastIndex] + k + 1);
        if (plusjump >= 0 && jump(stones, plusjump, k + 1)) {
            return true;
        }
        return false;
    }

    private int search(int[] stones, int start, int num) {
        int end = stones.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (stones[mid] == num) {
                return mid;
            } else if (stones[mid] > num) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
