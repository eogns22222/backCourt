	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>제목 입력</title>
	<link rel="stylesheet" href="../resources/css/common/reset.css">
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
	<script src="../resources/js/jquery.twbsPagination.js" type="text/javascript"></script>
	<link rel="stylesheet" href="../resources/css/header/header.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>
	<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<style>
		.teammateUpdateTable, .teammateUpdateTh, .teammateUpdateTd {
			border: 1px solid black;
			border-collapse: collapse;
		}
		
		.teammateUpdateTh, .teammateUpdateTd {
			padding: 5px 10px;
		}

		#datepicker {
			margin-bottom: 20px;
		}
		
		#teammateUpdateInfo {
			resize: none;
			width: 500px;
			height: 150px;
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

		.teammateUpdateTimeBtn{
			background-color : skyblue;
		}
	.black_overlay{
		display: none;
		position: absolute;
		top: 0%;
		left: 0%;
		width: 100%;
		height: 100%;
		background-color: black;
		z-index:1001;
		-moz-opacity: 0.8;
		opacity:.80;
		filter: alpha(opacity=80);
	}

	.white_content {
		display: none;
		position: absolute;
		top: 25%;
		left: 25%;
		width: 50%;
		height: 50%;
		padding: 16px;
		border: 1px solid black;
		background-color: white;
		z-index:1002;
		overflow: auto;
	}
		.teammateTable{
				width: 100%;
		}
		.teammateTable, .teammateTh, .teammateTd {

			border: 1px solid black;
			border-collapse: collapse;
		}
		
		.teammateTh, .teammateTd {
			padding: 5px 10px;
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
						<li><a href="../admin/teammate_list">공식 경기 관리</a></li>
						<li><a href="../admin/writing_list">글 관리</a></li>
						<li><a href="../admin/report_list">신고 관리</a></li>
					</ul>
				</div>
			</header>
			<div class="adminContainer">
				<h1>팀 관리</h1>
				<div class="leftCont">
				    <div class="teammateinfocont">
				        <div class="flexBox">
				            <div class="logo">
				<%--                 <img class="teammateLogo" src="../resources/img/teamLogo/${teammateDetail.logo}.jpg" alt="teammateLogo">> --%>
				                <img class="teammateLogo" src="/logo/${teammateDetail.logo}.jpg" alt="teammateLogo">>
				            </div>
				            <div>
				                <a class="teammateReportBtn">신고하기</a>
				                <h3 class="teamName">${teammateDetail.team_name}</h3>
				                <p class="teamLeader"><span>${teammateDetail.id}</span></p>
				            </div>
				        </div>
				
				        <p class="teamAddress">지역: <span>${teammateDetail.team_address}</span></p>
				        <p class="teammateLevel">모집 레벨: <span>${teammateDetail.join_team_level}</span></p>
				        <p class="teammateGender">모집 성별: <span>${teammateDetail.join_to_gender}</span></p>
				        <p class="teammatePositions">모집 포지션: <span>${teammateDetail.join_team_position}</span></p>
				        <p class="tj">
				        <a class="teammateJoinBtn">가입 신청하기</a>
				        </p>
   					 </div>
			</div>
		</div>
	</body>
	<script>
		var teammateIdx = '${teamInfo.team_idx}';
		var currentPage = 1;
		console.log('${teamInfo.team_idx}');
		console.log('${list[0].teamjang}');
		console.log('${list[0].id}');
		console.log('${totalPage}');
		
		function callteammateList(currentPage) {
			$.ajax({
				url:'./teammateList.ajax'
				,type:'POST'
				,dataType:'json'
				,data:{
					"currentPage":currentPage
				}
				,success:function(data){
					console.log(data);
					showList(data.list);
					$('#pagination').twbsPagination({
						startPage:1
						,totalPages:data.totalPage
						,visiblePages:5	
						,onPageClick:function(evt,pg){
							currentPage = pg;
							callteammateList(currentPage);
						}
						
					});
				}
				,error: function(xhr, status, error) {
					console.error("AJAX request failed:", status, error);
				}
			})

		}


		

		$('#teammateRegisterCancel').on('click',function(){
			if(confirm('정말 취소 하시겠습니까?')){
				alert('취소되었습니다.');
				window.location.href = "./teammateList.go";
			}
		});

		
		function showList(list){
			console.log(list);
			var content = '';
			for(item of list){
				content +=
					'<tr class="teammateListTr">'
					+'<td class="teammateListTd">'+item.teammateIdx+'</td>'
					+'<td class="teammateListTd">'+item.teammateName+'</td>'
					+'<td class="teammateListTd">'+item.teammateAddress.split(' ')[1]+'</td>'
					+'</tr>';
			}
			$('#list').html(content);
		    $('.teammateListTr').hover(function() {
		        $(this).css('background-color', 'lightgray');
		    }, function() {
		        $(this).css('background-color', '');
		    });
		    $('.teammateListTr').click(function() {
		        teammateIdx = $(this).find('.teammateListTd:first').text();
				$('#light').css('display', 'none');
				$('#fade').css('display', 'none');
		        callteammateInfo(teammateIdx);
		        console.log('선택한 행의 idx:', teammateIdx);
		    });
			
		}
		
		
			
		

		
	</script>

	</html>
