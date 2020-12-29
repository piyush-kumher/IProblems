package com.piyush.psds.facebook.array_and_string;

import java.util.HashMap;
import java.util.Map;

public class IntegerToEnglishWords {


    static Map<Integer, String> sd = new HashMap<>();
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
        sd.put(10, "Ten");
        sd.put(11, "Eleven");
        sd.put(12, "Twelve");
        sd.put(13, "Thirteen");
        sd.put(14, "Fourteen");
        sd.put(15, "Fifteen");
        sd.put(16, "Sixteen");
        sd.put(17, "Seventeen");
        sd.put(18, "Eighteen");
        sd.put(19, "Nineteen");

        td.put(2, "Twenty");
        td.put(3, "Thirty");
        td.put(4, "Forty");
        td.put(5, "Fifty");
        td.put(6, "Sixty");
        td.put(7, "Seventy");
        td.put(8, "Eighty");
        td.put(9, "Ninety");
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder ans = new StringBuilder();
        if (num / 1000000000 > 0) {
            ans.append(solveThreeDigits(num / 1000000000)).append(" ").append("Billion").append(" ");
            num = num % 1000000000;
        }
        if (num / 1000000 > 0) {
            ans.append(solveThreeDigits(num / 1000000)).append(" ").append("Million").append(" ");
            num = num % 1000000;
        }
        if (num / 1000 > 0) {
            ans.append(solveThreeDigits(num / 1000)).append(" ").append("Thousand").append(" ");
            num = num % 1000;
        }
        if (num > 0) {
            ans.append(solveThreeDigits(num));
        }
        return ans.toString().trim();
    }

    private String solveThreeDigits(int num) {
        StringBuilder s = new StringBuilder();
        if (num / 100 > 0) {
            s.append(sd.get(num / 100)).append(" ").append("Hundred").append(" ");
            num = num % 100;
        }
        if (num / 10 > 1) {
            s.append(td.get(num / 10)).append(" ");
            num = num % 10;
        }
        if (num > 0) {
            s.append(sd.get(num));
        }
        return s.toString().trim();
    }

}
