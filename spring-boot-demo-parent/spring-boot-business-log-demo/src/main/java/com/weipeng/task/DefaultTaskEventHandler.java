package com.weipeng.task;


import com.weipeng.enums.TaskEventTypeEnum;
import com.weipeng.log.TaskEventData;

/**
 * @Author: xxn
 * @Date: Created in 2020/06/05 17:42
 */
public class DefaultTaskEventHandler implements TaskEventHandler {

    @Override
    public void doHandleBeforeProceed(TaskEventData taskEventData) {

    }

    @Override
    public void doHandleAfterProceed(TaskEventData taskEventData) {

    }

    @Override
    public boolean match(TaskEventTypeEnum logType) {
        return false;
    }

}
