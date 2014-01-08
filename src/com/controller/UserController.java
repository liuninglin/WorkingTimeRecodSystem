package com.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class UserController{

	@Resource(name = "uService")
	private UserService<UserManage> uService;
	
	@RequestMapping("/loginUser")
	public String loginUser(HttpServletRequest req, HttpServletResponse resp, UserManage usermanage, Model model, String remembervalue) throws IOException {
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
							break;
						}
					}
				}
			}
		}
		
		UserManage usermanageTemp = null;
		try
		{
			usermanageTemp = uService.checkLogin(usermanage.getUsername(), usermanage.getPassword());
		}
		catch (ServiceException e) 
		{
			model.addAttribute("message", "用户名或密码不能为空！");
			return "layouts/login";
		}
		
		if(null != usermanageTemp)
		{
			if("1".equals(remembervalue))//保存密码
			{
				cookies = req.getCookies();
				boolean cookieFlag = false;
				if(null != cookies)
				{
					for(Cookie cookie : cookies)
					{
						if("WorkingTimeRecordSystem.UserState".equals(cookie.getName().trim()))
						{
							cookieFlag = true;
						}
					}
				}
				
				if(!cookieFlag)
				{
					Cookie cookieTemp = new Cookie("WorkingTimeRecordSystem.UserState", (usermanage.getUsername() + " " + usermanage.getPassword()));
					cookieTemp.setSecure(req.isSecure());
					cookieTemp.setMaxAge(3600);
					cookieTemp.setPath("/WorkingTimeRecordSystem");
					
					
					resp.addCookie(cookieTemp);
				}
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("usermanage", usermanageTemp);
			
			resp.sendRedirect("indexPage.html");
			return null;
		}
		else
		{
			model.addAttribute("message", "用户名或密码错误！");
			return "layouts/login";
		}
	}
	
	@RequestMapping("/logoutUser")
	public String logoutUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserManage usermanage = req.getSession().getAttribute("usermanage") == null ? null : (UserManage)req.getSession().getAttribute("usermanage");
		if(null != usermanage)
		{
			req.getSession().removeAttribute("usermanage");
		}

		Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies)
		{
			if("WorkingTimeRecordSystem.UserState".equals(cookie.getName().trim()))
			{
				cookie = new Cookie("WorkingTimeRecordSystem.UserState", null);
				cookie.setSecure(req.isSecure());
				cookie.setMaxAge(0);
				cookie.setPath("/WorkingTimeRecordSystem");
				resp.addCookie(cookie);
			}
		}
		
		resp.sendRedirect("loginPage.html");
		return null;
	}
}
