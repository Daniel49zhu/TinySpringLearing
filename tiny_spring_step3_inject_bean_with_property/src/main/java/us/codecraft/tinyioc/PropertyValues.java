package us.codecraft.tinyioc;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个bean所有的属性与值进行包装
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public PropertyValues() {
    }

    public void addPropertyValue(PropertyValue pv) {
        //TODO 在这里可以对重复的的propertyName进行判断
        this.propertyValueList.add(pv);
    }

    public List<PropertyValue> getPropertyValues() {
        return this.propertyValueList;
    }
}
