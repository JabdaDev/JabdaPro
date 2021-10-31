<%@page import="com.test.testTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath(); %>
   
<!doctype html>
<html lang="ko">
  <head>
  	<title>MainPage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<jsp:include page="/include/css.do"/>
	

  </head>
  <body>
		
		<div class="wrapper d-flex align-items-stretch">
		<jsp:include page="/include/adminsidebar.do"/>

        <!-- Page Content  -->
      	<div id="content" class="p-4 p-md-5 pt-5">
        
        <!-- slider_area_start -->
      	<jsp:include page="/include/mainlogo.do"/>
        <!-- slider_area_end -->
        
       
		        

      </div>
    

    </div>

    <jsp:include page="/include/js.do"/>
  </body>
</html>