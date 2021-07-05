package cn.bugstack.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 主要用于处理资源加载流
 * 在 Resource 的资源加载器的实现中包括了，
 * ClassPath、系统文件、云配置文件，这三部分与 Spring 源码中的设计和实现保持一致，
 * 最终在 DefaultResourceLoader 中做具体的调用。
 *
 * @author wuweipeng
 * @date 2021/7/2
 */
public interface Resource {

    /**
     * 定义 Resource 接口，提供获取 InputStream 流的方法，
     * 接下来再分别实现三种不同的流文件操作：classPath、FileSystem、URL
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;

}
