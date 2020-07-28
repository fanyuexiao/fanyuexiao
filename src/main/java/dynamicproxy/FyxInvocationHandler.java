package dynamicproxy;

import java.lang.reflect.Method;

public interface FyxInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
