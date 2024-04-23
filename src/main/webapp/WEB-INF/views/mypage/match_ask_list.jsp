<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="../resources/css/common/reset.css" type="text/css">
    <link rel="stylesheet" href="../resources/css/match_ask_list/match_ask_list.css" type="text/css">
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
      <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>    
      <script src="../resources/js/jquery.twbsPagination.js" type="text/javascript"></script>
    <title>신청/예약 리스트</title>
</head>
<body>
	<div class="match_ask_list">
        <h1 class="title">신청/예약 내역</h1>
        <button class="button">공식 경기</button>
        <button class="button">게스트</button>
        <button class="button">구장 예약</button>
        <table class="oficial_list">
            <thead class="oficial_list_thead">
                <tr>
                    <th class="oficial_list_th">No.</th>
                    <th class="oficial_list_th">경기 날짜</th>
                    <th class="oficial_list_th">경기 시간</th>
                    <th class="oficial_list_th"></th>
                </tr>
            </thead>
            <tbody id="list">
                
            </tbody>
            <tr>
                <!-- 플러그인 사용법 --> 
                   <td colspan="4">
                       <div class="container">                           
                        <nav aria-label="Page navigation" style="text-align:center">
                           <ul class="pagination" id="pagination"></ul>
                        </nav>               
                     </div>
                   </td>
                </tr>
        </table>
    </div>
</body>
<script>
var showPage = 1; //n번부터
var pagePerNum = 3; //n개 보여줌
var click;
listCall();

$('button').on('click',function(){
    var click = $(this).html();
    if(click=='신청 취소'){
        var cn = confirm("신청을 취소하시겠습니까?");
        if(cn){
            alert("신청 취소가 완료되었습니다. ");
        }
    }
    listCall(click);
	console.log(click);
});

//아작스 선언
function listCall(Choice){
    $.ajax({
        type:'post',
        url:'./match_ask_list.ajax', // 컨트롤어에 요청
        data:{
            'Choice':Choice,
            'page':showPage,
            'num':pagePerNum
        },
        dataType:'json',//아작스 방식
        success:function(data){
            drawList(data.list);
            console.log("총 페이지 수 : ",data);

        // 플러그인
        $('#pagination').twbsPagination({
    	  startPage:1, // 시작 페이지 [1] (너 몇 페이지야)
    	  totalPages:data.totalPages, // 총 페이지 갯수 (내가 개산 해야함) 
    	  visiblePages:5, //보여줄 페이지 수 [1][2][3][4][5]
    	  onPageClick:function(evt,pg){ //페이지 클릭시 실행 함수
    		  console.log(evt); //이벤트 객체
    		  console.log(pg); //클릭한 페이지 번호
    		  showPage = pg;
    		  //listCall(pg);
    	  }
      });

        },
        error:function(error){
            console.log(error);
        }
    });
}

function drawList(list){
    var con = '';
	var count = 0;
    for (match of list) {
    	count++;
        con += '<tr>';
        con += '<td>'+count+'</td>';
        con += '<td>'+match.official_match_date+'</td>';
        con += '<td>'+match.official_match_start_time+'</td>';
        con += '<td><button>신청 취소</button></td>';
        con += '</tr>';
    }
    $('#list').html(con);
}
</script>
</html>