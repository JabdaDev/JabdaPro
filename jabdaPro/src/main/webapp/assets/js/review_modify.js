"use strict";
$(document).ready(function(){
	function image_delete(){
		
		$.ajax({
			url: '/fileDelete',
			type: 'get',
			dataType: 'text',
			contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
			data : {
				"seq": $('[name="reviewSeq"]').val(),
				"nickname": $('[name="nickname"]').val(),
				"image1": $('[name="fileUpload1"]').val(),
				"image2": $('[name="fileUpload2"]').val(),
				"image3": $('[name="fileUpload3"]').val()
			},
			success : function(html){
				// ajax_image();
				console.log("삭제 성공");
			},
			error : function(error){
				alert('파일 삭제 실패');
				console.log(error);
				console.log(error.status);
			}
		});
	}
	function ajax_image(){
		let formData = new FormData($('#frm_review')[0]);
		$.ajax({
			type: 'post',
			url: '/fileUpload',
			data : formData,
			processData: false,
			contentType: false,
			success : function(html){
				$('#frm_review').submit();
			},
			error : function(error){
				alert('파일 업로드 실패');
				console.log(error);
				console.log(error.status);
			}
		});
	}
	function setImageFromFile(input, expression) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
				console.log( e.target.result );
	            $('[for="' + expression + '"]').css( 'background-image', 'url(' + e.target.result + ')' )
							 .css( 'background-size', '100% 100%' )
							 .css( 'background-repeat', 'no-repeat' );
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}
	
	function handleImgFileSelect(e) {
			var sel_file;
            var files = e.target.files;
            var filesArr = Array.prototype.slice.call(files);
 
            filesArr.forEach(function(f) {
                if(!f.type.match("image.*")) {
                    alert("확장자는 이미지 확장자만 가능합니다.");
                    return;
                }
 
                sel_file = f;
 
                var reader = new FileReader();
                reader.onload = function(e) {
                    $("#img").attr("src", e.target.result);
                }
                reader.readAsDataURL(f);
            });
        }
	
	$('.merit_text textarea, .demerit_text textarea').keyup(function(){
		$(this).prev().text( $(this).val().length + '자 / 최소 10자 이상' );
		$(this).prev().css( 'color', 'red' );
		if( $(this).val().length >= 10 ){
			$(this).prev().css( 'color', '#28a745' );
		}else if( $(this).val().length == 0 ){
			$(this).prev().css( 'color', '#7E8593' );
		}
	});
	$( '.btn_complete button' ).on('click', function(){
		let strGubun = $('[for=' + $("input:radio[name='gubun']:checked").attr('id') + ']').text().trim();
		let strConvenience = $('[for=' + $("input:radio[name='convenience']:checked").attr('id') + ']').text().trim();
		let strFunction = $('[for=' + $("input:radio[name='function']:checked").attr('id') + ']').text().trim();
		let strMerit = $('.merit_text textarea').val();
		let strDemerit = $('.demerit_text textarea').val();
		
		let validation_cnt = 1;
		
		if( !strGubun || strGubun == "네" ){
			validation_cnt++;
		}
		if( !strConvenience ){
			validation_cnt++;
		}
		if( !strFunction ){
			validation_cnt++;
		}
		console.log("장점개수" + strMerit.length );
		if( !strMerit || strMerit.length < 10 ){
			validation_cnt++;
		}
		console.log("단점개수" + strDemerit.length );
		if( !strDemerit || strDemerit.length < 10 ){
			validation_cnt++;
		}
		
		console.log( validation_cnt );
		if( validation_cnt == 1){
			let today = new Date();
			let year = today.getFullYear(); // 년도
			let month = today.getMonth() + 1; // 월
			let date = today.getDate(); // 일
			if( month < 10 ) {
				month = "0" + month; 
			}
			if( date < 10 ) {
				date = "0" + date;
			}
			
			for(let i = 1; i <= 3; i++){
				if( !$('#fileUpload'+i+'').val() ){
					console.log( '#fileUpload'+i+'' );
					console.log('[name="fileUpload'+i+'"]');
					$('#fileUpload'+i+'').remove();
					$('[for="fileUpload'+i+'"]').remove();
				}
			}
				if( $('#fileUpload1').val() ){
					let elements = $('#fileUpload1').val();
					let extensions = elements.substring( elements.lastIndexOf('.'), elements.length );
					let conFileName = elements.substring(0, elements.lastIndexOf('.') )
														.substring( elements.lastIndexOf('\\')+1, elements.length );
					let filename = "ITMI_" + year + "" + month + "" + date + "_" + nickname + "_" + "1";
					
					$('[name="fileUpload1"]').val( filename + extensions );
				}
				if( $('#fileUpload2').val() ){
					let elements = $('#fileUpload2').val();
					let extensions = elements.substring( elements.lastIndexOf('.'), elements.length );
					let conFileName = elements.substring(0, elements.lastIndexOf('.') )
														.substring( elements.lastIndexOf('\\')+1, elements.length );
					
					let filename = "ITMI_" + year + "" + month + "" + date + "_" + nickname + "_" + "2";
					
					$('[name="fileUpload2"]').val( filename + extensions );
				}
				if( $('#fileUpload3').val() ){
					let elements = $('#fileUpload3').val();
					let extensions = elements.substring( elements.lastIndexOf('.'), elements.length );
					let conFileName = elements.substring(0, elements.lastIndexOf('.') )
														.substring( elements.lastIndexOf('\\')+1, elements.length );
					
					let filename = "ITMI_" + year + "" + month + "" + date + "_" +  + nickname + "_" + "3";
					
					$('[name="fileUpload3"]').val( filename + extensions );
				}
			image_delete();
			ajax_image();
			
		}else{
			console.log("실패");
			$( 'html, body' ).animate({
				scrollTop: 0
			}, 0);
		}
	});


	$('#fileUpload1, #fileUpload2, #fileUpload3').on('change', function(e){
		let target = $(this).attr('id');
		setImageFromFile(this, target);
	});
	
$('#image1').css('background-image', 'url(' + "./assets/images/review/"+$('#image1').attr('data') + ')')
			.css( 'background-size', '100% 100%' )
			.css( 'background-repeat', 'no-repeat' );
$('#image2').css('background-image', 'url(' + "./assets/images/review/"+$('#image2').attr('data') + ')')
			.css( 'background-size', '100% 100%' )
			.css( 'background-repeat', 'no-repeat' );
$('#image3').css('background-image', 'url(' + "./assets/images/review/"+$('#image3').attr('data') + ')')
			.css( 'background-size', '100% 100%' )
			.css( 'background-repeat', 'no-repeat' );
$('.convenience_check div').eq( Number($('#convenience').attr('data')-1) ).find('input').prop( 'checked', true );
$('.function_check div').eq( Number($('#function').attr('data')-1) ).find('input').prop( 'checked', true );
	
});