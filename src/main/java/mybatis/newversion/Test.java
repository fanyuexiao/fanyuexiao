package mybatis.newversion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        FyxDao fyxDao = (FyxDao) ac.getBean("mybatis.newversion.FyxDao");
        fyxDao.count("a");
        GyDao gyDao = (GyDao) ac.getBean("mybatis.newversion.GyDao");
        gyDao.update();
    }
}
