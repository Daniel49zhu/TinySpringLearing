package us.codecraft.tinyioc;

public interface BeanDefinitionReader {

    void loadBeanDefinition(String location) throws Exception;
}
