package dynamicproxy;

public class Test {
    public static void main(String[] args) {
        Interface1 interface1 =new Interface1Impl();
        Interface1 o = null;
        try {
            o = (Interface1) FyxProxy.newProxyInstance(interface1,new FyxInvocationHadlerImpl(interface1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        o.say("sss");
    }
}
