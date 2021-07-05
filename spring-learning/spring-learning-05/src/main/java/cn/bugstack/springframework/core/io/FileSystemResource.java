package cn.bugstack.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 通过文件路径的形式加载文件
 *
 * @author wuweipeng
 * @date 2021/7/2
 */
public class FileSystemResource implements Resource {

    private final File file;

    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    /**
     * 通过指定文件路径的方式读取文件信息，
     * 这部分大家肯定还是非常熟悉的
     * ，经常会读取一些txt、excel文件输出到控制台。
     *
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public final String getPath() {
        return this.path;
    }

}
