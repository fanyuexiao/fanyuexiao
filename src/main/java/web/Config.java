package web;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ComponentScan("web")
@EnableWebMvc
public class Config implements WebMvcConfigurer {
    /**
     * @EnableWebMvc 引入了DelegatingWebMvcConfiguration，
     * DelegatingWebMvcConfiguration实现了ApplicationContextAware，会被spring生命周期回调函数调用，从而生效
     */
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
