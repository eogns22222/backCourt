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
	div{
		height: 150;
	}
    table{
         border: 2px solid;
        border-collapse: collapse;
    }
    th{
    	background-color: gainsboro;
    }
    th,td{
        border: 2px solid;
        padding: 10px 50px;
        text-align: center;
    }



    </style>
</head>
<body>
	<div>헤더</div>
	<h2>찜 내역</h2>
    <input type="button" value="선택 삭제">
    <table>
        <thead>
            <tr>
                <th><input type="checkbox" id="allCheck"/></th>
                <th>No.</th>
                <th>이름</th>
                <th>위치</th>
                <th>가격</th>
            </tr>
        </thead>
        <tbody class="jjimList">

        </tbody>
        <tr>
			<td colspan="5">
				<div class="container">                           
				  <nav aria-label="Page navigation" style="text-align:center">
					  <ul class="pagination" id="pagination"></ul>
				  </nav>               
				 </div>
			</td>
		</tr>
    </table>
	<div>푸터</div>
</body>
<script>
    var currentPage = 1;
    
    $('input[value="선택 삭제"]').on('click',function(){

        if(!confirm('선택한 구장을 찜목록에서 삭제하시겠습니까?')){
            return;
        }

        // 체크된 체크박스의 data-jjimIdx 값을 저장할 배열
        var selectedIdx = [];
        
        // .rowCheck 체크박스 중 체크된 것들을 반복하여 처리
        $('.rowCheck:checked').each(function() {
            // 체크된 체크박스의 data-jjimIdx 값을 배열에 추가
            selectedIdx.push($(this).data('jjimidx'));
        });
        
        // 선택된 체크박스의 data-jjimIdx 값들을 콘솔에 출력
        console.log(selectedIdx);
        
        $.ajax({
        	type:'POST'
        	, url:'./jjimDel.ajax'
        	, data:{
        		'selectedIdxList':selectedIdx
        	}
        	, dataType:'json'
        	, success:function(data){
        		currentPage = 1;
        		$('#pagination').twbsPagination('destroy');
        		callList(currentPage);
        	}
        	, error:function(error){
        		
        	}
        });
    	
    });
    
	$(document).ready(function() {
	    callList(currentPage);
	});
	
	$('#allCheck').on('click',function(){
		var state = $(this).prop('checked');
		$('.rowCheck').prop('checked', state);
	});
	
    function callList(currentPage){
        $.ajax({
            type:'POST'
            , url:'./jjimList.ajax'
            , data:{
                'currentPage':currentPage
            }
            , dataType:'json'
            , success:function(data){
                showList(data.list);
                var totalPage = data.totalPage/10 > 1 ? data.totalPage/10:1;
                console.log(data.totalPage+' '+totalPage);
                $('#pagination').twbsPagination({
                    startPage:1
                    , totalPages:totalPage
                    , visiblePages:5
                    , onPageClick:function(evt, pg){
                        currentPage = pg;
                        callList(currentPage);
                    }
                });
            }
            , error:function(){

            }
        });
    }
    function showList(list){
        var content = '';
        var count = 1;
        for (item of list) {
            content +=
                '<tr>'
                + '<td><input class="rowCheck" type="checkbox" data-jjimIdx="'+item.jjimIdx+'"/></td>'
                + '<td>'+count+'</td>'
                + '<td>'+item.courtName+'</td>'
                + '<td>'+item.courtAddress.split(' ')[1]+'</td>'
                + '<td>'+item.courtPrice+'</td>'
                + '</tr>';
            count++;
        }
        $('.jjimList').html(content);
    }
</script>
</html>