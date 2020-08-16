package mybatis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        EnableFyxScan enableFyxScan = Config.class.getAnnotation(EnableFyxScan.class);
        if (enableFyxScan != null) {
            FyxClassPathBeanDefinitionScanner scanner = new FyxClassPathBeanDefinitionScanner(ac);
            scanner.addIncludeFilter(new AnnotationTypeFilter(FyxScan.class));
            scanner.scan(enableFyxScan.value());
        }
        ac.register(Config.class);
        ac.refresh();
        FyxDao fyxDao = (FyxDao) ac.getBean("mybatis.FyxDao");
        fyxDao.count("a");
        GyDao gyDao = (GyDao) ac.getBean("mybatis.GyDao");
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
         * 而mybatis在registerBeanDefinitions中完成扫描，并将代理对象注册到spring中
         * 新版本mybatis改变为借助BeanDefinitionRegistryPostProcessor实现
         * 在postProcessBeanDefinitionRegistry中完成扫描，注入代理对象
         */
    }
}
