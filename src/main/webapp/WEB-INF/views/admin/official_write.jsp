<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>제목 입력</title>
<link rel="stylesheet" href="../resources/css/common/reset.css">
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../resources/css/header/header.css">

<style>
.officialWriteTable, .officialWriteTh, .officialWriteTd {
	border: 1px solid black;
	border-collapse: collapse;
}

.officialWriteTh, .officialWriteTd {
	padding: 5px 10px;
}

#officialWriteInfo {
	resize: none;
	width: 500px;
	height: 150px;
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
					<li><a href="../admin/official_list">구장 관리</a></li>
					<li><a href="../admin/writing_list">글 관리</a></li>
					<li><a href="../admin/report_list">신고 관리</a></li>
				</ul>
			</div>
		</header>
		<div class="adminContainer">
			<h1>구장 등록</h1>
			<form action="" method="post">
				<table class="officialWriteTable">
					<tr>
						<th class="officialWriteTh">구장 이름</th>
						<td class="officialWriteTd"><input type="text"
							id="officialWriteName" /></td>
					</tr>
					<tr>
						<th class="officialWriteTh">구장 사진</th>
						<td class="officialWriteTd"><input type="file"
							id="officialImageUpload" multiple></td>
					</tr>
					<tr>
						<th class="officialWriteTh">구장 정보</th>
						<td class="officialWriteTd"><textarea id="officialWriteInfo"
								maxlength="300"></textarea></td>
					</tr>
					<tr>
						<th class="officialWriteTh">구장 가격</th>
						<td class="officialWriteTd"><input type="number"
							id="officialWritePrice" /></td>
					</tr>
					<tr>
						<th class="officialWriteTh">구장 위치</th>
						<td class="officialWriteTd"><input type="text"
							id="officialWriteAddress" maxlength="300" /></td>
					</tr>
				</table>
				<br /> <input type="checkbox" id="officialIsOfficial" /> <label
					for="officialIsOfficial">공식 경기 구장</label> <input type="checkbox"
					name="" id="officialIsDisabled"> <label
					for="officialIsDisabled">구장 비활성</label> <br /> <input
					id="officialRegisterCancel" type="button" value="취소 하기" /> <input
					id="officialRegisterSubmit" type="button" value="등록 하기" />
			</form>
		</div>
	</div>

	<script>
    $('#officialRegisterSubmit').on('click',function(){
        var formData = new FormData();
        var files = $('#officialImageUpload')[0].files;

        // 파일이 선택되었는지 확인 후 FormData에 추가
        if (files.length > 0) {
            for (var i = 0; i < files.length; i++) {
                formData.append('file', files[i]);
            }
        } else {
            // 파일이 선택되지 않았을 때의 처리
            alert('파일이 선택되지 않았습니다.');
            return;
        }

    	var officialWriteName = $('#officialWriteName').val();
    	var officialWriteInfo = $('#officialWriteInfo').val();
    	var officialWritePrice = $('#officialWritePrice').val();
    	var officialWriteAddress = $('#officialWriteAddress').val();
    	var officialIsOfficial = $('#officialIsOfficial').is(':checked');
    	var officialIsDisabled = !$('#officialIsDisabled').is(':checked');
    	
//     	console.log(officialWriteName);
//     	console.log(officialWriteInfo);
//     	console.log(officialWritePrice);
//     	console.log(officialWriteAddress);
//     	console.log(officialIsOfficial);
//     	console.log(officialIsDisabled);

    	formData.append('officialWriteName', officialWriteName);
        formData.append('officialWriteInfo', officialWriteInfo);
        formData.append('officialWritePrice', officialWritePrice);
        formData.append('officialWriteAddress', officialWriteAddress);
        formData.append('officialIsOfficial', officialIsOfficial);
        formData.append('officialIsDisabled', officialIsDisabled);

        //키 벨류 formdata 에서는 entries써야됨
        for (pair of formData.entries()) {
            console.log(pair[0] + ': ', pair[1]);
        }

    	
    	if(officialWriteName == ''
    		|| officialWriteInfo == ''
        	|| officialWritePrice == ''
        	|| officialWriteAddress == ''
    	){
	        alert('모든 정보를 입력해 주세요');
	        return;
    	}
    	if(confirm('정말 등록 하시겠습니까?')){
        	$.ajax({
        		url:'./officialWrite.ajax'
        		,type:'post'
        		,data:formData
                ,contentType:false
                ,processData:false
                ,success:function(){
                	if(data.result == true){
						window.location.href = './officialList.go';                		
                	}
                }
                ,error:function(error){

                }
        	});
    	}
    });

    $('#officialRegisterCancel').on('click',function(){
        if(confirm('정말 취소 하시겠습니까?')){
        	alert('취소되었습니다.');
        	window.location.href = "./officialList.go";
        }
    });
    
	$('#officialImageUpload').on('change',function(){
		var files = $(this)[0].files;
		
		if(files.length > 0){
			for(file of files){
// 				console.log(file);
				var fileName = file.name;
				var fileSize = file.size;
				if(fileName.endsWith('.png') == false){
					alert('png 확장자만 업로드 가능합니다.');
					$('#officialImageUpload').val('');
					return;
				}
				if(fileSize > 5242880){// 5mb제한
					alert('5mb가 넘습니다.');
					$('#officialImageUpload').val('');
					return;
				}
				
			}
		}
		
	});    
    
</script>
</body>
</html>
