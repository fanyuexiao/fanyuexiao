package mybatis.oldversion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        FyxDao fyxDao = (FyxDao) ac.getBean("mybatis.oldversion.FyxDao");
        fyxDao.count("a");
        GyDao gyDao = (GyDao) ac.getBean("mybatis.oldversion.GyDao");
        gyDao.update();


        /**
         * this.reader = new AnnotatedBeanDefinitionReader(this);-->ac.register
         * this.scanner = new ClassPathBeanDefinitionScanner(this);-->scanner.scan
         * 这两句的主要作用是解耦，给程序员提供扩展api
         *
         * ComponentScanAnnotationParser
         * 在构造方法中，初始化了一个ComponentScanAnnotationParser给程序员使用，可以继承扩展
         * 而spring扫描时，又重新new了一个ComponentScanAnnotationParser，不提供扩展但是可以配置-->useDefaultFilters
         * @ComponentScan(value = {},includeFilters = {},useDefaultFilters = false)
         * -->org.springframework.context.annotation.ConfigurationClassPostProcessor#processConfigBeanDefinitions(org.springframework.beans.factory.support.BeanDefinitionRegistry)
         * -->org.springframework.context.annotation.ConfigurationClassParser#parse(java.util.Set)
         * -->org.springframework.context.annotation.ConfigurationClassParser#parse(org.springframework.core.type.AnnotationMetadata, java.lang.String)
         * -->org.springframework.context.annotation.ConfigurationClassParser#processConfigurationClass(org.springframework.context.annotation.ConfigurationClass)
         * -->org.springframework.context.annotation.ConfigurationClassParser#doProcessConfigurationClass(org.springframework.context.annotation.ConfigurationClass, org.springframework.context.annotation.ConfigurationClassParser.SourceClass)
         * -->org.springframework.context.annotation.ComponentScanAnnotationParser#parse(org.springframework.core.annotation.AnnotationAttributes, java.lang.String)
         *
         * ImportBeanDefinitionRegistrar
         * spring扫描完成后会处理实现了ImportBeanDefinitionRegistrar的类
         * 将他们new出来，执行相关的Aware接口，再加到一个map（importBeanDefinitionRegistrars）中
         * -->org.springframework.context.annotation.ConfigurationClassPostProcessor#processConfigBeanDefinitions(org.springframework.beans.factory.support.BeanDefinitionRegistry)
         * -->org.springframework.context.annotation.ConfigurationClassParser#parse(java.util.Set)
         * -->org.springframework.context.annotation.ConfigurationClassParser#parse(org.springframework.core.type.AnnotationMetadata, java.lang.String)
         * -->org.springframework.context.annotation.ConfigurationClassParser#processConfigurationClass(org.springframework.context.annotation.ConfigurationClass)
         * -->org.springframework.context.annotation.ConfigurationClassParser#doProcessConfigurationClass(org.springframework.context.annotation.ConfigurationClass, org.springframework.context.annotation.ConfigurationClassParser.SourceClass)
         * -->org.springframework.context.annotation.ConfigurationClassParser#processImports(org.springframework.context.annotation.ConfigurationClass, org.springframework.context.annotation.ConfigurationClassParser.SourceClass, java.util.Collection, boolean)
         * 后续再执行map（importBeanDefinitionRegistrars）中对象的registerBeanDefinitions方法
         * -->org.springframework.context.annotation.ConfigurationClassPostProcessor#processConfigBeanDefinitions(org.springframework.beans.factory.support.BeanDefinitionRegistry)
         * -->org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#loadBeanDefinitions(java.util.Set)
         * -->org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsForConfigurationClass(org.springframework.context.annotation.ConfigurationClass, org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.TrackedConditionEvaluator)
         * -->org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsFromRegistrars(java.util.Map)
         * 注意：
         * 老版本mybatis就是借助ConfigurationClassPostProcessor实现的
         * ConfigurationClassPostProcessor先实例化实现了ImportBeanDefinitionRegistrar的类，然后加入map（importBeanDefinitionRegistrars）中,最后执行registerBeanDefinitions
         * mybatis的@MapperScan有一个@Import（AutoConfiguredMapperScannerRegistrar）
         * AutoConfiguredMapperScannerRegistrar实现了ImportBeanDefinitionRegistrar
         * 在registerBeanDefinitions中完成扫描，并将代理对象注册到spring中
         * 新版本mybatis改变为借助ConfigurationClassPostProcessor和BeanDefinitionRegistryPostProcessor实现
         * mybatis在registerBeanDefinitions中向beanDefinitionMap中put了一个MapperScannerConfigurer
         * MapperScannerConfigurer实现了BeanDefinitionRegistryPostProcessor
         * 在后续的postProcessBeanDefinitionRegistry中完成扫描，注入代理对象
         *
         * 作用：
         * 提供了一个新的mybatis入口
         * 老版，mybatis的唯一入口是@MapperScan或者<mapper-scan></mapper-scan>
         * 新版本可以直接提供一个MapperScannerConfigurer来完成扫描
         */
    }
}
