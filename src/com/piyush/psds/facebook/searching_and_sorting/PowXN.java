package com.piyush.psds.facebook.searching_and_sorting;

public class PowXN {

    public static void main(String[] args) {
        PowXN p = new PowXN();
        System.out.println(p.myPow(2.0d, 10));
    }

    public double myPow1(double x, int n) {
        if(x == 0) return 0;
        long N = n;
        if(n < 0) {
            x = 1.0/x;
            N = -N;
        }
        return doIt(x, N);
    }

    private double doIt(double x, long n) {
        if(n == 0) return 1.0;
        double ans = 1.0;
        double half = doIt(x, n/2);
        if(n%2 == 1) {
            ans *= x;
        }
        ans *= (half * half);
        return ans;
    }


    public double myPow(double x, int n) {
        if(x == 0) return 0;
        long N = n;
        if(n < 0) {
            x = 1.0/x;
            N = -N;
        }
        double ans = 1.0;
        double prod = x;
        for(long i=N; i > 0; i /= 2) {
            if(i%2 == 1) {
                ans = ans * prod;
            }
            prod = prod * prod;
        }
        return ans;
    }

}
