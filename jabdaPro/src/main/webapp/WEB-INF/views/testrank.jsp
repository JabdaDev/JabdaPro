<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath(); %>    
<!doctype html>
<html lang="ko">
  <head>
  	<title>MainPage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <jsp:include page="/include/css.do"/>
    <link rel="stylesheet" href="css/testrank.css">
  </head>
  
  <body>
		<div class="wrapper d-flex align-items-stretch">
		<jsp:include page="/include/sidebar.do"/>

        <!-- Page Content  -->
      <div id="content" class="p-4 p-md-5 pt-5">
      
      <!-- Mainlogo Part -->
      
      <!-- End Mainlogo part -->
      <div class="test-content">
      	<div class="text-center">
      		<div class="card">	
	      		<div class="test-detail mb-4">
		          <table id="RanktablesSimple">
		            <thead>
		              <tr>
		                <td>순위</td>
		                <td>테스트이름</td>
		                <td>개발자</td>
		                <td>조회수</td>
		                <td>바로가기</td>
		              </tr>
		            </thead>
		            <tbody>
		              <tr>
		                <td>1</td>
		                <td>간단한 MBTI</td>
		                <td>잡다한 개발자</td>
		                <td></td>
		                <td>
		                  <button class="btn btn-outline-primary">test</button>
		                </td>
		              </tr>
		              <tr>
		                <td>2</td>
		                <td>간단한 MBTI</td>
		                <td>잡다한 개발자</td>
		                <td></td>
		                <td>
		                  <button class="btn btn-outline-primary">test</button>
		                </td>
		              </tr>
		            </tbody>
		          </table>
	        	</div>
        	</div>
      	</div>
      </div>
        
      </div>

    </div>

    <jsp:include page="/include/js.do"/>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="<%=rootPath %>/assets/js/datatables.js"></script>
  </body>
</html>