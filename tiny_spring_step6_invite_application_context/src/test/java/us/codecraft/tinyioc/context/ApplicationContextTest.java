package us.codecraft.tinyioc.context;

import org.junit.Test;
import us.codecraft.tinyioc.HelloWorldService;

/**
 * @author yihua.huang@dianping.com
 */
public class ApplicationContextTest {

    @Test
    public void test() throws Exception {
        //ApplicationContext会持有一个AutowireCapableBeanFactory对象，并在构造方法中调用refresh方法，来创建XmlBeanDefinitionReader来读取xml文件配置，
        //并将组装的所有benaDefiniiton交给工厂对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
