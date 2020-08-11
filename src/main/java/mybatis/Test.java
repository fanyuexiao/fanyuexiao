package mybatis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        EnableFyxScan enableFyxScan = Config.class.getAnnotation(EnableFyxScan.class);
        if (enableFyxScan != null) {
            FyxClassPathBeanDefinitionScanner scanner = new FyxClassPathBeanDefinitionScanner(ac);
            scanner.addIncludeFilter(new AnnotationTypeFilter(FyxScan.class));
            scanner.scan(enableFyxScan.value());
        }
        ac.register(Config.class);
        ac.refresh();
        FyxDao fyxDao = (FyxDao) ac.getBean("mybatis.FyxDao");
        fyxDao.count("a");
        GyDao gyDao = (GyDao) ac.getBean("mybatis.GyDao");
        gyDao.update();


        /**
         * this.reader = new AnnotatedBeanDefinitionReader(this);-->ac.register
         * this.scanner = new ClassPathBeanDefinitionScanner(this);-->scanner.scan
         * 这两句的主要作用是解耦，给程序员提供扩展api
         */
    }
}
