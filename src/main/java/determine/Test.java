package determine;

import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Constructor;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(Config.class);
        /**
         * hasConstructorArgumentValues的用法
         * 这里也可以通过ImportBeanDefinitionRegistrar引入beanDefinition
         */
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(Zyj.class);
        genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue("determine.Gy");
        ac.registerBeanDefinition("zyj",genericBeanDefinition);
        ac.refresh();
        ac.getBean(Zyj.class);
        /**
         * Constructor<?>[] ctors = determineConstructorsFromBeanPostProcessors(beanClass, beanName);
         * 自动注入（AUTOWIRE_CONSTRUCTOR）时
         *      spring会按照自动注入模型推断出构造函数，ctors=你所提供的全部构造函数,然后选择参数最长的那个
         *      注意：这里的参数最多指的参数是spring容器当中的bean，String s这种参数就不属于spring容器
         * 手动注入时
         *      constructorToUse = clazz.getDeclaredConstructor();
         *      如果没有任何构造函数，使用无参构造函数（没有构造函数的情况下是会自己生成一个空无参构造方法的），null
         *      如果你仅提供一个无参构造函数，使用无参构造函数，null
         *      多个@Autowired(true)，异常
         *      多个@Autowired(false)，多个
         *      一个带参构造方法，这个
         *      多个构造方法，null
         *      多个构造方法，一个@Autowired(true),这个
         *
         */
    }
}
