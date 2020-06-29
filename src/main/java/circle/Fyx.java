package circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Fyx {
    @Autowired
    Gy gy;

    public Fyx() {
        System.out.println("fyx constructor");
    }

    public void getGy() {
        System.out.println(gy);
    }
}
