package mybatis;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class FyxBatisRegistrar implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(FyxBatisFactoryBean.class);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        beanDefinition.getPropertyValues().add("mapperInterface","mybatis.FyxDao");
        registry.registerBeanDefinition("fyxBatisFactoryBean",beanDefinition);

        BeanDefinitionBuilder beanDefinitionBuilder1 = BeanDefinitionBuilder.genericBeanDefinition(FyxBatisFactoryBean.class);
        AbstractBeanDefinition beanDefinition1 = beanDefinitionBuilder1.getBeanDefinition();
        beanDefinition1.getPropertyValues().add("mapperInterface","mybatis.GyDao");
        registry.registerBeanDefinition("fyxBatisFactoryBean1",beanDefinition1);
    }
}
