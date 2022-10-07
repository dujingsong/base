package cn.imadc.application.base.samples.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-09-08
 */
public class ActualHandler implements InvocationHandler {

    private final IAction action;

    public ActualHandler(IAction action) {
        this.action = action;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(action, args);
    }

    public Object createProxy() {
        return Proxy.newProxyInstance(action.getClass().getClassLoader(), action.getClass().getInterfaces(), this);
    }
}
