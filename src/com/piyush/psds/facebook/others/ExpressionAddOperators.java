package com.piyush.psds.facebook.others;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

    public static void main(String[] args) {
        ExpressionAddOperators e = new ExpressionAddOperators();
        System.out.println(e.addOperators("123", 6));
    }

    String num;
    int target;
    List<String> ans = new ArrayList<>();
    public List<String> addOperators(String num, int target) {
        if(num.length() == 0) {
            return ans;
        }
        this.target = target;
        this.num = num;
        this.recurse(0, 0, 0, 0, new ArrayList<>());
        return this.ans;
    }

    private void recurse(int index, long prevOp, long currOp, long value, List<String> chain) {

        if(index == num.length()) {
            if(value == target && currOp == 0) {
                StringBuilder sb = new StringBuilder();
                chain.subList(1, chain.size()).forEach(sb::append);
                this.ans.add(sb.toString());
            }
            return;
        }

        currOp = currOp * 10 + Character.getNumericValue(num.charAt(index));
        String currValRep = Long.toString(currOp);
        if(currOp > 0) {
            recurse(index+1, prevOp, currOp, value, chain);
        }

        chain.add("+");
        chain.add(currValRep);
        recurse(index+1, currOp, 0, value+currOp, chain);
        chain.remove(chain.size() - 1);
        chain.remove(chain.size() - 1);

        if(chain.size() > 0) {
            chain.add("-");
            chain.add(currValRep);
            recurse(index + 1, -currOp, 0, value - currOp, chain);
            chain.remove(chain.size() - 1);
            chain.remove(chain.size() - 1);

            chain.add("*");
            chain.add(currValRep);
            recurse(index + 1, currOp * prevOp, 0, value - prevOp + currOp * prevOp, chain);
            chain.remove(chain.size() - 1);
            chain.remove(chain.size() - 1);
        }
    }

}
