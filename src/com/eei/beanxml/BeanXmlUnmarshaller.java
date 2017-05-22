package com.eei.beanxml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.eei.beanxml.type.TypeUtil;

public class BeanXmlUnmarshaller {
   

    

	public Object loadFromXml(InputStream is, Class type, String rootName) throws Exception {
		
		Object object = type.newInstance();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);
        Element root = document.getDocumentElement();
        parse(root, object);
    	return object;
    }
    
    public Object loadFromXmlString(String xml, Class type, String rootName) throws Exception {
        ByteArrayInputStream s = new ByteArrayInputStream(xml.getBytes());
        return loadFromXml(s, type, rootName);
    }

    public void parse(Element element, Object object) throws Exception
    {
    	Class clazz = object.getClass();
    	NodeList nodeList = element.getChildNodes();
    	int size = nodeList.getLength();
    	System.out.println(size);
    	for(int i=0; i<size; i++)
    	{
    		Node node = nodeList.item(i);
    		if(node instanceof Element)
    		{
    			Class clz = null;
     			Element e = (Element) node;
    			String nodeName = e.getNodeName();
    			Field[] fields = clazz.getDeclaredFields();
    			Field f = null;
    			for (Field field : fields)
				{
					if(field.getName().equalsIgnoreCase(nodeName))
					{
						clz = field.getType();
						f = field;
						break;
					}
				}
    			if(String.class.equals(clz))
    			{
    				Method method = TypeUtil.getWriteMethod(clazz, nodeName);
    				String value = e.getTextContent();
    				method.invoke(object, value);
    			}else if(List.class.equals(clz))
    			{
    				Type fc = f.getGenericType();
					if(fc == null)
						continue;
					if(fc instanceof ParameterizedType)
					{
						ParameterizedType pt = (ParameterizedType) fc;
						Class genericClazz = (Class) pt.getActualTypeArguments()[0];
						List list = new ArrayList();
						parse(e, list, genericClazz);
						Method method = TypeUtil.getWriteMethod(clazz, nodeName);
						method.invoke(object, list);
					}
    			}else
    			{
    				Method method = TypeUtil.getWriteMethod(clazz, nodeName);
    				Object  value = clz.newInstance();
    				parse(e, value);
    				method.invoke(object, value);
    			}
    		}
    	}
    }
   
    public void parse(Element element, List list, Class clazz) throws Exception
    {
    	NodeList nodeList = element.getChildNodes();
    	int len = nodeList.getLength();
    	for(int i=0; i<len; i++)
    	{
    		Node node = nodeList.item(i);
    		if(node instanceof Element)
    		{
    			Object object = clazz.newInstance();
    			parse((Element)node, object);
    			list.add(object);
    		}
    	}
    }

    

}
