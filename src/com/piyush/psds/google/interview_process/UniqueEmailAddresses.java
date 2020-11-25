package com.piyush.psds.google.interview_process;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/unique-email-addresses/submissions/
public class UniqueEmailAddresses {

    public int numUniqueEmails1(String[] emails) {
        Set<String> filteredEmails = new HashSet<>();
        for(String email : emails) {
            String[] emailParts = email.split("@");
            String localNameWithDots = emailParts[0].split("\\+")[0];
            String localName = localNameWithDots.replaceAll("\\.", "");
            String finalEmail = localName + "@" + emailParts[1];
            filteredEmails.add(finalEmail);
        }
        return filteredEmails.size();
    }

    public int numUniqueEmails(String[] emails) {
        Set<String> filteredEmails = new HashSet<>();
        for(String email : emails) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i < email.length(); i++) {
                char ch = email.charAt(i);
                if(ch == '@' || ch == '+') {
                    break;
                }
                if(ch != '.') {
                    sb.append(ch);
                }
            }
            sb.append(email.substring(email.indexOf('@')));
            filteredEmails.add(sb.toString());
        }
        return filteredEmails.size();
    }

}
