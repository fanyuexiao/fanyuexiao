package aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FyxAopInvocationHandler implements InvocationHandler {
    private Object object;

    public FyxAopInvocationHandler(Object object){
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        for (Annotation annotation : declaredAnnotations) {
            if (annotation instanceof FyxAdvice) {
                FyxAdvice fyxAop = (FyxAdvice) annotation;
                Class clazz = fyxAop.clazz();
                FyxAdviceClass fyxAdvice = (FyxAdviceClass) clazz.newInstance();
                String[] methods = fyxAop.methods();
                for (String m : methods){
                    Method methodM = FyxAdviceClass.class.getMethod(m);
                    methodM.invoke(fyxAdvice, null);
                }
            }
        }
        return method.invoke(object, args);
    }
}
