package com.weipeng.service;

import com.weipeng.aspect.TaskEvent;
import com.weipeng.bean.User;
import com.weipeng.enums.TaskEventTypeEnum;
import org.springframework.stereotype.Service;

/**
 * @author wuweipeng
 * @date 2020/9/30 20:04
 */
@Service
public class UserService {


    @TaskEvent(type= TaskEventTypeEnum.LOG_ADD)
    public void add(User user){
        dothing();
    }

    private void dothing() {
    }
}
