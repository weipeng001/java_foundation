package com.weipeng.log;

import com.alibaba.fastjson.JSONObject;
import com.weipeng.bean.User;
import com.weipeng.enums.TaskEventTypeEnum;
import com.weipeng.task.DefaultTaskEventHandler;
import com.weipeng.task.TaskEventChainHandler;
import com.weipeng.task.TaskEventHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 相关日志处理
 * @author wuweipeng
 * @date 2020/9/30
 */
@Component
public class UserEventChainHandler extends TaskEventChainHandler {

    @Override
    protected List<TaskEventHandler> provideOperateLogHandlers() {
        List<TaskEventHandler> taskEventHandlers = new ArrayList<>();
        taskEventHandlers.add(new UserAddHandler());
        return taskEventHandlers;
    }

    /**
     * 修改操作
     */
    private class UserAddHandler extends DefaultTaskEventHandler {

        @Override
        public void doHandleAfterProceed(TaskEventData taskEventData) {
            Map<String, Object> argsMap = taskEventData.getArgsMap();
            User u = (User) argsMap.get("user");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("subscribeId",u.getId());
            jsonObject.put("type", "ADD");
            UserEventChainHandler.this.initTaskEvent(taskEventData, jsonObject.toString());
        }

        @Override
        public boolean match(TaskEventTypeEnum taskEventType) {
            return taskEventType == TaskEventTypeEnum.LOG_ADD;
        }
    }

}
