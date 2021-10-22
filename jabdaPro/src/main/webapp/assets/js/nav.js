"use strict";
$(document).ready(function(){
    // 스크롤 위치를 반환
	function getScrollPosition() {
		return $(window).scrollTop() / ($(document).height() - $(window).height() ) * 100;
	}

    // 스크롤 위치를 반환 (첫 실행)
	$('.v_scroll').css('width',getScrollPosition()+'%');


	// 스크롤 시 위치를 반환하고 CSS를 변경 (상시대기)
	$(window).scroll(function(){
		$( ".v_scroll" ).css({
						'width':getScrollPosition()+ '%',
						'transition':'all 200ms linear'
					});
	});
	
	$('[list="datalistOptions"]').keyup(function(){
		$.ajax({
			url: "/nav_search.json",
			type: 'get',
			dataType: 'text', //서버로부터 내가 받는 데이터의 타입
			contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
			data: {
				"keyword": $('[name="keyword"]').val()
			},
			
			success: function(data){
				console.log(data);
				let html = '';
				let i = 0;
				const jsonParse = JSON.parse(data);
				for(i in jsonParse.data ){
					let datas = jsonParse.data[i];
					html += '<option value="' + datas.brand + " " + datas.name + '">';
					
					
					i++;
					
				}
				$('#datalistOptions').empty().html( html );
		    },
		    error: function (){        
		        console.log('실패');              
		    }
		});
	});
	
	$('[list="m_datalistOptions"]').keyup(function(){
		$.ajax({
			url: "/nav_search.json",
			type: 'get',
			dataType: 'text', //서버로부터 내가 받는 데이터의 타입
			contentType : 'text/plain; charset=utf-8;',//내가 서버로 보내는 데이터의 타입
			data: {
				"keyword": $('[name="keyword"]').val()
			},
			
			success: function(data){
				console.log(data);
				let html = '';
				let i = 0;
				const jsonParse = JSON.parse(data);
				for(i in jsonParse.data ){
					let datas = jsonParse.data[i];
					let text = datas.brand + ' ' + datas.name;
					let link = text.replace(/ /g, "+");
					
					html += '<a href="/product_list.do?keyword=' + link + '&gubun=모니터"><div><span>'+datas.brand+'</span>' + datas.name + '</div></a>';
					i++;
					
				}
				$('#m_datalistOptions').empty().html( html );
		    },
		    error: function (){        
		        console.log('실패');              
		    }
		});
	});
	if( typeof nickname !== "undefined" ) {
		
	}
});