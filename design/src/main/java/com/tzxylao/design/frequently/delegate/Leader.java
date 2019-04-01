package com.tzxylao.design.frequently.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author laoliangliang
 * @date 2019/4/1 16:53
 */
public class Leader implements IExecuteTask{

    private Map<String, IExecuteTask> actuators = new HashMap<>();
    public Leader() {
        actuators.put(ExecuteTypeEnum.A.getName(),new ExecuterA());
        actuators.put(ExecuteTypeEnum.B.getName(),new ExecuterB());
    }


    @Override
    public void execute(ExecuteTypeEnum command) {
        actuators.get(command.getName()).execute(command);
    }
}
