package cn.imadc.application.base.common.context;

import java.util.concurrent.ConcurrentHashMap;

import static java.lang.ThreadLocal.withInitial;

/**
 * 会话上下文
 */
public class RequestContext extends ConcurrentHashMap<String, Object> {

    private static final long serialVersionUID = 1L;
    private static final Class<? extends RequestContext> contextClass = RequestContext.class;
    private static final ThreadLocal<? extends RequestContext> threadLocal = withInitial(() -> {
        try {
            return (RequestContext) RequestContext.contextClass.getDeclaredConstructor().newInstance();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    });

    // 不可实例化
    private RequestContext() {
    }

    /**
     * 获取当前上下文信息
     *
     * @return 当前上下文信息
     */
    public static RequestContext getCurrentContext() {
        return threadLocal.get();
    }

    /**
     * 根据键和值类型获取
     *
     * @param key   键
     * @param clazz 值类型
     * @param <T>   值类型
     * @return 值
     */
    @SuppressWarnings("unchecked")
    public <T> T get(Object key, Class<T> clazz) {
        return (T) super.get(key);
    }

    /**
     * 释放
     */
    public void release() {
        threadLocal.remove();
    }
}
