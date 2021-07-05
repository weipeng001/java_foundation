package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认单例实现类
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(final String beanName) {
        return this.singletonObjects.get(beanName);
    }

    protected void addSingleton(final String beanName, final Object singletonObject) {
        this.singletonObjects.put(beanName, singletonObject);
    }

}
