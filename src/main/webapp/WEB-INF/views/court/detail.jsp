<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>제목 입력</title>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<link rel="stylesheet" href="../resources/css/common/reset.css">
<link rel="stylesheet" href="../resources/css/header/header.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>

<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>

<style>
    .courtDetailTable,.courtDetailTh,.courtDetailTd {
        border: 1px solid black;
        border-collapse: collapse;
    }
    .courtDetailTh,.courtDetailTd {
        padding: 5px 10px;
    }
    #courtDetailInfo {
        resize: none;
        width: 500px;
        height: 150px;
    }
    #datepicker {
        margin-bottom: 20px;
    }
	.swiper-container {
	 	width: 50%;
	 	height: 50%; 
	 }
	.swiper-slide {
	 	font-size: 18px;
	 	background: #fff;	
	 }
	.swiper-pagination-bullet {
		 width: 12px;
		 height: 12px; 
		 border: 1px solid pink; 
		 opacity: 1; 
	}
	.swiper-pagination-bullet-active { 
		width: 40px; 
		transition: width .5s; 
		border-radius: 5px; 
		background: pink; 
		border: 1px solid; 
	}
	.swiper-container {
        overflow: hidden; /* 테이블 셀에서 넘치는 부분을 잘라냄 */
        position: relative; /* 상대적인 위치 설정 */
    }
    .swiper-button-prev, .swiper-button-next, .swiper-pagination {
        position: absolute; /* 절대적인 위치 설정 */
        z-index: 1; /* 다른 요소 위로 배치 */
    }
</style>
</head>
<body>
<jsp:include page="../header/header.jsp"/>
<table class="courtDetailTable">
    <tr>
        <th class="courtDetailTh">구장 이름</th>
        <td class="courtDetailTd"><span id="courtDetailName"></span></td>
    </tr>
    <tr>
        <th class="courtDetailTh">구장 사진</th>
        <td class="courtDetailTd">
		<!-- Slider main container -->
		<div class="swiper-container">
		  <!-- Additional required wrapper -->
		  	<div class="swiper-wrapper" id="swiperImage">
		    
		  	</div>
		  
		  	<!-- 페이징 필요시 추가 -->
		  	<div class="swiper-pagination"></div>
		  <!-- 이전, 다음 버튼 필요시 추가 -->
		  	<div class="swiper-button-prev"></div>
		  	<div class="swiper-button-next"></div>
		
		</div> 
    </tr>
    <tr>
        <th class="courtDetailTh">구장 예약 시간</th>
        <td class="courtDetailTd">
			<input id="courtDetailDate" type="text"/>
            <br/>
            <input class="courtDetailTimeBtn" id="0" type="button" value="00~02">
            <input class="courtDetailTimeBtn" id="2" type="button" value="02~04">
            <input class="courtDetailTimeBtn" id="4" type="button" value="04~06">
            <input class="courtDetailTimeBtn" id="6" type="button" value="06~08">
            <input class="courtDetailTimeBtn" id="8" type="button" value="08~10">
            <input class="courtDetailTimeBtn" id="10" type="button" value="10~12">
            <input class="courtDetailTimeBtn" id="12" type="button" value="12~14">
            <input class="courtDetailTimeBtn" id="14" type="button" value="14~16">
            <input class="courtDetailTimeBtn" id="16" type="button" value="16~18">
            <input class="courtDetailTimeBtn" id="18" type="button" value="18~20">
            <input class="courtDetailTimeBtn" id="20" type="button" value="20~22">
            <input class="courtDetailTimeBtn" id="22" type="button" value="22~24">
        </td>
    </tr>
    <tr>
        <th class="courtDetailTh">구장 정보</th>
        <td class="courtDetailTd"><textarea id="courtDetailInfo" disabled></textarea></td>
    </tr>
    <tr>
        <th class="courtDetailTh">구장 가격</th>
        <td class="courtDetailTd"><span id="courtDetailPrice"></span><input id="courtDetailBooking" type="button" value="예약"/></td>
    </tr>
    <tr>
        <th class="courtDetailTh">구장 위치</th>
        <td class="courtDetailTd"><span id="courtDetailAddress"></span></td>
    </tr>
