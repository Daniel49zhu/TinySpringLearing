package us.codecraft.tinyioc.factory;

import us.codecraft.tinyioc.BeanDefinition;
import us.codecraft.tinyioc.PropertyValue;

import java.lang.reflect.Field;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    private void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        for (PropertyValue pv : mbd.getPropertyValues().getPropertyValueList()) {
            Field field = bean.getClass().getDeclaredField(pv.getName());
            field.setAccessible(true);
            field.set(bean, pv.getValue());
        }
    }
}
