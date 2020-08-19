package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("configuration")
public class Config {
    /**
     * @Configuration 的作用是为了保持bean的作用域，并不是配置类，删除@Configuration后spring容器依然可以正常初始化
     * 如果不加@Configuration，则Config类是一个原生对象，fyx()执行两次
     * 如果加了@Configuration，则Config类是一个代理对象(cglib)，fyx()只执行一次
     *
     * 代理对象的逻辑在
     * org.springframework.context.annotation.ConfigurationClassEnhancer.BeanMethodInterceptor#intercept(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], org.springframework.cglib.proxy.MethodProxy)
     * 在spring生命周期中，首先会getBean，get不到则去生成bean
     * 生成bean时，会把@Bean的method先放到一个threadLocal中（currentlyInvokedFactoryMethod）
     * org.springframework.beans.factory.support.SimpleInstantiationStrategy#instantiate(org.springframework.beans.factory.support.RootBeanDefinition, java.lang.String, org.springframework.beans.factory.BeanFactory, java.lang.Object, java.lang.reflect.Method, java.lang.Object...)
     * currentlyInvokedFactoryMethod.set(factoryMethod);
     * 在执行代理方法时，判断当前执行方法与currentlyInvokedFactoryMethod存的方法是否一致（org.springframework.context.annotation.ConfigurationClassEnhancer.BeanMethodInterceptor#isCurrentlyInvokedFactoryMethod(java.lang.reflect.Method)）
     * 若一致则调用父类该方法（invokeSuper）
     * 若不一致，则执行spring自己的逻辑（resolveBeanReference-->getBean）
     * 最后会把threadLocal（currentlyInvokedFactoryMethod）清空
     *
     * 例如Fyx和Gy
     * 先实例化Fyx
     *      -->getBean获取不到，开始生成bean
     *      -->currentlyInvokedFactoryMethod.set(fyx())
     *      -->执行代理子类的fyx(),即执行intercept
     *      -->判断当前执行方法和threadLocal中存的方法是否一致（isCurrentlyInvokedFactoryMethod）
     *      -->一致，则调用父类的fyx()
     *      -->清空currentlyInvokedFactoryMethod的fyx()
     *      -->Fyx生成完毕
     * 再实例化Gy
     *      -->getBean获取不到，开始生成bean
     *      -->currentlyInvokedFactoryMethod.set(gy())
     *      -->执行代理子类的gy(),即执行intercept
     *      -->判断当前执行方法和threadLocal中存的方法是否一致（isCurrentlyInvokedFactoryMethod）
     *      -->一致，则调用父类的gy()
     *      -->调用过程中要执行fyx(),这是一个代理方法,又要去intercept
     *      //这里跳去intercept是在Gy的生命周期中跳过去的,并没有走完整的生命周期,currentlyInvokedFactoryMethod也就没有被改变
     *      -->判断当前执行方法和threadLocal中存的方法是否一致（isCurrentlyInvokedFactoryMethod）
     *      -->不一致，则执行spring自己的逻辑（resolveBeanReference-->getBean）
     *      -->由于Fyx先实例化，getBean直接得到Fyx生成的对象
     *      -->清空currentlyInvokedFactoryMethod的gy()
     *      -->Gy生成完毕
     *
     * 反之同理
     * 先实例化Gy
     *      -->getBean获取不到，开始生成bean
     *      -->currentlyInvokedFactoryMethod.set(gy())
     *      -->执行代理子类的gy(),即执行intercept
     *      -->判断当前执行方法和threadLocal中存的方法是否一致（isCurrentlyInvokedFactoryMethod）
     *      -->一致，则调用父类的gy()
     *      -->调用过程中要执行fyx(),这是一个代理方法,又要去intercept
     *      //这里跳去intercept是在Gy的生命周期中跳过去的,并没有走完整的生命周期，currentlyInvokedFactoryMethod也就没有被改变
     *      -->判断当前执行方法和threadLocal中存的方法是否一致（isCurrentlyInvokedFactoryMethod）
     *      -->不一致，则执行spring自己的逻辑（resolveBeanReference-->getBean）
     *      -->此时Fyx未实例化，getBean获取不到，开始生成bean,开始Fyx的生命周期
     *      -->currentlyInvokedFactoryMethod.set(fyx())
     *      -->执行代理子类的fyx(),即执行intercept
     *      -->判断当前执行方法和threadLocal中存的方法是否一致（isCurrentlyInvokedFactoryMethod）
     *      -->一致，则调用父类的fyx()
     *      -->清空currentlyInvokedFactoryMethod中的fyx()
     *      -->Fyx生成完毕
     *      -->清空currentlyInvokedFactoryMethod中的gy()
     *      -->Gy生成完毕
     * 再实例化Gy
     *      -->getBean直接获取到
     */

    @Bean
    public Fyx fyx(){
        return new Fyx();
    }

    @Bean
    public Gy gy(){
        fyx();
        return new Gy();
    }
}
