package com.weipeng.task;

/**
 * @Author: xxn
 * @Date: Created in 2020/05/18 17:59
 */
public interface TaskEventHandlerChainFactory {
    /**
     * 得到操作日志执行链
     * @return
     */
    TaskEventHandlerChain getTaskEventHandlerChain();
}
