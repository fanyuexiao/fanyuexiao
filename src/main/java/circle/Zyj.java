package circle;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Zyj {
    public Zyj(){
        System.out.println("zyj constructor");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("zyj postConstruct");
    }
}
