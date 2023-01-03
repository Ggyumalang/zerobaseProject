<%@page import="com.model.LocInqHistDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.controller.WifiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위치 히스토리 목록</title>
<style>
table {
	width: 100%;
}

thead {
	border-width: 0px;
	background-color: #81DAE3;
	color: white;
}

#button {
	border-width: 0px;
	background-color: #00a3d2;
	color: white;
}

tbody {
	background-color: #D6D6D6;
	border: solid 1px #000;
	text-align: center;
}
</style>
</head>
<body>
	<%
		WifiService ws = new WifiService();
		List<LocInqHistDTO> list = ws.LocInqHistSelect();
	%>

	<h1>위치 히스토리 목록</h1>
	<div>
		<a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a
			href="getWifiInfo.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>위도</th>
				<th>경도</th>
				<th>조회일자</th>
				<th>비고
				<th>
			</tr>
		</thead>

		<tbody>
			<%			
				for(LocInqHistDTO lih : list){
			%>
			<tr>
				<td><%= lih.getInqNo() %></td>
				<td><%= lih.getLat() %></td>
				<td><%= lih.getLnt() %></td>
				<td><%= lih.getInq_dtm() %></td>
				<td><button id="delete"
						onclick="location.href = 'DeleteHistService.do?inqNo=<%= lih.getInqNo() %>'">
						삭제</button></td>
			</tr>
			<%	
				}
			%>
		</tbody>
	</table>
</body>
</html>