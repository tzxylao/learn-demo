package com.tzxylao.design.less.observer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author laoliangliang
 * @date 2019/4/2 14:54
 */
@Slf4j
public class Mouse extends EventListener {
    public void click() {
        log.info("click mouse");
        trigger("click");
    }

    public void doubleClick() {
        log.info("doubleClick mouse");
        trigger("doubleClick");
    }
}
