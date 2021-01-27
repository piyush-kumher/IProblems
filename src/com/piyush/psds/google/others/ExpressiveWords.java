package com.piyush.psds.google.others;

public class ExpressiveWords {

    public static void main(String[] args) {
        ExpressiveWords e = new ExpressiveWords();
        String[] arr = {"aaaa"};
        System.out.println(e.expressiveWords("aaa", arr));
    }

    public int expressiveWords(String S, String[] words) {
        int result = 0;
        for(String word : words) {
            int i=0; int j=0;
            for(;i < word.length() && j < S.length(); i++) {
                char c1 = word.charAt(i);
                char c2 = S.charAt(j);
                if(c1 == c2) {
                    int count1 = 1;
                    int count2 = 1;
                    j++;
                    i++;
                    while(j < S.length() && S.charAt(j) == c2) {
                        j++;
                        count2++;
                    }
                    while(i < word.length() && word.charAt(i) == c1) {
                        i++;
                        count1++;
                    }
                    i--;
                    if(count1 > count2) {
                        break;
                    } else if(count2 < 3 && count1 != count2) {
                        break;
                    }
                } else {
                    break;
                }
            }
            if(i == word.length() && j == S.length()) {
                result++;
            }
        }
        return result;
    }
}
