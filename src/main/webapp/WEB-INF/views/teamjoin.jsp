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
<script src="resources/js/jquery.twbsPagination.js" type="text/javascript"></script>
</head>
<body>
    <div class="team_join_list">
        <h1>🏀백코트</h1>
    </div>
    
    <h2>팀원 모집리스트</h2>
    <select name="addr">
        <option value="">지역</option>
    </select>
    <select name="position">
        <option value="">Center</option>
        <option value="">Guard</option>
        <option value="">Forward</option>
    </select>
    <select name="level">
        <option value="">브론즈</option>
        <option value="">실버</option>
        <option value="">골드</option>
        <option value="">플레티넘</option>
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
    <select name="team_name">
        <option value="">팀명</option>
        <option value="">지역명</option>
    </select>
    <input type="text" name="team_search" placeholder="내용을 입력해주세요.">
    <button>검색</button>

    <br/>
   
</body>
<script>
var showPage = 1;

$(document).ready(function(){ // html 문서가 모두 읽히면 되면(준비되면) 다음 내용을 실행 해라
	listCall(showPage);
});

$('#pagePerNum').on('change',function(){
	// 페이지당 보여줄 게시물의 숫자를 변경하면 전체 페이지 수의 변화가 생기므로
	// 페이징 처리 객체를 파괴하고 다시 만들도록 해야 한다.
	$('#pagination').twbsPagination('destroy');
	listCall(showPage);
});

function listCall(page){
    $.ajax({
       type:'get',
       url:'./teamjoinlist.ajax',
       data:{
    	   'page':page
       },
       dataType:'json',
       
       success:function(data){
           drawList(data.list);
           console.log(data); 
           
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
 
function drawList(list){
	 var content = '';
	 for(item of list){
	    //console.log(item);
	    content += '<tr  onclick="location.href='.'" class="point">';
	    var img = item.로고 > 0 ? 'image.png' : 'no_image.png';
	    content += '<img class="icon" src="resources/img/' + img + '"/>';
	    
	    content += '<td>' + item.팀명 +'</td>';
	    content += '<td>' + item.팀장아이디 + '</td>';
	    content += '<td>';
	    content += '</td>';
	    content += '<td>' + item.지역 + '</td>';
	    content += '<td>' + item.포지션 + '</td>';
	    content += '<td>' + item.레벨 +'</td>';
	    
	    
	    content += '</tr>';
	 }
	 
	 $('#list').html(content);
	}

</script>
</html>