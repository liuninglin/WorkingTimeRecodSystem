package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.model.User;
import com.service.UserService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:40
* 版本号： v1.0
*/
@Service("uService")
public class UserServiceImpl implements UserService {
	@Resource
	private JdbcTemplate jdbcTemplate;

	public void save(User u) {
		jdbcTemplate.update("insert into foo values(1,?)", u.getName());
	}

	public void delete(Integer id) {
		jdbcTemplate.update("delete from foo where id=?", id);
	}

	public List findAll() {
		return jdbcTemplate.queryForList("SELECT * FROM foo");
	}

	@Override
	public void create()
	{
		jdbcTemplate.update("create table foo (FOOID INT NOT NULL,FOONAME VARCHAR(30) NOT NULL)");
	}

}
