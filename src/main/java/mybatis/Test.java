package mybatis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        FyxDao fyxDao = (FyxDao) ac.getBean("fyxBatisFactoryBean");
        System.out.println(fyxDao);
        fyxDao.count("a");
        GyDao gyDao = (GyDao) ac.getBean("fyxBatisFactoryBean1");
        gyDao.update();
    }
}
