package us.codecraft.tinyioc;

import org.junit.Test;
import us.codecraft.tinyioc.factory.AbstractBeanFactory;
import us.codecraft.tinyioc.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.io.ResourceLoader;
import us.codecraft.tinyioc.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class BeanFactoryTest {
    @Test
    public void testLazy() throws Exception {
        // 1.从资源接口根据文件名加载配置文件
        //读取配置，将域值和引用分别存入beanfition中，再由reader中的Map对象来持有这些beanDefinition
        //此时beandefinition中还没有相应的bean实例，引用字段也只有要引用的类的名字
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

        // 2.初始化BeanFactory并注册bean
        //此时只是把reader中的属性值复制到了工厂的beandefinition的map中
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3.获取bean
        //getBean会实例化需要加载的bean，级联的创建对应的引用的实例
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }

    @Test
    public void testPreInstantiate() throws Exception {
        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

        // 2.初始化BeanFactory并注册bean
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3.初始化bean
        //与上面不同 在此处一次性将beanDefinitionMap中的所有bean对象建立
        beanFactory.preInstantiateSingletons();

        // 4.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
