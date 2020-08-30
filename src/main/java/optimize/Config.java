package optimize;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("optimize")
@EnableAspectJAutoProxy
//@Import(FyxImportBeanDefinitionRegistrar.class)
//@ImportResource("classpath:spring-optimize.xml")
public class Config {
    /**
     * org.springframework.aop.framework.DefaultAopProxyFactory#createAopProxy(org.springframework.aop.framework.AdvisedSupport)
     * config.isProxyTargetClass()==>@EnableAspectJAutoProxy(proxyTargetClass = true)
     * config.isOptimize()==>手动配置一个DefaultAopProxyFactory给spring，将optimize设置为true
     * 1、通过xml配置：spring-optimize.xml
     * 2、通过实现ImportBeanDefinitionRegistrar注册BeanDefinition
     * 两者作用一样，都是告诉spring生成cglib代理
     */
}
