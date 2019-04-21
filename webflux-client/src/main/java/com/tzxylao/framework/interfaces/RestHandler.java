package com.tzxylao.framework.interfaces;

import com.tzxylao.framework.beans.MethodInfo;
import com.tzxylao.framework.beans.ServerInfo;

/**
 * @author laoliangliang
 * @since 19/4/20 下午9:51
 */
public interface RestHandler {
    /**
     * 初始化服务器信息
     * @param serverInfo
     */
    void init(ServerInfo serverInfo);

    /**
     * 调用rest请求，返回接口
     * @param methodInfo
     * @return
     */
    Object invokeRest(MethodInfo methodInfo);
}
