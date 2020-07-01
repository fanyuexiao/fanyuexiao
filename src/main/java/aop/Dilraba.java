package aop;

@FyxAop
public interface Dilraba {
    @FyxAdvice(clazz = FyxAdviceClass.class,methods = {"advice1","advice2"})
    void say();

    @FyxAdvice(clazz = FyxAdviceClass.class,methods = {"advice2","advice3"})
    void tell();
}
