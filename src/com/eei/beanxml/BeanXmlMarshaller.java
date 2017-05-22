package com.eei.beanxml;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.eei.beanxml.type.TypeUtil;
import com.eei.beanxml.util.CommonUtil;



public class BeanXmlMarshaller {
 
    public static String writeToString(Object o, String rootName) throws BeanXmlException {
        StringWriter writer = new StringWriter();
        writeTo(o,writer,rootName);
        return writer.toString();
    }

   
    public static void writeTo(Object o, Writer w, String className) throws BeanXmlException {

        try {
        	if(o == null)
        		return;
        	Class<?> clazz = o.getClass();
        	if(className != null)
        	{
        		writeStartTag(className, w);
        	}else
        	{
        		String name = CommonUtil.toLowerCaseFirstOne(clazz.getSimpleName());
        		writeStartTag(name, w);
        	}
        	
           
            
            
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields)
			{
				//System.out.println(field.getName());
				Class<?> clz = field.getType();
				Method method = TypeUtil.getReadMethod(clazz, field.getName());
				Object obj = method.invoke(o);
				if(obj == null)
					continue;
				if(clz.equals(String.class))
				{
					writeStartTag(field.getName(), w);
					
					//System.out.println(method.getName());
					String value = obj.toString();
					writeValue(value, w);
					writeEndTag(field.getName(), w);
				}else if(clz.equals(List.class))
				{
					System.out.println("list");
					List<Object> list = (List<Object>) obj;
					if(list == null || list.size() <= 0)
						continue;
					writeStartTag(field.getName(), w);
					
					Type fc = field.getGenericType();
					if(fc == null)
						continue;
					if(fc instanceof ParameterizedType)
					{
						ParameterizedType pt = (ParameterizedType) fc;
						Class genericClazz = (Class) pt.getActualTypeArguments()[0];
						
						for (Object object : list)
						{
							if(genericClazz.equals(String.class))
							{
								writeStartTag(object.getClass().getSimpleName(), w);
								writeValue(object.toString(), w);
								writeEndTag(object.getClass().getSimpleName(), w);
							}else
							{
								writeTo(object, w, null);
							}
						}
						
					}
					writeEndTag(field.getName(), w);
				}else
				{
					writeTo(obj, w, null);
				}
			}
            if(className != null)
        	{
            	writeEndTag(className, w);
        	}else
        	{
        		String name = CommonUtil.toLowerCaseFirstOne(clazz.getSimpleName());
        		writeEndTag(name, w);
        	}
            

            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeStartTag(String name, Writer w) throws IOException {
        w.write("<");
        w.write(name);
        w.write(">");
    }

    private static void writeEndTag(String name, Writer w) throws IOException {
        w.write("</");
        w.write(name);
        w.write(">");
    }

    private static void writeValue(String value, Writer w) throws IOException {
        w.write(value);
    }
}
