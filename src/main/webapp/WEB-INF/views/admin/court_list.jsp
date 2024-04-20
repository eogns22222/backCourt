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
    	<select id="address">
            <option value="">전체지역</option>
        </select>
        <div><input type="button" id="courtRegist" value="구장 등록"/></div>
        <table>
            <thead>
	            <tr>
	                <th>구장번호</th>
	                <th>구장이름</th>
	                <th>지역</th>
	                <th>활성여부</th>
            	</tr>
            </thead>
            <tbody id="admincourtList"></tbody>
        </table>
		<div class="container">                           
			<nav aria-label="Page navigation" style="text-align:center">
				<ul class="pagination" id="pagination"></ul>
			</nav>               
		</div>
        <select id="searchCategory" >
            <option value="courtName">코트 명</option>
            <option value="courtAddress">지역 명</option>
        </select>
        
        <input type="text" id="searchWord" placeholder="검색단어입력" maxlength="20"/>
        <!-- 이거 누르면 아작스 하는걸로 -->
        <input type="button" id="searchBtn" value="검색" />
    
    </div>
    </div>
</body>
<script>
	var currentPage = 1;
	var filterFlag = false;
	var searchFlag = false;
	
	$(document).on('click', 'td', function(event) {
		 var courtIdx = $(this).closest('tr').find('td:first').text();
		window.location.href = './courtDetail.go?court_idx=' + courtIdx;
	});

	$(document).ready(function() {
	    // 페이지 로드 시 callList 호출
	    callList(currentPage);
	});

	$('#searchBtn').on('click', function(){
	    if($('#searchWord').val() == ''){
	        alert('검색단어를 입력해주세요');
	        return;
	    }
	    currentPage = 1;

	    $('#pagination').twbsPagination('destroy');
	    searchFlag = true;
	    callList(currentPage);

	});	
	
	$('#address').on('change',function(){
		$('#pagination').twbsPagination('destroy');
		searchFlag = false
		callList(currentPage);
		
	});
	
	
	
	function callList(currentPage) {
		
		$.ajax({
			type:'POST'
			,url:'./courtList.ajax'
			,data:{
				'currentPage':currentPage
				,'address':$('#address').val()
				,'searchCategory':$('#searchCategory').val()
				,'searchWord':$('#searchWord').val()
				,'searchFlag':searchFlag
			}
			,dataType:'json'
			,success:function(data){
				console.log(data.list);
// 				console.log(data.totalPage);
				showList(data.list);
				if(filterFlag == false){
					showFilterList(data.allList);
					filterFlag = true;
				}
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
					callList(currentPage);
				}
				
		});
	}
	function showList(list){
		var content = '';
		for(item of list){
			content +=
				'<tr>'
				+'<td>'+item.courtIdx+'</td>'
				+'<td>'+item.courtName+'</td>'
				+'<td>'+item.courtAddress.split(' ')[1]+'</td>'
				+'<td>'+item.courtState+'</td>'
				+'</tr>';
		}
		$('#admincourtList').html(content);
	}

	function showFilterList(list) {
		var content = '';
		var allAddress = [];
		var address = [];
		for(item of list){
			allAddress.push(item.courtAddress.split(' ')[1]);			
		}
		address = Array.from(new Set(allAddress));
		address.sort();
		content = '<option value="">전체 지역</option>';
		for(item of address){
			content += '<option value="'+item+'">'+item+'</option>';
		}

		$('#address').html(content);
		
	}
</script>
</html>