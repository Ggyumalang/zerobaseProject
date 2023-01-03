<%@page import="com.model.LocInqHistDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.controller.WifiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위치 히스토리 목록 삭제 실패</title>
</head>
<body>
	<%
		int inqNo = Integer.parseInt(String.valueOf(request.getAttribute("inqNo")));
	%>
	<h1> ID <%= inqNo %> 의 히스토리 목록 삭제에 실패하였습니다... </h1>
	<div>
		<a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a>
	</div>
</body>
</html>
