package com.tzxylao.design.less.observer3;

import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

/**
 * @author laoliangliang
 * @date 2019/7/19 9:47
 */
@Slf4j
public class ObserverA implements Observer {


    @Override
    public void update(Observable o, Object arg) {
        log.info(o.toString());

    }
}
