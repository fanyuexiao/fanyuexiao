package determine;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Constructor;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        /**
         * Constructor<?>[] ctors = determineConstructorsFromBeanPostProcessors(beanClass, beanName);
         * 自动注入（AUTOWIRE_CONSTRUCTOR）时
         *      spring会按照自动注入模型推断出构造函数，ctors=你所提供的全部构造函数,然后选择参数最长的那个
         *      注意：这里的参数最多指的参数是spring容器当中的bean，String s这种参数就不属于spring容器
         * 手动注入时
         *      除非你指定构造函数，否则spring是推断不出来的，ctors=null，然后直接调用默认无参构造函数
         *      constructorToUse = clazz.getDeclaredConstructor();
         *      在没有指定构造函数的情况下
         *          如果你提供无参构造函数，使用无参构造函数
         *          如果没有任何构造函数，使用无参构造函数（没有构造函数的情况下是会自己生成一个空无参构造方法的）
         *          如果你没有提供无参构造函数，但是提供了一个有参构造函数a，那么spring使用a，因为它只有a能用了
         *          如果你没有提供无参构造函数，但是提供了多个有参构造函数，那么spring报错，不知道使用哪个
         */
    }
}
