package cn.imadc.application.base.samples.proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-09-07
 */
public class JustForTest implements InitializingBean, DisposableBean, Lifecycle, LifecycleProcessor, SmartLifecycle, Phased, ApplicationContextAware
, ApplicationEventPublisherAware{

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @PostConstruct
    public void postConstruct() {

    }

    @PreDestroy
    public void preDestroy() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClose() {

    }


    public static void main(String[] args) {

//        IAction action = new ActualClass();
//        ActualHandler actualHandler = new ActualHandler(action);
//
//        IAction proxy = (IAction) Proxy.newProxyInstance(action.getClass().getClassLoader(), action.getClass().getInterfaces(), actualHandler);
//
//        proxy.hh2();
//
//        IAction proxy1 = (IAction) actualHandler.createProxy();
//        proxy1.hh2();
//
//        Enhancer enhancer = new Enhancer();
//        enhancer.setCallback(new MethodHandler());
//        enhancer.setSuperclass(ActualClass.class);
//
//        ActualClass actualClass = (ActualClass) enhancer.create();
//        actualClass.hh2();

        ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");

        ActualClass actualClass = context.getBean("actualClass", ActualClass.class);
        actualClass.hh2();

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

    }
}
