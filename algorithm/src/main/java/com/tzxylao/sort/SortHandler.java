package com.tzxylao.sort;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class SortHandler implements InvocationHandler {

    private ISort sort;
    public static final String ENDWITH = "Sort";

    public SortHandler(ISort sort) {
        this.sort = sort;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String name = method.getName();
        Object invoke = null;

        if (name.endsWith(ENDWITH)) {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            invoke = method.invoke(this.sort, args);
            stopWatch.end();

            if (this.sort.isShowResult()) {
                System.out.println("["+ name + "]耗时：" + stopWatch.getCalTime() + "ns");
                System.out.println("排序结果："+Arrays.toString((int[]) invoke));
            }else{
                System.out.println("["+name + "]耗时：" + stopWatch.getCalTime()/1000000 + "ms");
            }
            System.out.println();
        }
        return invoke;
    }

}