package dynamicproxy;

public class Interface1Impl implements Interface1 {
    @Override
    public void say(String s) {
        System.out.println("Interface1 logic " + s);
    }
}
