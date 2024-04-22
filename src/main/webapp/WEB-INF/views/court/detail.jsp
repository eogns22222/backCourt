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
        	<div class="swiper-container">
            	<div class="swiper-wrapper" id="imageList">
                	
            	</div>
            <!-- 네비게이션 버튼 추가 -->
            	<div class="swiper-button-prev"></div>
            	<div class="swiper-button-next"></div>
            	<div class="swiper-pagination"></div>
        	</div>
        </td>
    </tr>
    <tr>
        <th class="courtDetailTh">구장 예약 시간</th>
        <td class="courtDetailTd">
			<input id="courtDetailDate" type="text"/>
            <br/>
            <input class="courtDetailTimeBtn" id="00" type="button" value="00~02">
            <!-- 나머지 시간대 버튼들은 생략 -->
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

<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script>
    var courtIdx = ${courtIdx};
    var courtDetailTime = '';
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
    
    var swiper = new Swiper(".mySwiper", {
        spaceBetween: 30, // 슬라이드간 간격
        centeredSlides: true, // 신경안써도 됨
        // autoplay: { // 자동 슬라이드 여부
        //     delay: 2500,
        //     disableOnInteraction: false,
        // },
        autoplay: false, // 자동 슬라이드 false
        pagination: { // 페이지네이션
            el: ".swiper-pagination",
            clickable: true,
        },
        navigation: { // 이전, 다음 버튼
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
    });

    
    
    $(document).ready(function() {
        // Datepicker 초기화
        $('#courtDetailDate').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true
        });

        // 기본 날짜 설정
        $('#courtDetailDate').datepicker('setDate', new Date());

        // 날짜 선택 이벤트 리스너
        $('#courtDetailDate').on('changeDate', function(e) {
            var selectedDate = e.format();
            console.log('선택된 날짜:', selectedDate);

            // 선택된 날짜에 기반한 추가 작업을 여기에 추가할 수 있습니다.
        });
    });

    // Datepicker 설정
    $('#datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayHighlight: true
    });
    $('#datepicker').on('changeDate', function(e){
        var selectedDate = e.format();
        console.log('선택된 날짜:', selectedDate);

        // 여기에 선택된 날짜를 이용한 추가 작업을 수행할 수 있습니다.
    });
   
    callDetail();
    function callDetail(){
        $.ajax({
            type:'post'
            , url:'./detail.ajax'
            , data:{
                "courtIdx":courtIdx
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
                	content +=
                		'<div class="swiper-slide"><img src="../resources/img/court/'+item+'.png" alt="Image"></div>'
                }
                $('#imageList').html(content);
                
                // Swiper 초기화
                var swiper = new Swiper('.swiper-container', {
                    // 선택적 매개변수
                    navigation: {
                        nextEl: '.swiper-button-next',
                        prevEl: '.swiper-button-prev',
                    },
                });
                
                $.each(data.bookingStartTime, function(index, item) {
                    $('.courtDetailTimeBtn').each(function() {
                        if ($(this).attr('id') == item) {
                            $(this).prop('disabled', true);
                        }
                    });
                });
                $('.courtDetailTimeBtn:not([disabled])').css('background-color', 'skyblue');


            }
            , error:function(error){

            }
        });
    }
   	function showDate(){
   		
   	}
</script>
</body>
</html>
