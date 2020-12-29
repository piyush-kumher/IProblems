package com.piyush.psds.facebook.array_and_string;

import java.util.ArrayList;
import java.util.List;

public class PancakeSorting {

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        for(int i = arr.length; i > 0; i--) {
            int index = find(arr, i);
            if(index == i-1) {
                continue;
            }
            if(index != 0) {
                flip(arr, index);
                ans.add(index+1);
            }
            flip(arr, i-1);
            ans.add(i);
        }
        return ans;
    }

    private void flip(int[] nums, int last) {
        int start = 0;
        while(start < last) {
            int temp = nums[start];
            nums[start++] = nums[last];
            nums[last--] = temp;
        }
    }

    private int find(int[] arr, int num) {
        for(int i=0; i < arr.length; i++) {
            if(arr[i] == num) {
                return i;
            }
        }
        return -1;
    }

}
