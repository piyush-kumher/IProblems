package com.piyush.psds.facebook.random;

public class AddBinary {

    public static void main(String[] args) {
        AddBinary ab = new AddBinary();
        System.out.println(ab.addBinary2("1010", "1011"));
    }

    public String addBinary(String a, String b) {
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < Math.max(a.length(), b.length()); i++) {
            int sum = (a.length()-i-1 >= 0 ? Character.getNumericValue(a.charAt(a.length()-i-1)) : 0) +
                    (b.length()-i-1 >= 0 ? Character.getNumericValue(b.charAt(b.length()-i-1)) : 0) + carry;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if(carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public String addBinary2(String a, String b) {
        int num1 = 0;
        int num2 = 0;
        for(int i=a.length()-1; i >=0; i--) {
            num1 = num1 + (int)Math.pow(2, a.length()-1-i) * Character.getNumericValue(a.charAt(i));
        }
        System.out.println(num1);
        for(int i=b.length()-1; i >=0; i--) {
            num2 = num2 + (int)Math.pow(2, b.length()-1-i) * Character.getNumericValue(b.charAt(i));
        }
        System.out.println(num2);
        int res = num1 + num2;
        StringBuilder sb = new StringBuilder();
        while(res != 0) {
            sb.append(res%2);
            res = res/2;
        }
        return sb.reverse().toString();
    }

    public String addBinary3(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length()-1;
        int j = b.length()-1;
        int carry = 0;
        while(i >= 0 && j >= 0) {
            int sum = (a.charAt(i--)-'0') + (b.charAt(j--)-'0') + carry;
            result.append(sum % 2);
            carry = sum / 2;
        }
        while(i >= 0) {
            int sum = (a.charAt(i--) - '0') + carry;
            result.append(sum % 2);
            carry = sum / 2;
        }
        while(j >= 0) {
            int sum = (b.charAt(j--) - '0') + carry;
            result.append(sum % 2);
            carry = sum / 2;
        }
        if(carry > 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }

}
