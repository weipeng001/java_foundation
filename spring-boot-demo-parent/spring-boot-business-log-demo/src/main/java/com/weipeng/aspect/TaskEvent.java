package com.weipeng.aspect;

import com.weipeng.enums.TaskEventTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskEvent {
    /**
     * 业务操作类型（必须配置）
     * @return
     */
    TaskEventTypeEnum type();

    /**
     * 是否异步记录，默认异步
     * @return
     */
    boolean async() default true;
}
