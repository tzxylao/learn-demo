package com.tzxylao.sort;

/**
 * @author laoliangliang
 * @date 2019/4/17 11:15
 */
public class StopWatch {
    public void start(){
        startTime = System.nanoTime();
    }
    public void end(){
        endTime = System.nanoTime();
    }

    private long startTime;

    private long endTime;

    public long getCalTime(){
        return endTime - startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
