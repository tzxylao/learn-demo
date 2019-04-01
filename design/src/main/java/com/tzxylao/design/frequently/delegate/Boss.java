package com.tzxylao.design.frequently.delegate;

/**
 * @author laoliangliang
 * @date 2019/4/1 16:53
 */
public class Boss {
    public static void main(String[] args) {
        Leader leader = new Leader();
        leader.execute(ExecuteTypeEnum.A);
        leader.execute(ExecuteTypeEnum.B);
        leader.execute(ExecuteTypeEnum.A);
    }
}
