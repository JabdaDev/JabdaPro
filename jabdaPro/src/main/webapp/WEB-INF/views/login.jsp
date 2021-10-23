<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!doctype html>
<html lang="en">
  <head>
  	<title>MainPage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/login.css">
    
  </head>
  <body>
		
		<div class="wrapper d-flex align-items-stretch">
			<nav id="sidebar">
				<div class="custom-menu">
					<button type="button" id="sidebarCollapse" class="btn btn-primary">
	        </button>
        </div>
	  		<div class="img bg-wrap text-center py-4" style="background-image: url(img/sidebarpic.jpg);">
	  			<div class="user-logo">
	  				<div class="img" style="background-image: url(img/allbackground.png)"></div>
	  				<h3>반갑습니다!</h3>
	  			</div>
	  		</div>
        <ul class="list-unstyled components mb-5">
          <li class="active">
            <a href="index.html"><span class="fa fa-home mr-3"></span> Home</a>
          </li>
          <li>
              <a href="community.html"><span class="fa fa-comments mr-3 notif"><small class="d-flex align-items-center justify-content-center">5</small></span> Community(커뮤니티)</a>
          </li>
          <li>
            <a href="event.html"><span class="fa fa-gift mr-3"></span> Event(이벤트) </a>
          </li>
          <li>
            <a href="testrank.html"><span class="fa fa-trophy mr-3"></span> Top Test </a>
          </li>
          <li>
            <a href="#"><span class="fa fa-cog mr-3"></span> QnA(고객센터) </a>
          </li>
          <li>
            <a href="#"><span class="fa fa-support mr-3"></span> 개발자소개(잡다한) </a>
          </li>
          <li>
            <a href="#"><span class="fa fa-sign-out mr-3"></span> Login </a>
          </li>
        </ul>
        <footer>

        </footer>
    	</nav>

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
                <img src="img/fb.png" alt="facebook">
                <img src="img/tw.png" alt="twitter">
                <img src="img/gl.png" alt="google">
              </div>
              <form id="login" action="" class="login-input-group">
                <input type="text" class="login-input-field" placeholder="User name or Email" required>
                <input type="password" class="login-input-field" placeholder="Enter Password" required>
                <input type="checkbox" class="checkbox"><spanL>Remember Password</spanL>
                <button class="submit">Login</button>
              </form>
              <form id="register" action="" class="login-input-group">
                <input type="text" class="login-input-field" placeholder="User name or Email" required>
                <input type="email" class="login-input-field" placeholder="Your Email" required>
                <input type="password" class="login-input-field" placeholder="Enter Password" required>
                <input type="checkbox" class="checkbox"><spanL>Terms and conditions</spanL>
                <button type="button" class="submit">REGISTER</button>
              </form>
          </div>
        </div>  
      </div>
    

    </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
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