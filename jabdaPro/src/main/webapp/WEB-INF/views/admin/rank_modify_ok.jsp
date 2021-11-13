<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int flag = (Integer)request.getAttribute( "flag" );
	String email = (String)request.getParameter("email");

	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		out.println( "alert( '변경이 완료되었습니다..' );" );
		out.println( "location.href='adminuser.do';" );	
	} else {
		out.println( "alert( '변경 실패하였습니다.' );" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );
%>