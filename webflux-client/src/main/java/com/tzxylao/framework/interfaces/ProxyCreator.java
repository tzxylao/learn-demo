package com.tzxylao.framework.interfaces;

/**
 * 创建代理类接口
 * @author laoliangliang
 * @since 19/4/20 下午9:39
 */
public interface ProxyCreator {

    /**
     * 创建代理
     * @param type
     * @return
     */
    Object createProxy(Class<?> type);
}
