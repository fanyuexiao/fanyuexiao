package optimize;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FyxAspect {
    @Pointcut("execution(* optimize.Fyx.*(..))")
    public void anyFyxMethod(){

    }

    @Before("anyFyxMethod()")
    public void fyxBefore(){
        System.out.println("------------------------fyx aop--------------------------");
    }
}
