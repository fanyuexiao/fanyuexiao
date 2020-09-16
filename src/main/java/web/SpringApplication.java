package web;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringApplication {
    /**
     * tomcat.addWebapp和tomcat.addContext的区别
     * tomcat.addWebapp相当于告诉tomcat，我是一个web应用，此时servlet3.0新特性会生效，而tomcat.addContext则不会
     * tomcat.addWebapp时，tomcat默认会执行一个jsp引擎，如果不引入对应jar包（tomcat-embed-jasper）会报错
     * 如果不想引入jasper的依赖，那就使用tomcat.addContext，也就不使用servlet3.0的新特性，此时就需要手动调用onStartup的逻辑
     * 手动调用时，servletContext通过tomcat的api获取
     * springboot源码就是手动调用onStartup逻辑的
     */
    public static void run(){
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

    public static void runWithoutServlet3(){
        Tomcat tomcat = new Tomcat();
        tomcat.addContext("/","C:\\fyx\\");
        tomcat.setPort(56666);
        System.out.println("init");
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(Config.class);
        //ac.refresh();
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
        Wrapper springMVC = tomcat.addServlet("/", "springMVC", dispatcherServlet);
        springMVC.setLoadOnStartup(1);
        springMVC.addMapping("*.do");
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
