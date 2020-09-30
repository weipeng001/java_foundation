package com.weipeng.aspect;

import com.weipeng.enums.TaskEventTypeEnum;
import com.weipeng.log.TaskEventData;
import com.weipeng.task.TaskEventHandler;
import com.weipeng.thread.ExecutorServiceHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * 操作日志管理者
 * @Author: xxn
 * @Date: Created in 2020/05/18 11:16
 */
public class TaskEventManager {
    private static final Logger logger = LoggerFactory.getLogger(TaskEventManager.class);
    private ApplicationContext applicationContext;
    private ExecutorServiceHolder executorServiceHolder;
    private TaskEventHandler handler;

    TaskEventManager(ApplicationContext applicationContext, ExecutorServiceHolder executorServiceHolder) {
        this.applicationContext = applicationContext;
        this.executorServiceHolder = executorServiceHolder;
    }

    void processBeforeProceed(TaskEventAspect.TaskEventMetaData metaData, TaskEventData parameterData) {
        this.handler = this.getHandler(metaData.getType());
        this.handler.doHandleBeforeProceed(parameterData);
    }

    void processAfterProceed(TaskEventAspect.TaskEventMetaData metaData, TaskEventData parameterData) {
        if (this.handler == null) {
            this.handler = this.getHandler(metaData.getType());
        }
        if (metaData.isAsync()) {
            executorServiceHolder.execute(() -> handler.doHandleAfterProceed(parameterData));
        }else {
            handler.doHandleAfterProceed(parameterData);
        }
    }

    private TaskEventHandler getHandler(TaskEventTypeEnum type) {
        TaskEventHandler taskEventHandler;
        try {
            taskEventHandler = (TaskEventHandler) this.applicationContext.getBean(type.getHandlerClassName());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(String.format("操作类型：%s，找不到对应推送中台处理类", type.getDesc()));
        }
        return taskEventHandler;
    }

}
