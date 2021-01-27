package com.piyush.psds.facebook.random;

import java.util.HashMap;
import java.util.Map;

public class IntegerToEnglishWord {

    static Map<Integer, String> sd = new HashMap<>();
    static Map<Integer, String> dd = new HashMap<>();
    static Map<Integer, String> td = new HashMap<>();

    static {
        sd.put(1, "One");
        sd.put(2, "Two");
        sd.put(3, "Three");
        sd.put(4, "Four");
        sd.put(5, "Five");
        sd.put(6, "Six");
        sd.put(7, "Seven");
        sd.put(8, "Eight");
        sd.put(9, "Nine");

        dd.put(2, "Twenty");
        dd.put(3, "Thirty");
        dd.put(4, "Forty");
        dd.put(5, "Fifty");
        dd.put(6, "Sixty");
        dd.put(7, "Seventy");
        dd.put(8, "Eighty");
        dd.put(9, "Ninety");

        td.put(10, "Ten");
        td.put(11, "Eleven");
        td.put(12, "Twelve");
        td.put(13, "Thirteen");
        td.put(14, "Fourteen");
        td.put(15, "Fifteen");
        td.put(16, "Sixteen");
        td.put(17, "Seventeen");
        td.put(18, "Eighteen");
        td.put(19, "Nineteen");
    }

    public String numberToWords(int num) {
        StringBuilder sb = new StringBuilder();
        if(num == 0) {
            return "Zero";
        }
        if(num / 1000000000 > 0) {
            sb.append(threeDigit(num/1000000000)).append(" ").append("Billion").append(" ");
            num = num % 1000000000;
        }
        if(num / 1000000 > 0) {
            sb.append(threeDigit(num/1000000)).append(" ").append("Million").append(" ");
            num = num % 1000000;
        }
        if(num / 1000 > 0) {
            sb.append(threeDigit(num/1000)).append(" ").append("Thousand").append(" ");
            num = num % 1000;
        }
        if(num > 0) {
            sb.append(threeDigit(num));
        }
        return sb.toString().trim();
    }

    private String threeDigit(int num) {
        StringBuilder sb = new StringBuilder();
        if(num / 100 > 0) {
            sb.append(sd.get(num/100)).append(" ").append("Hundred").append(" ");
            num = num % 100;
        }
        if(num / 10 == 1) {
            sb.append(td.get(num)).append(" ");
            num = 0;
        } else if(num / 10 > 1) {
            sb.append(dd.get(num/10)).append(" ");
            num = num % 10;
        }
        if(num > 0) {
            sb.append(sd.get(num)).append(" ");
        }
        return sb.toString().trim();
    }

}
