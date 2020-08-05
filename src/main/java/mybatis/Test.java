package mybatis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        FyxDao fyxDao = (FyxDao) ac.getBean("fyxBatisFactoryBean");
        fyxDao.count("a");
        GyDao gyDao = (GyDao) ac.getBean("fyxBatisFactoryBean1");
        gyDao.update();

        FyxClassPathBeanDefinitionScanner scanner = new FyxClassPathBeanDefinitionScanner(ac);
        scanner.addIncludeFilter(new AnnotationTypeFilter(FyxScan.class));
        System.out.println(scanner.scan("mybatis"));
        /**
         * this.reader = new AnnotatedBeanDefinitionReader(this);-->ac.register
         * this.scanner = new ClassPathBeanDefinitionScanner(this);-->scanner.scan
         * 这两句的主要作用是解耦，给程序员提供扩展api
         */
    }
}
