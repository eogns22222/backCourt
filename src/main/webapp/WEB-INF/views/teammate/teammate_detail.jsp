<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="../resources/css/common/reset.css">
<link rel="stylesheet" href="../resources/css/header/header.css">
<link rel="stylesheet" href="../resources/css/teammate/teammate_detail.css">
</head>
<body>
<jsp:include page="../header/header.jsp"/>
<div class="leftCont">
    <div class="teammateinfocont">
        <div class="flexBox">
            <div class="logo">
                <img class="teammateLogo" src="../resources/img/teamLogo/${teammateDetail.logo}.jpg" alt="teammateLogo">>
            </div>
            <div>
                <a href="javascript:;" class="teammateReportBtn">신고하기</a>
                <h3 class="teamName">${teammateDetail.team_name}</h3>
                <p class="teamLeader"><span>${teammateDetail.id}</span></p>
            </div>
        </div>

        <p class="teamAddress">지역: <span>${teammateDetail.team_address}</span></p>
        <p class="teammateLevel">모집 레벨: <span>${teammateDetail.join_team_level}</span></p>
        <p class="teammateGender">모집 성별: <span>${teammateDetail.join_to_gender}</span></p>
        <p class="teammatePositions">모집 포지션: <span>${teammateDetail.join_team_position}</span></p>
        <p class="tj">
            <a href="" class="teammatejoinBtn" onclick="teammateJoin()">가입 신청하기</a>
        </p>
    </div>
  
    <div class="teamDescriptionBox">
        <p>${teammateDetail.join_to_content}</p>
    </div>
</div>
</body>
<script>
$('.teammateReportBtn').on('click',function(){
	window.location.href = '../mypage/report.go?join_team_idx='+${teammateDetail.join_team_idx}+'&reportWirteType="신고하기"';
});

function teammateJoin(){
	
}
</script>
</html>