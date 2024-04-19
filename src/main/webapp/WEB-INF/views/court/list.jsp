<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

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
        .courtImage{
        	height: 50px;
        	width: auto;
        }
       .courtJjim{
        	height: 50px;
        	width: auto;
        }

    </style>
</head>
<body>
    <select name="date">
        <option value="">현재시간</option>
        <option value=""></option>
    </select>
    <select id="address">
    	<option value="">전체 지역</option>
        <!-- 원래는 반복문 돌리는게 나을꺼 같은데 -->
    </select>
    <br/>
    <br/>
    <table>
		<thead>
			<tr>
				<th>구장 사진</th>
				<th>구장 이름</th>
				<th>지역</th>
				<th>가격</th>
				<th>찜</th>
			</tr>
		</thead>
		<tbody id="list">
			
		</tbody>
        <!-- 여기 원래는 데이터 받아와서 반복문 돌려야됨 -->
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

    <select name="search" >
        <option value="courtName">코트 명</option>
        <option value="courtLocation">지역 명</option>
    </select>
    
    <input type="text" name="searchInput" />
    <!-- 이거 누르면 아작스 하는걸로 -->
    <button onclick="callList()">d</button>
    <div id="paging">페이징처리 여기로</div>
</body>
<script>
	var filterFlag = false;
	$(document).ready(function() {
	    callList();
	});
	
	$('#address').on('change',function(){
		callList();
	});
	
	/*찜 아이콘 눌렀을때 처리 */
	$('#list').on('click', '.courtJjim', function() {
	    var currentJjimImg = $(this).attr('src');
		var imgThis = $(this);
	    console.log($(this));
	    console.log($(this).data('courtidx'));
	    
	    if (currentJjimImg.indexOf('/jjim.png') != -1) {
	        $.ajax({
	        	type:'get'
	        	,url:'./noJjim.ajax'
	        	,data:{
	        		"courtIdx":$(this).data('courtidx')
	        	}
	   			,dataType:'json'
	   			,success:function(data){
	   				console.log(data.result);
	   				console.log($(this));
	   				if(data.result){
	   					imgThis.attr('src', '../resources/img/court/no_jjim.png');
	   				}
	   			}
	   			,error:function(error){
	   				
	   			}
	        });
	    } else {
	    	
	        $.ajax({
	        	type:'get'
	        	,url:'./jjim.ajax'
	        	,data:{
	        		"courtIdx":$(this).data('courtidx')
	        	}
	   			,dataType:'json'
	   			,success:function(data){
	   				console.log(data.result);

	   				console.log($(this));
	   				if(data.result){
	   					imgThis.attr('src', '../resources/img/court/jjim.png');
	   				}
	   		    }
	   			,error:function(error){
	   				
	   			}
	        });
	    }
	});
	
	function callList() {
		console.log('sd');
		$.ajax({
			
			type:'get'
			,url:'./list.ajax'
			,data:{
				'address':$('#address').val()
			}
			,dataType:'json'
			,success:function(data){
				console.log(data.list);
				showList(data.list);
				if(filterFlag == false){
					showFilterList(data.list);
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
			console.log(item.court_idx);
			var img = item.first_img_name != null ? item.first_img_name+'.png':'no_image.png';
			var jjim = item.jjim > 0 ? 'jjim.png':'no_jjim.png';
			content +=
				'<tr>'
				+'<td><img class="courtImage"  src="../resources/img/court/'+img+'" alt="ImageCheck"></td>'
				+'<td>'+item.court_name+'</td>'
				+'<td>'+item.court_address.split(' ')[1]+'</td>'
				+'<td>'+item.court_price+'</td>'
				+'<td><img class="courtJjim" data-courtIdx="'+item.court_idx+'" src="../resources/img/court/'+jjim+'" alt="ImageCheck"></td>'
				+'</tr>';
		}
		$('#list').html(content);
	}

	function showFilterList(list) {
		var content = '';
		var allAddress = [];
		var address = [];
		for(item of list){
			allAddress.push(item.court_address.split(' ')[1]);			
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