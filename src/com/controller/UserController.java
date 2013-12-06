package com.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.service.UserService;

/**
 * 控制器
 * 
 * @author dongji
 * 
 */
@Controller
public class UserController{

	@Resource(name = "uService")
	private UserService uService;

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
		request.setAttribute("message", "您刚才删除了ID为"+id+"的人!!!");
		request.setAttribute("users", uService.findAll());
		return new ModelAndView("hello");
	}

	@RequestMapping("/hello")
	public ModelAndView hello(HttpServletRequest request) {
		request.setAttribute("users", uService.findAll());
		return new ModelAndView("hello");
	}
}
