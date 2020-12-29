package com.piyush.psds.google.others;

public class ReverseInteger {

    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
        System.out.println(r.reverse(-145));
    }


    // positive max : 214748364[7]
    // negitive min: -214748364[8]
    public int reverse(int x) {
        int res = 0;
        while(x != 0) {
            int fd = x % 10;
            x = x / 10;
            if(res > Integer.MAX_VALUE/10 || res == Integer.MAX_VALUE/10 && fd > 7) {
                return 0;
            }
            if(res < Integer.MIN_VALUE/10 || res == Integer.MIN_VALUE/10 && fd < -8) {
                return 0;
            }
            res = res * 10 + fd;
        }
        return res;
    }

}
