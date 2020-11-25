package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.*;

public class FindDuplicateFileInASystem {

    public static void main(String[] args) {
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"};
        FindDuplicateFileInASystem f = new FindDuplicateFileInASystem();
        System.out.println(f.findDuplicate(paths));
    }

    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for(String path : paths) {
            String[] splits = path.split(" ");
            for(int i=1; i < splits.length; i++) {
                String[] fileSplits = splits[i].split("\\(");
                String content = fileSplits[1].replace(")", "");
                String absPath = splits[0] + "/" + fileSplits[0];
                map.putIfAbsent(content, new ArrayList<>());
                map.get(content).add(absPath);
            }
        }
        List<List<String>> results = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()) {
            if(entry.getValue().size() > 1) {
                results.add(entry.getValue());
            }
        }
        return results;
    }

}
