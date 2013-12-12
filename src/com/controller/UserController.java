package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.service.UserService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:51
* 版本号： v1.0
*/
@Controller
public class UserController{

	@Resource(name = "uService")
	private UserService uService;

	@RequestMapping("/create")
	public String create(HttpServletRequest request) {
		uService.create();
		return "hello";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request, User u) {
		uService.save(u);
		String message = "Save Ok, Spring 3.0!";
		request.setAttribute("message", message);
		request.setAttribute("users", uService.findAll());
		// return new ModelAndView("hello", "message", message);
		return "hello";
	}

	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		uService.delete(id);
		request.setAttribute("message", "AA"+id+"CC");
		request.setAttribute("users", uService.findAll());
		return new ModelAndView("hello");
	}

	@RequestMapping("/hello")
	public ModelAndView hello(HttpServletRequest request) {
		request.setAttribute("users", uService.findAll());
		return new ModelAndView("hello");
	}
}
