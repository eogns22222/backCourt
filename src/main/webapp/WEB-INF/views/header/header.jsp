<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/common/common.css" type ="text/css">
<link rel="stylesheet" href="../resources/css/common/reset.css" type ="text/css">
<link rel="stylesheet" href="../resources/css/header/header.css" type ="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
    <!-- 블라인드 -->
    <div class="curtain"></div>
    <!-- header -->
    <header class="header">
        <div class="inner">
            <!-- header 상단 -->
            <div class="top">
                <h1><a href="#"><img src="../resources/img/icon/img04.jpg" alt=""></a></h1>
                <ul class="icoCont">
                    <!-- 팀 정보 -->
                    <li>
                        <a href="javascript:;" id="teamInfo">
                            <span class="icon"><img src="../resources/img/icon/img03.jpg" alt=""></span>
                            <span class="txt">팀정보</span>
                        </a>
                    </li>
                    <!-- 알림 -->
                    <li>
                        <a href="javascript:;" id="notice">
                            <span class="icon"><img src="../resources/img/icon/img01.jpg" alt=""></span>
                            <span class="txt">알림</span>
                            <span class="num">0</span>
                        </a>
                        <!-- 알림창 -->
                        <div class="noticeBox">
                            <a href="#" class="close"><img src="../resources/img/icon/close.png" alt=""></a>
                            <h2>알림</h2>
                            <ul id="noticeCont">
<!--                                 <li class="cont">
                                    <p class="date">2020.10.10</p>
                                    <p class="content">내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용</p>
                                    <button class="btn">확인</button>
                                </li> -->
                            </ul>
                        </div>
                    </li>
                    <!-- 마이페이지 -->
                    <li>
                        <a href="javascript:;" id="myPage">
                            <span class="icon"><img src="../resources/img/icon/img02.jpg" alt=""></span>
                            <span class="txt">내정보</span>
                        </a>
                    </li>
                </ul>
            </div>
            
            <!-- header 하단 -->
            <ul class="menu">
                <li><a href="#">공식 경기</a></li>
                <li><a href="#">팀 서비스</a></li>
                <li><a href="#">구장 대여</a></li>
            </ul>
        </div>
    </header>

    <!-- 내정보 nav -->
    <div class="nav">
        <a href="#" class="close"><img src="../resources/img/icon/close.png" alt=""></a>
        <ul>
            <li class="topCont">
                <div class="profile">
                    <p class="nik"><a href="#">admin</a></p>
                    <span class="photo"><img src="../resources/img/icon/img01.jpg" alt=""></span>
                </div>
                <div class="point">
                    <p>포인트<span><a href="#">50,000</a></span></p>
                    <button>로그아웃</button>
                </div>
            </li>
            <li class="cont">
                <a href="#">신청 내역</a>
            </li>
            <li class="cont">
                <a href="#">신고 내역</a>
            </li>
            <li class="cont">
                <a href="#">찜 목록</a>
            </li>
        </ul>
    </div>

    <!-- 팀 정보 팝업 -->
    <div class="popup teamPop type01">
        <a href="#" class="close"><img src="../resources/img/icon/close.png" alt=""></a>
        <div class="popWrap">
            <a href="../team/write.go" class="btnTeam">팀 만들기</a>
            <h1>내 팀 리스트</h1>
            <div class="listCont">
                <ul id="listCont">
                    <!-- <li>
                        <a href="#">
                            <span class="logo"><img src="../resources/img/icon/img01.jpg" alt="로고"></span>
                            <span class="teamNik">팀1</span>
                        </a>
                    </li> -->
                </ul>
            </div>
        </div>
    </div>

</body>
<script>
	// 프론트
    $(function(){
        // 팀 정보 팝업
        $('#teamInfo').on('click',function(){
            $('.popup.type01').addClass('on');
            $('.curtain').addClass('on');
            $('html').addClass('on');
            
            teamListCall();
        });
        $('.close').on('click',function(){
            $('.popup.type01').removeClass('on');
            $('.curtain').removeClass('on');
            $('html').removeClass('on');
        });

        // 알림
        $('#notice').on('click',function(){
            $('.noticeBox').addClass('on');
            
            noticeListCall();
        });
        $('.noticeBox .close').on('click',function(){
            $('.noticeBox').removeClass('on');
        });

        // 마이페이지 nav
        $('#myPage').on('click',function(){
            $('.nav').stop(true, false).animate({
                right:'0'
            });
            $('.nav').addClass('on');
        });
        $('.nav .close').on('click',function(){
            $('.nav').stop(true, false).animate({
                right:'-450px'
            });
            $('.nav').removeClass('on');
        });

    })
    
	// 팀 페이지 ajax
    function teamListCall(){
		$.ajax({
			type : 'get', 
			url : './team/list.ajax',
			data : {},
			dataType : 'json',
			success : function(data){ 
				
				if(data.list != null){
					drawTeamList(data.list);
				}else{
					drawTeamList02();
				}
				
				
			}, 
			error : function(error){
				console.log(error);
			}, 
		});
	}
	
 	// 팀 리스트
	function drawTeamList(list){
		var content = '';
		
		for(item of list){
			content += '<li>';
			content += '<a href="#">';
			content += '<span class="logo"><img src="../resources/img/teamLogo/' + item.logo + '.jpg" alt="로고"></span>';
			content += '<span class="teamNik">' + item.team_name + '</span>';
			content += '</a>';
			content += '</li>';
		}
		
		$('#listCont').html(content);
	}
	function drawTeamList02(){
		var content = '';
		
		content += '<ul>';
		content += '<li class="noTeam">팀이 없습니다.</li>';
		content += '</ul>';
		
		$('#listCont').html(content);
	}
	
	// 알림 ajax
    function noticeListCall(){
		$.ajax({
			type : 'get', 
			url : './notice/list.ajax',
			data : {},
			dataType : 'json',
			success : function(data){ 
				
				drawNoticeList(data.list);
				
			}, 
			error : function(error){
				console.log(error);
			}, 
		});
	}
	
 	// 알림 리스트
	function drawNoticeList(list){
		var content = '';
		
		for(item of list){
			
			content += '<li class="cont">';
			
			var date = new Date(item.notice_time);
		    var dateStr = date.toLocaleDateString("ko-KR");
			
			content += '<p class="date">' + dateStr + '</p>';
			content += '<p class="content">' + item.notice_content + '</p>';
			content += '<button class="btn" onclick = "location.href=\'del.do?idx=' + item.notice_idx + '\'">확인</button>';
			content += '</li>';
        	
		}
		
		$('#noticeCont').html(content);
	}

</script>
</html>























