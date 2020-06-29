package mybatis;

import circle.Fyx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        FyxDao fyxDao = (FyxDao) ac.getBean("fyxBatisFactoryBean");
        System.out.println(fyxDao);
//        fyxDao.count();
//        GyDao gyDao = (GyDao) ac.getBean("fyxBatisFactoryBean1");
//        gyDao.update();
    }
}
