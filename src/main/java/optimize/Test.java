package optimize;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        Fyx fyx = ac.getBean(Fyx.class);
        fyx.say();
    }
}
