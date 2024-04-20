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
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }

    th, td {
        padding: 5px 10px;
    }

    button {
        margin: 5px
    }

    textarea {
        resize: none;
        width: 500px;
        height: 150px;
    }

    input[type="text"] {
        width: 100%;
    }
    input[type="button"] {
        
    }
    #buttonDiv{
        text-align: right;
    }
    </style>
</head>
<body>
    <table>
        <tr>
            <th>글 유형</th>
            <td id="reprotInfoCategory"></td>
        </tr>
        <tr>
            <th>글 번호</th>
            <td id="reportInfoIdx"></td>
        </tr>
        <tr>
            <th>신고자ID</th>
            <td id="reportInfoUserId"></td>
        </tr>
        <tr>
            <th>신고날짜</th>
            <td id="reportInfoDate"></td>
        </tr>
        <tr>
        	<th>처리 상태</th>
        	<td>
        		<select>
        			<option value="처리전">처리전</option>
        			<option value="처리중">처리중</option>
        			<option value="처리완료">처리완료</option>
        		</select>
        	</td>
        </tr>
        <tr>
            <th>제목</th>
            <td id="reprotInfoSubject"></td>
        </tr>
        <tr>
            <th>내용</th>
            <td id="reportInfoContent"></td>
        </tr>
        <tr>
            <th>피드백</th>
            <td><textarea id="reportFeed" maxlength="300"></textarea></td>
        </tr>
        <tr>
            <td colspan="2">
                <div id="buttonDiv">
                    <input type="button" value="취소">
                    <input type="button" value="전송">
                </div>
            </td>
        </tr>
    </table>
</body>
<script>
    $(document).ready(function(){
        showReportInfo();
    });

    function showReportInfo(){
        $.ajax({
            type:'post'
            , url:'./detail.ajax'
            , data:{

            }
            , dataType:'json'
            , success:function(data){

            }
            , error:function(error){
                
            }
        });
    }
</script>
</html>