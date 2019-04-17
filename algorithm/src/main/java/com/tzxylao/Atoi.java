package com.tzxylao;

/**
 * @author laoliangliang
 * @since 19/4/14 下午8:25
 */
public class Atoi {
    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0 || (str.length() == 1 && ('-' == str.charAt(0) || '+' == str.charAt(0)))) {
            return 0;
        }
        boolean zflag = true;
        if ('-' == str.charAt(0)) {
            str = str.substring(1);
            zflag = false;
        }else if ('+' == str.charAt(0)) {
            str = str.substring(1);
        }

        int cutPos = 0;
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                j = i;
            }else{
                break;
            }
        }
        if (j > 0) {
            str = str.substring(j + 1);
            if("".equals(str)){
                return 0;
            }
        }
        for (int i = 0; i < str.length(); i++) {
            cutPos = i;
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                cutPos--;
                break;
            }
        }
        str = str.substring(0, cutPos + 1);
        if (str.length() == 0) {
            return 0;
        }

        if (str.length() > 10) {
            return zflag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            Long lon = Long.parseLong(str);
            if (lon > Integer.MAX_VALUE) {
                return zflag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            } else {
                return lon.intValue() * (zflag ? 1 : -1);
            }
        }
    }
}
