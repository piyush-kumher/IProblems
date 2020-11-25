package com.piyush.psds.aug_6_2020_streak.leetcode.first;

public class ReverseInteger {

    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
        System.out.println(r.reverse(1534236469));
    }

    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int r = x % 10;
            int d = x / 10;
            result = result * 10 + r;
            x = d;
        }
        return result;
    }

}
