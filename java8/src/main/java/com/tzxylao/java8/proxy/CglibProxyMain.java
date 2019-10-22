package com.tzxylao.java8.proxy;

import com.alibaba.fastjson.JSON;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author laoliangliang
 * @date 2019/10/21 10:57
 */
public class CglibProxyMain {
    static class CglibService{
        void sayHello(){
            System.out.println("hello !");
        }
    }

    static class CglibProxyInterceptor implements MethodInterceptor {


        @Override
        public Object intercept(Object o, Method method,
                                Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before args:"+ JSON.toJSONString(objects));
            Object invoke = methodProxy.invokeSuper(o, objects);
            System.out.println("after");
            return invoke;
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibService.class);
        enhancer.setCallback(new CglibProxyInterceptor());
        CglibService proxy = (CglibService) enhancer.create();
        System.out.println(CglibService.class);
        System.out.println(proxy.getClass());
        proxy.sayHello();
    }
}
