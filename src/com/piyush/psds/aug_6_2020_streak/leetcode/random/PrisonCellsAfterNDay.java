package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.HashMap;
import java.util.Map;

public class PrisonCellsAfterNDay {

    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, Integer> seen = new HashMap<>();
        boolean repetitionStart = false;
        while(N > 0) {
            if(!repetitionStart) {
                Integer bitMap = getBitMap(cells);
                if(seen.containsKey(bitMap)) {
                    N = N % (seen.get(bitMap) - N);
                    repetitionStart = true;
                } else {
                    seen.put(bitMap, N);
                }
            }
            if(N > 0) {
                N--;
                cells = getDay(cells);
            }
        }
        return cells;
    }

    private int[] getDay(int[] cells) {
        int[] newCells = new int[cells.length];
        newCells[0] = 0;
        for(int i=1; i < cells.length-1; i++) {
            newCells[i] = cells[i-1] == cells[i+1] ? 1 : 0;
        }
        newCells[cells.length-1] = 0;
        return newCells;
    }

    private Integer getBitMap(int[] cells) {
        int bitMap = 0;
        for(int cell : cells) {
            bitMap <<= 1;
            bitMap = (bitMap | cell);
        }
        return bitMap;
    }


    public int[] prisonAfterNDays_better(int[] cells, int N) {
        Map<Integer, Integer> seen = new HashMap<>();
        boolean repetitionStart = false;
        int bitMap = 0;
        for (int cell : cells) {
            bitMap <<= 1;
            bitMap = (bitMap | cell);
        }
        while(N > 0) {
            if(!repetitionStart) {
                if(seen.containsKey(bitMap)) {
                    N = N % (seen.get(bitMap) - N);
                    repetitionStart = true;
                } else {
                    seen.put(bitMap, N);
                }
            }
            if(N > 0) {
                N--;
                bitMap = getDay(bitMap);
            }
        }
        int[] res = new int[cells.length];
        for(int i=cells.length-1; i>=0; i--) {
            res[i] = bitMap & 1;
            bitMap >>= 1;
        }
        return res;
    }

    private int getDay(int bitMap) {
        bitMap = ~((bitMap << 1) ^ (bitMap >> 1));
        // set the head and tail to zero
        bitMap = bitMap & 0x7e;
        return bitMap;
    }

}
