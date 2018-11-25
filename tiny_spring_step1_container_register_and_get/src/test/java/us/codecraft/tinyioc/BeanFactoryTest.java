package us.codecraft.tinyioc;

import org.junit.Test;

public class BeanFactoryTest {
    @Test
    public void test() {
        //1 初始化工厂
        BeanFactory factory = new BeanFactory();

        //2 实例化service并将其注入到工厂
        BeanDefinition beanDefinition = new BeanDefinition(new HelloWorldService());
        factory.registerBeanDefinition("helloWorldService",beanDefinition);

        //从工厂中获取bean
        HelloWorldService helloWorldService = (HelloWorldService) factory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
