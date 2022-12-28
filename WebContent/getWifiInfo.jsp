<%@page import="com.controller.WifiInfoRegisterService"%>
<%@page import="com.controller.WifiService"%>
<%@page import="com.model.WifiInfoDTO"%>
<%@page import="com.api.GetOpenAPI"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Open API WIFI 정보 가져오기</title>
<style>
	h1,div{
		text-align: center;
	}
</style>
</head>
<body>
<%
	WifiService ws = new WifiService();
	//정보 가져오기 전에 테이블에 있는 데이터들 삭제
	ws.deleteAll();
	GetOpenAPI wifiInfo = new GetOpenAPI();
	WifiInfoDTO wifiInfoList = wifiInfo.getWifiInfo();
	WifiInfoRegisterService registerService = new WifiInfoRegisterService();
	int result = registerService.wifiInfoRegister(wifiInfoList);
	if(result > 0){
%>
	<h1><%=wifiInfoList.getTbPublicWifiInfo().getListTotalCount()%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
	<%} else{%>
	<h1> WIFI 정보 저장에 실패하였습니다.</h1>
	<%} %>
	<div>
		<a href = "home.jsp">홈으로 가기</a>
	</div>

</body>
</html>