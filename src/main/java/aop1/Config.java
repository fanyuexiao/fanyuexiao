package aop1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("aop1")
@Configuration
@EnableAspectJAutoProxy
public class Config {
}
