package com.jabda;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.user.UserDAO;
import com.user.UserTO;

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
		
		ArrayList<UserTO> user = null;
		
		user = udao.memberlist();
		
		
		ModelAndView modelAndView = new ModelAndView("admin/AdminMain");
		
		modelAndView.addObject("member_list", user);
		
		return modelAndView;
	}
	
	/* AdminUser (유저 관리) */
	@RequestMapping("adminuser.do")
	public ModelAndView adminuser(HttpServletRequest request) {
		System.out.println("AdminUser() 호출");
		
		
		
		ModelAndView modelAndView = new ModelAndView("AdminUser");
		return modelAndView;
	}
}
