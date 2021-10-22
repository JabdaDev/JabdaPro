$(document).ready(function(){
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
        } else if( click_text == 'TV' ){
			$( '.sortCategory_body > div' ).css( 'font-weight', 'normal' );
            $( '.sortCategory_body > div' ).eq(0).css( 'font-weight', 'bold' );
		} else if( click_text == '모니터' ){
			$( '.sortCategory_body > div' ).css( 'font-weight', 'normal' );
            $( '.sortCategory_body > div' ).eq(1).css( 'font-weight', 'bold' );
		} else if( click_text == '스마트폰' ){
			$( '.sortCategory_body > div' ).css( 'font-weight', 'normal' );
            $( '.sortCategory_body > div' ).eq(2).css( 'font-weight', 'bold' );
		}
    });
    
    // li button을 클릭하면
    $( '.container:nth-child(2) ul > li button' ).on('click', function(){
        $( '#bnt_search_modal' ).html( $(this).text() + ' <i class="fas fa-sort-down"></i>' );
    });

});