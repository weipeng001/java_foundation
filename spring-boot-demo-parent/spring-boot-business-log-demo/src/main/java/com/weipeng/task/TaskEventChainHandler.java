package com.weipeng.task;

import com.weipeng.enums.TaskEventTypeEnum;
import com.weipeng.log.TaskEventData;
import com.weipeng.service.TaskEventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 支持链式处理
 * @Author: xxn
 * @Date: Created in 2020/05/18 19:17
 */
public class TaskEventChainHandler extends AbstractTaskEventHandlerChainFactory implements TaskEventHandler {

    @Autowired
    private TaskEventService taskEventService;

    protected void initTaskEvent(TaskEventData taskEventData, String jsonParams) {

    }

    @Override
    public void doHandleBeforeProceed(TaskEventData parameterData) {
        TaskEventHandlerChain logHandlerChain = this.getTaskEventHandlerChain();
        if (logHandlerChain != null) {
            logHandlerChain.processBeforeProceed(parameterData);
        }
    }

    @Override
    public void doHandleAfterProceed(TaskEventData parameterData) {
        TaskEventHandlerChain logHandlerChain = this.getTaskEventHandlerChain();
        if (logHandlerChain != null) {
            logHandlerChain.processAfterProceed(parameterData);
        }
    }

    @Override
    public boolean match(TaskEventTypeEnum logType) {
        return false;
    }

    @Override
    protected List<TaskEventHandler> provideOperateLogHandlers() {
        return null;
    }
}
