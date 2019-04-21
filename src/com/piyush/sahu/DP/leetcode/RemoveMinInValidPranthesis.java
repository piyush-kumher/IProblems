package com.piyush.sahu.DP.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveMinInValidPranthesis {

    /**
     * Runtime: 3 ms, faster than 78.91% of Java online submissions for Remove Invalid Parentheses.
     * Memory Usage: 40.6 MB, less than 55.69% of Java online submissions for Remove Invalid Parentheses.
     */
    public List<String> removeInvalidParentheses(String s) {
        // Find how many left and right invalid parenthesis are there.
        int leftRem = 0;
        int rightRem = 0;
        Set<String> validExpression = new HashSet<>();
        for(int i=0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                leftRem++;
            }else if(s.charAt(i) == ')' && leftRem > 0){
                leftRem--;
            }else if(s.charAt(i) == ')'){
                rightRem++;
            }
        }
        recurse(s, 0, 0, 0, leftRem, rightRem, new StringBuilder(), validExpression);
        return new ArrayList<>(validExpression);
    }

    private void recurse(String s, int index, int left, int right, int leftRem, int rightRem,
                         StringBuilder expression, Set<String> validExpression) {
        // If we have reached the end and we ave removed leftRem
        // and rightRem parenthesis already then this is valid solution
        if(index == s.length()){
            if(leftRem == 0 && rightRem == 0){
                validExpression.add(expression.toString());
            }
        }else{
            char ch = s.charAt(index);
            int length = expression.length();
            // Case where we dont consider current ( or ) as we have leftRem and rightRem parenthesis to remove.
            if((ch == '(' && leftRem > 0) || (ch == ')' && rightRem > 0)){
                recurse(s, index+1, left, right,
                        ch == '(' ? leftRem - 1 : leftRem,
                        ch == ')' ? rightRem-1: rightRem,
                        expression, validExpression);
            }
            expression.append(ch);
            // Any character other than ( and ), should be ignored.
            if(ch != '(' && ch != ')'){
                recurse(s, index+1, left, right,
                        leftRem,
                        rightRem,
                        expression, validExpression);
             // We have ( as current char and we have already tried removing this, now consider it valid.
            }else if(ch == '('){
                recurse(s, index+1, left+1, right, leftRem, rightRem, expression, validExpression);
            //We have ) as current char and we have already tried removing this,
                // now if we have a matching left parenthesis, consider it.
            }else if(right < left){
                recurse(s, index+1, left, right+1, leftRem, rightRem, expression, validExpression);
            }
            expression.deleteCharAt(length);
        }
    }
}
