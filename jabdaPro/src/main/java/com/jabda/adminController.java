package com.jabda;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.user.UserDAO;

@Controller
public class adminController {
	
	@Autowired
	UserDAO udao;
	@Autowired
	private DataSource dataSource;
	
	/* admin main */
	@RequestMapping({ "admin.do", "/admin" })
	public ModelAndView admin(HttpServletRequest request) {
		System.out.println("admin() 호출");
		
		ModelAndView modelAndView = new ModelAndView("admin/AdminMain");
		return modelAndView;
	}
	
}
