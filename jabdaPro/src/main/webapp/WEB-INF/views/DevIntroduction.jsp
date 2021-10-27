<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath(); %>    
<!doctype html>
<html lang="en">
  <head>
  	<title>MainPage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<jsp:include page="/include/css.do"/>
    
    <link rel="stylesheet" href="<%=rootPath %>/assets/css/devstyle.css">
  </head>
  <body>
		
		<div class="wrapper d-flex align-items-stretch">
		<jsp:include page="/include/sidebar.do"/>
		
        <!-- Page Content  -->
      <div id="content" class="p-4 p-md-5 pt-5">
        <div class="developer-logo">
            <div class="dev-logo">
              <div class="jabda-img" style="background-image: url(<%=rootPath %>/assets/img/develogo.png);"></div>
            </div>
        </div>
        <div class="dev-wrap">
            
        </div>
      </div>

    </div>

    <jsp:include page="/include/js.do"/>
  </body>
</html>