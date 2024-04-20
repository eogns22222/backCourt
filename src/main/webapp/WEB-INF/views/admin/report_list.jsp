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
        content
    </div>
    </div>
</body>
<script>

</script>
</html>