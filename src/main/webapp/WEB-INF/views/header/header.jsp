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
                            <ul>
                                <li class="cont">
                                    <p class="date">2020.10.10</p>
                                    <p class="content">내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용</p>
                                    <button class="btn">확인</button>
                                </li>
                                <li class="cont">
                                    <p class="date">2020.10.10</p>
                                    <p class="content">내용내용내용</p>
                                    <button class="btn">확인</button>
                                </li>
                                <li class="cont">
                                    <p class="date">2020.10.10</p>
                                    <p class="content">내용내용내용내용내용내용</p>
                                    <button class="btn">확인</button>
                                </li>
                                <li class="cont">
                                    <p class="date">2020.10.10</p>
                                    <p class="content">내용내용내용내용내용내용내용내용내용</p>
                                    <button class="btn">확인</button>
                                </li>
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
            <a href="#" class="btnTeam">팀 만들기</a>
            <h1>내 팀 리스트</h1>
            <div class="listCont">
                <ul>
                    <li>
                        <a href="#">
                            <span class="logo"><img src="../resources/img/icon/img01.jpg" alt="로고"></span>
                            <span class="teamNik">팀1</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="logo"><img src="../resources/img/icon/img02.jpg" alt="로고"></span>
                            <span class="teamNik">팀2</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="logo"><img src="../resources/img/icon/img03.jpg" alt="로고"></span>
                            <span class="teamNik">팀3</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="logo"><img src="../resources/img/icon/img04.jpg" alt="로고"></span>
                            <span class="teamNik">팀4</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="logo"><img src="../resources/img/icon/img01.jpg" alt="로고"></span>
                            <span class="teamNik">팀5</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!-- 팀 정보 팝업 - 팀이 없는 경우 -->
    <div class="popup teamPop type02">
        <a href="#" class="close"><img src="../resources/img/icon/close.png" alt=""></a>
        <div class="popWrap">
            <a href="#" class="btnTeam">팀 만들기</a>
            <h1>내 팀 리스트</h1>
            <div class="listCont">
                <p>팀이 없습니다.</p>
            </div>
        </div>
    </div>
</body>
<script>

    $(function(){
        // 팀 정보 팝업
        $('#teamInfo').on('click',function(){
            $('.popup.type01').addClass('on');
            $('.curtain').addClass('on');
            $('html').addClass('on');
        });
        $('.close').on('click',function(){
            $('.popup.type01').removeClass('on');
            $('.curtain').removeClass('on');
            $('html').removeClass('on');
        });

        // 알림
        $('#notice').on('click',function(){
            $('.noticeBox').addClass('on');
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


</script>
</html>

