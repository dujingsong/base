package cn.imadc.application.base.common.thread;

import lombok.NonNull;

import java.util.concurrent.*;

/**
 * <p>
 * 线程池
 * </p>
 *
 * @author 杜劲松
 * @since 2022-08-05
 */
public class BaseThreadPoolExecutor extends ThreadPoolExecutor {

    public BaseThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public BaseThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public BaseThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public BaseThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public void execute(@NonNull Runnable command) {
        super.execute(command);
    }

    @Override
    public Future<?> submit(@NonNull Runnable task) {
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(@NonNull Runnable task, T result) {
        return super.submit(task, result);
    }

    @Override
    public <T> Future<T> submit(@NonNull Callable<T> task) {
        return super.submit(task);
    }
}
