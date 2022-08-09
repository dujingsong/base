package cn.imadc.application.base.samples.mybatis;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-08-09
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
