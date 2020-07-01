package aop;

import org.springframework.stereotype.Component;

@Component
public class Fyx implements Dilraba {

    public void say() {
        System.out.println("fyx say");
    }

    public void tell() {
        System.out.println("fyx tell");
    }
}
