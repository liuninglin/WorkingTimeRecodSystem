package com.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.exception.ServiceException;
import com.entity.UserManage;
import com.service.UserService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:51
* 版本号： v1.0
*/
@Controller
public class PageController{

	@Resource(name = "uService")
	private UserService<UserManage> uService;
	
	@RequestMapping("/loginPage")
	public String loginPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Cookie[] cookies = req.getCookies();
		if(null != cookies)
		{
			for(Cookie cookie : cookies)
			{
				if("WorkingTimeRecordSystem.UserState".equals(cookie.getName().trim()))
				{
					if(!"".equals(cookie.getValue().trim()))
					{
						String username = cookie.getValue().split(" ")[0];
						String password = cookie.getValue().split(" ")[1];
						
						try
						{
							if(null != uService.checkLogin(username, password))
							{
								resp.sendRedirect("indexPage.html");
								return null;
							}
						}
						catch (ServiceException e) 
						{
							return "layouts/login";
						}
					}
				}
			}
		}
		
		return "layouts/login";
	}
	
	@RequestMapping("/indexPage")
	public String indexPage() {
		return "layouts/index";
	}
	
	@RequestMapping("/workdaycheckinPage")
	public String workdaycheckinPage() {
		return "layouts/workdaycheckin";
	}
	
	@RequestMapping("/workercheckinPage")
	public String workercheckinPage() {
		return "layouts/workercheckin";
	}
	
	@RequestMapping("/buildingsiteheckinPage")
	public String buildingsiteheckinPage() {
		return "layouts/buildingsiteheckin";
	}
}
