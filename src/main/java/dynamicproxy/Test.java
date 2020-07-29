package dynamicproxy;

public class Test {
    public static void main(String[] args) {
        Interface1 interface1 =new Interface1Impl();
        try {
            Interface1 o = (Interface1) FyxProxy.newProxyInstance(interface1,new FyxInvocationHadlerImpl(interface1));
            o.say("a");
            o.count(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
