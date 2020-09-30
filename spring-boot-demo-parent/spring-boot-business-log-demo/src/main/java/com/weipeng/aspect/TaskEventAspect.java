package com.weipeng.aspect;

import com.weipeng.enums.TaskEventTypeEnum;
import com.weipeng.log.TaskEventData;
import com.weipeng.thread.ExecutorServiceHolder;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 新房业务 定时事件
 * @author wuweipeng
 * @date 2020/8/18
 */
@Component
@Aspect
public class TaskEventAspect {

    private static final Logger logger = LoggerFactory.getLogger(TaskEventAspect.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ExecutorServiceHolder executorServiceHolder;

    @Around(value = "@annotation(taskEvent)")
    public Object doProcessTaskEvent(ProceedingJoinPoint joinPoint, TaskEvent taskEvent) {
        Object returnData = null;
        try {
            TaskEventMetaData metaData = TaskEventMetaData.parse(taskEvent);
            // 校验注解信息
            metaData.validate();
            // 创建接口参数
            TaskEventData taskEventData = new TaskEventData(taskEvent.type(), joinPoint);
            // 创建操作日志管理者
            TaskEventManager taskEventManager = new TaskEventManager(this.applicationContext, this.executorServiceHolder);
            // 目标方法执行前处理
            taskEventManager.processBeforeProceed(metaData, taskEventData);
            // 执行目标方法
            returnData = joinPoint.proceed();
            // 目标方法执行后处理
            taskEventManager.processAfterProceed(metaData, taskEventData);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error(throwable.getMessage(),throwable);
            throw new RuntimeException(throwable.getMessage());
        }
        return returnData;
    }

    /**
     * OperateLog注解信息
     */
    @Data
    static class TaskEventMetaData {

        /**
         *   日志类别
         */
        private TaskEventTypeEnum type;

        /**
         * 是否异步
         */
        private boolean async;

        private static TaskEventMetaData parse(TaskEvent taskEvent) {
            TaskEventMetaData metaData = new TaskEventMetaData();
            metaData.setType(taskEvent.type());
            metaData.setAsync(taskEvent.async());
            return metaData;
        }

        private void validate() {
            if (this.type == null) {
                throw new RuntimeException("注解@TaskEvent须配置属性：type");
            }
        }
    }
}
