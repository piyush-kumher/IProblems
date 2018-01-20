package com.piyush.sahu.google;

public class ThePaintersProblem {

    public static void main(String[] args) {
        int k=2, sumOfLength = 0;
        int[] boards = {10, 20, 30, 40};
        for(int i=0; i< boards.length; i++){
            sumOfLength += boards[i];
        }
        int maxPaintersNeeded = k > boards.length ? k : boards.length;
        int average = sumOfLength / maxPaintersNeeded;
        int m=0, maxTillNow = 0;
        for(int i=0; i < boards.length; i++){
            if(m + boards[i] > average && m > 0){
                m = 0;
                maxTillNow = maxTillNow > m ? maxTillNow : m;
            }
        }
    }

}
