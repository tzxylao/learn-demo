package com.tzxylao.design.less.observer;

import java.lang.reflect.Method;

/**
 * @author laoliangliang
 * @date 2019/4/2 14:43
 */
public class Event {
    private Object source;

    private String trigger;

    private Object target;

    private Method callback;

    private long time;

    public Event(Object target, Method callback) {
        this.target = target;
        this.callback = callback;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public String getTrigger() {
        return trigger;
    }

    public Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getCallback() {
        return callback;
    }

    public void setCallback(Method callback) {
        this.callback = callback;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event{" +
                "source=" + source +
                ", trigger='" + trigger + '\'' +
                ", target=" + target +
                ", callback=" + callback +
                ", time=" + time +
                '}';
    }
}
