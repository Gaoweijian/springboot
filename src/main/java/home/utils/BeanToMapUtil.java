package home.utils;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/25 上午 09:37
 * @Version: 1.0
 * @Description: Description:Map与Bean的相互转换
 */
@Slf4j
public class BeanToMapUtil {

    /**
     * 属性名称 class
     */
    private static final String PROPERTY_NAME_CLASS = "class";

    /**
     * @描述 将一个 Map 对象转化为一个 JavaBean
     * @参数 [map, type]
     * @返回值 java.lang.Object
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/25
     * @修改人
     */
    public static Object convertMap(Map map, Class type) throws IntrospectionException,
            InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        log.info("[BeanToMapUtil]Map转JavaBean,START,map={},type={}", map, type);
        // 获取类属性
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        // 创建 JavaBean 对象
        Object obj = type.newInstance();

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);
                Object[] args = new Object[1];
                args[0] = value;
                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        log.info("[BeanToMapUtil]Map转JavaBean,SUCCESS,JavaBean={}", obj);
        return obj;
    }

    /**
     * @描述 将一个 JavaBean 对象转化为一个  Map
     * @参数 [bean(要转化的JavaBean 对象) ]
     * @返回值 Map<String,Object>(转化出来的  Map 对象)
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/25
     * @修改人
     */
    public static Map<String, Object> convertBean(Object bean) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException {
        log.info("[BeanToMapUtil]JavaBean转Map,START,bean={}", bean);
        Class type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        //获取bean的全部属性信息
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            //遍历所有属性
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!PROPERTY_NAME_CLASS.equals(propertyName)) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        log.info("[BeanToMapUtil]JavaBean转Map,SUCCESS,returnMap={}", returnMap);
        return returnMap;
    }
}
