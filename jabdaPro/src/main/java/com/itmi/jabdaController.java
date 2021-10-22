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

import com.product.ProductDAO;
import com.product.ProductTO;
import com.review.HeartTO;
import com.review.ReviewDAO;
import com.review.ReviewTO;

import com.submit.SubmitDAO;
import com.submit.SubmitPagingTO;
import com.submit.SubmitTO;

import com.user.UserTO;
import com.user.UserDAO;

@Controller
public class jabdaController {
	
	@Autowired
	UserDAO dao;
	@Autowired
	private DataSource dataSource;
	
	
	
	/* main */
	@RequestMapping( {"main.do", "/"} )
	public ModelAndView main(HttpServletRequest request) {
		System.out.println( "main() 호출" );
	    
		ProductTO pdto = new ProductTO();
		
		ArrayList<ProductTO> viewmonitor = productDAO.viewmonitor();
		ArrayList<ProductTO> viewnotebook = productDAO.viewnotebook();
		
		ModelAndView modelAndView = new ModelAndView( "index" );
		modelAndView.addObject( "viewmonitor", viewmonitor );
		modelAndView.addObject( "viewnotebook", viewnotebook );	
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
		
		ProductTO pdto = new ProductTO();
		
		ArrayList<ProductTO> viewmonitor = productDAO.viewmonitor();
		ArrayList<ProductTO> viewnotebook = productDAO.viewnotebook();
		
		int flag = 1;
		//로그아웃기능
		HttpSession httpSession = request.getSession(true); // 세션이 있으면 가져오고, 없으면 만들어라 라는 부분
		httpSession.invalidate(); // -> invalidate(); 로그인시 유지되고 있던 세션을 지워주는 부분
		
		
		ModelAndView modelAndView = new ModelAndView( "index" );
		modelAndView.addObject("flag", flag); // flag는 들고 or 안들고 들어가도 괜찮음, 로그인이 성공시 처리를 위해서 들고가는것
		modelAndView.addObject( "viewmonitor", viewmonitor );
		modelAndView.addObject( "viewnotebook", viewnotebook );	
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
	
	/* product_list */
	@RequestMapping( "/product_list.do" )
	public ModelAndView product_list(HttpServletRequest request) {
		System.out.println( "product_list() 호출" );
		ModelAndView modelAndView = new ModelAndView( "product_list" );
		ArrayList<ProductTO> datas = null;
		ArrayList<ProductTO> brands = null;
		ArrayList<ProductTO> resolutions = null;
		ArrayList<ProductTO> sizes = null;
		ArrayList<ProductTO> navs = null;
		
		if( request.getParameter("keyword").equals("") || request.getParameter("keyword") == null || request.getParameter("gubun").equals("모니터")) {
			navs = navDAO.nav_search_product( request.getParameter( "keyword" ) );
		}else {
			datas = productDAO.productListAll( request.getParameter( "gubun" ) );
			
			
			modelAndView.addObject( "product_list", datas );
		}
		brands = productDAO.brandList( request.getParameter( "gubun" ) );
		resolutions = productDAO.resolutionList( request.getParameter( "gubun" ) );
		sizes = productDAO.sizeList( request.getParameter( "gubun" ) );
		
		modelAndView.addObject( "nav_search", navs );
		
		modelAndView.addObject( "product_brands", brands );
		modelAndView.addObject( "product_resolutions", resolutions );
		modelAndView.addObject( "product_sizes", sizes );
		return modelAndView;
	}
	
	/* product_detail */
	   @RequestMapping( "/product_detail.do" )
	   public ModelAndView product_detail(HttpServletRequest req) {
	      System.out.println( "product_detail() 호출" );
	   
	      String gubun = req.getParameter("gubun");
	      String seq = req.getParameter("seq");
	      
	      ArrayList<ProductTO> datas = productDAO.productDetailList(gubun, seq);
	      
	      ModelAndView modelAndView = new ModelAndView( "product_detail" );
	      modelAndView.addObject("product_detail", datas);
	      return modelAndView;
	   }
	   
	   
	   /* review_list */
	   @RequestMapping( "/review_list.json" )
	   public ModelAndView review_list(HttpServletRequest req) {
	      System.out.println( "review_list() 호출" );
	      ReviewDAO pto = new ReviewDAO();
	      ArrayList<ReviewTO> datas = pdao.review_list(req.getParameter("productname")); //2단계 처리할것들 처리하고
	         
	      ModelAndView modelAndView = new ModelAndView( "/json/review_list" );
	      modelAndView.addObject("datas", datas); // datas 라는 value값 "datas"는 key값 , datas를 "datas" object add해준것 -> test.jsp로 보내진다
	      return modelAndView;
	   }
	
	/* sort */
	@RequestMapping( "/sort.json" )
	public ModelAndView sort(HttpServletRequest request, UserTO to) throws ParseException {
		String brand = request.getParameter("brand");
		String size = request.getParameter("size");
		String gubun = request.getParameter("gubun");
		String click_gubun = request.getParameter("click_gubun");

		// System.out.println( gubun );
		ArrayList<ProductTO> datas = null;
		ModelAndView modelAndView = null;
		
		if( click_gubun.equals("낮은 가격순") ) {
			datas = productDAO.if_search( gubun, brand, size, click_gubun );
			modelAndView = new ModelAndView( "/json/sort" );
			modelAndView.addObject( "price", datas );
		}else if( click_gubun.equals("높은 가격순") ) {
			datas = productDAO.if_search( gubun, brand, size, click_gubun );
			modelAndView = new ModelAndView( "/json/sort" );
			modelAndView.addObject( "price", datas );
		}else if( click_gubun.equals("브랜드순") ) {
			datas = productDAO.if_search( gubun, brand, size, click_gubun );
			modelAndView = new ModelAndView( "/json/sort" );
			modelAndView.addObject( "price", datas );
		}else if( click_gubun.equals("전체") ) {
			datas = productDAO.productListAll( gubun );
			modelAndView = new ModelAndView( "/json/sort" );
			modelAndView.addObject( "price", datas );
		}
		return modelAndView;
	}
	
	/* brand_list */
	@RequestMapping( "/brand_list.json" )
	public ModelAndView brand_list(HttpServletRequest request, UserTO to) throws ParseException {
		String gubun = request.getParameter("gubun");
		if( gubun.equals("노트북") ) {
			gubun = "notebook";
		}else if( gubun.equals("모니터") ) {
			gubun = "monitor";
		}else if( gubun.equals("스마트폰") ) {
			gubun = "smartphone";
		}
		// System.out.println( gubun );
		
		ArrayList<ProductTO> brands = productDAO.brandList( gubun );
		
		ModelAndView modelAndView = new ModelAndView( "/json/brand_list" );
		modelAndView.addObject( "brands", brands );
		return modelAndView;
	}
	
	/* size_list */
	@RequestMapping( "/size_list.json" )
	public ModelAndView size_list(HttpServletRequest request, UserTO to) throws ParseException {
		String gubun = request.getParameter("gubun");
		if( gubun.equals("노트북") ) {
			gubun = "notebook";
		}else if( gubun.equals("모니터") ) {
			gubun = "monitor";
		}else if( gubun.equals("스마트폰") ) {
			gubun = "smartphone";
		}
		// System.out.println( gubun );
		
		ArrayList<ProductTO> sizes = productDAO.sizeList( gubun );
		
		ModelAndView modelAndView = new ModelAndView( "/json/size_list" );
		modelAndView.addObject( "sizes", sizes );
		return modelAndView;
	}
	
	
	/*product_search*/
	@RequestMapping( "/product_search" )
	public ModelAndView product_search(HttpServletRequest request, UserTO to) throws ParseException {
		String gubun = request.getParameter("gubun");
		String brand = request.getParameter("brand");
		String size = request.getParameter("size");
		
		ArrayList<ProductTO> datas = productDAO.productList_search( gubun, brand, size );
		// System.out.println(datas);
		ModelAndView modelAndView = new ModelAndView( "/json/product_search" );
		modelAndView.addObject( "product_search", datas );
		return modelAndView;
	}
	
	/* review_write */
	@RequestMapping( "/review_write.do" )
	public ModelAndView review_write(HttpServletRequest request, UserTO to) throws ParseException {
		System.out.println( "review_write() 호출" );
		
		ModelAndView modelAndView = new ModelAndView( "/review_write" );
		return modelAndView;
	}
	
	/* review_modify */
	@RequestMapping( "/review_modify.do" )
	public ModelAndView review_modify(HttpServletRequest req, UserTO to) throws ParseException {
		System.out.println( "review_modify() 호출" );
		
		ArrayList<ReviewTO> datas = pdao.review_modify( req.getParameter("seq") );
		
		
		ModelAndView modelAndView = new ModelAndView( "/review_modify" );
		modelAndView.addObject( "review_modify", datas );
		return modelAndView;
	}
	
	/* review_modify_ok */
	@RequestMapping( "/review_modify_ok.do" )
	public ModelAndView review_modify_ok(HttpServletRequest request, ReviewTO to) {
		System.out.println( "review_modify_ok() 호출" );
		
		
		String subject = request.getParameter( "subject" );
		String nickname = request.getParameter( "nickname" );
		String productname = request.getParameter( "productname" );
		String merit = request.getParameter( "merit" );
		String demerit = request.getParameter( "demerit" );
		String image1 = "";
		String image2 = "";
		String image3 = "";
		// String result = fileDAO.file_delete(request.getParameter("reviewSeq"), nickname);
		if( request.getParameter( "fileUpload1" ) == null || request.getParameter( "fileUpload1" ).equals("") ) {}
		else{
			image1 = request.getParameter( "fileUpload1" );
			String ex = image1.substring(image1.lastIndexOf("."), image1.length() );
			image1 = image1.substring(0, image1.lastIndexOf(".")) +"_"+productname + ex;
		}
		if( request.getParameter( "fileUpload2" ) == null || request.getParameter( "fileUpload2" ).equals("") ) {}
		else{
			image2 = request.getParameter( "fileUpload2" );
			String ex = image2.substring(image2.lastIndexOf("."), image2.length() );
			image2 = image2.substring(0, image2.lastIndexOf(".")) +"_"+productname + ex;
		}
		if( request.getParameter( "fileUpload3" ) == null || request.getParameter( "fileUpload3" ).equals("") ) {}
		else {
			image3 = request.getParameter( "fileUpload3" );
			String ex = image3.substring(image3.lastIndexOf("."), image3.length() );
			image3 = image3.substring(0, image3.lastIndexOf(".")) +"_"+productname + ex;
		}
		
		String convenience = request.getParameter( "convenience" );
		String function = request.getParameter("function");
		System.out.println("작성중인 리뷰 Seq는?" + request.getParameter("reviewSeq"));
		to.setSeq( request.getParameter("reviewSeq") );
		to.setSubject( subject );
		to.setNickname( nickname );
		to.setProductname( productname );
		to.setMerit( merit );
		to.setDemerit( demerit );
		to.setImage1( image1 );
		to.setImage2( image2 );
		to.setImage3( image3 );
		to.setConvenience( convenience );
		to.setFunction( function );
		
		int flag = this.pdao.review_modify_ok( to );
		// System.out.println("flag : " + flag);
		System.out.println("플래그는?"+flag);
		
		ModelAndView modelAndView = new ModelAndView( "review_modify_ok" );
		modelAndView.addObject("review_ok", flag);
		return modelAndView;
	}
	
	/* 이미지 업로드시 필요 */
	@RequestMapping( "ajaxUpload.do" )
	public ModelAndView ajaxUpload(HttpServletRequest request) {
		System.out.println( "ajaxUpload() 호출" );
		
		ModelAndView modelAndView = new ModelAndView( "ajaxUpload" );
		return modelAndView;
	}
	
	/* 파일 삭제 */
	@RequestMapping( "/fileDelete" )
	public void fileDelete(HttpServletRequest req){
		String image1 = req.getParameter("image1");
		String image2 = req.getParameter("image2");
		String image3 = req.getParameter("image3");
		
		System.out.println("넘어온 이미지이름 : " + req.getParameter("image1"));
		System.out.println("넘어온 이미지이름 : " + req.getParameter("image2"));
		System.out.println("넘어온 이미지이름 : " + req.getParameter("image3"));
		String result = fileDAO.file_delete(req.getParameter("seq"), req.getParameter("nickname"), image1, image2, image3 );
    }
	
	/* 파일 삭제 */
	@RequestMapping( "/reviewDelete" )
	public ModelAndView reviewfileDelete(HttpServletRequest req, ReviewTO to){
		String result = fileDAO.review_file_delete(req.getParameter("seq"));
		to.setSeq(req.getParameter("seq"));
		int flag = pdao.review_delete_ok(to);
		
		ModelAndView modelAndView = new ModelAndView( "review_delete_ok" );
		modelAndView.addObject("review_delete_ok", flag);
		return modelAndView;
    }
	
	/* 업로드시 필요 */
	@RequestMapping(value = "/fileUpload", method=RequestMethod.POST )
	public void fileUp(MultipartHttpServletRequest multi, ReviewTO to){
		String result = fileDAO.file_upload(multi);	
    }
	
	/* review_write_ok */
	@RequestMapping( "/review_write_ok.do" )
	public ModelAndView review_write_ok(HttpServletRequest request, ReviewTO to) {
		System.out.println( "review_write_ok() 호출" );
		System.out.println( request.getParameter( "fileUpload1" ) );
		String subject = request.getParameter( "subject" );
		String nickname = request.getParameter( "nickname" );
		String productname = request.getParameter( "productname" );
		String merit = request.getParameter( "merit" );
		String demerit = request.getParameter( "demerit" );
		String image1 = "";
		String image2 = "";
		String image3 = "";
		
		if(request.getParameter( "fileUpload1" ) == null || request.getParameter( "fileUpload1" ).equals("") ) {}
		else {
			image1 = request.getParameter( "fileUpload1" );
			String ex = image1.substring(image1.lastIndexOf("."), image1.length() );
			image1 = image1.substring(0, image1.lastIndexOf(".")) +"_"+productname + ex;
		}
		if(request.getParameter( "fileUpload2" ) == null || request.getParameter( "fileUpload2" ).equals("") ) {}
		else {
			image2 = request.getParameter( "fileUpload2" );
			String ex = image2.substring(image2.lastIndexOf("."), image2.length() );
			image2 = image2.substring(0, image2.lastIndexOf(".")) +"_"+productname + ex;
		}
		if(request.getParameter( "fileUpload3" ) == null || request.getParameter( "fileUpload3" ).equals("") ) {}
		else {
			image3 = request.getParameter( "fileUpload3" );
			String ex = image3.substring(image3.lastIndexOf("."), image3.length() );
			image3 = image3.substring(0, image3.lastIndexOf(".")) +"_"+productname + ex;
		}
		String convenience = request.getParameter( "convenience" );
		String function = request.getParameter("function");
		
		to.setSubject( subject );
		to.setNickname( nickname );
		to.setProductname( productname );
		to.setMerit( merit );
		to.setDemerit( demerit );
		to.setImage1( image1 );
		to.setImage2( image2 );
		to.setImage3( image3 );
		to.setConvenience( convenience );
		to.setFunction( function );
		
		int flag = this.pdao.review_write_ok( to );
		// System.out.println("flag : " + flag);
		
		
		ModelAndView modelAndView = new ModelAndView( "review_write_ok" );
		modelAndView.addObject("review_ok", flag);
		return modelAndView;
	}
	
	/* guide */
	@RequestMapping( "/guide.do" )
	public ModelAndView guide(HttpServletRequest request) {
		System.out.println( "guide() 호출" );
		
		ModelAndView modelAndView = new ModelAndView( "guide" );
		return modelAndView;
	}
	
	/* monitor_guide */
	@RequestMapping( "/monitor_guide.do" )
	public ModelAndView monitor_guide(HttpServletRequest request) {
		System.out.println( "monitor_guide() 호출" );
		
		ModelAndView modelAndView = new ModelAndView( "monitor_guide" );
		return modelAndView;
	}
	
	/* notebook_guide */
	@RequestMapping( "/notebook_guide.do" )
	public ModelAndView notebook_guide(HttpServletRequest request) {
		System.out.println( "notebook_guide() 호출" );
		
		ModelAndView modelAndView = new ModelAndView( "notebook_guide" );
		return modelAndView;
	}
	
	/* phone_guide */
	@RequestMapping( "/phone_guide.do" )
	public ModelAndView phone_guide(HttpServletRequest request) {
		System.out.println( "phone_guide() 호출" );
		
		ModelAndView modelAndView = new ModelAndView( "phone_guide" );
		return modelAndView;
	}
	
	/* submit_list.do */
	@RequestMapping("/submit_list.do")
	public ModelAndView submit_list(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("submit_list() 호출");
		
		SubmitPagingTO listTO = new SubmitPagingTO();
		listTO.setCpage(1);
		if( request.getParameter("cpage") != null && !request.getParameter("cpage").equals("")) {
			listTO.setCpage(Integer.parseInt( request.getParameter("cpage") ));
		}
		
		SubmitDAO sdao = new SubmitDAO(dataSource);
		listTO = sdao.sumitPaginglist(listTO);
		
		UserTO uto = new UserTO();
		uto.setSeq(request.getParameter("seq"));
		
		uto = sdao.submitWrite(uto);
		
		
		ModelAndView modelAndView = new ModelAndView("/submit_list");
		modelAndView.addObject("listTO", listTO);
		modelAndView.addObject("uto", uto);
		return modelAndView;
	}
	
	/* submit_view.do */
	@RequestMapping("/submit_view.do")
	public ModelAndView submit_view(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("submit_view() 호출");
		
		SubmitTO to = new SubmitTO();
		to.setSeq(request.getParameter("seq"));

		SubmitDAO sdao = new SubmitDAO(dataSource);
		to = sdao.submitView(to);
		
		ModelAndView modelAndView = new ModelAndView("submit_view");
		modelAndView.addObject("to", to);
		return modelAndView;
	}
	
	/* submit_write.do */
	@RequestMapping("/submit_write.do")
	public ModelAndView submit_write(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("submit_write 호출");
		
		HttpSession httpSession = request.getSession(true);
		UserTO uto =  (UserTO)httpSession.getAttribute("user");
		int flag = 1; // 기본 : 실패
		if(uto != null) {
			flag = 0; // 로그인 성공
			System.out.println("로그인이 성공했습니다. : " + flag);
		}
		
		ModelAndView modelAndView = new ModelAndView("submit_write");
		modelAndView.addObject("uto", uto);
		return modelAndView;
	}
	
	/*submit_write_ok.do*/
	@RequestMapping("/submit_write_ok.do")
	public ModelAndView submit_write_ok(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("submit_write_ok 호출");
		
		SubmitTO to  = new SubmitTO();
		to.setSubject(request.getParameter("subject"));
		to.setContent(request.getParameter("content"));
		to.setWriter(request.getParameter("writer"));
		
		SubmitDAO sdao = new SubmitDAO(dataSource);
		int flag = sdao.submitWriteOk(to);
		
		ModelAndView modelAndView = new ModelAndView("submit_write_ok");
		modelAndView.addObject("flag", flag);
		return modelAndView;
	}
	
	/* submit_delete.do */
	@RequestMapping("/submit_delete.do")
	public ModelAndView submit_delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("submit_delete 호출");
		
		SubmitTO to = new SubmitTO();
		to.setSeq(request.getParameter("seq"));

		SubmitDAO sdao = new SubmitDAO(dataSource);
		to = sdao.submitDelete(to);
		
		ModelAndView modelAndView = new ModelAndView("submit_delete");
		modelAndView.addObject("to", to);
		return modelAndView;
	}
	
