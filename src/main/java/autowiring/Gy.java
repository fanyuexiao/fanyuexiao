package autowiring;

import org.springframework.stereotype.Component;

@Component
public class Gy {
    A a;

    public void setA(A a) {
        this.a = a;
    }

    public void print(){
        System.out.println("A:" + a);
    }
}
