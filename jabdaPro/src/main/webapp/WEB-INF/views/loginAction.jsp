<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.user.UserDAO" %>
<%@page import="com.user.UserTO"%>
<%@ page import="java.io.PrintWriter" %>
<%@page import="java.util.ArrayList"%>
<%
request.setCharacterEncoding("UTF-8");
%>


<jsp:useBean id="user" class="com.user.UserTO" scope="page" />
<jsp:setProperty name="user" property="email" />
<jsp:setProperty name="user" property="password" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Insert title here</title>
</head>
<body>

<%
	UserDAO userDAO = new UserDAO();
// 	int result = userDAO.login(user.getUserID(), user.getUserPassword());
	int result = (Integer)request.getAttribute("flag");

    //회원 정보를 불러오는 부분
    
    UserTO uto = (UserTO)request.getAttribute("uto");
    String username = uto.getNickname();

	if(result == 0) {
		PrintWriter script = response.getWriter();
		out.println("<script>");
		out.println("alert('"+ username +"님 환영합니다.')");
		out.println("location.href='/main.do'");
		out.println("</script>");
		
	}
	else if (result == 1 ) {
		PrintWriter script = response.getWriter();
		out.println("<script>");
		out.println("alert('비밀번호가 틀립니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
	else if (result == -1 ) {
		PrintWriter script = response.getWriter();
		out.println("<script>");
		out.println("alert('존재하지 않는 아이디 입니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
	else if (result == -2 ) {
		PrintWriter script = response.getWriter();
		out.println("<script>");
		out.println("alert('데이터베이스 오류가 발생했습니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
%>

</body>
</html>