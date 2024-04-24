<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>제목 입력</title>
<link rel="stylesheet" href="../resources/css/common/reset.css">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="../resources/css/header/header.css">

<style>
    .courtWriteTable,.courtWriteTh,.courtWriteTd {
        border: 1px solid black;
        border-collapse: collapse;
    }
    .courtWriteTh,.courtWriteTd {
        padding: 5px 10px;
    }
    #courtWriteInfo {
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
    .courtWriteTimeBtn{
   		background-color : skyblue;
   	}
</style>
</head>
<body>
    <div class="adminBody">
        <header class="adminHeader">
            <div class="headerCont">
                <a href="../admin_logout.do" class="logout">로그아웃</a>
                <ul class="menu">
                    <li><a href="../admin/member_list">회원 관리</a></li>
                    <li><a href="../admin/team_list">팀 관리</a></li>
                    <li><a href="../admin/court_list">구장 관리</a></li>
                    <li><a href="../admin/writing_list">글 관리</a></li>
                    <li><a href="../admin/report_list">신고 관리</a></li>
                </ul>
            </div>
        </header>
        <div class="adminContainer">
			<h1>구장 등록</h1>
            <table class="courtWriteTable">
                <tr>
                    <th class="courtWriteTh">구장 이름</th>
                    <td class="courtWriteTd">
                        <input type="text" id="courtWriteName"/>
                    </td>
                </tr>
                <tr>
                    <th class="courtWriteTh">구장 사진</th>
                    <td class="courtWriteTd">
                        <!-- Slider main container -->
                        <div class="swiper-container">
                        <!-- Additional required wrapper -->
                            <div class="swiper-wrapper" id="swiperImage"></div>
                            <!-- 페이징 필요시 추가 -->
                            <div class="swiper-pagination"></div>
                        <!-- 이전, 다음 버튼 필요시 추가 -->
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-button-next"></div>
                        </div>
                        <br/>
                        <br/>
                        <input type="button" id="courtImageUpload" value="사진등록">
                    </td> 
                </tr>
                <tr>
                    <th class="courtWriteTh">구장 정보</th>
                    <td class="courtWriteTd">
                        <textarea id="courtWriteInfo" maxlength="300"></textarea>
                    </td>
                </tr>
                <tr>
                    <th class="courtWriteTh">구장 가격</th>
                    <td class="courtWriteTd">
                        <input type="text" id="courtWritePrice"/>
                    </td>
                </tr>
                <tr>
                    <th class="courtWriteTh">구장 위치</th>
                    <td class="courtWriteTd">
                        <input type="text" id="courtWriteAddress" maxlength="300"/>
                    </td>
                </tr>
            </table>
            <br/>

            <input type="checkbox" id="courtIsOfficial"/>
                <label for="courtIsOfficial">공식 경기 구장</label>
            <input type="checkbox" name="" id="courtIsUnabled">
                <label for="courtIsUnabled">구장 비활성</label>
            <br/>
            <input id="courtRegisterCancel" type="button" value="취소 하기"/>
            <input id="courtRegisterSubmit" type="button" value="등록 하기"/>
        </div>
	</div>
        
<script>
    $('.menu').css('display','none');
    
    $('#courtRegisterSubmit').on('click',function(){
    	if($('#courtWriteName').val() == ''
    		&& $('#courtWriteInfo').val() == ''
        	&& $('#courtWritePrice').val() == ''
        	&& $('#courtWriteAddress').val() == ''
    	){
	        alert('모든 정보를 입력해 주세요');
	        return;
    	}
    	if(confirm('정말 등록 하시겠습니까?')){
        	//아작스 써서 등록 하자
        	
        	///////////////////
    	}
    });

    $('#courtRegisterCancel').on('click',function(){
        if(confirm('정말 취소 하시겠습니까?')){
        	alert('취소되었습니다.');
        	window.location.href = "./courtList.go";
        }
    });
    
</script>
</body>
</html>
