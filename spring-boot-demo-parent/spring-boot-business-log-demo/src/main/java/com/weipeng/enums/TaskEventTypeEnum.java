package com.weipeng.enums;

/**
 * 定时事件类型
 * @author wuweipeng
 * @date 2020/8/18
 */
public enum TaskEventTypeEnum {

    LOG_ADD("新增","userEventChainHandler");

    private String desc;//触发点_事件描述

    private String handlerClassName;

    TaskEventTypeEnum(String desc, String handlerClassName) {
        this.desc = desc;
        this.handlerClassName = handlerClassName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHandlerClassName() {
        return handlerClassName;
    }

    public void setHandlerClassName(String handlerClassName) {
        this.handlerClassName = handlerClassName;
    }
}
