package com.eei.beanxml.test;

import java.util.List;


/**
 * Simple value object to use for testing
 */
public class SimpleVO {
    private String name;
    private String gender;
    private String hobby;
    private List<String> list;
    private Child child;
    private List<Child> childs;
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<String> getList()
	{
		return list;
	}

	public void setList(List<String> list)
	{
		this.list = list;
	}

	public Child getChild()
	{
		return child;
	}

	public void setChild(Child child)
	{
		this.child = child;
	}

	public List<Child> getChilds()
	{
		return childs;
	}

	public void setChilds(List<Child> childs)
	{
		this.childs = childs;
	}

    
}
