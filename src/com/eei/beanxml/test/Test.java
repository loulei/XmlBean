package com.eei.beanxml.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.eei.beanxml.BeanXmlMarshaller;
import com.eei.beanxml.BeanXmlUnmarshaller;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase
{
	public String test = "";
	public void testFields() throws Exception
	{
/*		SimpleVO vo = new SimpleVO();
        vo.setGender("male");
           vo.setHobby("darts");
           vo.setName("jim");
           Child child = new Child();
           child.setName("haha");
           child.setAge("11");
           vo.setChild(child);
             
        List<String> list = new ArrayList<String>();
        list.add("sssa");
        list.add("ewee");
        vo.setList(list);
        
        
        Child child2 = new Child();
        child2.setName("hello");
        child2.setAge("23");
        List<Child> childs = new ArrayList<Child>();
        childs.add(child);
        childs.add(child2);
        
        vo.setChilds(childs);
        
        String result = BeanXmlMarshaller.writeToString(vo, "request");
        System.out.println(result);
        
        BeanXmlUnmarshaller beanXmlUnmarshaller =  new BeanXmlUnmarshaller();
        Object obj = beanXmlUnmarshaller.loadFromXmlString(result, SimpleVO.class, "request");
        System.out.println(obj.toString());
        
        result = BeanXmlMarshaller.writeToString(vo, "request");
        System.out.println(result);
        */
        
        InputStream is = getContext().getAssets().open("RSP.xml");
        BeanXmlUnmarshaller beanXmlUnmarshaller = new BeanXmlUnmarshaller();
        RSP rsp = (RSP) beanXmlUnmarshaller.loadFromXml(is, RSP.class, "RSP");
        System.out.println(rsp);
        
        String result = BeanXmlMarshaller.writeToString(rsp, "RSP");
        System.out.println(result);
	}
}
