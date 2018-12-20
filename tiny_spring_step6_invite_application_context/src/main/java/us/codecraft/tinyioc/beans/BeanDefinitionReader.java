package us.codecraft.tinyioc.beans;

public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws  Exception;
}
