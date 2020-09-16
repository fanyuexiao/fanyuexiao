package web;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
@ComponentScan("web")
@EnableWebMvc
public class Config implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/","classpath:/");
    }

    /**
     * <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     *         <property name="prefix" value="/WEB-INF/jsp" />
     *         <property name="suffix" value=".jsp" />
     * </bean>
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        System.out.println("configureViewResolvers");
        registry.jsp("/static/",".html");
    }

    /**
     * @EnableWebMvc 引入了DelegatingWebMvcConfiguration，等同于xml的<mvc:annotation-driven/>
     * DelegatingWebMvcConfiguration实现了ApplicationContextAware，会被spring生命周期回调函数调用，从而生效
     *
     * 也可以实现extendMessageConverters
     * configureMessageConverters不会继承框架默认的消息转换器，只有方法里面自己配置的消息转换器
     * extendMessageConverters会继承框架默认的消息转换器，除了自己配置的还有框架自带的
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.out.println("configureMessageConverters");
        /**
         * <bean id="MappingJackson2HttpMessageConverter" class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
         *     <property name="supportedMediaTypes">
         *         <list>
         *             <value>text/html;charset=UTF-8</value>
         *         </list>
         *     </property>
         * </bean>
         */
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        /**
         * <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
         *     <property name="messageConverters">
         *         <list>
         *             <ref bean="MappingJackson2HttpMessageConverter" /><!-- json转换器 -->
         *         </list>
         *     </property>
         * </bean>
         */
        converters.add(fastJsonHttpMessageConverter);//converters.add(new MappingJackson2HttpMessageConverter());
    }
}
