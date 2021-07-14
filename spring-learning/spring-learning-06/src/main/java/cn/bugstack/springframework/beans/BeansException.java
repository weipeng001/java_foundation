package cn.bugstack.springframework.beans;

/**
 *
 */
public class BeansException extends RuntimeException {

    private static final long serialVersionUID = 1173292818025725494L;

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
