package determine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Fyx {
    public Fyx(){
        System.out.println("default");
    }

    public Fyx(Gy gy){
        System.out.println("aaaaaa");
    }

//    @Autowired
//    public Fyx(Gy gy,Gy gy1){
//        System.out.println("bbbb");
//    }

//    public Fyx(Gy gy,String s){
//        System.out.println("ccccc");
//    }
}
