package com.tzxylao.design.less.observer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author laoliangliang
 * @date 2019/4/2 14:45
 */
public abstract class EventListener {
    private Map<String, Event> eventMap = new HashMap<>();

    /**
     * 添加监听事件
     */
    public void addEventListener(String type, Object target, Method method) {
        Event event = new Event(target, method);
        eventMap.put(type, event);
    }

    private void trigger(Event event) {
        event.setSource(this);
        event.setTime(System.currentTimeMillis());
        try {
            event.getCallback().invoke(event.getTarget(), event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void trigger(String type) {
        if (!eventMap.keySet().contains(type)) {
            return;
        }
        trigger(eventMap.get(type).setTrigger(type));
    }

}