	/* submit_delete_ok.do */
	@RequestMapping("/submit_delete_ok.do")
	public ModelAndView submit_delete_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("submit_delete_ok 호출");
		
		SubmitTO to  = new SubmitTO();
		to.setSeq(request.getParameter("seq"));
		
		SubmitDAO sdao = new SubmitDAO(dataSource);
		int flag = sdao.submitDeleteOk(to);
		
		ModelAndView modelAndView = new ModelAndView("submit_delete_ok");
		modelAndView.addObject("flag", flag);
		return modelAndView;
	}
		
	/* submit_modify */
	@RequestMapping("/submit_modify.do")
	public ModelAndView submit_modify(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("submit_modify() 호출");
		
		SubmitTO to = new SubmitTO();
		to.setSeq(request.getParameter("seq"));

		SubmitDAO sdao = new SubmitDAO(dataSource);
		to = sdao.submitView(to);
		
		ModelAndView modelAndView = new ModelAndView("submit_modify");
		modelAndView.addObject("to", to);
		return modelAndView;
	}
	
	/* submit_modify_ok */
	@RequestMapping("/submit_modify_ok.do")
	public ModelAndView submit_modify_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("submit_modify_ok() 호출");
		
		SubmitTO to = new SubmitTO();
		to.setSeq(request.getParameter("seq"));
		to.setSubject(request.getParameter("subject"));
		to.setContent(request.getParameter("content"));

		SubmitDAO sdao = new SubmitDAO(dataSource);
		int flag = sdao.submitModifyOk(to);
		
		ModelAndView modelAndView = new ModelAndView("submit_modify_ok");
		modelAndView.addObject("flag", flag);
		modelAndView.addObject("to", to);
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
		flag = dao.email_check( to );
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
		flag = dao.nickname_check( to );
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
		
		int flag = this.dao.join_ok(to);
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
	/* nav_search */
	@RequestMapping( "/nav_search.json" )
	public ModelAndView search_data(HttpServletRequest request) {
		System.out.println( "nav_search() 호출" );
		String keyword = request.getParameter("keyword");
		
		ArrayList<ProductTO> datas = navDAO.search_data( keyword );
		// System.out.println(datas);
		ModelAndView modelAndView = new ModelAndView( "/json/nav_search" );
		modelAndView.addObject( "nav_search", datas );
		return modelAndView;
	}
	
	/* global */
	@RequestMapping( "/global.do" )
	public ModelAndView global(HttpServletRequest request) {
		System.out.println( "global() 호출" );
		
		ModelAndView modelAndView = new ModelAndView( "global" );
		return modelAndView;
	}
	
	//heart_check.json
	   /* heart_check */
	   @RequestMapping( "/heart_check.json" )
	   public ModelAndView heart_check(HttpServletRequest req, HeartTO to) throws ParseException {
	      //클라이언트가 보낸 ID값
	      System.out.println( "heart_check() 호출" );
	      to.setNickname( req.getParameter("nickname") );
	      to.setReview_seq( req.getParameter("review_seq") );
	      
	      int flag = pdao.heart_cnt(to);
	      
	      ModelAndView modelAndView = new ModelAndView( "/json/heart_cnt" );
	      modelAndView.addObject("heart_cnt", flag);
	      return modelAndView;
	   }
	   
	   /* heart_up */
	   @RequestMapping( "/heart_up.json" )
	   public ModelAndView heart_up(HttpServletRequest req, ReviewTO to) throws ParseException {
	      //클라이언트가 보낸 ID값
	      System.out.println( "heart_up() 호출" );
	      to.setSeq( req.getParameter("review_seq") );
	      to.setNickname( req.getParameter("nickname") );
	      
	      int flag = pdao.heart_up(to);
	      System.out.println("증가함");
	      ModelAndView modelAndView = new ModelAndView( "/json/heart_up" );
	      modelAndView.addObject("heart_up", flag);
	      return modelAndView;
	   }
	   
	   /* heart_down */
	   @RequestMapping( "/heart_down.json" )
	   public ModelAndView heart_down(HttpServletRequest req, ReviewTO to) throws ParseException {
	      //클라이언트가 보낸 ID값
	      System.out.println( "heart_down() 호출" );
	      
	      to.setSeq( req.getParameter("review_seq") );
	      to.setNickname( req.getParameter("nickname") );
	      
	      int flag = pdao.heart_down(to);
	      System.out.println("감소함");
	      ModelAndView modelAndView = new ModelAndView( "/json/heart_down" );
	      modelAndView.addObject("heart_down", flag);
	      return modelAndView;
	   }
	
}
