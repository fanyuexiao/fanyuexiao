package mybatis.newversion;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class FyxMapperScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
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

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
