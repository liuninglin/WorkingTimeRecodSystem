/**
* Copyright ? 2014-1-9 liuninglin
* WorkingTimeRecordSystem 上午01:05:56
* Version 1.0
* All right reserved.
*
*/

package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.entity.UserManage;
import com.mapper.UserManageMapper;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-1-9 上午01:05:56
 * 版本号： v1.0
 */
@SuppressWarnings("unchecked")
@Repository
public class UserManageDaoImpl<T extends UserManage> implements UserManageDao<T>
{
	@Autowired
    private UserManageMapper mapper;
	
	@Override
	public boolean addUserManage(T t) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.addUserManage(t);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public T checkUserManageIsExist(String username, String password) throws DataAccessException
	{
		T t = null;
		try
		{
			t = (T)mapper.checkUserManageIsExist(username, password);
		}
		catch (DataAccessException e)
		{
			throw e;
		}
		return t;
	}

}
