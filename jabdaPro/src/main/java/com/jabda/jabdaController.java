package com.itmi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import com.user.UserTO;
import com.user.UserDAO;

@Controller
public class jabdaController {
	
	@Autowired
	UserDAO udao;
	@Autowired
	private DataSource dataSource;
	
	
	
	/* main */
	@RequestMapping( {"main.do", "/"} )
	public ModelAndView main(HttpServletRequest request) {
		System.out.println( "main() 호출" );
	    
		
		ModelAndView modelAndView = new ModelAndView( "index" );
		return modelAndView;
	}
	
	/* login */
	@RequestMapping( "/login.do" )
	public ModelAndView login(HttpServletRequest request) {
		System.out.println( "login() 호출" );
		
		ModelAndView modelAndView = new ModelAndView( "login" );
		return modelAndView;
	}
	
	/* logout */
	@RequestMapping( "/logout.do" )
	public ModelAndView logout(HttpServletRequest request) {
		System.out.println( "logout() 호출" );
		
		int flag = 1;
		//로그아웃기능
		HttpSession httpSession = request.getSession(true); // 세션이 있으면 가져오고, 없으면 만들어라 라는 부분
		httpSession.invalidate(); // -> invalidate(); 로그인시 유지되고 있던 세션을 지워주는 부분
		
		
		ModelAndView modelAndView = new ModelAndView( "index" );
		modelAndView.addObject("flag", flag); // flag는 들고 or 안들고 들어가도 괜찮음, 로그인이 성공시 처리를 위해서 들고가는것
		
		return modelAndView;
	}
	
	/* loginAction */
	@RequestMapping( "/loginAction.do" )
	public ModelAndView loginAction(HttpServletRequest request) throws NoSuchAlgorithmException {
		System.out.println( "loginAction() 호출" );
		
		String id = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println(id);
		System.out.println(password);
		UserTO uto = new UserTO();
		
		int flag = udao.user(id, password);

		if(flag == 0) {
			uto = udao.logincheck(id);
			
			HttpSession httpSession = request.getSession(true);
			httpSession.setAttribute("user", uto); 	//"user" 키값 , value는  uto
		}
		System.out.println(uto.getNickname());
		
		ModelAndView modelAndView = new ModelAndView( "loginAction" );
		modelAndView.addObject("flag", flag );
		modelAndView.addObject("uto", uto); //uto 안에 있는 유저정보를 "uto"라는 키 값으로 주겠다
		return modelAndView;
	}
	
	/* join */
	@RequestMapping( "/join.do" )
	public ModelAndView join(HttpServletRequest request) {
		System.out.println( "join() 호출" );
		
		ModelAndView modelAndView = new ModelAndView( "join" );
		return modelAndView;
	}

	/* 이메일중복 / 닉네임 중복검사 부분 글로벌 cdn_link*/
	/* email_check */
	@RequestMapping( "/email_check" )
	public ModelAndView email_check(@RequestBody String paramData, UserTO to) throws ParseException {
		//클라이언트가 보낸 ID값
		int flag = 1;
		//System.out.println("처음 flag : " + flag);
		
		String ID = paramData.trim();
		// System.out.println(ID);
		
		to.setEmail(ID);
		flag = udao.email_check( to );
		// System.out.println("최종 flag : " + flag);
		
		ModelAndView modelAndView = new ModelAndView( "email_check" );
		modelAndView.addObject("email_check", flag);
		return modelAndView;
	}
	
	/* nickname_check */
	@RequestMapping( "/nickname_check" )
	public ModelAndView nickname_check(@RequestBody String paramData, UserTO to) throws ParseException {
		//클라이언트가 보낸 ID값
		int flag = 1;
		//System.out.println("처음 flag : " + flag);
		
		String nickname = paramData.trim();
		// System.out.println(ID);
		
		to.setNickname(nickname);
		flag = udao.nickname_check( to );
		// System.out.println("최종 flag : " + flag);
		
		ModelAndView modelAndView = new ModelAndView( "nickname_check" );
		modelAndView.addObject("nickname_check", flag);
		return modelAndView;
	}
	
	/* join_ok */
	@RequestMapping( "/join_ok.do" )
	public ModelAndView join_ok(HttpServletRequest request, UserTO to) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println( "join_ok() 호출" );
		
		
		String email = request.getParameter( "email" );
		String password = request.getParameter( "password" );
		String phone = request.getParameter( "phone1" ) + request.getParameter( "phone2" ) + request.getParameter( "phone3" );
		String nickname = request.getParameter( "nickname" );
		String[] temp = request.getParameterValues( "check_product" );
		String interest = "";
		for(int i = 0; i < temp.length; i++) {
			interest += "," +temp[i];
		}
		interest = interest.substring(1);
		
		to.setEmail( email );
		to.setPassword( password );
		to.setPhone( phone );
		to.setNickname( nickname );
		to.setInterest( interest );
		
		int flag = this.udao.join_ok(to);
		// System.out.println("flag : " + flag);
		
		
		ModelAndView modelAndView = new ModelAndView( "join_ok" );
		modelAndView.addObject("join_flag", flag);
		return modelAndView;
	}
	/* nav */
	@RequestMapping( "/include/nav.do" )
	public ModelAndView nav(HttpServletRequest request) {
		System.out.println( "nav() 호출" );

		// 로그인된 회원의 정보를 가져오고 보여주는 부분  
				// 로그인 세션이 있으면 가져온다.
				HttpSession httpSession = request.getSession(true);
				System.out.println( "nav() 호출1" );
				UserTO uto =  (UserTO)httpSession.getAttribute("user"); // 로그인 정보를 가져오는것
				int flag = 1; // 기본 : 실패
					if(uto != null) {
						flag = 0; // 로그인 성공
						System.out.println("로그인이 성공했습니다. : " + flag);
					}
					
					
				//여기까지
		
		ModelAndView modelAndView = new ModelAndView( "include/nav" );
		modelAndView.addObject("uto", uto); // 유저정보를 들고가는것 , 다른페이지에서 보여줄때도 무조건 가져가야함
		// modelAndView.addObject("uto", uto); -> 이것을 무조건 가지고 들어가야함
		// 왜냐하면 uto안에 있는 "uto"라는 키값으로 회원의 정보를 가져오는 부분이라서 보여주고자 하는 페이지에 꼭필요함
		return modelAndView;
	}
	
}
