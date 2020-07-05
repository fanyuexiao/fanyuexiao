package circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Fyx {
    @Autowired
    Gy gy;

    public Fyx() {
        System.out.println("fyx constructor");
    }

    public Gy getGy() {
        return gy;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("fyx postConstruct");
    }
}
