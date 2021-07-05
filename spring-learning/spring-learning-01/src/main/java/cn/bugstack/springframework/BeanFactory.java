package cn.bugstack.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean 工厂
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
public class BeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(final String name) {
        return this.beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(final String name, final BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
    }

}
