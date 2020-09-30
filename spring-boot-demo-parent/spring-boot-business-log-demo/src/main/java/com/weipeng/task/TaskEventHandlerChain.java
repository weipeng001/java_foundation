package com.weipeng.task;


import com.weipeng.log.TaskEventData;
import org.springframework.util.Assert;

import java.util.List;

/**
 *
 * @author wuweipeng
 * @date 2020/9/30
 */
public class TaskEventHandlerChain {
    private List<TaskEventHandler> taskEventHandlers;

    TaskEventHandlerChain(List<TaskEventHandler> operateLogHandlers) {
        Assert.notEmpty(operateLogHandlers);
        this.taskEventHandlers = operateLogHandlers;
    }

    void processBeforeProceed(TaskEventData parameterData) {
        for (TaskEventHandler operateLogHandler : taskEventHandlers) {
            if (operateLogHandler.match(parameterData.getType())) {
                operateLogHandler.doHandleBeforeProceed(parameterData);
            }
        }
    }

    void processAfterProceed(TaskEventData parameterData) {
        for (TaskEventHandler operateLogHandler : taskEventHandlers) {
            if (operateLogHandler.match(parameterData.getType())) {
                operateLogHandler.doHandleAfterProceed(parameterData);
            }
        }
    }

}
