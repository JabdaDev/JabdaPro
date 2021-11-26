<%@page import="com.user.UserTO"%>
<%@page import="com.test.testTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath(); %>

<% 
	UserTO to = (UserTO)request.getAttribute("to");
	
	String email = request.getParameter("email");
	String nickname = to.getNickname();
	String date = to.getDate();
	String rank = to.getRank();
%>
   
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
        
        <div class="container-xl px-4 mt-4">
                        <nav class="nav nav-borders">
                            <a class="nav-link active ms-0">MemberEdit</a>
                        </nav>
                        <hr class="mt-0 mb-4">
                        <div class="row gx-4">
                            <div class="col-xl-12">
                                <div class="card mb-4">
                                    <div class="card-header">회원수정</div>
                                    <div class="card-body">
                                        <form novalidate action="./modify_ok.do" method="post" name="medit" class="ng-untouched ng-pristine ng-valid">
                                            <div class="mb-3">
                                                <label for="inputnickname" class="small mb-1">닉네임</label>
                                                <input id="inputnickname" name="nickname" type="text" placeholder="Nickname" value="<%=nickname %>" class="form-control">
                                            </div>
                                            <div class="row">
                                                <div class="mb-3 col-md-6">
                                                    <label for="inputemail" class="small mb-1">이메일(ID)</label>
                                                    <input id="inputemail" name="email" type="text" placeholder="Email(ID)" value="<%=email %>" class="form-control">
                                                </div>
                                                <div class="mb-3 col-md-6">
                                                    <label for="inputinterest" class="small mb-1">등급</label>
                                                    <input id="inputinterest" name="interest" type="text" placeholder="interest" value="" class="form-control">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="mb-3 col-md-12">
                                                    <label for="inputdate" class="small mb-1">가입날짜</label>
                                                    <input id="inputdate" type="text" placeholder="date" value="<%=date %>" class="form-control">
                                                </div>
                                            </div>
                                            <button type="button" id="btnedit" class="btn btn-primary">Save</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
        
        
        
		        

      </div>
    

    </div>

    <jsp:include page="/include/js.do"/>
  </body>
</html>