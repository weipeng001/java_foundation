package cn.bugstack.springframework;

import lombok.Getter;

/**
 * bean的定义信息
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
@Getter
public class BeanDefinition {

    private final Object bean;

    public BeanDefinition(final Object bean) {
        this.bean = bean;
    }

}
