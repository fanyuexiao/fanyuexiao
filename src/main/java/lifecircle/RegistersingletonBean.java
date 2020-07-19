package lifecircle;

import javax.annotation.PostConstruct;

public class RegistersingletonBean {
    @PostConstruct
    public void post(){
        System.out.println("RegistersingletonBean PostConstruct");
    }
}
