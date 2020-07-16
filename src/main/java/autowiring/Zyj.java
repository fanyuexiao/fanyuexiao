package autowiring;

import org.springframework.stereotype.Component;

@Component
public class Zyj {
    A a;

    public void setA(A a) {
        this.a = a;
    }

    public void print(){
        System.out.println("A:" + a);
    }
}
