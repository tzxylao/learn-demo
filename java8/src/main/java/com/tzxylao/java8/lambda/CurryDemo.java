package com.tzxylao.java8.lambda;

import java.util.function.Function;

/**
 * 级联表达式和柯里化
 * 柯里化：把多个参数的函数转换为只有一个参数的函数
 * 柯里化的目的：函数标准化
 * 高阶函数： 返回函数的函数
 * @author laoliangliang
 * @since 19/4/17 下午10:28
 */
public class CurryDemo {
    public static void main(String[] args) {
        //输入一个数字，返回一个函数
        Function<Integer,Function<Integer,Integer>> fun = x ->y -> x+y;
        System.out.println(fun.apply(2).apply(3));

        //多级的输入返回函数
        Function<Integer,Function<Integer,Function<Integer,Integer>>> fun2 = x -> y -> z -> x+y+z;
        System.out.println(fun2.apply(1).apply(2).apply(3));

        int[] nums = {2, 3, 4};
        Function f = fun2;
        for (int i = 0; i < nums.length; i++) {
            if (f instanceof Function) {
                Object obj = f.apply(nums[i]);
                if(obj instanceof Function){
                    f =  (Function) obj;
                }else{
                    System.out.println("调用结束：结果为 "+obj);
                }
            }
        }
    }
}
