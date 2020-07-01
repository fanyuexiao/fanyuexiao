package aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;

public class FyxAopBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        for (Class clazz : interfaces){
            Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                if (annotation instanceof FyxAop){
                    return Proxy.newProxyInstance(
                            FyxAopBeanPostProcessor.class.getClassLoader(),
                            new Class[]{clazz},
                            new FyxAopInvocationHandler(bean));
                }
            }
        }
        return bean;
    }
}
