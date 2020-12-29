package com.piyush.psds.facebook.array_and_string;

public class StringToIntegerATOI {

    public static void main(String[] args) {
        StringToIntegerATOI s = new StringToIntegerATOI();
        System.out.println(s.myAtoi("   -42"));
    }

    public int myAtoi(String s) {
        int sign = 1;
        int number = 0;
        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '+' ? 1 : -1;
            i++;
        }
        while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            if (number > Integer.MAX_VALUE / 10 ||
                    (number == Integer.MAX_VALUE / 10 && s.charAt(i) - '0' > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            number = number * 10 + (s.charAt(i++) - '0');
        }
        return number * sign;
    }

}
