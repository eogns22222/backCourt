<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>팀원 모집글 작성</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="../resources/css/common/reset.css"
	type="text/css">
<link rel="stylesheet" href="../resources/css/teammate/teammate_write.css" 
	type="text/css">
<link rel="stylesheet" href="../resources/css/header/header.css"
	type="text/css">
</head>
<body>
<jsp:include page="../header/header.jsp"/>
	<div class="wrapper">
		<div class="header">
			<img src="../resources/img/icon/logo.png" class="img" alt="로고" /> <span>게스트
				모집글 작성</span>
		</div>
		<br />
		<div class="content-wrapper">
			<div class="content">팀명 : ${info.team_name}</div>
		</div>
		<div class="content-wrapper">
			<div class="content" disabled id="address">지역 : ${info.team_address}</div>
		</div>
		<div class="content-wrapper">
			<div class="content">팀 설명 : ${info.team_info}</div>
		</div>
		<form id="teammateForm" action="teammate_join_write.do" method="post">
		<div class="content-wrapper">
			<div class="content">모집내용 : </div>
			<textarea name="teammate_info" class="textarea" id="teammateContent" maxlength="500"
				placeholder=" 팀원 모집내용(특이사항, 참가비를 받을 개인계좌, 연락처 등)을 자유롭게 입력해주세요.(글자수 제한: 500자)">
            </textarea>
			<div id="char-count"></div>
		</div>
		<div class="content-wrapper">
			<div class="content">모집 성별 :</div>
			<div class="radio-wrapper">
				<input type="radio" id="male" name="teammateGender" value="male"> 
				<label for="male">남자</label> 
				<input type="radio" id="female" name="teammateGender"	value="female"> 
				<label for="female">여자</label>
			</div>
		</div>
		<div class="content-wrapper">
			<div class="content">모집 레벨 : </div>
			<div class="radio-wrapper">
				<input type="radio" id="bronze" name="teammateLevel" value="bronze">
					<label for="bronze">브론즈</label> 
				<input type="radio" id="silver"name="teammateLevel" value="silver">
				 	<label for="silver">실버</label>
				<input type="radio" id="gold" name="teammateLevel" value="gold">
				 	<label	for="gold">골드</label>
				<input type="radio" id="platinum" name="teammateLevel" value="platinum"> 
					<label for="platinum">플레티넘</label>
			</div>
		</div>
		<div class="content-wrapper">
			<div class="content">모집 포지션 :</div>
			<select class="select" name="teammatePosition" id="teammatePosition">
				<option value="">포지션 선택</option>
				<option value="center">센터</option>
				<option value="forward">포워드</option>
				<option value="guard">가드</option>
			</select>
		</div>
		<div class="write">
			<input type="submit" value="작성 취소" id="cancel" class="submit">
			<input type="submit" value="작성 완료" id="finish" class="submit">
		</div>
		</form>
	</div>
</body>
<script>
var team_idx = '${info.team_idx}';

	$('.menu').css('display','none');
	// textarea 값을 초기화함
	window.onload = function () {
	    document.getElementById("teammateContent").value = "";
	};

	// 작성완료 시 폼 제출
	$(document).ready(function() {
		$('#finish').click(function(event) {
			// 필수 입력값을 확인하여 누락된 것이 있는지 확인
			var teammateContent = $('#teammateContent').val();
			var teammateGender = $('input[name="teammateGender"]:checked').val();
			var teammateLevel = $('input[name="teammateLevel"]:checked').val();
			var teammatePosition = $('#teammatePosition').val();
			var errorMessage = "";
			
			// 팀원모집내용 입력 여부 확인
			if (!teammateContent || !teammateGender || !teammateLevel || !teammatePosition) {
				errorMessage += "입력하지 않은 내용이 있습니다.";
			}

			// 필수 입력값이 누락된 경우 알림창 표시
			if (errorMessage !== "") {
				alert(errorMessage);
				event.preventDefault(); // 폼 제출을 중지
			} else {
				// 모든 필수 입력값이 제공된 경우 확인 메시지 표시
				var confirmed = confirm("작성을 완료하시겠습니까?");
				if (!confirmed) {
					event.preventDefault(); // 확인을 누르지 않으면 폼 제출 중지
				}
				console.log(teammateContent);
				console.log(teammateGender);
				console.log(teammateLevel);
				console.log(teammatePosition);
			}
		});
	});
	
	// 작성 취소	
	$(document).ready(function() {
	    $('#cancel').click(function(event) {
	        // 취소 여부 확인
	        var confirmed = confirm("작성을 취소하시겠습니까?");
	        if (confirmed) {
	            // 확인을 누를 경우 이전 페이지로 이동
	            window.history.back();
	        } else {
	            // 취소를 누르면 폼 제출을 중지
	            event.preventDefault();
	        }
	    });
	});
	
	// 작성 취소 시 컨펌창
	$(document).ready(function() {
		$('#cancel').click(function(event) {
			// 취소 여부 확인
			var confirmed = confirm("작성을 취소하시겠습니까?");
			if (confirmed) {
				// 확인을 누를 경우 이전 페이지로 이동
				window.history.back();
			} else {
				// 취소를 누르면 폼 제출을 중지
				event.preventDefault();
			}
		});
	});

	 	
</script>
</html>