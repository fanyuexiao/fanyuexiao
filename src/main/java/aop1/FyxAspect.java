package aop1;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class FyxAspect {
    @Pointcut("execution(* aop1.Fyx.say())")
    public void fyx(){ }

    @Before("fyx()")
    public void beforeFyxSay(){
        System.out.println("beforeFyxSay");
    }
    /**
     * aop、aspectj、spring aop
     * aop是目标，spring aop和aspectj均是对aop的实现
     * aspectj实现的比较好，spring也引用了aspectj
     *
     * Aspect：切点、连接点、通知所在的类
     * Pointcut：切点，连接点的集合
     * joinPoint：连接点，具体要被增强的方法，@Pointcut的括号中的集合
     * advice：通知，对切点在某时刻采取的行动（@Before...）
     * weaving、introduction
     * https://www.cnblogs.com/yangyquin/p/5462488.html
     */
}
