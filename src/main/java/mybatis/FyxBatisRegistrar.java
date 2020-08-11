package mybatis;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.Arrays;

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
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            String beanClassName = beanDefinition.getBeanClassName();
            try {
                Class<?> clazz = Class.forName(beanClassName);
                FyxScan fyxScan = clazz.getAnnotation(FyxScan.class);
                if (fyxScan != null) {
                    beanDefinition.setLazyInit(true);
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
