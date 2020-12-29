package com.piyush.psds.facebook.searching_and_sorting;

public class DivideTwoIntegers {

    public static void main(String[] args) {
        int dividend = 25000;
        int divisor = 5;
        DivideTwoIntegers d = new DivideTwoIntegers();
        System.out.println(d.divide(dividend, divisor));
    }

    public int divide(int dividend, int divisor) {
        int HALF_MIN = -1073741824;
        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int positives = 0;
        if(dividend > 0) {
            dividend = -dividend;
            positives++;
        }
        if(divisor > 0) {
            divisor = -divisor;
            positives++;
        }

        int power = -1;
        int value = divisor;
        while(value >= HALF_MIN && (value + value) >= dividend) {
            value = value + value;
            power += power;
        }

        int quotient = 0;

        while(dividend <= divisor) {
            if(dividend <= value) {
                quotient += power;
                dividend -= value;
            }
            value >>= 1;
            power >>= 1;
        }
        if(positives == 2 || positives == 0) {
            return -quotient;
        }
        return quotient;
    }


    public int divide_1(int dividend, int divisor) {
        int HALF_MIN = -1073741824;
        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int positives = 0;
        if(dividend > 0) {
            dividend = -dividend;
            positives++;
        }
        if(divisor > 0) {
            divisor = -divisor;
            positives++;
        }
        int quotient = 0;
        while(dividend <= divisor) {
            int power = -1;
            int value = divisor;
            while(value >= HALF_MIN && (value + value) >= dividend) {
                value = value + value;
                power += power;
            }
            quotient += power;
            dividend -= value;
        }
        if(positives == 2 || positives == 0) {
            return -quotient;
        }
        return quotient;
    }

}
