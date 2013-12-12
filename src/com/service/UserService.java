package com.service;

import java.util.List;

import com.model.User;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:31
* 版本号： v1.0
*/
public interface UserService {
	void save(User u);
	void delete(Integer id);
	List<User> findAll();
	void create();
}
