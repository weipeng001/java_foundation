package cn.bugstack.springframework.beans;

/**
 * @author wuweipeng
 * @date 2021/6/29
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
