package com.eei.beanxml.type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.eei.beanxml.util.CommonUtil;



public class TypeUtil {
    public static Method getReadMethod(Class<?> clazz, String name)
    {
    	Method[] methods = clazz.getDeclaredMethods();
    	for (Method method : methods)
		{
			if(method.getName().equalsIgnoreCase(CommonUtil.GET+name))
			{
				return method;
			}
		}
		return null;
    }
    
    
    public static Method getWriteMethod(Class<?> clazz, String name)
    {
    	Method[] methods = clazz.getDeclaredMethods();
    	for (Method m : methods)
		{
			if(m.getName().equalsIgnoreCase(CommonUtil.SET+name))
			{
				return m;
			}
		}
		return null;
    
    }
}
