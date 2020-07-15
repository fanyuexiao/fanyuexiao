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
