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
    
    <link rel="stylesheet" href="<%=rootPath %>/assets/css/login.css">
    
  </head>
  <body>
		
		<div class="wrapper d-flex align-items-stretch">
		<jsp:include page="/include/sidebar.do"/>	

        <!-- Page Content -->
      <div id="content" class="p-4 p-md-5 pt-5">
        <div class="login-wrap">
          <div class="login-form-wrap">
              <div class="login-button-wrap">
                <div id="login-btn"></div>
                <div class="login-register">
                  <a type="button" class="login-togglebtn" onclick="login()">LOG IN</a>
                  <a type="button" class="login-togglebtn" onclick="register()">REGISTER</a>
                </div>  
              </div>
              <div class="social-icons">
                <img src="<%=rootPath %>/assets/img/fb.png" alt="facebook">
                <img src="<%=rootPath %>/assets/img/tw.png" alt="twitter">
                <img src="<%=rootPath %>/assets/img/gl.png" alt="google">
              </div>
              <form id="login" action="loginAction.do" class="login-input-group" method="post">
                <input type="text" class="login-input-field" placeholder="User name or Email" id="email" name="email" required>
                <input type="password" class="login-input-field" placeholder="Enter Password" id="password" name="password" required>
                <input type="checkbox" class="checkbox"><spanL>Remember Password</spanL>
                <button class="submit">Login</button>
              </form>
              <form id="register" action="./join_ok.do" class="login-input-group" method="get">
                <input type="text" class="login-input-field" placeholder="User name or Email" id="nickname" name="nickname" required>
                <input type="email" class="login-input-field" placeholder="Your Email" id="email" name="email" required>
                <input type="password" class="login-input-field" placeholder="Enter Password" id="password" name="password" required>
                <input type="checkbox" class="checkbox"><spanL>Terms and conditions</spanL>
                <button class="submit">REGISTER</button>
              </form>
          </div>
        </div>  
      </div>
    

    </div>

    <jsp:include page="/include/js.do"/>
    <script>
      var x = document.getElementById("login");
      var y = document.getElementById("register");
      var z = document.getElementById("login-btn");
      
      
      function login(){
          x.style.left = "50px";
          y.style.left = "450px";
          z.style.left = "0";
      }

      function register(){
          x.style.left = "-400px";
          y.style.left = "50px";
          z.style.left = "110px";
      }
  </script>
  </body>
</html>