package com.weipeng.task;

import java.util.List;

/**
 * @Author: xxn
 * @Date: Created in 2020/05/19 10:08
 */
public abstract class AbstractTaskEventHandlerChainFactory implements TaskEventHandlerChainFactory {
    private volatile TaskEventHandlerChain taskEventHandlerChain;

    @Override
    public TaskEventHandlerChain getTaskEventHandlerChain() {
        if (taskEventHandlerChain != null) {
            return taskEventHandlerChain;
        }else {
            if (taskEventHandlerChain == null) {
                synchronized (this) {
                    if (taskEventHandlerChain == null) {
                        taskEventHandlerChain = new TaskEventHandlerChain(this.provideOperateLogHandlers());
                    }
                }
            }
        }
        return taskEventHandlerChain;
    }

    /**
     * 提供日志handler
     * @return
     */
    protected abstract List<TaskEventHandler> provideOperateLogHandlers();

}
