<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>팀원 리스트</title>
<link rel="stylesheet" href="../resources/css/common/common.css" type ="text/css">
<!-- <link rel="stylesheet" href="../resources/css/common/reset.css" type ="text/css"> -->
<link rel="stylesheet" href="../resources/css/header/header.css" type ="text/css">
<link rel="stylesheet" href="../resources/css/teamjoin/teamjoin.css" type ="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="../resources/js/jquery.twbsPagination.js" type="text/javascript"></script>
</head>
<body>
    <div class="team_join_list">
        <h1>🏀백코트</h1>
    </div>
    
    <h2>팀원 모집리스트</h2>
    <select id="teamjoinaddr">
        <option value="">전체 지역</option>
    </select>
    <select id="teamjoinpos">
        <option value="">모집 포지션</option>
    </select>
    <select id="teamjoinlevel">
        <option value="">모집 레벨</option>
    </select>
    
    <table>
        <thead>
           <tr>
              <th>로고</th>
              <th>팀명</th>
              <th>팀장아이디</th>
              <th>지역</th>
              <th>포지션</th>
              <th>레벨</th>
           </tr>
           </thead>
           <tbody id="list"></tbody>
           <tr>
               <td colspan="7">
                   <div class="container">                     
                     <nav aria-label="Page navigation" style="text-align:center">
                         <ul class="pagination" id="pagination"></ul>
                     </nav>               
                 </div>
               </td>
           </tr>
        </table>
    <br/>
    
    <select id="search">
        <option value="teamJoinName">팀 명</option>
        <option value="teamJoinLoc">지역 명</option>
    </select>
    
    <input type="text" name="searchTeamJoin" placeholder="내용을 입력해주세요."/>
    <button onclick="callList">검색</button>
	<div id="paging">페이징처리 여기</div>
    <br/>
   
</body>
<script>
var filterFlag = false;
var showPage = 1;
$(document).ready(function(){ // html 문서가 모두 읽히면 되면(준비되면) 다음 내용을 실행 해라
	callList();
});

$('#teamjoinaddr').on('change',function(){
	callList1();
});
$('#teamjoinpos').on('change',function(){
	callList2();
});
$('#teamjoinlevel').on('change',function(){
	callList3();
});

$('#pagePerNum').on('change',function(){
	// 페이지당 보여줄 게시물의 숫자를 변경하면 전체 페이지 수의 변화가 생기므로
	// 페이징 처리 객체를 파괴하고 다시 만들도록 해야 한다.
	$('#pagination').twbsPagination('destroy');
	listCall(showPage);
});

function listCall(page){
	console.log('리스트콜');
    $.ajax({
    	
       type:'get',
       url:'./teamjoinpage.ajax',
       data:{
    	   'page':page
       },
       dataType:'json',
       
       success:function(data){
           console.log(data); 
           drawList(data.list);
           
        // 플러그인 추가
           
           var startPage = data.currPage >data.totalPages ? data.totalPages : data.currPage;
           
           $('#pagination').twbsPagination({
         	  startPage:startPage,		// 시작페이지
         	  totalPages:data.totalPages, 	// 총 페이지 갯수
         	  visiblePages:5,	// 보여줄 페이지 수 [1][2][3][4][5]
         	  onPageClick:function(evt, pg){ // 페이지 클릭시 실행 함수
         		  console.log(evt);//이벤트 객체
         		  console.log(pg);//클릭한 페이지 번호
         		  showPage = pg;
         		  listCall(pg);
         	  }
           });                    
        },
        error:function(error){
           console.log(error)
        }
     });
 }
 
function callList1() {
	console.log('가즈아');
	$.ajax({
		
		type:'get'
		,url:'./teamjoinaddr.ajax'
		,data:{
			'teamjoinaddr':$('#teamjoinaddr').val()
		}
		,dataType:'json'
		,success:function(data){
			console.log(data.addrlist);
			showList(data.addrlist);
			
			if(filterFlag == false){
				showFilterList1(data.addrlist);
				filterFlag = true;
			}
		}
		,error:function(error){
			console.log(error);
		}
	});
function callList2() {
	console.log('가즈아');
	$.ajax({
		
		type:'get'
		,url:'./teamjoinpos.ajax'
		,data:{
			'teamjoinpos':$('#teamjoinpos').val()
		}
		,dataType:'json'
		,success:function(data){
			console.log(data.list);
			showList(data.list);
			
			if(filterFlag == false){
				showFilterList2(data.list);
				filterFlag = true;
			}
		}
		,error:function(error){
			console.log(error);
		}
	});
function callList3() {
	console.log('가즈아');
	$.ajax({
		
		type:'get'
		,url:'./teamjoinlevel.ajax'
		,data:{
			'teamjoinlevel':$('#teamjoinlevel').val()
		}
		,dataType:'json'
		,success:function(data){
			console.log(data.list);
			showList(data.list);
			
			if(filterFlag == false){
				showFilterList3(data.list);
				filterFlag = true;
			}
		}
		,error:function(error){
			console.log(error);
		}
	});
}
 
function showList(list){
	 var content = '';
	 for(item of list){
		 cosole.log(item.join_team_idx);
	    content +=
	    	'<tr>'
	    +'<td><img class="teamLogo" src="../resources/img/teamLogo/test_logo2.jpg" alt="ImageCheck"/></td>'
	    + '<td>' + item.team_name +'</td>'
	    + '<td>' + item.id + '</td>'
	    + '<td>' + item.team_address + '</td>'
	    + '<td>' + item.join_to_position + '</td>'
	    + '<td>' + item.join_to_level +'</td>'
	    + '</tr>';
	 }
	 
	 $('#list').html(content);
	}
	
function showFilterList1(list) {
	var content = '';
	var allTeamJoinAddress = [];
	var teamJoinAddress = [];
	for(item of list){
		allTeamJoinAddress.push(item.team_address.split(' ')[1]);			
	}
	teamJoinAddress = Array.from(new Set(allTeamJoinAddress));
	teamJoinAddress.sort();
	content = '<option value="">전체 지역</option>';
	for(item of teamJoinAddress){
		content += '<option value="'+item+'">'+item+'</option>';
	}

	$('#teamjoinaddr').html(content);
	
}

function showFilterList2(list) {
	var content = '';
	var allTeamJoinPosition = [];
	var teamJoinPosition = [];
	for(item of list){
		allTeamJoinPosition.push(item.join_to_position.split(' ')[1]);			
	}
	teamJoinPosition = Array.from(new Set(allTeamJoinPosition));
	teamJoinPosition.sort();
	content = '<option value="">전체 지역</option>';
	for(item of teamJoinPosition){
		content += '<option value="'+item+'">'+item+'</option>';
	}

	$('#teamjoinpos').html(content);
	
}

function showFilterList3(list) {
	var content = '';
	var allTeamJoinLevel = [];
	var teamJoinLevel = [];
	for(item of list){
		allTeamJoinLevel.push(item.join_to_level.split(' ')[1]);			
	}
	teamJoinLevel = Array.from(new Set(allTeamJoinLevel));
	teamJoinLevel.sort();
	content = '<option value="">전체 지역</option>';
	for(item of teamJoinLevel){
		content += '<option value="'+item+'">'+item+'</option>';
	}

	$('#teamjoinlevel').html(content);
	
}


</script>
</html>