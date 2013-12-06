package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.model.User;
import com.service.UserService;

@Service("uService")
public class UserServiceImpl implements UserService {
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 玩数据库添加一条记录
	 * 
	 * @param u
	 *            用户
	 */
	public void save(User u) {
		jdbcTemplate.update("insert into user values(null,?)", u.getName());
	}

	/**
	 * 删除一个用户
	 * 
	 * @param id
	 *            用户id
	 */
	public void delete(Integer id) {
		jdbcTemplate.update("delete from user where id=?", id);
	}

	/**
	 * 查询所有用户信息
	 * 
	 * @return 所有用户的集合
	 */
	public List findAll() {
		return jdbcTemplate.queryForList("SELECT * FROM `user`");
	}

}
