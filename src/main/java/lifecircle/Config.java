package lifecircle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@ComponentScan("lifecircle")
@ImportResource("classpath:spring-lifecycle.xml")
public class Config {
    @Bean
    public BeanBean beanBean(){
        return new BeanBean();
    }
}
