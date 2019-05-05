package com.tzxylao;

/**
 * @author laoliangliang
 * @date 2019/5/5 11:38
 */
public class Palindrome {
    public boolean isPalindrome(int x) {
        int[] buffer = new int[16];
        int bufferlen = 0;
        int i;

        /* 负数一定不是回文. */
        if (x < 0) {
            return false;
        }

        while (x>0) {
            buffer[bufferlen++] = x % 10;
            x /= 10;
        }

        for (i = 0; i < bufferlen>>1; i++) {
            if (buffer[i] != buffer[bufferlen-i-1]) {
                return false;
            }
        }

        return true;
    }
}
