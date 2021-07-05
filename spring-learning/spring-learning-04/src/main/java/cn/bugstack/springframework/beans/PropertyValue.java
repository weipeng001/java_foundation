package cn.bugstack.springframework.beans;

import lombok.Getter;

/**
 * bean 属性信息
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
@Getter
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
}
