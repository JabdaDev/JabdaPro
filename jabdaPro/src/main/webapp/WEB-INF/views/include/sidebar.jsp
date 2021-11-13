<%@page import="com.user.UserTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath(); %>    

<%

	UserTO uto = (UserTO)request.getAttribute("uto");
	String username = null;
	String rank = null;
	
	if(uto != null) { //uto가 null이 아닌경우만 username 의 경우만 불러오는것
		System.out.println("닉네임: " + uto.getNickname());
		System.out.println("등급: " + uto.getRank());
		rank = uto.getRank();
		username = uto.getNickname();
		
		
		
		out.println("<script type='text/javascript'>");
		out.println("var nickname = '" + username + "';");
		out.println("var rank = '" + rank + "';");
		out.println("</script>");
	}else{
		out.println("<script type='text/javascript'>");
		out.println("var nickname = '';");
		out.println("var rank = '';");
		out.println("</script>");
	}
	System.out.println(rank);
	
%>
<head>
<meta name ="google-signin-client_id" content="141540944374-oa989fab02dhp22fo6efqndj5b8mgu4s.apps.googleusercontent.com">
</head>


	<nav id="sidebar">
		<div class="custom-menu">
			<button type="button" id="sidebarCollapse" class="btn btn-primary">
	        </button>
        </div>
	  		<div class="img bg-wrap text-center py-4" style="background-image: url(<%=rootPath %>/assets/img/background.jpg);">
	  			<div class="user-logo">
	  				<div class="img" style="background-image: url(<%=rootPath%>/assets/img/allbackground.png);"></div>
	  				<%
	  					if(username != null) {
	  						out.println("<h3>" + username + " 반갑습니다.</h3>");
	  					} else{
	  						out.println("<h3>로그인 해주세요.</h3>");
	  					}
	  				%>
	  				
	  			</div>
	  		</div>
        <ul class="list-unstyled components mb-5">
          <li class="active">
            <a href="main.do"><span class="fa fa-home mr-3"></span> Home </a>
          </li>
          <li>
              <a href="community.html"><span class="fa fa-comments mr-3 notif"><small class="d-flex align-items-center justify-content-center">3</small></span> Community(커뮤니티) </a>
          </li>
          <li>
            <a href="event.html"><span class="fa fa-gift mr-3"></span> Event(이벤트) </a>
          </li>
          <li>
            <a href="testrank.do"><span class="fa fa-trophy mr-3"></span> Top Test</a>
          </li>
          <li>
            <a href="testqna.html"><span class="fa fa-cog mr-3"></span> QnA(고객센터) </a>
          </li>
          <li>
            <a href="DevIntroduction.do"><span class="fa fa-support mr-3"></span> 개발자소개(잡다한) </a>
          </li>
          <%
          	if(username != null) {
          		out.println("<li>");
          		out.println("<a href='logout.do'> <span class='fa fa-sign-out mr-3'></span> Logout </a>");
          	 	out.println("</li>");
          	} else {
          		out.println("<li>");
          		out.println("<a href='login.do'> <span class='fa fa-sign-in mr-3'></span> Login </a>");
          	 	out.println("</li>");
          	}
          %>
          <% if( rank != null ) { %>
          	<% if( "admin".equals(rank) ) { %>
	          		<li>
	            		<a href="admin.do"><span class="fa fa-support mr-3"></span> Dev Page </a>
	          		</li>
	          <% } else { %>
	          		<li>
	          			<a href="mypage.do"><span class="fa fa-support mr-3"></span> My Page </a>
	          		</li>
	          <% } %>
          <% } else { %>
	      
	      <% } %>    
        <footer>

        </footer>
    </nav>