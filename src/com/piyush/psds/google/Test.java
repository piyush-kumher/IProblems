package com.piyush.psds.google;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(t.solution("2 * 3 - 5 / 1"));
    }


    static Map<String, Set<String>> map = new HashMap<>();
    static {
        map.put("+", new HashSet<>(Arrays.asList("*" , "/")));
        map.put("-", new HashSet<>(Arrays.asList("*" , "/")));
        map.put("*", new HashSet<>(Arrays.asList("/")));
        map.put("/", new HashSet<>(Arrays.asList("")));
    }

    public String solution(String S) {
        // write your code in Java SE 8
        if(S == null && S.trim().equals("")) {
            return "NaN";
        }
        String[] elements = S.split(" ");
        Stack<Double> numStk = new Stack<>();   // Deque
        Stack<String> opStk = new Stack<>();
        for(int i=0; i < elements.length; i++) {
            switch(elements[i]) {
                case "+":
                case "-":
                case "*":
                case "/":
                    while(!opStk.isEmpty() && map.get(elements[i]).contains(opStk.peek())) {
                        double num2 = numStk.pop();
                        double num1 = numStk.pop();
                        double result = getResult(num1, num2, elements[i]); // find
                        numStk.push(result);
                    }
                    opStk.push(elements[i]);
                    break;
                case "(":
                    opStk.push("(");
                    break;
                case ")":
                    String op = opStk.pop();
                    while(!op.equals("(")) {
                        double num2 = numStk.pop();
                        double num1 = numStk.pop();
                        double result = getResult(num1, num2, op); // find
                        numStk.push(result);
                        op = opStk.pop();
                    }
                    break;
                default:
                    double num = getNumber(elements[i]);
                    numStk.push(num);
            }
        }
        while(!opStk.isEmpty()) {
            String op = opStk.pop();
            double num2 = numStk.pop();
            double num1 = numStk.pop();
            numStk.push(getResult(num1, num2, op));
        }
        return new Double(numStk.pop()).toString();
    }

    private double getNumber(String num) {
        return Double.parseDouble(num);
    }

    private double getResult(double num1, double num2, String op) {
        switch(op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
        }
        return 0.0d;
    }
}

