package scan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.addBeanFactoryPostProcessor(new ApiBeanDefinitionRegistryPostProcessor());
        ac.addBeanFactoryPostProcessor(new ApiBeanFactoryPostProcessor());
        ac.register(Config.class);
        ac.refresh();
    }

    /**
     * BeanDefinitionRegistryPostProcessor继承了BeanFactoryPostProcessor
     * BeanDefinitionRegistryPostProcessor在自定义bean被扫描之前提供扩展点
     * BeanFactoryPostProcessor在bean被扫描之后提供扩展点
     *
     * spring bean的扫描（被加到beanDefinitionMap）
     * 一、spring内置bean的扫描（spring开天辟地的类）
     *      org.springframework.context.annotation.AnnotationConfigApplicationContext.AnnotationConfigApplicationContext()
     *      this.reader = new AnnotatedBeanDefinitionReader(this)
     *      org.springframework.context.annotation.AnnotatedBeanDefinitionReader#AnnotatedBeanDefinitionReader
     *      org.springframework.context.annotation.AnnotationConfigUtils#registerAnnotationConfigProcessors
     *      org.springframework.context.annotation.AnnotationConfigUtils#registerAnnotationConfigProcessors
     *
     * 二、手动传给spring的bean的扫描（Config.class）
     *      org.springframework.context.annotation.AnnotationConfigApplicationContext.register()
     *      此时只是被加到beanDefinitionMap中，并没有对其进行全注解解析
     * 三、自定义bean的扫描（@Component xml）
     *      org.springframework.context.support.AbstractApplicationContext#invokeBeanFactoryPostProcessors()
     *
     * invokeBeanFactoryPostProcessors的执行顺序
     * 1、执行程序员通过api(addBeanFactoryPostProcessor)注册的，实现了BeanDefinitionRegistryPostProcessor的类的postProcessBeanDefinitionRegistry
     *    在org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors中，
     *    首先会遍历通过api注册进去的BeanFactoryPostProcessor
     *    如果是BeanDefinitionRegistryPostProcessor则直接执行postProcessBeanDefinitionRegistry
     *    如果是BeanFactoryPostProcessor则加进list，后面执行
     * 2、执行spring内置的BeanDefinitionRegistryPostProcessor的postProcessBeanDefinitionRegistry
     *    第一个执行spring内置的ConfigurationClassPostProcessor，它实现了PriorityOrdered，
     *    实际上就是这个类完成了自定义类的扫描与解析，包括手动传给spring的类（Config.class）
     *    也就是说，这个类的postProcessBeanDefinitionRegistry执行完之后，@Component的，xml配置的都进来了
     *    接下来的BeanDefinitionRegistryPostProcessor就是混在一起执行了
     *    注意：此时会执行ConfigurationClassPostProcessor的postProcessBeanDefinitionRegistry
     *         完成了对@Configuration的代理，即替换beanDefinition
     *         Class<?> configClass = beanDef.resolveBeanClass(this.beanClassLoader);-->原始类
     *         Class<?> enhancedClass = enhancer.enhance(configClass, this.beanClassLoader);-->代理类
     *         beanDef.setBeanClass(enhancedClass);-->替换
     * 3、执行被spring(ConfigurationClassPostProcessor)扫描出来的实现了BeanDefinitionRegistryPostProcessor、PriorityOrdered的类的postProcessBeanDefinitionRegistry
     * 4、执行被spring(ConfigurationClassPostProcessor)扫描出来的实现了BeanDefinitionRegistryPostProcessor、Ordered的类的BeanDefinitionRegistryPostProcessor的postProcessBeanDefinitionRegistry
     * 5、执行被spring(ConfigurationClassPostProcessor)扫描出来的实现了BeanDefinitionRegistryPostProcessor的类的postProcessBeanDefinitionRegistry
     * 6、执行所有实现BeanDefinitionRegistryPostProcessor的类的postProcessBeanFactory
     *    程序员通过api(addBeanFactoryPostProcessor)注册的，@Component的，xml的，spring内置的
     *    在执行以上每一个BeanDefinitionRegistryPostProcessor，都会把BeanDefinitionRegistryPostProcessor存在list中
     *    在这一步一次性调用他们的postProcessBeanFactory
     * 7、执行程序员通过api(addBeanFactoryPostProcessor)注册的，实现了BeanFactoryPostProcessor的类的postProcessBeanFactory
     *    即第一步提到的，被加到list的那些BeanFactoryPostProcessor
     * 8、执行被spring(ConfigurationClassPostProcessor)扫描出来的实现了BeanPostProcessor、PriorityOrdered的类的postProcessBeanFactory
     * 9、执行被spring(ConfigurationClassPostProcessor)扫描出来的实现了BeanPostProcessor、Ordered的类的postProcessBeanFactory
     * 10、执行被spring(ConfigurationClassPostProcessor)扫描出来的实现了BeanPostProcessor的类的postProcessBeanFactory
     * 注意：processedBeans
     * 在以上的执行过程中，如果某个bean执行过了，则会被加到processedBeans中，后续不用再执行
     *
     *
     * 全注解类（full）、半注解类（lite）、其他
     * full：@Configuration
     * lite：@Component @ComponentScan @Import @ImportResource @Bean
     * 注意：
     * 第一，ConfigurationClassPostProcessor在扫描beanDefinition时，会解析是否时全注解类，即是否有@Configuration注解，并做标识
     * 先解析配置类
     *      org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanDefinitionRegistryPostProcessors
     *      org.springframework.context.annotation.ConfigurationClassPostProcessor.processConfigBeanDefinitions
     *      org.springframework.context.annotation.ConfigurationClassUtils#checkConfigurationClassCandidate
     * 再扫描业、解析业务类
     *      org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanDefinitionRegistryPostProcessors
     *      org.springframework.context.annotation.ConfigurationClassPostProcessor.processConfigBeanDefinitions
     *      org.springframework.context.annotation.ConfigurationClassParser#parse
     *      org.springframework.context.annotation.ConfigurationClassParser#parse
     *      org.springframework.context.annotation.ConfigurationClassParser#processConfigurationClass
     *      org.springframework.context.annotation.ConfigurationClassParser#doProcessConfigurationClass
     *      org.springframework.context.annotation.ConfigurationClassUtils#checkConfigurationClassCandidate
     * 第二，在实例化beanDefinition时，还会检查他们是否是“全注解类”，如果是，则会用cglib代理，否则则是普通bean。
     *      org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors
     *      org.springframework.context.annotation.ConfigurationClassPostProcessor.enhanceConfigurationClasses
     */
}
