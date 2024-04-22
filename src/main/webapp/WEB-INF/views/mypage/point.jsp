<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="./resources/css/common/reset.css"  type="text/css">
    <link rel="stylesheet" href="./resources/css/point/point.css" type="text/css">
      <script src="./resources/js/jquery.twbsPagination.js" type="text/javascript"></script>
    <title>포인트 리스트</title>
</head>
<body>
	<!-- jsp 붙이는 법 -->
	<%-- <jsp:include page="../header/header.jsp"/> --%>
	  <button id="mon">문의 하기</button>
    <h2>내 포인트 내역</h2>
    <div id="he">
        <br/><br/>
        <h1>포인트 내역</h1>
        <br/><br/>
        <h3>${point }원</h3>
        <br/><br/><br/><br/>
        <button>충전</button>
        <button>환급</button>
    </div>
    <table>
        <thead>
        <tr>
            <th>ON.</th>
            <th>날짜</th>
            <th>금액</th>
            <th>구분</th>
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
</body>
<script>
/* 아작스로 리스트 가져오기 */
//var showPage = 1;

/* 리스트 호출 */
listCall();

/* 아작스로 리스트 만들기 */
	function listCall() {
	    $.ajax({
	        type:'post', //method 방식
	        url:'./point.ajax', //컨트롤러에 리퀘스트 주소
	        data:{
	        	//'page':page	
	        }, //보낼 파라미터 값이 있을때
	        dataType:'JSON', //아작스 타입
	        success:function(data){//리턴된 값을 받는 곳
	           drawList(data.list); //drawList 라는 이름으로 data에 있는 값을 넣어서 넣어준다.
	            console.log("총페이지수",data); 
	           //플러그인 추가
	            $('#pagination').twbsPagination({
	         	  startPage:1, // 시작 페이지 [1] (너 몇 페이지야)
	         	  totalPages:5, // 총 페이지 갯수 (내가 개산 해야함) 
	         	  visiblePages:5, //보여줄 페이지 수 [1][2][3][4][5]
	         	  onPageClick:function(evt,pg){ //페이지 클릭시 실행 함수
	         		  console.log(evt); //이벤트 객체
	         		  console.log(pg); //클릭한 페이지 번호

	         	  }
	            });
	        },
	        error:function(error){ //에허러가 났을 떄
	            console.log(error);
	        }
	    });
	    
	}

//리스트를 출력
function drawList(list){
    var con = '';
    for (lists of list) {
        //console.log(lists);
        con += '<tr>';
        con += '<td>'+lists.point_idx+'</td>';
        con += '<td>'+lists.point_date+'</td>';
        con += '<td>'+lists.point_change+'</td>';
        con += '<td>'+lists.point_state+'</td>';
        con += '<tr>';
    }
    $('#list').html(con);
}






var msg = '${msg}';
if (msg !='') {
	alert(msg);
}

$('button').on('click',function(){
    var butt = $(this).text();
    console.log(butt);
    if(butt=='충전'){
        location.href="point_add.Go";
    }
    if(butt=='환급'){
        location.href="point_minus.Go";
    }
    if(butt=='문의 하기'){
        location.href="report.Go";
    }
});
</script>
</html>