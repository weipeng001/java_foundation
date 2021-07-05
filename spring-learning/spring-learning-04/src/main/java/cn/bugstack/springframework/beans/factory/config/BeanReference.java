package cn.bugstack.springframework.beans.factory.config;

import lombok.Getter;

/**
 * Bean 的引用
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
@Getter
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

}
