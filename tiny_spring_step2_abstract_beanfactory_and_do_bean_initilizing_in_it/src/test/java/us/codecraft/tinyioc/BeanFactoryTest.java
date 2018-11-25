package us.codecraft.tinyioc;

import org.junit.Test;
import us.codecraft.tinyioc.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.factory.BeanFactory;

public class BeanFactoryTest {
    @Test
    public void test() {
        //1 初始化工厂
        BeanFactory factory = new AutowireCapableBeanFactory();
        //2 初始化bean，BeanDefinition的className和class属性在set方法里被初始化
        //在registerBeanDefinition里bean被实例化，此时beanDefinition的三个域都已经有值了
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("us.codecraft.tinyioc.HelloWorldService");
        factory.registerBeanDefinition("helloWorldService", beanDefinition);
        //获取bean
        HelloWorldService helloWorldService = (HelloWorldService) factory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
