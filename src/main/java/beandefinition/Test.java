package beandefinition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        MutablePropertyValues propertyValues = rootBeanDefinition.getPropertyValues();
        propertyValues.add("highSchool","巢湖四中");
        propertyValues.add("homeTown","巢湖");
        rootBeanDefinition.setAbstract(true);
        //rootBeanDefinition.setBeanClass(Config.class); 设置类也可以实例化
        ac.registerBeanDefinition("rootBeanDefinition",rootBeanDefinition);

        ChildBeanDefinition gy = new ChildBeanDefinition("rootBeanDefinition");
        gy.setBeanClass(Gy.class);
        gy.getPropertyValues().add("name","桂越");
        ChildBeanDefinition fyx = new ChildBeanDefinition("rootBeanDefinition");
        fyx.setBeanClass(Fyx.class);
        fyx.getPropertyValues().add("name","范月潇");
        ac.registerBeanDefinition("gy",gy);
        ac.registerBeanDefinition("fyx",fyx);
        ac.register(Config.class);
        ac.refresh();

        System.out.println(ac.getBean("gy"));
        System.out.println(ac.getBean("fyx"));

        ac.close();

        /**
         * BeanMetadataElement
         * 获取类的源文件
         *
         * AttributeAccessor
         * 扩展beanDefinition属性
         * spring为beanDefinition设置了：scope，beanClass，beanClassName，abstract等等
         * 我们可以增加自己的属性
         *
         * BeanDefinition
         * 继承BeanMetadataElement、AttributeAccessor
         *
         * AttributeAccessorSupport
         * 实现AttributeAccessor
         *
         * BeanMetadataAttributeAccessor
         * 实现BeanMetadataElement，继承AttributeAccessorSupport
         *
         * AbstractBeanDefinition
         * 实现BeanDefinition，继承BeanMetadataAttributeAccessor，可作为模板让其他beanDefinition继承
         * 有三个beanDefinition直接继承它（RootBeanDefinition、ChildBeanDefinition、GenericBeanDefinition）
         * ps：一个鸡肋的小知识（INFER_METHOD）
         * AbstractBeanDefinition中有一个常量INFER_METHOD,可以推断@preDestroy的默认方法（方法名为close或者shutdown）
         *
         *
         * RootBeanDefinition
         * 在spring2.5时，通常作为一个模板被设置为Abstract（若设置beanClass也可以被实例化），设置一些共同的属性，减少冗余
         * 它只能作为父bd，为RootBeanDefinition设置parentName会抛出异常
         *
         * ChildBeanDefinition
         * 在spring2.5时，通常继承RootBeanDefinition
         * 它只能作为子bd，它的构造函数必须传parentName
         *
         * GenericBeanDefinition-->xml
         * 可以替代所有ChildBeanDefinition和大部分RootBeanDefinition的作用，可以为子bd也可以为父bd
         * 唯一不能替代RootBeanDefinition在于：mergeBeanDefinition后要用RootBeanDefinition接收。
         * spring之前开发的时候，在mergeBeanDefinition的很多地方都传的RootBeanDefinition,懒得改过来了
         *
         * AnnotatedBeanDefinition
         * 继承BeanDefinition
         * 有三个实现类（ConfigurationClassBeanDefinition、ScannedGenericBeanDefinition、AnnotatedGenericBeanDefinition）
         *
         * ConfigurationClassBeanDefinition-->@Bean
         *
         * ScannedGenericBeanDefinition-->@Component
         *
         * AnnotatedGenericBeanDefinition-->ac.register()
         *
         * 在判断是否是注解类的时候
         * 先根据beanDefinition的类型判断
         * -->org.springframework.context.annotation.ConfigurationClassUtils#checkConfigurationClassCandidate
         * -->if(beanDef instanceof AnnotatedBeanDefinition)
         * 再分全注解类（full）、半注解类（lite）、其他
         *
         * MergedBeanDefinition
         * spring要实例化一个对象的时候，会合并beanDefinition
         * 理论上来说spring应该开发一个MergedBeanDefinition，但是并没有，而是直接借用了RootBeanDefinition
         * 也就是说，所有的beanDefinition被扫描出来后都要被合并成为RootBeanDefinition
         * spring的合并方法是把子BeanDefinition的属性copy到RootBeanDefinition中
         *
      */
    }
}
