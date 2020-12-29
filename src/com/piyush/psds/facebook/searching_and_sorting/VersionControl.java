package com.piyush.psds.facebook.searching_and_sorting;

class VC {
    public boolean isBadVersion(int i) {
        if(i >= 1702766719) {
            return true;
        }
        return false;
    }
}

public class VersionControl extends VC {

    public static void main(String[] args) {
        VersionControl v = new VersionControl();
        System.out.println(v.firstBadVersion(2126753390));
    }

    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;
        while(l < r) {
            int mid = l + (r-l) / 2;
            if(isBadVersion(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
