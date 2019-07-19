package com.tzxylao.design.less.observer3;

import java.util.Observable;

/**
 * @author laoliangliang
 * @date 2019/7/19 9:44
 */
public class Subject extends Observable {

    private int state;

    public void setState(int state){
        setChanged();
        this.state = state;
        notifyObservers(state);
    }
}
