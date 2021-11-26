<%@page import="com.user.UserTO"%>
<%@page import="com.test.testTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath(); %>

<%
	ArrayList<UserTO> datas = (ArrayList)request.getAttribute("user_list");
		

	StringBuilder user = new StringBuilder();
	
	for( UserTO to : datas) {
		String seq = to.getSeq();
		String nickname = to.getNickname();
		String email = to.getEmail();
		String rank = to.getRank();
		String date = to.getDate();
		
		user.append("<tr>");
		user.append("	<td>" + seq + "</td>");
		user.append("	<td>" + nickname + "</td>");
		user.append("	<td>" + email + "</td>");
		user.append("	<td>" + date + "</td>");
		user.append("<td>");
		user.append("	<a type='button' href='member_modify.do?email = " + email +"' class='btn btn-outline-primary'>수정</a> ");
		user.append("</td>");
		user.append("<tr>");	
	}
%>
   
<!doctype html>
<html lang="ko">
  <head>
  	<title>MainPage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<jsp:include page="/include/css.do"/>
	<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />

  </head>
  <body>
		
		<div class="wrapper d-flex align-items-stretch">
		<jsp:include page="/include/adminsidebar.do"/>

        <!-- Page Content  -->
      	<div id="content" class="p-4 p-md-5 pt-5">
        
        <div class="row">
       		<div class="col-xl-12">
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-chart-bar me-1"></i>
                    유저관리
                </div>
                <div class="card-body">
                  <table id="UsertablesSimple">
                    <thead>
                      <tr>
                      	<th>Num</th>
                        <th>Nickname</th>
                        <th>Email(ID)</th>
                        <th>Join Day</th>
                        <th>기능</th>
                      </tr>
                    </thead>
                    
                    <tbody>
                      <%=user %>
                    </tbody>
                  </table>
                </div>
            </div>
        </div> 
        </div>
        
        
       
       
		        

      </div>
    

    </div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
	<script src="<%=rootPath %>/assets/js/datatables.js"></script>
    <jsp:include page="/include/js.do"/>
  </body>
</html>