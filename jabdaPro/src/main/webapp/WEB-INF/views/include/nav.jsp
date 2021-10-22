<%@page import="com.user.UserTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	UserTO uto = (UserTO)request.getAttribute("uto");
	String username = null;
	
	if(uto != null) { //uto가 null이 아닌경우만 username 의 경우만 불러오는것
		System.out.println("닉네임: " +uto.getNickname());
		username = uto.getNickname();
		
		out.println("<script type='text/javascript'>");
		out.println("var nickname = '" + username + "';");
		out.println("</script>");
	}else{
		out.println("<script type='text/javascript'>");
		out.println("var nickname = '';");
		out.println("</script>");
	}

%>
		
<script src="./assets/js/nav.js"></script>
<div class="v_scroll"></div>
<!-- Full screen modal -->
<div class="modal fade" id="full_search" tabindex="-1" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-fullscreen">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="full_screen_label">무엇을 찾으시나요?</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form class="d-flex" action="/product_list.do" method="get" name="m_search">
					<input type="search" class="form-control me-2 input_m_search" list="m_datalistOptions" name="keyword" placeholder="브랜드 or 상품이름" aria-label="Search" />
					<input type="hidden" name="gubun" value="모니터" />
					<button class="btn" type="submit"><i class="fas fa-search" style="color:#4B5AB4;"></i></button>
				</form>
				<div id="m_datalistOptions"></div>
			</div>
		</div>
	</div>
</div>
<header class="p-3 nav_bar text-white">
	<div class="container">
		<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			<a href="/main.do" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none logo">
				ITMI
			</a>
			<span class="media"><a href="./login.do"><button type="button" class="btn btn-outline-light me-2">로그인</button></a></span>
			<span class="media"><a href="./join.do"><button type="button" class="btn btn-warning">회원가입</button></a></span>
			<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
				<li><a href="/guide.do" class="nav-link px-2 text-white">구매가이드</a></li>
				<li><a href="/product_list.do?gubun=모니터&keyword=" class="nav-link px-2 text-white">스토어</a></li>
				<li><a href="/submit_list.do" class="nav-link px-2 text-white">고객센터</a></li>
				<li><button class="btn_search_open" data-bs-toggle="modal" data-bs-target="#full_search"><i class="fas fa-search"></i></button></li>
			</ul>
			<form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" action="/product_list.do" method="get" >
				<input type="search" class="form-control form-control-dark nav_search" list="datalistOptions" name="keyword" placeholder="검색어를 입력해주세요" aria-label="Search">
				<input type="hidden" name="gubun" value="모니터" />
				<datalist id="datalistOptions">
					
				</datalist>
			</form>
			<div class="text-end full_media">
				<%
				System.out.println(username);
				if(username != null) { // username 이 null이 아닐때만 출력해주는 부분
					out.println(username + "님 환영합니다");
					out.println("<a href='./logout.do'><button type='button' class='btn btn-outline-light me-2' id='loginbox'>로그아웃</button></a>");
				
				} else {
					out.println("<a href='./login.do'><button type='button' class='btn btn-outline-light me-2' id='loginbox'>로그인</button></a>");
					out.println("<a href='./join.do'><button type='button' class='btn btn-warning'>회원가입</button></a>");
					
				}
				
// 				username + "님 환영합니다"
				
				

				%>

				
<!-- 			<a href="./login.do"><button type="button" class="btn btn-outline-light me-2" id="loginbox">로그인</button></a> -->
				
				
				
			</div>
		</div>
	</div>
</header>