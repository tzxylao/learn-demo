package com.tzxylao.design.less.observer;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author laoliangliang
 * @date 2019/4/2 15:00
 */
public class MouseTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Observer observer = new Observer();
        Method method = observer.getClass().getDeclaredMethod("onClick", Event.class);
        Mouse mouse = new Mouse();
        mouse.addEventListener("click", observer, method);

//        mouse.trigger("click");
        mouse.click();

        Set set = new HashSet();
    }
}
