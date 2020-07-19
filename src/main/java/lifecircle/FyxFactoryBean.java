package lifecircle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class FyxFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new FactoryBeanBean();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBeanBean.class;
    }
}
