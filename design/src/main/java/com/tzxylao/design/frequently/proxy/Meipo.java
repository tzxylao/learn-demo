package com.tzxylao.design.frequently.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author laoliangliang
 * @date 2019/4/2 11:06
 */
@Slf4j
public class Meipo implements InvocationHandler {

    private IPerson man;

    public IPerson getInstance(IPerson man) {
        this.man = man;
        Class<? extends IPerson> aClass = man.getClass();
        return (IPerson) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("before");
        Object invoke = method.invoke(man, args);
        log.info("after : {}",invoke);
        return invoke;
    }

    public static void main(String[] args) {
        IPerson man = new Man();
        IPerson instance = new Meipo().getInstance(man);
        boolean love = instance.findLove();
        log.info("result:{}", love);
    }
}
