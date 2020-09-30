package com.weipeng.task;


import com.weipeng.enums.TaskEventTypeEnum;
import com.weipeng.log.TaskEventData;

/**
 * 日志处理接口
 * @Author: xxn
 * @Date: Created in 2020/05/18 9:59
 */
public interface TaskEventHandler {

    /**
     * 目标方法执行前处理
     * @param taskEventData
     */
    void doHandleBeforeProceed(TaskEventData taskEventData);

    /**
     * 目标方法执行后处理
     * @param taskEventData
     */
    void doHandleAfterProceed(TaskEventData taskEventData);

    /**
     * 匹配
     * @param logType
     * @return
     */
    boolean match(TaskEventTypeEnum logType);
}
