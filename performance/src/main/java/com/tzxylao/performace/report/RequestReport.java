package com.tzxylao.performace.report;

import java.util.Map;

public class RequestReport{
    private Map<String,Object> header;
    private Object body;

    public Map<String, Object> getHeader() {
        return header;
    }

    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
