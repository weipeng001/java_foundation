package cn.bugstack.springframework.beans.factory.config;

import cn.bugstack.springframework.beans.PropertyValues;
import lombok.Data;

import java.util.Optional;

/**
 * Bean的定义信息
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
@Data
public class BeanDefinition {

    private Class beanClass;

    /**
     * bean 属性信息
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = Optional.ofNullable(propertyValues).orElse(new PropertyValues());
    }

}
