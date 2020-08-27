package synthetic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Out {
    /**
     * 私有属性、私有构造方法只能自己访问
     * 但是内部类的情况下，外部类可以访问内部类的私有属性和构造方法
     * 这是靠编译器生成合成方法和合成类解决的
     * access$100-->synthetic method,用于访问私有属性
     * Out$1.class-->synthetic class,用于生成public constructor
     * public Inner(Out,Out$1){}
     */
    public static void main(String[] args) {
        for (Method declaredMethod : Inner.class.getDeclaredMethods()) {
            System.out.println(declaredMethod.getName());
        }
        for (Constructor<?> constructor : Inner.class.getDeclaredConstructors()) {
            System.out.println(constructor);
            for (Parameter parameter : constructor.getParameters()) {
                System.out.println(parameter.getType());
            }
        }
    }

    public void test() {
        Out.Inner inner = new Out.Inner();
        System.out.println(inner.i);
    }

    class Inner{
        private int i;

        private Inner(){
            this.i = 1;
        }
    }
}
