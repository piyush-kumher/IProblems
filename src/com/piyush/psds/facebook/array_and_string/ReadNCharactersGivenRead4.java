package com.piyush.psds.facebook.array_and_string;


/**
 * Method read4:
 *
 * The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf4.
 *
 * The return value is the number of actual characters read.
 *
 * Note that read4() has its own file pointer, much like FILE *fp in C.
 *
 * Definition of read4:
 *
 *     Parameter:  char[] buf4
 *     Returns:    int
 *
 * Note: buf4[] is destination not source, the results from read4 will be copied to buf4[]
 *
 * File file("abcde"); // File is "abcde", initially file pointer (fp) points to 'a'
 * char[] buf4 = new char[4]; // Create buffer with enough space to store characters
 * read4(buf4); // read4 returns 4. Now buf4 = "abcd", fp points to 'e'
 * read4(buf4); // read4 returns 1. Now buf4 = "e", fp points to end of file
 * read4(buf4); // read4 returns 0. Now buf4 = "", fp points to end of file
 *
 */

class Reader4 {
    public int read4(char[] buff) {
        return 0;
    }
}

public class ReadNCharactersGivenRead4 extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) {
        if (n <= 0) {
            return 0;
        }
        int itr = 0;
        char[] arr = new char[4];
        while (itr < n) {
            int ele = read4(arr);
            for (int i = 0; i < ele && itr < n; i++) {
                buf[itr++] = arr[i];
            }
            if (ele < 4) {
                break;
            }
        }
        return itr;
    }
}
