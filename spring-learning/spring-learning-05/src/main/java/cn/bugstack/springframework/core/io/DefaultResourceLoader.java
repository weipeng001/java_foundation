package cn.bugstack.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认包资源实现
 *
 * @author wuweipeng
 * @date 2021/7/2
 */
public class DefaultResourceLoader implements ResourceLoader {

    /**
     * 在获取资源的实现中，
     * 主要是把三种不同类型的资源处理方式进行了包装，
     * 分为：判断是否为ClassPath、URL以及文件。
     * 虽然 DefaultResourceLoader 类实现的过程简单，但这也是设计模式约定的具体结果，
     * 像是这里不会让外部调用放知道过多的细节，而是仅关心具体调用结果即可。
     *
     * @param location
     * @return
     */
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(ResourceLoader.CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(ResourceLoader.CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }

}
