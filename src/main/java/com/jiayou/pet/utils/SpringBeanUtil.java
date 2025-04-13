package com.jiayou.pet.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashMap;
import java.util.Map;

public class SpringBeanUtil {

    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        BeanWrapper beanWrapper = new BeanWrapperImpl(obj);  // 创建 BeanWrapperImpl 实例
        
        // 获取所有属性描述符（包括 getter 和 setter）
        for (var descriptor : beanWrapper.getPropertyDescriptors()) {
            String propertyName = descriptor.getName();
            
            // 跳过 "class" 属性，它是自动包含在 JavaBean 中的
            if (!propertyName.equals("class")) {
                // 获取属性的值并加入到 Map 中
                map.put(propertyName, beanWrapper.getPropertyValue(propertyName));
            }
        }
        return map;
    }
}
