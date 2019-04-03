package com.tzxylao.design.less.observer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author laoliangliang
 * @date 2019/4/2 14:58
 */
@Slf4j
public class Observer {
    public void onClick(Event event) {
        log.info("Observer onClick:{}",event);
    }
}
