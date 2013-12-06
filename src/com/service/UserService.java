package com.service;

import java.util.List;

import com.model.User;

public interface UserService {
	void save(User u);
	void delete(Integer id);
	List<User> findAll();
}
