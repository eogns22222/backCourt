<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/common/reset.css">
<link rel="stylesheet" href="resources/css/header/header.css">
<link rel="stylesheet" href="resources/css/match_list/match_list.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>

</style>
</head>
<body>
	<jsp:include page="../header/header.jsp"/>
	<div class="content">
        <div class="filterCont">
            <!-- 지역 -->
            <select id="address">
                <option value="">전체 지역</option>
            </select>
    
            <!-- 날짜 -->
            <select id="date">
                <option value="">날짜</option>
            </select>
    
            <!-- 레벨 -->
            <select id="level">
                <option value="브론즈">브론즈</option>
                <option value="실버">실버</option>
                <option value="골드">골드</option>
                <option value="플레티넘">플레티넘</option>
            </select>
        </div>
    
        <div class="table">
            <table>
                <colgroup>
                    <col width="5%"/>
                    <col width="10%"/>
                    <col width="50%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                </colgroup>
                <tr>
                    <th></th>
                    <th>경기 날짜</th>
                    <th>지역</th>
                    <th>레벨</th>
                    <th>모집인원</th>
                    <th>상태</th>
                </tr>
                <tr>
                    <td class="num">1</td>
                    <td class="gameDate">5월5일</td>
                    <td class="address"><a href="#">서울시 금천구</a></td>
                    <td class="level">골드</td>
                    <td>
                        <span class="present">5</span> / 
                        <span class="Recruitment">10</span>
                    </td>
                    <td class="state">모집중</td>
                </tr>
                <tr>
                    <td class="num">1</td>
                    <td class="gameDate">5월5일</td>
                    <td class="address"><a href="#">서울시 금천구</a></td>
                    <td class="level">골드</td>
                    <td>
                        <span class="present">5</span> / 
                        <span class="Recruitment">10</span>
                    </td>
                    <td class="state finish">모집중</td>
                </tr>
            </table>
        </div>
        
    
        <div class="searchBox">
            <input type="text" name="searchInput" />
            <button onclick="callList()">d</button>
        </div>
    
        <div id="paging"></div>
    </div>
</body>
<script>
    // 퍼블 영역
    $(function(){
        // 공식 경기
        $('.menu li').eq(0).children('a').addClass('on');
    })

</script>
</html>

