package circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gy {
    @Autowired
    Fyx fyx;

    public Gy(){
        System.out.println("gy constructor");
    }

    public Fyx getFyx(){
        return fyx;
    }
}
