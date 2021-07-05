package cn.bugstack.springframework.beans;

/**
 * 定义 Bean 异常
 *
 * @author wuweipeng
 * @date 2021/6/29
 */
public class BeansException extends RuntimeException {

    public BeansException(final String msg) {
        super(msg);
    }

    public BeansException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

}
