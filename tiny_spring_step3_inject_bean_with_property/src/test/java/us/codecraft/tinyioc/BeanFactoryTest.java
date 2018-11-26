package us.codecraft.tinyioc;

import org.junit.Test;
import us.codecraft.tinyioc.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.factory.BeanFactory;

public class BeanFactoryTest {
    @Test
    public void test() throws Exception{
        //1 初始化beanFactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        //2 bean定义
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("us.codecraft.tinyioc.HelloWorldService");//此时beanDefinition中的beanClass和beanClassName都已经有对应的值

        //3 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("text","Hello World! --step3"));
        beanDefinition.setPropertyValues(propertyValues);

        //4. 注册bean
        beanFactory.registerBeanDefinition("helloWorldService",beanDefinition);

        //5 获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
