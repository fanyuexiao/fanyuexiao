package web;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class TomcatApplication {
    public static void main(String[] args){
        Tomcat tomcat = new Tomcat();
        tomcat.addWebapp("/","C:\\fyx\\");
        tomcat.setPort(56666);
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
