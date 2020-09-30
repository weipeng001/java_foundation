package com.weipeng.log;

import com.weipeng.enums.TaskEventTypeEnum;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口参数
 * @author wuweipeng
 * @date 2020/8/18
 */
@Data
public class TaskEventData {
    private TaskEventTypeEnum type;
    private Map<String, Object> argsMap = new HashMap<>(16);

    public TaskEventData(TaskEventTypeEnum type, ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < args.length; i++) {
            argsMap.put(parameterNames[i], args[i]);
        }
        this.type = type;
    }

    public void put(String key, Object value) {
        if (!StringUtils.isEmpty(key) && value != null) {
            this.argsMap.put(key, value);
        }
    }

    public Object get(String key) {
        if (StringUtils.isEmpty(key) ) {
            return null;
        }
        return this.argsMap.get(key);
    }

}
