package com.tzxylao.design;

import org.junit.Test;

/**
 * @author laoliangliang
 * @since 19/4/10 下午8:12
 */
public class PalindromeTest {

    @Test
    public void testReverse() {
//        System.out.println(longestPalindrome("jfkldsahqwertyuuytrewqdklsahgjkfdhsasdffdsafhdsjkghfjdkshgfjkdshgfjdkh"));
        System.out.println(manacher("aabccba"));
    }

    public String longestPalindrome(String s) {
        // 判空
        if(s == null || s.length() < 1){
            return "";
        }
        // 最终拿到的开始位置和结束位置
        int start=0, end=0;
        for(int i = 0; i<s.length(); i++){
            int len1 = expandCenterAround(s, i, i);
            int len2 = expandCenterAround(s, i, i+1);
            int max = Math.max(len1, len2);
            if(max > end - start+1){
                start = i-(max-1)/2;
                end = i+max/2;
            }
        }
        return s.substring(start, end+1);


    }

    /**
     *以left，right为中心一点或两点进行扩展判断是否是回文
     */
    private int expandCenterAround(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }

    public static char[] manacherString(String str){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append("#");
            sb.append(str.charAt(i));
        }
        sb.append("#");
        return sb.toString().toCharArray();
    }

    public static int manacher(String str){
        if(str == null || str.length() < 1){
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] radius = new int[charArr.length];
        int R = -1;
        int c = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < radius.length; i++) {
            radius[i] = R > i ? Math.min(radius[2*c-i],R-i+1):1;
            while(i+radius[i] < charArr.length && i - radius[i] > -1){
                if(charArr[i-radius[i]] == charArr[i+radius[i]]){
                    radius[i]++;
                }else{
                    break;
                }
            }
            if(i + radius[i] > R){
                R = i + radius[i]-1;
                c = i;
            }
            max = Math.max(max,radius[i]);
        }
        return max-1;
    }
}
