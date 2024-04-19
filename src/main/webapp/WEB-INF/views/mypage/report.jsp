<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>report</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="../resources/css/common/reset.css" type ="text/css">
<link rel="stylesheet" href="../resources/css/header/header.css" type ="text/css">
<link rel="stylesheet" href="../resources/css/report/report.css" type ="text/css">
</head>
<body>
    <div class="top">
        <br/><br/><br/><br/><br/>
        <h1>
            <a href="#" >
                <img src="../resources/img/icon/report.png" class="img"/>
                신고/문의
            </a>
        </h1>
        <br/><br/>
        <form id="reportForm" action="mypage/report.do" method="post">
            <label class="title">제목</label>
            <br/>
            <input type="text" id="name" name="name" class="zz" placeholder=" 제목을 입력해 주세요.(글자수 제한: 30자)" maxlength="30">
            <br/><br/>
            <label class="title">내용</label>
            <textarea id="message" name="message" class="text" placeholder="내용을 입력해 주세요.(글자수 제한: 500자)" maxlength="500"></textarea>
            <br/>
            <div class="but">
                <input type="submit" value="취소" id="cancel" class="submit">
                <input type="submit" value="제출" class="submit">
            </div>
        </form>
    </div>
</body>
<script>
    // 다른 유형에 대한 처리 추가
	/* document.getElementById("reportForm").addEventListener("submit", function(event) {
	    var category = document.getElementById("category").value;
	    if (category === "report") {
	        this.action = "submit_report.php?type=report";
	    } else if (category === "inquiry") {
	        this.action = "submit_report.php?type=inquiry";
	    }
	}); */
    
	document.getElementById("reportForm").addEventListener("submit", function(event) {
	    var name = document.getElementById("name").value;
	    var message = document.getElementById("message").value;
	
	    if (name.trim() === '' || message.trim() === '') {
	        alert("제목과 내용을 모두 입력해주세요.");
	        event.preventDefault();// 폼 제출 동작을 중지함
	    }else{
	    	var confirmed = confirm("정말로 제출하시겠습니까?"); // 확인 창을 띄움
	    	
	    	if (confirmed) {
	             // 사용자가 확인을 눌렀을 경우 폼을 제출
	             this.submit();
	         } else {
	             // 사용자가 취소를 눌렀을 경우 아무 동작도 하지 않음
	         }
	    }
	});

	
	
</script>
</html>