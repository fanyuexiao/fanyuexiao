package configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        Config config = ac.getBean(Config.class);
        System.out.println(config);
    }
}
