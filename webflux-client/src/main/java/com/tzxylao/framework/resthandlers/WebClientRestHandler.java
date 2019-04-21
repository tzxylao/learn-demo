package com.tzxylao.framework.resthandlers;

import com.tzxylao.framework.beans.MethodInfo;
import com.tzxylao.framework.beans.ServerInfo;
import com.tzxylao.framework.interfaces.RestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author laoliangliang
 * @since 19/4/20 下午10:36
 */
public class WebClientRestHandler implements RestHandler {
    private WebClient client;

    @Override
    public void init(ServerInfo serverInfo) {
        this.client = WebClient.create(serverInfo.getUrl());
    }

    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        Object result = null;

        WebClient.RequestBodySpec request = this.client
                //请求方法
                .method(methodInfo.getMethod())
                //请求url和参数
                .uri(methodInfo.getUrl(), methodInfo.getParams())

                .accept(MediaType.APPLICATION_JSON);

        WebClient.ResponseSpec retrieve = null;
        if (methodInfo.getBody() != null) {
            //发出请求
            retrieve = request.body(methodInfo.getBody(), methodInfo.getBodyElementType()).retrieve();
        }else{
            retrieve = request.retrieve();
        }

        //处理异常
        retrieve.onStatus(status -> status.value() == 404,
                response -> Mono.just(new RuntimeException("Not Found")));


        //处理body
        if (methodInfo.isReturnFlux()) {
            result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
        }else{
            result = retrieve.bodyToMono(methodInfo.getReturnElementType());
        }
        return result;
    }
}
