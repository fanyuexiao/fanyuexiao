package autowiring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("autowiring")
@ImportResource("classpath:spring-autowiring.xml")
public class Config {
}
