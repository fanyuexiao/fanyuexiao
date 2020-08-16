package mybatis.oldversion;

import org.springframework.beans.factory.FactoryBean;

public class FyxBatisFactoryBean implements FactoryBean {
    Class mapperInterface;

    public Object getObject() {
        return FyxBatisSession.queryMapper(mapperInterface);
    }

    public Class<?> getObjectType() {
        return mapperInterface;
    }

    public void setMapperInterface(Class mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
}
