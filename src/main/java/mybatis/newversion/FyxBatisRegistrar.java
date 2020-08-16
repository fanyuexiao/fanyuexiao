package mybatis.newversion;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Objects;

public class FyxBatisRegistrar implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String scanPackage = (String) Objects.requireNonNull(importingClassMetadata.getAnnotationAttributes(EnableFyxScan.class.getName())).get("value");
        System.out.println(scanPackage);
        if (scanPackage != null) {
            FyxClassPathBeanDefinitionScanner scanner = new FyxClassPathBeanDefinitionScanner(registry);
            scanner.addIncludeFilter(new AnnotationTypeFilter(FyxScan.class));
            scanner.scan(scanPackage);
        }
        /**
         * <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
         *      <property name="basePackage" value="org.ezca.autocount.dao"/>
         *      <property name="markerInterface" value="org.ezca.autocount.dao.CertRegionMapper"/>
         * </bean>
         * 直接提供MapperScannerConfigurer类也可以，此时不依赖ImportBeanDefinitionRegistrar
         */
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(FyxMapperScannerConfigurer.class);
        registry.registerBeanDefinition("fyxMapperConfigurer",builder.getBeanDefinition());
    }
}
