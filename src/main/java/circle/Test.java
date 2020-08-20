package circle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        ac.getBean(Fyx.class).getGy();
        Zyj bean = ac.getBean(Zyj.class);
        System.out.println();
        /**
         * String[] beanDefinitionNames = ac.getBeanDefinitionNames();
         *
         * ps：循环依赖可以自己注入自己
         *     循环依赖必须是单例且利用set方法注入（包括set方法的变体：Field.set）
         *
         * 一二三级缓存在SingletonBeanRegistry（DefaultSingletonBeanRegistry）中
         * beanDefinitionMap在beanFactory中
         * beanFactory默认使用DefaultListableBeanFactory（它继承了DefaultSingletonBeanRegistry）
         * AnnotationConfigApplicationContext的父类GenericApplicationContext在构造函数中new了DefaultListableBeanFactory
         * public GenericApplicationContext() {
         * 	    this.beanFactory = new DefaultListableBeanFactory();
         * }
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
         * commonAnnotationBeanPostProcessor-->@Resource @PostConstructor @Value
         * AutowiredAnnotationBeanPostProcessor-->@Autowired
         *
         *
         * 一级缓存，单例池（earlySingletonObjects）
         * 三级缓存，防止重复创建（earlySingletonObjects）
         * 二级缓存，存了一个工厂（singletonFactories），在循环依赖的情况下可以提前某些生命周期，例如：提前完成代理
         * 正常步骤：注入属性-->生命周期回调方法（@PostConstruct-->InitializingBean-->代理），而循环依赖则情况下在注入属性时完成代理
         * Zyj（被代理）无循环依赖，则代理在
         * org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#initializeBean
         * 	org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization（完成代理）
         * 		org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization（某个后置处理器）
         * Fyx（被代理）和Gy循环依赖，则代理在
         * org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean
         *  org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#populateBean（完成代理）
         *  即Fyx注入Gy，Gy生命周期走到注入Fyx的时候（此时Fyx并没有走到代理的生命周期），Gy通过二级缓存获得生成Fyx的工厂，此工厂完成了代理
         *  另外，只有代理被提前了，其他生命周期回调方法（@PostConstruct，InitializingBean）并没有被提前
         *
         *  Spring容器
         *  Spring中有许多组件，BeanFactory,BeanDefinitionMap,三个缓存,BeanDefinition,BeanFactoryPostProcessor,BeanPostProcessor,等等
         *  这些组件在一起，统称为Spring容器
         */
    }
}
