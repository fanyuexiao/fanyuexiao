package lifecircle;

import javax.annotation.PostConstruct;

public class BeanBean {
    @PostConstruct
    public void post(){
        System.out.println("BeanBean PostConstruct");
    }
}
