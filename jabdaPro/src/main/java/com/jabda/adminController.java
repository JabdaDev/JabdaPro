package com.jabda;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.admin.adminDAO;
import com.user.UserDAO;
import com.user.UserTO;

@Controller
public class adminController {
	
	@Autowired
	UserDAO udao;
	@Autowired
	adminDAO adao;
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
		
		ArrayList<UserTO> user = null;
		
		user = udao.memberlist();
		
		
		ModelAndView modelAndView = new ModelAndView("admin/AdminUser");
		
		modelAndView.addObject("user_list", user);
		
		return modelAndView;
	}
	
	/* 유저 등급 및 재설정 실행 부분 */
	@RequestMapping("rank_modify_ok.do")
	public ModelAndView rank_modify(HttpServletRequest request) {
		System.out.println("rank_modify() 호출");
		
		UserTO to = new UserTO();
		to.setEmail(request.getParameter("email"));
		to.setRank(request.getParameter("rank"));
		
		adminDAO adao = new adminDAO(dataSource);
		
		
		ModelAndView modelAndView = new ModelAndView("admin/rank_modify_ok");
		
		//modelAndView.addObject("flag", flag);
		modelAndView.addObject("to", to);
		
		return modelAndView;
		
	}
	
	@RequestMapping("member_modify.do")
	public ModelAndView member_modify(HttpServletRequest request) {
		System.out.println("member_modify()호출");
		
		UserTO to = new UserTO();
		to.setEmail(request.getParameter("email"));
		
		adminDAO adao = new adminDAO(dataSource);
		to = adao.memberview(to);
		
		ModelAndView modelAndView = new ModelAndView("admin/member_modify");
		modelAndView.addObject("to", to);
		
		return modelAndView;
	
	}
}
