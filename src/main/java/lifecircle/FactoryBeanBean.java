package lifecircle;

import javax.annotation.PostConstruct;

public class FactoryBeanBean {
    @PostConstruct
    public void post(){
        System.out.println("FactoryBeanBean PostConstruct");
    }
}
