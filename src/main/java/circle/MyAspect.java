package circle;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    @Pointcut("execution(* circle.Fyx.*(..))")
    public void anyFyxMethod(){

    }

    @Pointcut("execution(* circle.Zyj.*(..))")
    public void anyZyjMethod(){

    }

    @Before("anyFyxMethod()")
    public void fyxBefore(){
        System.out.println("------------------------fyx aop--------------------------");
    }

    @Before("anyZyjMethod()")
    public void zyjBefore(){
        System.out.println("------------------------zyj aop--------------------------");
    }
}
