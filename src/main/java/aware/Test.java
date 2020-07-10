package aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    /**
     * ApplicationContextAware实现原理-->ApplicationContextAwareProcessor
     * AnnotationConfigApplicationContext()初始化调用......
     * -->org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean（生命周期回调方法调用）
     * -->org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization
     * -->org.springframework.context.support.ApplicationContextAwareProcessor.invokeAwareInterfaces
     * if (bean instanceof ApplicationContextAware) {
     *     ((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
     * }
     *
     * ApplicationContextAwareProcessor是spring自己加进去的
     * org.springframework.context.support.AbstractApplicationContext#prepareBeanFactory
     * -->beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
     * -->org.springframework.beans.factory.support.AbstractBeanFactory#addBeanPostProcessor
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
    }
}
