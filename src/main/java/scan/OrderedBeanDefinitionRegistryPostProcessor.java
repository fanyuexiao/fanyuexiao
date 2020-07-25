package scan;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class OrderedBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, Ordered {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("Ordered ---- postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("Ordered ---- postProcessBeanFactory");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
