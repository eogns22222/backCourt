<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/common/reset.css">
<link rel="stylesheet" href="../resources/css/header/header.css">
<link rel="stylesheet" href="../resources/css/team/team.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<style>

</style>
</head>
<body>
	<jsp:include page="../header/header.jsp"/>
    <div class="createTeamContainer">
        <form action="" method="post" enctype="multipart/form-data">
            <div class="leftCont">
                <div class="imgBox">
                    <img src="../resources/img/teamLogo/defaultLogo.png" alt="">
                </div>
                <button class="siteLogo">로고 선택</button>
                <input type="hidden" name="sitePhotos" id="sitePhotos">
                <label class="inputFileButton" for="inputFile">내 PC 에서 찾기</label>
                <input id="inputFile" class="pcLogo" type="file" name="photos" multiple="multiple"/>
            </div>
            <div class="rightCont">
                <p>
                    <span>팀명 : </span>
                    <input class="teamName" type="text" name="teamNikName">
                </p>
                <p>
                    <span>지역 : </span>
                    <select id="address" class="selectBox">
                        <option value="금천구">금천구</option>
                        <option value="구로구">구로구</option>
                        <option value="도봉구">도봉구</option>
                        <option value="노원구">노원구</option>
                        <option value="성북구">성북구</option>
                        <option value="마포구">마포구</option>
                        <option value="강서구">강서구</option>
                        <option value="종로구">종로구</option>
                        <option value="동대문구">동대문구</option>
                        <option value="용산구">용산구</option>
                        <option value="관악구">관악구</option>
                        <option value="중랑구">중랑구</option>
                        <option value="영등포구">영등포구</option>
                    </select>
                </p>
                <p>
                    <span>팀 레벨 : </span>
                    <label for="bronz">브론즈</label>
                    <input class="radio" type="radio" name="level" id="bronz" value="브론즈">
                    <label for="silver">실버</label>
                    <input class="radio"  type="radio" name="level" id="silver" value="실버">
                    <label for="gold">골드</label>
                    <input class="radio"  type="radio" name="level" id="gold" value="골드">
                    <label for="platinum">플레티넘</label>
                    <input class="radio"  type="radio" name="level" id="platinum" value="플레티넘">
                </p>
                <p>
                    <span>팀 설명 : </span>
                    <textarea name="teamDescription" id="description"></textarea>
                </p>
                <div class="btnWrap">
                    <button id="cancle" type="button">취소</button>
                    <button id="complete" type="button">완료</button>
                </div>
            </div>
        </form>
    </div>
</body>
<script>
	// 퍼블 영역
    $(function(){});
    
	// 개발 영역
	var val = '';
	var fileName = '';
	var newFileName = '';
	
	// 로고 교체 이벤트
    $('.pcLogo').on('change',function(){
        val = $('.pcLogo').val();
        console.log(val);
        
        fileName = val.substring(val.lastIndexOf('\\') + 1);
        console.log(fileName);
        
        newFileName = fileName.substring(0, fileName.lastIndexOf('.'));
        console.log(newFileName);
        
    });
    $('.pcLogo').on('change',function(){
        val = $('.pcLogo').val();
        console.log(val);
        
        fileName = val.substring(val.lastIndexOf('\\') + 1);
        console.log(fileName);
        
        newFileName = fileName.substring(0, fileName.lastIndexOf('.'));
        console.log(newFileName);
        
        $('#sitePhotos').val(newFileName);
        console.log($('#sitePhotos').val());
    });

</script>
</html>



















