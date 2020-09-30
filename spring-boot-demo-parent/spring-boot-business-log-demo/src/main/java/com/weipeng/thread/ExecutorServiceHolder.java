package com.weipeng.thread;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.function.Supplier;


/**
 * @author: liaozhicheng
 * @date: 2018-09-18
 * @since: 1.0
 */
@Component
public class ExecutorServiceHolder implements InitializingBean, DisposableBean {

    private ExecutorService executor;


    private int poolSize;


    public <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
        return CompletableFuture.supplyAsync(supplier, executor);
    }

    public void simpleExecute(Runnable runnable) {
        executor.execute(runnable);
    }

    public <T> Future<T> submit(final Callable<T> callable) {
        return this.submit(callable);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        poolSize = Runtime.getRuntime().availableProcessors();
        executor = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public void destroy() throws Exception {
        if (executor != null) {
            executor.shutdown();
        }
    }

    public void execute(Runnable runnable) {
        this.execute(runnable);
    }
}
