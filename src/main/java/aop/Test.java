package aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        Dilraba fyx = (Dilraba)ac.getBean("fyx");
        fyx.say();
        fyx.tell();
    }
}
