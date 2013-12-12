package com.model;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:05:10
* 版本号： v1.0
*/
public class User
{
	private int id;
	private String name;

	public User(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public User()
	{
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void say(String str)
	{
		System.out.println(str);
	}
}
