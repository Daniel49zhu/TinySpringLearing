package us.codecraft.tinyioc.beans;

import java.util.Map;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private Map<String,BeanDefinition> registry;
}
