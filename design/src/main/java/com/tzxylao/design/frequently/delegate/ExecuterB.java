package com.tzxylao.design.frequently.delegate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author laoliangliang
 * @date 2019/4/1 16:53
 */
@Slf4j
public class ExecuterB implements IExecuteTask{
    @Override
    public void execute(ExecuteTypeEnum command) {
        log.info("executer B begin work:{}",command.getName());
    }
}
