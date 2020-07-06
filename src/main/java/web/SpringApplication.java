package web;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class SpringApplication {
    public static void run(){
        Tomcat tomcat = new Tomcat();
        tomcat.addWebapp("/","C:\\fyx\\");
        //tomcat.addContext("/","C:\\fyx\\");
        tomcat.setPort(56666);
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }


        /**
         * tomcat.addWebapp和tomcat.addContext的区别
         * tomcat.addWebapp相当于告诉tomcat，我是一个web应用，此时servlet3.0新特性会生效，而tomcat.addContext则不会
         * tomcat.addWebapp时，tomcat默认会执行一个jsp引擎，如果不引入对应jar包（tomcat-embed-jasper）会报错
         */
    }
}
