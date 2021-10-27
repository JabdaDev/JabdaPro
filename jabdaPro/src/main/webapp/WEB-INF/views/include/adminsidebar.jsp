<%@page import="com.user.UserTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath(); %>    

<%

	UserTO uto = (UserTO)request.getAttribute("uto");
	String username = null;
	String rank = null;
	
	if(uto != null) { //uto가 null이 아닌경우만 username 의 경우만 불러오는것
		System.out.println("닉네임: " +uto.getNickname());
		username = uto.getNickname();
		rank = uto.getRank();
		
		out.println("<script type='text/javascript'>");
		out.println("var nickname = '" + username + "';");
		out.println("</script>");
	}else{
		out.println("<script type='text/javascript'>");
		out.println("var nickname = '';");
		out.println("</script>");
	}

%>
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
            <a href="main.do"><span class="fa fa-home mr-3"></span> Admin Home </a>
          </li>
          <li>
              <a href="usermanager.do"><span class="fa fa-user-circle mr-3 notif"></span> User Manager </a>
          </li>
          <li>
            <a href="addtest.do"><span class="fa fa-gift mr-3"></span> Test Manager </a>
          </li>
          <li>
            <a href="addevent.do"><span class="fa fa-gift mr-3"></span> Event Manager </a>
          </li>
          <li>
            <a href="adminqna.do"><span class="fa fa-cog mr-3"></span> QnA Manager </a>
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
        <footer>

        </footer>
    </nav>