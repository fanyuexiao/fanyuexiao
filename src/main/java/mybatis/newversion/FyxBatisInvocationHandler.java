package mybatis.newversion;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FyxBatisInvocationHandler implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Select select = method.getAnnotation(Select.class);
        if (select != null) {
            System.out.println("conn db init");
            String value = select.value();
            System.out.println(value);
        }
        if (method.getName().equals("toString")){
            return proxy.getClass().getInterfaces()[0].getName();
        }
        return null;
    }
}
