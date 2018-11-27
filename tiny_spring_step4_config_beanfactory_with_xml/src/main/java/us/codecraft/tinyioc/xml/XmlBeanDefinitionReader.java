package us.codecraft.tinyioc.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import us.codecraft.tinyioc.AbstractBeanDefinitionReader;
import us.codecraft.tinyioc.BeanDefinition;
import us.codecraft.tinyioc.PropertyValue;
import us.codecraft.tinyioc.io.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinition(String location) throws Exception {
        InputStream inputStream = this.getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputStream);
//        System.out.println(doc.getNodeName());
//        System.out.println("--------------------------------");
        //解析bean
        registerBeanDefinitions(doc);
        inputStream.close();
    }
    public void registerBeanDefinitions(Document doc) {
        Element root = doc.getDocumentElement();
//        System.out.println(root.getTagName());
//        System.out.println("--------------------------------");
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if(node instanceof Element) {
                Element element = (Element) node;
                processBeanDefinition(element);
            }
        }
    }

    protected void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele,beanDefinition);
        beanDefinition.setBeanClassName(className);
        getRegistry().put(name,beanDefinition);
    }

    private void processProperty(Element ele,BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if(node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
            }
        }
    }
}
