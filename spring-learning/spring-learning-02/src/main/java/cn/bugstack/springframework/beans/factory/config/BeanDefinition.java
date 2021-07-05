package cn.bugstack.springframework.beans.factory.config;

import lombok.Data;

/**
 * bean的定义信息
 *
 * Object bean 替换为 Class实体
 * 这样就可以把 Bean 的实例化操作放到容器中处理了
 * spring也是通过class来帮我们实例化的
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
@Data
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(final Class beanClass) {
        this.beanClass = beanClass;
    }

}
