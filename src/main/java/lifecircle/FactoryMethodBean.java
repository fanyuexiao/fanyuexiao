package lifecircle;

import javax.annotation.PostConstruct;

public class FactoryMethodBean {
    @PostConstruct
    public void post(){
        System.out.println("FactoryMethodBean PostConstruct");
    }
}
