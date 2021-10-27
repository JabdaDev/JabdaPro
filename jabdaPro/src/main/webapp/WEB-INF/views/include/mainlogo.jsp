<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String rootPath = request.getContextPath(); %>
	<div class="jabda-slider">
        <div class="text-center">
            <h3 class="jabda-slider-logo">
                <a href="main.do">
                    <img src="<%=rootPath %>/assets/img/mainlogo.png" alt="">
                </a>
            </h3>
            <!-- search button -->
            <div class="jabda-group mb-3">
                <form action="search.do" method="POST">
                    <div>
                        <input type="text" class="form-control" id="search-box" placeholder="잡다한 테스트 검색하기..." aria-label="Search" aria-describedby="button-addon2">
                        <!-- <button class="btn btn-outline-warning" type="submit" id="btn-search"><i class="bi bi-search"></i></button> -->
                    </div>
                </form> 
            </div>
            <div class="jabda-group mb-3 sns-icon">
                <ul>
                    <li><a id="btnTwitter" class="btn btn-icon" style="background-color: #ac2bac69;" href="javascript:shareTwitter();" role="button"><i class="fa fa-instagram"></i></a></li>
                    <li><a id="btnFacebook" class="btn btn-icon" style="background-color: rgb(13 110 253 / 45%)" href="javascript:shareTwitter();" role="button"><i class="fa fa-twitter"></i></a></li>
                    <li><a id="btnInsta" class="btn btn-icon" style="background-color: rgb(13 110 253 / 25%)" href="javascript:shareInsta();"  role="button"><i class="fa fa-facebook-square"></i></a></li>
                </ul>
            </div>
          </div>
        </div>