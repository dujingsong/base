# 基础common模块

## 基础通用组件，包含了一些常用的DTO、常量、自定义线程池等

### usage

引入依赖

~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-common</artifactId>
    <version>${project.version}</version>
</dependency>
~~~

#### 自定义线程池

~~~
private final AtomicLong threadIndex = new AtomicLong(0);

BaseThreadPoolExecutor baseThreadPoolExecutor = new BaseThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadFactory() {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "MyThread_" + threadIndex.incrementAndGet());
    }
});
~~~