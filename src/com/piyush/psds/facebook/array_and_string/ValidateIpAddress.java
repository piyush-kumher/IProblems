package com.piyush.psds.facebook.array_and_string;

import java.util.*;

public class ValidateIpAddress {

    class Solution {

        public String validIPAddress(String IP) {
            return validIPv4(IP) ? "IPv4" : (validIPv6(IP) ? "IPv6" : "Neither");
        }

        private boolean validIPv6(String IP) {
            long count = IP.chars().filter(c -> c == ':').count();
            if(count != 7) {
                return false;
            }
            String[] splits = IP.split(":", -1);
            Set<Character> set = new HashSet<>(Arrays.asList('0','1','2','3','4','5','6','7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f',
                    'A', 'B', 'C', 'D', 'E', 'F'));
            for(String split : splits) {
                if(split.length() == 0 || split.length() > 4) {
                    return false;
                }
                for(char ch : split.toCharArray()) {
                    if(!set.contains(ch)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean validIPv4(String IP) {
            long count = IP.chars().filter(c -> c == '.').count();
            if(count != 3) {
                return false;
            }
            String[] splits = IP.split("\\.", -1);
            for(String split : splits) {
                if(split.length() == 0 || split.length() > 3) {
                    return false;
                } else if(split.charAt(0) == '0' && split.length() != 1) {
                    return false;
                }
                for(char ch : split.toCharArray()) {
                    if(!Character.isDigit(ch)) {
                        return false;
                    }
                }
                if(Integer.parseInt(split) > 255) {
                    return false;
                }
            }
            return true;
        }
    }

}
