"use strict";
$(document).ready(function(){
    
    //이메일 중복검사 ( 이메일 input에 포커스가 벗어나면 )
    $('#email').focusout( function(){
        if($('#email').val().trim() ){
            reduplication_check( 'email_check', $('#email').val() );
        }
    });

    //닉네임 중복검사 ( 이메일 input에 포커스가 벗어나면)
    $('#nickname').focusout( function(){
        if( $('#nickname').val().trim()){
            reduplication_check('nickname_check', $('#nickname').val());
        }
    });


});