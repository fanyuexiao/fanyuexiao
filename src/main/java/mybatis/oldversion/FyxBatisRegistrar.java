package mybatis.oldversion;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Objects;

public class FyxBatisRegistrar implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        /**
         * <bean id = "fyxBatisFactoryBean" class="mybatis.FyxBatisFactoryBean">
         *     <poperty name="mapperInterface" value="mybatis.FyxDao"></poperty>
         * </bean>
         *
         * <bean id = "fyxBatisFactoryBean" class="org.mybatis.spring.mapper.MapperFactoryBean">
         *      <poperty name="mapperInterface" value="mybatis.FyxDao"></poperty>
         * </bean>
         */
        String scanPackage = (String) Objects.requireNonNull(importingClassMetadata.getAnnotationAttributes(EnableFyxScan.class.getName())).get("value");
        if (scanPackage != null) {
            FyxClassPathBeanDefinitionScanner scanner = new FyxClassPathBeanDefinitionScanner(registry);
            scanner.addIncludeFilter(new AnnotationTypeFilter(FyxScan.class));
            scanner.scan(scanPackage);
        }
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            AbstractBeanDefinition abstractBeanDefinition = (AbstractBeanDefinition) registry.getBeanDefinition(beanDefinitionName);
            String beanClassName = abstractBeanDefinition.getBeanClassName();
            try {
                FyxScan fyxScan = Class.forName(beanClassName).getAnnotation(FyxScan.class);
                if (fyxScan != null) {
                    abstractBeanDefinition.setAbstract(true);
                    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(FyxBatisFactoryBean.class);
                    AbstractBeanDefinition dynamicBeanDefinition = beanDefinitionBuilder.getBeanDefinition();
                    dynamicBeanDefinition.getPropertyValues().add("mapperInterface",beanClassName);
                    registry.registerBeanDefinition(beanClassName,dynamicBeanDefinition);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
