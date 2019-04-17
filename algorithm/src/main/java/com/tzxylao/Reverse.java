package com.tzxylao;

/**
 * @author laoliangliang
 * @since 19/4/13 下午7:58
 */
public class Reverse {
    public int reverse(int x){
        int y = 0;
        StringBuilder sb = new StringBuilder();
        boolean xflag = false;
        if(x < 0){
            sb.append("-");
            if(x == Integer.MIN_VALUE){
                return 0;
            }else{
                x = Math.abs(x);
            }
            xflag = true;
        }
        if(x == 0){
            return 0;
        }
        while(x>0){
            y = x % 10;
            x = x / 10;
            sb.append(y);
        }
        String s = null;
        if(xflag){
            s = sb.substring(1);
        }else{
            s = sb.toString();
        }
        if(s.length()>10){
            return 0;
        }else{
            Long tempLong = Long.parseLong(s);
            if((tempLong > (2<<30)-1 && !xflag) || (tempLong > (2<<30)-1 && xflag)){
                return 0;
            }
        }
        return Integer.valueOf(sb.toString());
    }
}
