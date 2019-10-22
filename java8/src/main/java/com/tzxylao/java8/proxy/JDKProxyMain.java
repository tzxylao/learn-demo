package com.tzxylao.java8.proxy;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author laoliangliang
 * @date 2019/10/21 10:44
 */
public class JDKProxyMain {

    public static void main(String[] args) {
        JDKProxyInterface jdkProxy = new JDKProxyImpl("ha li ru ya");
        JDKInvocationHandler handler = new JDKInvocationHandler(jdkProxy);
        JDKProxyInterface proxy = (JDKProxyInterface) Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(),
                jdkProxy.getClass().getInterfaces(), handler);
        proxy.testProxy("lilei");
    }

    interface JDKProxyInterface{
        void testProxy(String param);
    }

    static class JDKProxyImpl implements JDKProxyInterface{

        private String content;

        public JDKProxyImpl(String content) {
            this.content = content;
        }

        @Override
        public void testProxy(String param) {
            System.out.println(param+":"+content);
        }
    }

    static class JDKInvocationHandler implements InvocationHandler {

        private JDKProxyInterface jdkProxy;

        public JDKInvocationHandler(JDKProxyInterface jdkProxy) {
            this.jdkProxy = jdkProxy;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("before args:"+ JSON.toJSONString(args));
            Object invoke = method.invoke(jdkProxy, args);
            System.out.println("after");
            return invoke;
        }
    }

}
