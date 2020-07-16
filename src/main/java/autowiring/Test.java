package autowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        ac.getBean(Fyx.class).print();
        ac.getBean(Gy.class).print();
        ac.getBean(Zyj.class).print();
    }
}
