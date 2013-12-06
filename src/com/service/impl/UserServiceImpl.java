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
	 * �����ݿ����һ����¼
	 * 
	 * @param u
	 *            �û�
	 */
	public void save(User u) {
		jdbcTemplate.update("insert into user values(null,?)", u.getName());
	}

	/**
	 * ɾ��һ���û�
	 * 
	 * @param id
	 *            �û�id
	 */
	public void delete(Integer id) {
		jdbcTemplate.update("delete from user where id=?", id);
	}

	/**
	 * ��ѯ�����û���Ϣ
	 * 
	 * @return �����û��ļ���
	 */
	public List findAll() {
		return jdbcTemplate.queryForList("SELECT * FROM `user`");
	}

}
