package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.Stack;

public class AsteroidCollision {

    public static void main(String[] args) {
        AsteroidCollision ac = new AsteroidCollision();
        int[] arr = {-2,-1,1,2};
        System.out.println(ac.asteroidCollision(arr));
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stk = new Stack<>();
        for(int i=0; i < asteroids.length; i++) {
            if(asteroids[i] != Math.abs(asteroids[i])) {
                while(true) {
                    if(stk.isEmpty()) {
                        stk.push(asteroids[i]);
                        break;
                    } else {
                        int top = stk.peek();
                        if(top == Math.abs(top)) {
                            if(top > Math.abs(asteroids[i])) {
                                break;
                            }
                            stk.pop();
                            if(top == Math.abs(asteroids[i])) {
                                break;
                            }
                        } else {
                            stk.push(asteroids[i]);
                            break;
                        }
                    }
                }
            } else {
                stk.push(asteroids[i]);
            }
        }
        int[] answer = new int[stk.size()];
        for(int i=answer.length-1; i >=0 ; i--) {
            answer[i] = stk.pop();
        }
        return answer;
    }

}
