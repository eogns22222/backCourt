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
	<link rel="stylesheet" href="../resources/css/header/header.css">
      <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>    
      <script src="../resources/js/jquery.twbsPagination.js" type="text/javascript"></script>
    <title>신청/예약 리스트</title>
</head>
<body>
	<jsp:include page="../header/header.jsp"/>
	<div class="match_ask_list">
        <h1 class="title">신청/예약 내역</h1>
        <button class="button">공식 경기</button>
        <button class="button">게스트</button>
        <button class="button">구장 예약</button>
        <table class="match_list">
        	<thead id = "listHead" class="match_list_thead">
        	
        	</thead>
            <tbody id="listBody">
                
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
var currentPage = 1; //n번부터
var pagePerNum = 3; //n개 보여줌
var click = '공식 경기'; //페이지 기본값
listCall(currentPage,click); //기본 페이지 출력


// 특정 클래스를 클릭하면 그 클릭한 html을 추출한다
$('.button').on('click',function(){
    var click = $(this).html();
//버튼을 클릭 했을때 아작스를 파괴하고 다시 만든다(그렇지 않으면 데이터가 겹쳐서 제대로 출력 되지 않는다.)
    $('#pagination').twbsPagination('destroy');
    
    //리스트 콜로 currentPage,click를 담아서 보내준다.
    listCall(currentPage,click);
});


    //취소 처리 버튼
    $('.Cancell_button').on('click',function(){
    	console.log($(this));
        /* var cancell = confirm('신청을 취소하시겠습니까?');
        if (cancell) {
            alert('신청 취소가 완료되었습니다');
        } */
    });



//아작스 선언
function listCall(page,Choice){
    $.ajax({
        type:'post',
        url:'./match_ask_list.ajax', // 컨트롤어에 요청
        data:{
            'Choice':Choice,
            'page':page,
            'num':pagePerNum
        },
        dataType:'json',//아작스 방식
        success:function(data){
        	if(data.Choice=='공식 경기'){
        		officialList(data.list);
        		HeadTable();
        	}
        	if(data.Choice=='게스트'){
                gusetList(data.list);
                HeadTable_guset();
            }
        	if(data.Choice=='구장 예약'){
        		courtList(data.list);
        		HeadTable();
        	}
            //console.log("총 페이지 수 : ",data);

        // 플러그인
        $('#pagination').twbsPagination({
    	  startPage:1, // 시작 페이지 [1] (너 몇 페이지야)
    	  totalPages:data.totalPages, // 총 페이지 갯수 (내가 개산 해야함) 
    	  visiblePages:5, //보여줄 페이지 수 [1][2][3][4][5]
    	  onPageClick:function(evt,pg){ //페이지 클릭시 실행 함수
    		  console.log(evt); //이벤트 객체
    		  console.log(pg); //클릭한 페이지 번호
    		  showPage = pg;
    		  listCall(pg,data.Choice);
    	  }
      });

        },
        error:function(error){
            console.log(error);
        }
    });
}


  //리스트 삭제
function del(idx){  
	
	//삭제 아작스
    $.ajax({
        type:'post',
        url:'./match_ask_list_del.ajax',
        data:{
			'idx':idx
        },
        dataType:'json',
        success:function(data){
			console.log('삭제 완료');
        },
        error:function(error){
            console.log(error);
        }
    });
	
    console.log(idx);
}





// 테이블 해더 디자인 (게스트 리스트 (디자인)
function HeadTable_guset(){
	var  HeadContent = '';
	HeadContent += '<tr>';
	HeadContent +='<th class="match_list_th">No.</th>';
    HeadContent +='<th class="match_list_th">경기 날짜</th>';
    HeadContent +='<th class="match_list_th">경기 시간</th>';
    HeadContent +='<th class="match_list_th">신청 상태</th>';
    HeadContent +='<th class="match_list_th"></th>';
    HeadContent += '</tr>';
    $('#listHead').html(HeadContent);
}

// 테이블 해더 디자인 (공식 경기,구장예약 (디자인)
function HeadTable(){
	var  HeadContent = '';
	HeadContent += '<tr>';
	HeadContent +='<th class="match_list_th">No.</th>';
    HeadContent +='<th class="match_list_th">경기 날짜</th>';
    HeadContent +='<th class="match_list_th">경기 시간</th>';
    HeadContent +='<th class="match_list_th"></th>';
    HeadContent += '</tr>';
    $('#listHead').html(HeadContent);
}


// 오피셜 경기 리스트
function officialList(list){
    var Content = '';
	var count = 0;
    for (official of list) {
    	count++;
    	Content += '<tr class="match_list_tbody">';
    	Content += '<td class="match_list_td">'+count+'</td>';
    	// 날짜 오류 수정
    	var date = new Date(official.official_match_date);
        var ModifyDate = date.toLocaleDateString("ko-KR");
    	Content += '<td class="match_list_td">'+ModifyDate+'</td>';
    	Content += '<td class="match_list_td">'+official.time+'</td>';
    	Content += '<td class="match_list_td"><button  class="Cancell_button" onclick="del('+official.official_match_ask_idx+')">신청 취소</button></td>';
    	Content += '</tr>';
    }
    $('#listBody').html(Content);
}

//게스트 경기 리스트
function gusetList(list){
	var Content = '';
	var count=0;
	for (guset of list) {
		count++;
		Content += '<tr class="match_list_tbody">';
		Content += '<td class="match_list_td">'+count+'</td>';
		// 날짜 오류 수정
    	var date = new Date(official.official_match_date);
        var ModifyDate = date.toLocaleDateString("ko-KR");
		Content += '<td class="match_list_td">'+ModifyDate+'</td>';
		Content += '<td class="match_list_td">'+guset.guest_time+'</td>';
        Content += '<td class="match_list_td">'+guset.guest_join_state+'</td>';
        Content += '<td class="match_list_td"><button  class="Cancell_button onclick="del()"">신청 취소</button></td>';
        Content += '</tr>';
	}
	$('#listBody').html(Content);
}

//구장 예약 리스트
function courtList(list){
    var Content = '';
	var count = 0;
    for (court of list) {
    	count++;
    	Content += '<tr class="match_list_tbody">';
    	Content += '<td class="match_list_td">'+count+'</td>';
        Content += '<td class="match_list_td">'+court.court_name+'</td>';
     	// 날짜 오류 수정
    	var date = new Date(official.official_match_date);
        var ModifyDate = date.toLocaleDateString("ko-KR");
        Content += '<td class="match_list_td">'+ModifyDate+'<br/>'+court.court_time+'</td>';
        Content += '<td class="match_list_td"><button  class="Cancell_button onclick="del()"">신청 취소</button></td>';
        Content += '</tr>';
    }
    $('#listBody').html(Content);
}
</script>
</html>