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
    .courtDetailTimeBtn{
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
            content
            <table class="courtDetailTable">
                <tr>
                    <th class="courtDetailTh">구장 이름</th>
                    <td class="courtDetailTd"><input type="text" id="courtDetailName"></input></td>
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
                    <th class="courtDetailTh">구장 정보</th>
                    <td class="courtDetailTd"><textarea id="courtDetailInfo"></textarea></td>
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
        </div>
<script>
    $('.menu').css('display','none');
    
    $(document).ready(function() {
    });
    
</script>
</body>
</html>