</table>
<input id="courtDetailReport" type="button" value="문의하기"/>

<script>
    var courtIdx = ${courtIdx};
    var courtDetailTime = '';
    var currentDate = new Date();
    var formattedDate = currentDate.getFullYear() + "-" + ((currentDate.getMonth() + 1) < 10 ? '0' + (currentDate.getMonth() + 1) : (currentDate.getMonth() + 1)) + "-" + (currentDate.getDate() < 10 ? '0' + currentDate.getDate() : currentDate.getDate());

    $('#courtDetailDate').val(formattedDate);
    
    $('.menu').css('display','none');
    $('.courtDetailTimeBtn:not([disabled])').click(function() {
        // 모든 버튼의 배경색을 하늘색으로 초기화합니다.
        $('.courtDetailTimeBtn:not([disabled])').not(this).css('background-color', 'skyblue');

        // 클릭된 버튼의 배경색을 검정색으로 변경합니다.
        $(this).css('background-color', 'black');
        courtDetailTime = $(this).attr('id');
       	console.log(courtDetailTime);
    });
    $('#courtDetailBooking').on('click',function(){

        if (courtDetailTime == '') {
            alert('예약할 시간을 선택하세요.');
        } else {
           if(confirm('정말로 예약 하시겠습니까?')){

           }
        }
    });
    

   
   
    callDetail();
    function callDetail(){
        $.ajax({
            type:'post'
            , url:'./detail.ajax'
            , data:{
                "courtIdx":courtIdx
                ,"selectDate":$('#courtDetailDate').val()
            }
            , dataType:'json'
            , success:function(data){
                console.log(data);
                $('#courtDetailName').html(data.detail[0].court_name);
                $('#courtDetailInfo').val(data.detail[0].court_info);
                $('#courtDetailPrice').html(data.detail[0].court_price);
                $('#courtDetailAddress').html(data.detail[0].court_address);
                var content = '';
                
                for(item of data.fileName){
                	content += '<div class="swiper-slide"><img src="../resources/img/court/'+item+'.png" alt="Image"></div>';
                }
                
                $('#swiperImage').html(content);
                
                var swiper = new Swiper('.swiper-container', {
                    // 다양한 옵션 설정, 
                    // 아래에서 설명하는 옵션들은 해당 위치에 들어갑니다!!
                    slidesPerView : 'auto',
                    autoplay: {
                    	delay:5000
                    },
                    loop:true,
                    spaceBetween : 10, 
                    pagination: {
                        el: '.swiper-pagination',
                        clickable: true,
                      },
                    navigation: {
                    	nextEl: '.swiper-button-next',
                        prevEl: '.swiper-button-prev',
                   	},
                })


                
                var currentHour = currentDate.getHours() < 10 ? '0' + currentDate.getHours():currentDate.getHours(); // 현재 시간(0부터 23까지)
//                 console.log(currentHour);
                
                $.each(data.bookingStartTime, function(index, item) {
                    $('.courtDetailTimeBtn').each(function() {
                    	if($('#courtDetailDate').val() == formattedDate){
	                    	var thisHour = $(this).attr('id') < 10 ? '0' + $(this).attr('id'):$(this).attr('id');
	                        if (thisHour == item || $(this).attr('id') <  (currentDate.getHours()-1 < 0 ? 0: currentDate.getHours()-1)) {
	                            $(this).prop('disabled', true);
	                        }
                    	}else{
	                    	var thisHour = $(this).attr('id') < 10 ? '0' + $(this).attr('id'):$(this).attr('id');
	                        if (thisHour == item) {
	                            $(this).prop('disabled', true);
	                        }
                    	}
                    });
                });
                $('.courtDetailTimeBtn:not([disabled])').css('background-color', 'skyblue');
                

            }
            , error:function(error){

            }
        });
    }
</script>
</body>
</html>
