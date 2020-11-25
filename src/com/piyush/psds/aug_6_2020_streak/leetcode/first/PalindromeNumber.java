package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.Stack;

public class PalindromeNumber {

    public static void main(String[] args) {
        PalindromeNumber p = new PalindromeNumber();
        System.out.println(p.isPalindrome(1000021));
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x = x / 10;
        }
        if (x == revertedNumber || x == revertedNumber / 10) {
            return true;
        }
        return false;
    }

    public boolean isPalindrome_1(int x) {
        if (x < 0) {
            return false;
        }
        final int count = countDigits(x);
        Stack<Integer> s = new Stack<>();
        boolean isOddDigits = count % 2 == 1;
        int itr = 0;

        while (x != 0) {
            if (itr < count / 2) {
                s.push(x % 10);
            } else if (!(isOddDigits && itr == count / 2) && s.pop() != x % 10) {
                return false;
            }
            x = x / 10;
            itr++;
        }
        return true;
    }

    private int countDigits(int x) {
        int count = 0;
        while (x != 0) {
            x = x / 10;
            count++;
        }
        return count;
    }
}
