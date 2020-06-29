package circle;

import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
//        ac.getBean(Fyx.class).getGy();
        ac.getBean(Dilraba.class);
        /**
         * String[] beanDefinitionNames = ac.getBeanDefinitionNames();
         *
         * beanDefinitionMap在beanFactory中
         * beanFactory默认使用DefaultListableBeanFactory
         * ac.getBeanFactory().getBeanDefinition("");
         * 调用的是DefaultListableBeanFactory中的this.beanDefinitionMap.get(beanName);
         */
    }
}
