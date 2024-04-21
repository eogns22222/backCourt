<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>    
<script src="../resources/js/jquery.twbsPagination.js" type="text/javascript"></script>
<style>
    table{
        border: 2px solid;
        border-collapse: collapse;
    }
    th{
        background-color: gainsboro;
    }
    th,td{
        border: 2px solid;
        padding: 10px 30px;
        text-align: center;
    }
    #adminReportListDiv{
        width: 100%;
    }
    tr:hover {
		background-color: lightgray;
	}
    
    </style>
</head>
<body>
	<div class="adminBody">
    	<header class="adminHeader">
        	<div class="headerCont">
            	<a href="admin_logout.do" class="logout">로그아웃</a>
            	<ul class="menu">
	                <li><a href="admin/member_list">회원 관리</a></li>
	                <li><a href="admin/team_list">팀 관리</a></li>
	                <li><a href="admin/court_list">구장 관리</a></li>
	                <li><a href="admin/writing_list">글 관리</a></li>
	                <li><a href="admin/report_list">신고 관리</a></li>
	            </ul>
	        </div>
	    </header>
    <div class="adminContainer">
        <h1>신고/문의 내역</h1>
        <div id="adminReportListDiv">
            처리유형 
            <select id="reportState">
                <option value="">전체</option>
                <option value="처리 전">처리 전</option>
                <option value="처리 중">처리 중</option>
                <option value="처리 완료">처리 완료</option>
            </select>
            <input type="text" id="reportSearch" placeholder="신고/문의자 ID 검색"/>
            <table>
                <thead>
                    <tr>
                        <th>no.</th>
                        <th>처리상태</th>
                        <th>제목</th>
                        <th>신고/문의자 ID</th>
                        <th>신고/문의 날짜</th>
                        <th>관리자</th>
                    </tr>
                </thead>
                <tbody id="reportList"></tbody>
            </table>
            <div class="container">                           
                <nav aria-label="Page navigation" style="text-align:center">
                    <ul class="pagination" id="pagination"></ul>
                </nav>               
            </div>
        </div>
    </div>
    </div>
</body>
<script>
var currentPage = 1;
var searchFlag = false;
	
	$(document).on('click', 'td', function(event) {
		 var reportIdx = $(this).closest('tr').find('td:first').text();
		window.location.href = './reportDetail.go?reportIdx=' + reportIdx;
	});

	$(document).ready(function() {
	    // 페이지 로드 시 callList 호출
	    callList(currentPage);
	});

	$('#reportSearch').on('keydown', function(evt){
		if(evt.keyCode == 13){
			console.log(evt);
		    if($(this).val() == ''){
		        alert('검색단어를 입력해주세요');
		        return;
		    }
		    currentPage = 1;
	
		    $('#pagination').twbsPagination('destroy');
		    searchFlag = true;
		    callList(currentPage);
		}
	});	
	
	$('#reportState').on('change',function(){
		$('#pagination').twbsPagination('destroy');
		searchFlag = false
		callList(currentPage);
		
	});
	
	
	
	function callList(currentPage) {
		
		$.ajax({
			type:'POST'
			,url:'./reportList.ajax'
			,data:{
				'currentPage':currentPage
				,'reportState':$('#reportState').val()
				,'reportSearch':$('#reportSearch').val()
				,'searchFlag':searchFlag
			}
			,dataType:'json'
			,success:function(data){
				console.log(data.list);
// 				console.log(data.totalPage);
				showList(data.list);
				var totalPage = data.totalPage/10 > 1 ? data.totalPage/10:1;
				showPagination(totalPage);
				
			}
			,error:function(error){
				console.log(error);
			}
		});
	}
	function showPagination(totalPage) {
		$('#pagination').twbsPagination({
				startPage:1
				,totalPages:totalPage
				,visiblePages:5	
				,onPageClick:function(evt,pg){
// 					console.log(pg);
				
					currentPage = pg;
// 					callList(currentPage);
				}
				
		});
	}
	function showList(list){
		var content = '';
		for(item of list){
			content +=
				'<tr>'
				+'<td>'+item.reportIdx+'</td>'
				+'<td>'+item.reportState+'</td>'
				+'<td>'+item.reportSubject+'</td>'
				+'<td>'+item.reportId+'</td>'
				+'<td>'+item.reportDate+'</td>'
				+'<td>'+item.reportAdminId+'</td>'
				+'</tr>';
		}
		$('#reportList').html(content);
	}
</script>
</html>