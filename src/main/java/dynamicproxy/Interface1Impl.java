package dynamicproxy;

public class Interface1Impl implements Interface1 {
    @Override
    public void say(String s) {
        System.out.println("Interface1 logic " + s);
    }

    @Override
    public Integer count(Integer a) {
        System.out.println(a);
        return 0;
    }
}
