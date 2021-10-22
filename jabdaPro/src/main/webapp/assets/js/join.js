"use strict";
$(document).ready(function(){
	
	// VS code로 작업할때 nav.html 페이지에 추가하는 법
	/*$.get("./include/nav.html", function(data){
	    $('body').prepend($(data).fadeIn());
	});*/
	$( '#email' ).focusout( function(){
		$('.email_check').css('display', 'inline-block');
	});
	$('.mail_auth').on('click', function(){
		if( $( '.email > .fail_text' ).text() == '사용가능' && $('#email').val().trim() && /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/.exec( $('#email').val().trim() ) ){
			alert('메일이 전송되었습니다.');
			$.ajax({
				url: "/mail_auto.json",
				type: 'get',
				dataType: 'json', //서버로부터 내가 받는 데이터의 타입
				contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
				data: {
					"email": $('#email').val()
				} ,
				
				success: function(data){
					console.log( data ); 
					
				},
				error: function ( err ){
				    console.log( err );              
				}
			});
			$( '.mail_check' ).css('display', 'inline-block');
		} else {
			alert('메일형식이 잘못되었습니다.');
		}
		
	});
	
	$('.mail_check').on('click', function(){
		mail_check();
	});
	function mail_check(){
		if( $('#email').val().trim() && /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/.exec( $('#email').val().trim() ) ){
			$.ajax({
				url: "/auth_check.json",
				type: 'get',
				dataType: 'text', //서버로부터 내가 받는 데이터의 타입
				contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
				data: {
					"email": $('#email').val(),
					"auth_key": $('#email_auth').val()
				} ,
				
				success: function(data){
				if( data.trim() == '실패' ){
					alert('이메일이나 인증번호가 잘못입력되었습니다.');
				}else if( data.trim() == '성공' ){
					$('.email_check > .fail_text').text("인증 되었습니다.").css( 'color', 'green');
				}
					
				},
				error: function ( err ){
				    console.log( err, "오류" );            
				}
			});
		}else{
			alert('이메일 형식이 아닙니다.');
		}
		
	}
	function meach_password(){
		$( '#password, #repassword' ).focusout( function(){
			if( $( '#password' ).val() || $( '#repassword' ).val() ){
				if( $( '#password' ).val() == $( '#repassword' ).val() ){
					$( '.password > .fail_text, .repassword > .fail_text' ).text('비밀번호 일치').css('color', 'green');
					password_ck_cnt = 1;
				}else{
					$( '.password > .fail_text, .repassword > .fail_text' ).text('비밀번호 불일치').css('color', 'red');
					password_ck_cnt++;
				}
			}
		});
	}
	
	function reduplication_check( url, text ){
		console.log(url, text);
		if(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/.exec( $('#email').val().trim() ) ){
			$.ajax({
			    url: url,
			    type: 'POST',
			    dataType: 'text', //서버로부터 내가 받는 데이터의 타입
			    contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
			    data: text ,
		
			    success: function(data){
					console.log("AJAX flag: ", data, url );
				
					if(url == "email_check"){
						
						if(data == 0){
							$( '.email > .fail_text' ).text( '사용가능' ).css('color', 'green');
							return "1";	
						}else{
							$( '.email > .fail_text' ).text( '사용불가' ).css('color', 'red');
						return "2";	
						}
					}
		
					if(url == "nickname_check"){
						if(data == 0){
							$( '.nickname > .fail_text' ).text( '사용가능' ).css('color', 'green');
						}else{
							$( '.nickname > .fail_text' ).text( '사용불가' ).css('color', 'red');
						}
					}
			    },
			    error: function (){        
			                      
			    }
			  });
		}else{
		}
}
	let mail_ck_cnt = 1;
	let nickname_ck_cnt = 1;
	let password_ck_cnt = 1;
	// 이메일 중복검사 ( 이메일 input에 포커스가 벗어나면 )
	
	$( '#email' ).focusout( function(){
		if( $( '#email' ).val().trim() ){
			reduplication_check( 'email_check', $( '#email' ).val() );
		}
	});
	
	
	
	// 닉네임 중복검사 ( 이메일 input에 포커스가 벗어나면 )
	$( '#nickname' ).focusout( function(){
		if( $( '#nickname' ).val().trim() ){
			reduplication_check( 'nickname_check', $( '#nickname' ).val() );
			
		}
	});
	
	meach_password();
	
	// 가입 완료를 클릭하면
    $( '.btn_complete button' ).on( 'click', function(){
        let cnt = 1;
        if( !$( '#email' ).val() ){
            /* 이메일 입력 값 검증 */
            $( '.email > .fail_text' ).css('color', 'red');
            cnt++;
        }
        if( !$( '#password' ).val() ){
            /* 비밀번호 입력 값 검증 */
            $( '.password > .fail_text' ).css('color', 'red');
            cnt++;

        }
        if( !$( '#repassword' ).val() ){
            /* 비밀번호2 입력 값 검증 */
            $( '.repassword > .fail_text' ).css('color', 'red');
            cnt++;

        }
        if( !$( '.phone1' ).val() ){
            /* phone1 입력 값 검증 */
            $( '.phone > .fail_text' ).css('color', 'red');
            cnt++;

        } else {
            $( '.phone > .fail_text' ).css('color', 'transparent');
        }
        if( !$( '.phone2' ).val() ){
            /* phone2 입력 값 검증 */
            $( '.phone > .fail_text' ).css('color', 'red');
            cnt++;

        } else {
            $( '.phone > .fail_text' ).css('color', 'transparent');
        }
        if( !$( '.phone3' ).val() ){
            /* phone3 입력 값 검증 */
            $( '.phone > .fail_text' ).css('color', 'red');
            cnt++;

        } else {
            $( '.phone > .fail_text' ).css('color', 'transparent');
        }
        if( !$( '#nickname' ).val() ){
            /* 닉네임 입력 값 검증 */
            $( '.nickname > .fail_text' ).css('color', 'red');
            cnt++;

        } else {
            $( '.nickname > .fail_text' ).css('color', 'transparent');
        }
        if( $("input:checkbox[name=check_product]:checked").length == 0 ){
            $( '.favorite_product > .fail_text' ).css('color', 'red');
            cnt++;

        } else {
            $( '.favorite_product > .fail_text' ).css('color', 'transparent');
        }
        if( $( 'input:checkbox[name=check_info]:checked' ).length != 3 ){
			$( '.agreement_box > .fail_text' ).css('color', 'red');
			cnt++;
			
		}else{
			$( '.agreement_box > .fail_text' ).css('color', 'transparent');
		}
		
		console.log('cnt',cnt);
		console.log('mail_ck_cnt',mail_ck_cnt);
		console.log('nickname_ck_cnt',nickname_ck_cnt);
		console.log('password_ck_cnt',password_ck_cnt);
		reduplication_check( 'nickname_check', $( '#nickname' ).val() );
		reduplication_check( 'nickname_check', $( '#nickname' ).val() );
		meach_password();
		if( $( '.email > .fail_text' ).text() != "사용가능" ){
			cnt++;
		}
		if( $( '.nickname > .fail_text' ).text() != "사용가능" ){
			cnt++;
		}
		if( $( '.password > .fail_text' ).text() != "비밀번호 일치" ){
			cnt++;
		}
		if( $( '.repassword > .fail_text' ).text() != "비밀번호 일치" ){
			cnt++;
		}
		mail_check();
		if($('.email_check > .fail_text').text() != '인증 되었습니다.' ){
			cnt++;	
		}
		// 입력값 검증에 모두 성공하면
        if( cnt == 1 ){
            $('[name=frm_reg]').submit();
        } else {
			$( 'html, body' ).animate({
				scrollTop: 0
			}, 0);
		}
		
    });

    $( 'input:checkbox[name=check_product]' ).on('click', function(){
        /* 관심상품에 없음이 체크 될 경우 */
        if( $( '#check_no' ).is( ':checked' ) ){
            $( 'input:checkbox[name=check_product]' ).not($(this)).prop( 'checked', false);
            $( 'input:checkbox[name=check_product]' ).not($(this)).prop( 'disabled', true);
        }else{
            $( 'input:checkbox[name=check_product]' ).not($(this)).prop( 'disabled', false);
        }
    });

    $( 'input:checkbox[name=check_all]' ).on('click', function(){
        /* 전체동의를 체크할 경우 */
        if( $( '#check_all' ).is( ':checked' ) ){
            $( '#check_age, #check_agreement, #check_info' ).prop( 'checked', true);
        }
    });

});