package us.codecraft.tinyioc.xml;

import org.junit.Test;
import us.codecraft.tinyioc.BeanDefinition;
import us.codecraft.tinyioc.io.ResourceLoader;

import java.util.Map;

public class XmlBeanDefinitionReaderTest {
    @Test
    public void test() throws Exception{
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinition("tinyioc.xml");
        Map<String, BeanDefinition> map = xmlBeanDefinitionReader.getRegistry();
        System.out.println(map);
    }
}
