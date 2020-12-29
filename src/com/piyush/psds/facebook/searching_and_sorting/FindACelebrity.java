package com.piyush.psds.facebook.searching_and_sorting;

import java.util.HashSet;
import java.util.Set;


class Relation {
    boolean knows(int a, int b) {
        return true;
    }
}

public class FindACelebrity extends Relation {


    public int findCelebrity(int n) {
        int celebrityCandidate = 0;
        for(int i=0; i < n; i++) {
            // remember of the essence of this condition, if a person does not know other person,
            //the other person can't be celebrity. Thats why we are interested in 'knows'.
            if(knows(celebrityCandidate, i)) {
                celebrityCandidate = i;
            }
        }
        if(isCelebrity(celebrityCandidate, n)) {
            return celebrityCandidate;
        }
        return -1;
    }

    private boolean isCelebrity(int i, int n) {
        for(int j=0; j < n; j++) {
            if(i != j) {
                if(knows(i, j) || !knows(j, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int findCelebrity_1(int n) {
        int[] know = new int[n];
        int[] dk = new int[n];
        for(int i=0; i < n; i++)  {
            for(int j=i+1; j < n; j++) {
                if(knows(i, j)) {
                    know[j]++;
                } else {
                    dk[i]++;
                }
                if(knows(j, i)) {
                    know[i]++;
                } else {
                    dk[j]++;
                }
            }
        }
        for(int i=0; i < n; i++) {
            if(know[i] == n-1 && dk[i] == n-1) {
                return i;
            }
        }
        return -1;
    }

}
