package cn.imadc.application.base.common.context;

import lombok.NonNull;

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
     * 存放键值，如果值为空值则移除键
     *
     * @param key   键
     * @param value 值
     * @return 存放结果
     */
    public Object put(@NonNull String key, Object value) {
        if (null == value) return super.remove(key);
        return super.put(key, value);
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
    public <T> T get(@NonNull String key, @NonNull Class<T> clazz) {
        return (T) super.get(key);
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
    public <T> T get(@NonNull String key, @NonNull Class<T> clazz, T defaultValue) {
        T value = (T) super.get(key);
        return null != value ? value : defaultValue;
    }

    /**
     * 释放
     */
    public void release() {
        threadLocal.remove();
    }
}
