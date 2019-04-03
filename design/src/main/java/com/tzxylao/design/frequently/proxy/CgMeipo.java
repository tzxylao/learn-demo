package com.tzxylao.design.frequently.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author laoliangliang
 * @date 2019/4/2 11:37
 */
@Slf4j
public class CgMeipo implements MethodInterceptor{

    public Object getInstance(Class clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("before");
        Object invoke = methodProxy.invokeSuper(o, objects);
        log.info("after:{}",invoke);
        return invoke;
    }

    public static void main(String[] args) {
        CgMan cgMan = new CgMan();
        CgMan instance = (CgMan) new CgMeipo().getInstance(cgMan.getClass());
        boolean love = instance.findLove();
        log.info("love:"+love);
    }
}
