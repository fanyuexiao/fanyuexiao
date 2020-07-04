package circle;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        ac.getBean(Gy.class).getFyx().getGy();
        /**
         * String[] beanDefinitionNames = ac.getBeanDefinitionNames();
         *
         * beanDefinitionMap在beanFactory中
         * beanFactory默认使用DefaultListableBeanFactory
         * ac.getBeanFactory().getBeanDefinition("");
         * 调用的是DefaultListableBeanFactory中的this.beanDefinitionMap.get(beanName);
         *
         *
         * 如何关闭spring的循环依赖
         * 方法一、改源码
         * 将org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#allowCircularReferences改为false，默认为true（支持循环依赖）
         * 方法二、调用api修改
         * AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
         * ac.setAllowCircularReferences(false);
         * ac.register(Config.class);
         * ac.refresh();
         * 事实上，ac.register(config.class)调用的是beanFactory的setAllowCircularReferences()
         *
         *
         * commonAnnotationBeanPostProcessor-->@Resource
         * AutowiredAnnotationBeanPostProcessor-->@Autowired
         */
    }
}
