package us.codecraft.tinyioc;

import org.junit.Test;
import us.codecraft.tinyioc.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.factory.BeanFactory;
import us.codecraft.tinyioc.io.ResourceLoader;
import us.codecraft.tinyioc.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class BeanFactoryTest {
    @Test
    public void test() throws Exception{
        //1 生成reader,其持有一个ResourceLoader和一个Map<String, BeanDefinition> registry
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        //根据xml文件,读取inputStream,解析成Document类型,将class,property等属性添加到BeanDefinition中
        xmlBeanDefinitionReader.loadBeanDefinition("tinyioc.xml");

        //2.创建工厂,将XmlBeanDefinitionReader持有的BeanDefinition对象中的所有属性都赋给beanFactory持有的 Map<String, BeanDefinition> beanDefinitionMap
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        for(Map.Entry<String,BeanDefinition> beanDefinitionEntry:xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }

        //3 实例化bean,将BeanDefinition中定义的域的值赋给bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
