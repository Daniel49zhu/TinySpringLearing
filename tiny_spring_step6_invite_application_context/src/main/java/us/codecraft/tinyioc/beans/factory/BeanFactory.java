package us.codecraft.tinyioc.beans.factory;

/**
 * bean的容器
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

}