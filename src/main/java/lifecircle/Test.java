package lifecircle;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ConfigurableListableBeanFactory beanFactory = ac.getBeanFactory();
        beanFactory.registerSingleton("registersingleton",new RegistersingletonBean());
        ac.register(Config.class);
        ac.refresh();
    }
}
