$(document).ready(function(){
	function ajax_sort( url, click_gubun ){
		$.ajax({
			url: url,
			type: 'get',
			dataType: 'text', //서버로부터 내가 받는 데이터의 타입
			contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
			data: {
				"gubun": $('#btn_category_offcanvas').text().trim(),
				"click_gubun": click_gubun,
				"brand": $('[for="'+$('[name="brand"]:checked').attr('id')+'"]').text(),
				"size": $('[for="'+$('[name="display"]:checked').attr('id')+'"]').text().replace("인치", "")
			},
			
			success: function(data){
				// console.log(data);
				let html = '';
				const jsonParse = JSON.parse(data);
				for(i in jsonParse.data ){
					let datas = jsonParse.data[i];
					html += '<a href="/product_detail.do?seq=' + datas.seq + '&gubun=' + datas.gubun + '&productname=' + datas.image.substring(0,datas.image.lastIndexOf(".")) + '">';
					html += '	<div class="card card_box">';
					html += '		<div class="card-body">';
					html += '			<div class="text-muted list_image_box float-start">';
					html += '				<img src="./assets/images/product/'+ datas.image +'" />';
					html += '			</div>';
					html += '			<span>';
					html += '				<h5 class="card-title"><b>' + datas.brand + ' ' + datas.name + '</b></h5>';
					html += '				<div>';
					html += '					<span class="card_smail_brand">브랜드 : </span>';
					html += '					<span class="card-text">' + datas.brand + '</span>';
					html += '				</div>';
					html += '				<div>';
					html += '					<span class="card_smail_resolution">해상도 : </span>';
					html += '					<span class="card-text">' + datas.resolution + '</span>';
					html += '				</div>';
					html += '				<div>';
					html += '					<span class="card_smail_display">화면크기 : </span>';
					html += '					<span class="card-text">' + datas.size + '인치</span>';
					html += '				</div>';
					html += '				<div>';
					html += '					<span class="card_smail_price">가격 : </span>';
					html += '					<span class="card-text">' + datas.price.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '원</span>';
					html += '				</div>';
					html += '				<div>';
					html += '					<span class="card_smail_panel">패널 : </span>';
					html += '					<span class="card-text">' + datas.panel + '</span>';
					html += '				</div>';
					html += '			</span>';
					html += '			<div class="btn_box float-end">';
					html += '				<a href="http://prod.danawa.com/info/' + datas.url + '" class="btn btn_buy" target="_blank">구매하기</a>';
					html += '				<a href="/product_detail.do?seq=' + datas.seq + '&gubun=' + datas.gubun + '&productname=' + datas.image.substring(0,datas.image.lastIndexOf(".")) + '" class="btn btn_detail">자세히 보기</a>';
					html += '			</div>';
					html += '		</div>';
					html += '	</div>';
					html += '</a>';
				}
				$('#home').empty();
				$('#home').html( html );
				
				let count = jsonParse.data.length;
				if( count ){
				}else{
					$('#home').html( '<div style="text-align: center; margin-top: 30px;">검색된 결과가 없습니다.</div>' );
				}
				$('.search_result_count').html('<b>' + count + '</b>건 검색 됨'  );
				
		    },
		    error: function (){        
		        console.log('실패');              
		    }
		});
	}
	function ajax_check_list(url){
		$.ajax({
			url: url,
			type: 'get',
			dataType: 'text', //서버로부터 내가 받는 데이터의 타입
			contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
			data: {
				"gubun": $('#btn_category_offcanvas').text().trim()
			},
			
			success: function(data){
				console.log(url);
				if( url == '/brand_list.json' ){
					let brand = '';
					const jsonParse = JSON.parse(data);
					let cnt = 1;
					brand += '<div>';
					brand += '	<input type="radio" class="btn-check" id="btn_brand' + cnt + '" name="brand" autocomplete="off" />';
					brand += '	<label class="btn btn_select" for="btn_brand' + cnt + '" >전체</label>';
					brand += '</div>';
					for(i in jsonParse.data ){
						let datas = jsonParse.data[i];
						cnt++;
					brand += '<div>';
					brand += '	<input type="radio" class="btn-check" id="btn_brand' + cnt + '" name="brand" autocomplete="off" />';
					brand += '	<label class="btn btn_select" for="btn_brand' + cnt + '" >' + datas.brand + '</label>';
					brand += '</div>';
					}
					$('#v-pills-brand div').empty().html( brand );
					
				}else if( url == '/size_list.json' ){
					let size = '';
					const jsonParse = JSON.parse(data);
					let cnt = 1;
					size += '<div>';
					size += '	<input type="radio" class="btn-check" id="btn_display' + cnt + '" name="display" autocomplete="off" />';
					size += '	<label class="btn btn_select" for="btn_display' + cnt + '" >전체</label>';
					size += '</div>';
					for(i in jsonParse.data ){
						let datas = jsonParse.data[i];
						cnt++;
						
    					if( Number(datas.size) >= 30 ){
							console.log(Number(datas.size));
							size += '<div>';
							size += '	<input type="radio" class="btn-check" id="btn_display' + cnt + '" name="display" autocomplete="off" />';
							size += '	<label class="btn btn_select" for="btn_display' + cnt + '" >30인치 이상</label>';
							size += '</div>';
							break;
						}
					size += '<div>';
					size += '	<input type="radio" class="btn-check" id="btn_display' + cnt + '" name="display" autocomplete="off" />';
					size += '	<label class="btn btn_select" for="btn_display' + cnt + '" >' + datas.size + '인치</label>';
					size += '</div>';
					}
					$('#v-pills-display div').empty().html( size );
				}
		    },
		    error: function (){        
		        console.log('실패');              
		    }
		});
	}
	
  	// 선택완료 버튼을 누르면
	$('.select_complete').on('click', function(){
		$.ajax({
			url: "/product_search",
			type: 'get',
			dataType: 'text', //서버로부터 내가 받는 데이터의 타입
			contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
			data: {
				"gubun": $('#btn_category_offcanvas').text().trim(),
				"brand": $('[for="'+$('[name="brand"]:checked').attr('id')+'"]').text(),
				"size": $('[for="'+$('[name="display"]:checked').attr('id')+'"]').text().replace("인치", "")
			},
		
			success: function(data){
				//console.log( url );
				// console.log(data);
				let html = '';
				const jsonParse = JSON.parse(data);
				for(i in jsonParse.data ){
					let datas = jsonParse.data[i];
					console.log(datas.brand);
					html += '<a href="/product_detail.do?seq=' + datas.seq + '&gubun=' + datas.gubun + '&productname=' + datas.image.substring(0, datas.image.lastIndexOf(".")) + '">';
					html += '	<div class="card card_box">';
					html += '		<div class="card-body">';
					html += '			<div class="text-muted list_image_box float-start">';
					html += '				<img src="./assets/images/product/'+ datas.image +'" />';
					html += '			</div>';
					html += '			<span>';
					html += '				<h5 class="card-title"><b>' + datas.brand + ' ' + datas.name + '</b></h5>';
					html += '				<div>';
					html += '					<span class="card_smail_brand">브랜드 : </span>';
					html += '					<span class="card-text">' + datas.brand + '</span>';
					html += '				</div>';
					html += '				<div>';
					html += '					<span class="card_smail_resolution">해상도 : </span>';
					html += '					<span class="card-text">' + datas.resolution + '</span>';
					html += '				</div>';
					html += '				<div>';
					html += '					<span class="card_smail_display">화면크기 : </span>';
					html += '					<span class="card-text">' + datas.size + '인치</span>';
					html += '				</div>';
					html += '				<div>';
					html += '					<span class="card_smail_price">가격 : </span>';
					html += '					<span class="card-text">' + datas.price.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '원</span>';
					html += '				</div>';
					html += '				<div>';
					html += '					<span class="card_smail_panel">패널 : </span>';
					html += '					<span class="card-text">' + datas.panel + '</span>';
					html += '				</div>';
					html += '			</span>';
					html += '			<div class="btn_box float-end">';
					html += '				<a href="http://prod.danawa.com/info/' + datas.url + '" class="btn btn_buy" target="_blank" >구매하기</a>';
					html += '				<a href="/product_detail.do?seq=' + datas.seq + '&gubun=' + datas.gubun + '&productname=' + datas.image.substring(0,datas.image.lastIndexOf(".")) + '" class="btn btn_detail">자세히 보기</a>';
					html += '			</div>';
					html += '		</div>';
					html += '	</div>';
					html += '</a>';
				}
				$('#home').empty();
				$('#home').html( html );
				
				let count = jsonParse.data.length;
				if( count ){
				}else{
					$('#home').html( '<div style="text-align: center; margin-top: 30px;">검색된 결과가 없습니다.</div>' );
				}
				$('.search_result_count').html('<b>' + count + '</b>건 검색 됨'  );
				
				
		    },
		    error: function (){        
		        console.log('실패');              
		    }
		});
	});

    let display_size = $(window).width();
    if( display_size <= 1200 ){
        $('.tab-content > div').addClass('tab-pane fade');
        // $('.tab-content > div').first().addClass('active show');
        $('.btn-group').css('display', 'inline-block');
        $('.align-items-start').removeClass('d-flex');
        $('.btn_box').removeClass('float-end');


        // $('.container:nth-child(2) ul').css('display', 'none');

    }else{
        // 화면이 1200보다 크면
        $('.tab-content > div').removeClass('tab-pane fade');
        $('.btn-group').css('display', 'block');
        $('.align-items-start').addClass('d-flex');
        $('.btn_box').addClass('float-end');

        

        // $('.container:nth-child(2) ul').css('display', 'inline-block');
    }
    $( window ).resize(function() {
        let display_size = $(window).width();
        if( display_size <= 1200 ){
            $('.tab-content > div').addClass('tab-pane fade');
            //$('.tab-content > div').first().addClass('active show');
            $('.btn-group').css('display', 'inline-block');
            $('.align-items-start').removeClass('d-flex');
            $('.btn_box').removeClass('float-end');
        }else{
            // 화면이 1200보다 크면
            $('.tab-content > div').removeClass('tab-pane fade');
            $('.btn-group').css('display', 'block');
            $('.align-items-start').addClass('d-flex');
            $('.btn_box').addClass('float-end');
        }
    });
    /* 전체를 체크할때 */
    $("input:checkbox[name=brand]").on('click', function(){
        if( $(this).first().attr('id').charAt($(this).first().attr('id').length-1) == 1 ){
            $('input:checkbox[name=brand]').prop('checked', false);
            $('input:checkbox[name=brand]:first').prop('checked', true)
        }else{
            $('input:checkbox[name=brand]:first').prop('checked', false);

        }
    });

    $("input:checkbox[name=price]").on('click', function(){
        if( $(this).first().attr('id').charAt($(this).first().attr('id').length-1) == 1 ){
            $('input:checkbox[name=price]').prop('checked', false);
            $('input:checkbox[name=price]:first').prop('checked', true)
        }else{
            $('input:checkbox[name=price]:first').prop('checked', false);

        }
    });
    
    $("input:checkbox[name=resolution]").on('click', function(){
        if( $(this).first().attr('id').charAt($(this).first().attr('id').length-1) == 1 ){
            $('input:checkbox[name=resolution]').prop('checked', false);
            $('input:checkbox[name=resolution]:first').prop('checked', true)
        }else{
            $('input:checkbox[name=resolution]:first').prop('checked', false);

        }
    });

    $("input:checkbox[name=display]").on('click', function(){
        if( $(this).first().attr('id').charAt($(this).first().attr('id').length-1) == 1 ){
            $('input:checkbox[name=display]').prop('checked', false);
            $('input:checkbox[name=display]:first').prop('checked', true)
        }else{
            $('input:checkbox[name=display]:first').prop('checked', false);

        }
    });

    $("input:checkbox[name=stars]").on('click', function(){
        if( $(this).first().attr('id').charAt($(this).first().attr('id').length-1) == 1 ){
            $('input:checkbox[name=stars]').prop('checked', false);
            $('input:checkbox[name=stars]:first').prop('checked', true)
        }else{
            $('input:checkbox[name=stars]:first').prop('checked', false);

        }
    });
   
    /* modal in item click */
    $( '.sortModal_body > div' ).on('click', function(){
        $( '#btn_search_modal' ).html( $(this).text() + ' <i class="fas fa-sort-down"></i>' );
    });
	$( '.sortCategory_body > div' ).on('click', function(){
        $( '#btn_category_offcanvas' ).html( $(this).text() + ' <i class="fas fa-sort-down"></i>' );
		ajax_check_list( '/brand_list.json' );
		ajax_check_list( '/size_list.json' );

    });
    
    // modal 열떄 이벤트
    $( '#btn_search_modal, #btn_category_offcanvas' ).on('click', function(){
        const click_text = $(this).text().trim();
        if( click_text == '전체' ){
            $( '.sortModal_body > div' ).css( 'font-weight', 'normal' );
            $( '.sortModal_body > div' ).eq(0).css( 'font-weight', 'bold' );
        } else if( click_text == '추천순' ){
            $( '.sortModal_body > div' ).css( 'font-weight', 'normal' );
            $( '.sortModal_body > div' ).eq(1).css( 'font-weight', 'bold' );
        } else if( click_text == '브랜드순' ){
            $( '.sortModal_body > div' ).css( 'font-weight', 'normal' );
            $( '.sortModal_body > div' ).eq(2).css( 'font-weight', 'bold' );
        } else if( click_text == '낮은 가격순' ){
            $( '.sortModal_body > div' ).css( 'font-weight', 'normal' );
            $( '.sortModal_body > div' ).eq(3).css( 'font-weight', 'bold' );
        } else if( click_text == '높은 가격순' ){
            $( '.sortModal_body > div' ).css( 'font-weight', 'normal' );
            $( '.sortModal_body > div' ).eq(4).css( 'font-weight', 'bold' );
        } else if( click_text == '모니터' ){
			$( '.sortCategory_body > div' ).css( 'font-weight', 'normal' );
            $( '.sortCategory_body > div' ).eq(0).css( 'font-weight', 'bold' );
		} else if( click_text == '노트북' ){
			$( '.sortCategory_body > div' ).css( 'font-weight', 'normal' );
            $( '.sortCategory_body > div' ).eq(1).css( 'font-weight', 'bold' );
		} else if( click_text == '스마트폰' ){
			$( '.sortCategory_body > div' ).css( 'font-weight', 'normal' );
            $( '.sortCategory_body > div' ).eq(2).css( 'font-weight', 'bold' );
		}
    });

	
	$('.sortModal_body > div, #list_tabs li button').on('click', function(){
		let msg = $(this).text();
		ajax_sort('/sort.json', msg);
		if( msg == '높은 가격순' ){
			
		}else if( msg == '낮은 가격순' ){
			ajax_sort('/sort.json', msg);
		}else if( msg == '브랜드순' ){
			ajax_sort('/sort.json', msg);
		}else if( msg == '전체' ){
			$('[name="brand"], [name="display"]').prop( 'checked', false );
			ajax_sort('/sort.json', msg);
			
		}
		console.log( $(this).text() );
	});
//$('[for="'+$('[name="brand"]:checked').attr('id')+'"]').text();
});