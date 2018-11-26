package us.codecraft.tinyioc.factory;

import us.codecraft.tinyioc.BeanDefinition;
import us.codecraft.tinyioc.PropertyValue;

import java.lang.reflect.Field;

/**
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    /**
     * 对bean进行装配，具体包括
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        //创建bean实例
        Object bean = createBeanInstance(beanDefinition);
        //为bean中的属性赋值
        applyPropertyValues(bean,beanDefinition);
        return bean;
    }

    /**
     * 根据propertyValue中的name找到实例bean中是否存在对应的域，并对域赋值
     * @param bean
     * @param mbd
     * @throws Exception
     */
    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception{
        for(PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
            Field field = bean.getClass().getDeclaredField(propertyValue.getName());
            field.setAccessible(true);
            field.set(bean,propertyValue.getValue());
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
        return beanDefinition.getBeanClass().newInstance();
    }
}
