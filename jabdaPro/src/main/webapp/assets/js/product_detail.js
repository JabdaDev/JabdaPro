$(document).ready(function(){
	//alert(typeof nickname === "undefined");
   if( typeof nickname !== "undefined" ) {
		$('#nickname').val( nickname );
		
   }else{
	   
   }
   	function heart_up(seq){
		$.ajax({
			url: "/heart_up.json",
			type: 'get',
			dataType: 'text', //서버로부터 내가 받는 데이터의 타입
			contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
			data: {
				"review_seq" : seq,
				"nickname" : nickname
			} ,
			
			success: function(data){
				console.log( data.trim() );
				
				let heart = Number( $( '[seq="' + seq + '"]' ).text() ) + 1;
				
				
				$( '[seq="' + seq + '"]' ).empty();
				$( '[seq="' + seq + '"]' ).html( '<i class="fas fa-heart text-danger"></i> '+ heart );
				$( '[seq="' + seq + '"]' ).removeClass('review_heart_up');
				$( '[seq="' + seq + '"]' ).addClass('review_heart_down');
				
			},
			error: function ( err ){
			    console.log( err, "오류" );            
			}
		});
	}
	
	function heart_down(seq){
		$.ajax({
			url: "/heart_down.json",
			type: 'get',
			dataType: 'text', //서버로부터 내가 받는 데이터의 타입
			contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
			data: {
				"review_seq" : seq,
				"nickname" : nickname
			} ,
			
			success: function(data){
				console.log( data.trim() );
				
				let heart = Number( $( '[seq="' + seq + '"]' ).text() ) -1;
				
				$( '[seq="' + seq + '"]' ).empty();
				$( '[seq="' + seq + '"]' ).html( '<i class="far fa-heart text-danger"></i> '+ heart );
				$( '[seq="' + seq + '"]' ).removeClass('review_heart_down');
				$( '[seq="' + seq + '"]' ).addClass('review_heart_up');
			},
			error: function ( err ){
			    console.log( err, "오류" );
			}
		});
	}
	
	/* 리뷰를 클릭할때 좋아요를 눌렀으면 꽉찬하트 아니면 빈하트를 출력 */
	function view_heart( seq, heart, review_nickname ){
		console.log(nickname);
		
		let result = '';
		$.ajax({
			url: "/heart_check.json",
			type: 'get',
			dataType: 'text', //서버로부터 내가 받는 데이터의 타입
			async: false,
			contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
			data: {
				"review_seq" : seq,
				"nickname" : nickname
			} ,
			
			success: function(data){
				console.log("하트출력"+data.trim());
				if( data.trim() == '0' ){
					result = '      <div class="float-end review_heart_down" seq="' + seq + '" nickname="' + review_nickname + '" ><i class="far fa-heart text-danger"></i> ' + heart + '</div>';
				}else if( data.trim() == '1' ){
					result = '      <div class="float-end review_heart_up" seq="' + seq + '" nickname="' + review_nickname + '" ><i class="fas fa-heart text-danger"></i> ' + heart + '</div>';
					// alert("좋아요는 한번만 누를수있어요.");
				}
			},
			error: function ( err ){
			    console.log( err, "오류" );            
			}
		});
		console.log( result );
		return result;
	}
/* 좋아요 클릭하면 증가하기전 좋아요를 눌렀는지 확인 */
	function heart_check( seq ){
		console.log(nickname);
		$.ajax({
			url: "/heart_check.json",
			type: 'get',
			dataType: 'text', //서버로부터 내가 받는 데이터의 타입
			contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
			data: {
				"review_seq" : seq,
				"nickname" : nickname
			} ,
			
			success: function(data){
				console.log(data.trim());
				if( data.trim() == '0' ){
					heart_up(seq);
				}else if( data.trim() == '1' ){
					heart_down(seq);
					// alert("좋아요는 한번만 누를수있어요.");
				}
			},
			error: function ( err ){
			    console.log( err, "오류" );            
			}
		});
	}
	function review_list(){
   $.ajax({
         url: "/review_list.json",
         type: 'get',
         dataType: 'json', //서버로부터 내가 받는 데이터의 타입
         contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
         data: {
            "productname": $('[name="productname"]').val()
         },
         
         success: function(data){
            // console.log(data);
            let html = '';
            /*const jsonParse = JSON.parse(data);*/
            if( data.data.length == 0 ){
               html += '<div style="text-align:center color: #000; font-size: 16px;">작성된 리뷰가 없습니다.</div>';
            }
            for(i in data.data ){
               let datas = data.data[i];
               html += '<div class="card col-md-12">';
               html += '   <div class="card-header"><div title="[ ' + datas.nickname+' ] ' + datas.subject + '">[ ' + datas.nickname + ' ] ' + datas.subject + '</div>';
               if(nickname == datas.nickname){
               html += '      <button type="button" class="float-end btn_delete" seq="' + datas.seq + '" data-bs-toggle="modal" data-bs-target="#deleteModal"><i class="fas fa-times btn_delete" seq="' + datas.seq + '"></i></button>';
               html += '      <button type="button" class="float-end btn_modify" seq="' + datas.seq + '" data-bs-toggle="modal" data-bs-target="#deleteModal">수정</button>';
               }else{
                  
               }
               html += '      <div class="float-end review_date">' + datas.date + '</div>';
			   if( !nickname  ) {
			   	html += view_heart( datas.seq, datas.heart, datas.nickname );			
			   }else{
				html += '      <div class="float-end review_heart_up" seq="' + datas.seq + '" nickname="' + datas.nickname + '" ><i class="far fa-heart text-danger"></i> ' + datas.heart + '</div>';
			   }
			   
               html += '      <div class="float-end review_comment_cnt"><i class="fas fa-comment text-primary"></i> ' + datas.reply + '</div>';
               html += '   </div>';
               html += '   <div class="card-body col-md-12">';
               html += '      <div id="carouselExampleControlsNoTouching' + i + '" class="carousel slide col-md-12" data-bs-touch="false" data-bs-interval="false">';
               html += '         <div class="carousel-inner col-md-12">';
               if(datas.image1){
               html += '            <div class="carousel-item active review_image" >';
               html += '               <img src="./assets/images/review/' + datas.image1 + '"  style=" height: 500px;"/>';
               html += '            </div>';   
               }
               if(datas.image2){
               html += '            <div class="carousel-item review_image">';
               html += '               <img src="./assets/images/review/' + datas.image2 + '" style=" height: 500px;"/>';
               html += '            </div>';   
               }
               if(datas.image3){
               html += '            <div class="carousel-item review_image">';
               html += '               <img src="./assets/images/review/' + datas.image3 + '" style=" height: 500px;"/>';
               html += '            </div>';   
               }
               html += '         </div>';
               html += '         <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControlsNoTouching' + i + '" data-bs-slide="prev">';
               html += '            <span class="visually-hidden">Previous</span>';
               html += '         </button>';
               html += '         <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControlsNoTouching' + i + '" data-bs-slide="next">';
               html += '            <span class="visually-hidden">Next</span>';
               html += '         </button>';
               html += '      </div>';
               html += '      <div class="review_data">';
               html += '         <div class="convenience">';
               html += '            <span class="title">편의성 : </span>';
               html += '            <span class="contents">';
               for(let i = 1; i<=datas.convenience; i++){
               html += '               <i class="fas fa-circle text-warning"></i>';   
               }
               html += '            </span>';
               html += '         </div>';
               
               html += '         <div class="function">';
               html += '            <span class="title">편의성 : </span>';
               html += '            <span class="contents">';
               for(let i = 1; i<=datas.function; i++){
               html += '               <i class="fas fa-circle text-warning"></i>';   
               }
               html += '            </span>';
               html += '         </div>';
               html += '         <div class="merit">';
               html += '            <div class="title">장점 : </div>';
               html += '            <span class="contents"><textarea rows="5" readonly="readonly">' + datas.merit + '</textarea></span>';
               html += '         </div>';
               html += '         <div class="demerit">';
               html += '            <div class="title">단점 : </div>';
               html += '            <span class="contents"><textarea rows="5" readonly="readonly">' + datas.demerit + '</textarea></span>';
               html += '         </div>';
               html += '      </div>';
               html += '      <div class="comment_box">';
               html += '         <div class="comment_item">';
               html += '            <button type="button" class="float-end comment_delete"><i class="fas fa-times"></i></button>';
               html += '            <div class="comment_date float-end">2021-09-14</div>';
               
               html += '            <div class="comment_nick">게으른여우</div>';
               html += '            <div class="comment_contents">도움이 되었습니다.</div>';
               html += '         </div>';
               html += '      </div>';
               html += '   </div>';
               html += '</div>';
            }
            $( '.comment' ).append( html );
          },
          error: function (){        
              console.log('실패');              
          }
      });
	}
	review_list();
      $(document).on('click', '.review_delete', function(){
         alert( $(this).attr('dataSeq') );
         alert( $(this).attr('dataNickname') );
      });
      $(document).on('click', '.btn_delete', function(e){
         let temp = $(this).attr('seq');
         $('[name="reivew_seq"]').val( temp );
      });
      $('.delete_complete').on('click', function(){
         image_delete( $('[name="reivew_seq"]').val() );
      });
      function image_delete(seq){
      $.ajax({
         url: '/reviewDelete',
         type: 'get',
         dataType: 'text',
         contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
         data : {
            "seq": seq,
         },
         success : function(html){
            // ajax_image();
            console.log("삭제 성공");
            alert("삭제되었습니다.");
            location.reload();
         },
         error : function(error){
            alert('파일 삭제 실패');
            console.log(error);
            console.log(error.status);
         }
      });
   }
   $(document).on('click', '.btn_modify', function(){
      
      let seq = $(this).attr('seq');
      location.href="/review_modify.do?seq="+seq+"&productname="+$('.productname_val').val();
   });
	$(document).on('click', '.review_heart_up', function(){
		console.log(typeof nickname === "undefined");
		// typeof nickname === "undefined" false: 비로그인,
		if( $('#nickname').val() ) {
			let seq = $(this).attr('seq');
			let re_nickname = $(this).attr('nickname');
			//alert(re_nickname + $('#nickname').val());
			
			// 로그인한 사용자와 리뷰작성자와 같을경우
			if( $('#nickname').val() == re_nickname ){
				alert('자신의 리뷰에는 좋아요를 누를 수 없습니다.');
			}else{
				heart_check( seq );	
			}
		}
		if( $('#nickname').val() == "" ){
			alert("로그인 후 이용 가능합니다.");
		}
	});
	$(document).on('click', '.review_heart_down', function(){
	   if( $('#nickname').val() ) {
			let seq = $(this).attr('seq');
			let re_nickname = $(this).attr('nickname');
			//alert(re_nickname + $('#nickname').val());
			
			// 로그인한 사용자와 리뷰작성자와 같을경우
			if( $('#nickname').val() == re_nickname ){
				alert('자신의 리뷰에는 좋아요를 누를 수 없습니다.');
			}else{
				heart_check( seq );	
			}
		}
		if( $('#nickname').val() == "" ){
			alert("로그인 후 이용 가능합니다.");
		}
	});
	/* 댓글부분 */
	$( '.comment_title' ).on('click', function(){
		let elements = $(this).children('svg').attr('data-icon'); 
		// console.log(elements);
		if( elements == 'sort-down' ){
			$(this).html( '댓글 ' + '<i class="fas fa-sort-up"></i>' );
		}
		if( elements == 'sort-up' ){
			$(this).html( '댓글 ' + '<i class="fas fa-sort-down"></i>' );	
		}
	});
	/* 댓글이 작성되고 있을때 */
	$( '.comment_input textarea' ).keyup( function(){
		let preview_text = $(this).val();
		console.log(preview_text);
		if( !preview_text.trim() ){
			$( '.comment_item_preview .comment_contents' ).text( "이 영역은 미리보기입니다." );
		}
		$( '.comment_item_preview .comment_contents' ).text( preview_text );
	});
});