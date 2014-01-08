package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exception.ServiceException;
import com.dao.UserManageDao;
import com.entity.UserManage;
import com.service.UserService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:40
* 版本号： v1.0
*/
@Service("uService")
public class UserServiceImpl<T extends UserManage> implements UserService<T> {

	@Autowired
	private UserManageDao<T> userManageDao;
	
	//用户登录
	@Override
	public T checkLogin(String username, String password) throws ServiceException
	{
		if(null == username || "".equals(username.trim()) || null == password || "".equals(password.trim()))
		{
			throw new ServiceException("用户名或密码不能为空！");
		}
		return userManageDao.checkUserManageIsExist(username, password);
	}
}
