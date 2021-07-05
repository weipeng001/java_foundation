package cn.bugstack.springframework.beans.factory.config;

/**
 * 单例bean注册表
 * @author wuweipeng
 * @date 2021/6/29
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
