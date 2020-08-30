package optimize;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("optimize")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Config {
}
