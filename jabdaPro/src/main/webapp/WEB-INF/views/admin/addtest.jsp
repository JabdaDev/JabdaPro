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
		<jsp:include page="/include/sidebar.do"/>

        <!-- Page Content  -->
      	<div id="content" class="p-4 p-md-5 pt-5">
        
        <!-- slider_area_start -->
      	<jsp:include page="/include/mainlogo.do"/>
        <!-- slider_area_end -->
        
        <div class="test-content">
          <div class="text-center">
            <div class="test-detail mb-4">
              <table class="table">
                <thead>
                  <td>Image</td>
                  <td>TestName</td>
                  <td>개발자</td>
                  <td>조회수</td>
                  <td>바로가기</td>
                </thead>
                <tbody class="jabda-test-list">
                  <tr>
                    <td><img src="/img/test.png"></td>
                    <td>내 손은 이쁜 손일까?</td>
                    <td>잡다한 개발자</td>
                    <td></td>
                    <td><a type="button" href="testOne.html" class="btn btn-outline-primary"> Go </a></td>
                  </tr>
                  <tr>
                    <td><img src="/img/test.png"></td>
                    <td>간단한 MBTI</td>
                    <td>잡다한 개발자</td>
                    <td></td>
                    <td><a type="button" href="testTwo.html" class="btn btn-outline-primary"> Go </a></td>
                  </tr>
                </tbody>
              </table> 
            </div>
          </div>
        </div>
		        

      </div>
    

    </div>

    <jsp:include page="/include/js.do"/>
  </body>
</html>