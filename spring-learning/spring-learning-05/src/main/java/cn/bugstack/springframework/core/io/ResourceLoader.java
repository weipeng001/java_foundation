package cn.bugstack.springframework.core.io;

/**
 * xml资源处理类
 *
 * @author wuweipeng
 * @date 2021/7/2
 */
public interface ResourceLoader {

    /**
     * 从类路径加载的伪 URL 前缀："classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
