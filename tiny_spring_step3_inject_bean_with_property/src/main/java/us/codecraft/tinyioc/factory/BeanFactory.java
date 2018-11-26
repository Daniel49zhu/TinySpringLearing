package us.codecraft.tinyioc.factory;

import us.codecraft.tinyioc.BeanDefinition;

public interface BeanFactory {
    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
