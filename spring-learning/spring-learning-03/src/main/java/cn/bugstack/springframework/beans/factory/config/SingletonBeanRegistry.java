package cn.bugstack.springframework.beans.factory.config;

/**
 * 单例注册表
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例实体
     *
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

}
