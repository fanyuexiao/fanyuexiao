package dynamicproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FyxInvocationHadlerImpl implements FyxInvocationHandler {
    public Object o;

    public FyxInvocationHadlerImpl(Object o) {
        this.o = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("log");
        return method.invoke(o,args);
    }
}
