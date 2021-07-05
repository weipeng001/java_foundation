package cn.bugstack.springframework.test;

import cn.bugstack.springframework.BeanDefinition;
import cn.bugstack.springframework.BeanFactory;
import cn.bugstack.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * 测试demo
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        final BeanFactory beanFactory = new BeanFactory();

        // 2.注入bean
        final BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取bean
        final UserService userService = (UserService) beanFactory.getBean("userService");
        UserService.queryUserInfo();
    }

}
