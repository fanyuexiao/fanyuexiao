package web;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MyWebApplicationInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        /**
         * <context-param>
         *     <param-name>contextConfigLocation</param-name>
         *     <param-value>classpath:application.xml</param-value>
         * </context-param>
         * <listener>
         *     <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
         * </listener>
         */
        System.out.println("init");
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(Config.class);
        ac.refresh();
        /**
         * <servlet>
         *     <servlet-name>SpringMVC</servlet-name>
         *     <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
         *     <init-param>
         *         <param-name>contextConfigLocation</param-name>
         *         <param-value>classpath:springMVC.xml</param-value>
         *     </init-param>
         *     <load-on-startup>1</load-on-startup>
         * </servlet>
         */
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic springMVC = servletContext.addServlet("SpringMVC", dispatcherServlet);
        springMVC.setLoadOnStartup(1);
        /**
         * <servlet-mapping>
         *     <servlet-name>SpringMVC</servlet-name>
         *     <url-pattern>/</url-pattern>
         * </servlet-mapping>
         */
        springMVC.addMapping("/");


        /**
         * 如何将dispatchServlet注册给web容器（tomcat、jetty、undertow）
         * 1、web.xml
         * 2、在类上加@WebServlet
         * 3、利用spi和servlet3.0新特性（https://blog.csdn.net/pingnanlee/article/details/80940993），手动注册
         * 注意：
         *      web.xml方式中，dispatchServlet需要关联一个配置文件（springMVC.xml），
         *      而手动注册方式中，配置类被关联给了AnnotationConfigWebApplicationContext，
         *      因此，要将AnnotationConfigWebApplicationContext关联给dispatchServlet
         *
         * @HandlesTypes （https://www.cnblogs.com/feixuefubing/p/11593411.html）
         */
    }
}
