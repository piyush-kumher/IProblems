package com.piyush.psds.google.array_string;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int id = n-1; id >= 0; id--) {
            if(digits[id] == 9) {
                digits[id] = 0;
            } else {
                digits[id]++;
                return digits;
            }
        }
        digits = new int[n+1];
        digits[0] = 1;
        return digits;
    }

}
