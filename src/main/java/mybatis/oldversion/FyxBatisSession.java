package mybatis.oldversion;

import java.lang.reflect.Proxy;

public class FyxBatisSession {
    public static Object queryMapper(Class clazz){
        return Proxy.newProxyInstance(
                FyxBatisSession.class.getClassLoader(),
                new Class[]{clazz},
                new FyxBatisInvocationHandler());
    }
}
