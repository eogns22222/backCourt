<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>게스트 모집글 작성</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="../resources/css/common/reset.css"
	type="text/css">
<link rel="stylesheet" href="../resources/css/header/header.css"
	type="text/css">
<link rel="stylesheet" href="../resources/css/report/report.css"
	type="text/css">
<link rel="stylesheet"
	href="../resources/css/guest_join/write.css" type="text/css">
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<img src="../resources/img/icon/logo.png" class="img" alt="로고" /> <span>게스트
				모집글 작성</span>
		</div>
		<br />
		<form id="guestForm" action="write.do" method="post">
		<div class="content-wrapper">
			<div class="content">지역 :</div>
			<input type="text" disabled id="address" />
		</div>
		<div class="content-wrapper">
			<div class="content">날짜 :</div>
			<input type="text" id="date" disabled>
		</div>
		<div class="content-wrapper">
			<div class="content">구장명 :</div>
			<button class="find-button">구장 찾기</button>
		</div>
		<div class="content-wrapper">
			<div class="content">경기정보 :</div>
			<textarea name="guest_info" class="textarea" id="game-content" maxlength="500"
				placeholder=" 매치설명(구장 특이사항, 경기 룰, 참가비를 받을 개인계좌, 연락처 등)을 자유롭게 입력해 주세요.(글자수 제한: 500자)">
                    </textarea>
			<div id="char-count"></div>
		</div>
		<div class="content-wrapper">
			<div class="content">모집 레벨 :</div>
			<div class="radio-wrapper">
				<input name="guest_level" type="radio" id="bronze" name="level" value="bronze">
					<label for="bronze">브론즈</label> 
				<input type="radio" id="silver"name="level" value="silver">
				 	<label for="silver">실버</label>
				<input type="radio" id="gold" name="level" value="gold">
				 	<label	for="gold">골드</label>
				<input type="radio" id="platinum" name="level" value="platinum"> 
					<label for="platinum">플레티넘</label>
			</div>
		</div>
		<div class="content-wrapper">
			<div class="content">모집 포지션 :</div>
			<select class="select" name="guest_position" id="position">
				<option value="">포지션 선택</option>
				<option value="center">센터</option>
				<option value="forward">포워드</option>
				<option value="guard">가드</option>
			</select>
		</div>
		<div class="content-wrapper">
			<div class="content">모집 성별 :</div>
			<div class="radio-wrapper">
				<input type="radio" id="male" name="guest_gender" value="male"> <label
					for="male">남자</label> <input type="radio" id="female" name="gender"
					value="female"> <label for="female">여자</label>
			</div>
		</div>
		<div class="content-wrapper">
			<div class="content">모집 인원 :</div>
			<div class="aa">
				<input type="text" name="guest_to" maxlength="1"
					oninput="this.value = this.value.replace(/[^1-9]|10/g, '')"
					placeholder="1~9까지의 숫자 중 입력해 주세요" class="text" />
			</div>
		</div>
		<div class="content-wrapper">
			<div class="content">참가비 :</div>
			<div class="aa">
				<input type="text" class="text" name="guest_fee" id="feeInput"
					oninput="addCommas(event)" maxlength="7" placeholder="참가비를 입력해 주세요" />
			</div>
		</div>
		<div class="write">
			<input type="submit" value="작성 취소" id="cancel" class="submit">
			<input type="submit" value="작성 완료" id="finish" class="submit">
		</div>
		</form>
	</div>
</body>
<script>
//textarea 값을 초기화함
window.onload = function () {
    document.getElementById("game-content").value = "";
};

// 참가비 text박스에 숫자만입력하고, 세자리 수마다 컴마 추가
function addCommas(event) {
    // 입력된 값에서 쉼표 제거
    let value = event.target.value.replace(/,/g, '');
    // 숫자 이외의 문자 제거
    value = value.replace(/\D/g, '');
    // 세 자리마다 쉼표 추가
    value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    // 입력된 값 업데이트
    event.target.value = value;
}

// 작성완료 시 폼 제출

	$(document).ready(function() {
		$('#finish').click(function(event) {
			// 필수 입력값을 확인하여 누락된 것이 있는지 확인
			var level = $('input[name="level"]:checked').val();
			var position = $('#position').val();
			var gender = $('input[name="gender"]:checked').val();
			var participants = $('.text').val();
			var gameInfo = $('#game-content').val();
			var fee = $('#feeInput').val();
			var errorMessage = "";

			// 레벨 선택 여부 확인
			if (!level) {
				errorMessage += "레벨을 선택하세요.\n";
			}

			// 모집 포지션 선택 여부 확인
			if (!position || position == "포지션 선택") {
				errorMessage += "모집 포지션을 선택하세요.\n";
			}

			// 성별 선택 여부 확인
			if (!gender) {
				errorMessage += "성별을 선택하세요.\n";
			}

			// 모집 인원 입력 여부 확인
			if (!participants) {
				errorMessage += "모집 인원을 입력하세요.\n";
			}

			// 경기 정보 입력 여부 확인
			if (!gameInfo) {
				errorMessage += "경기 정보를 입력하세요.\n";
			}

			// 참가비 입력 여부 확인
			if (!fee) {
				errorMessage += "참가비를 입력하세요.\n";
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