<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    int flag = (Integer)request.getAttribute( "join_flag" );
    
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		out.println( "alert( '회원가입 성공' );" );
		out.println( "location.href='./main.do';" );
	} else {
		out.println( "alert( '회원가입 실패' );" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );
    
    %>
