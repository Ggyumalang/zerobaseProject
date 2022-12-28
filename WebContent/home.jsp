<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.NearDistDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
	<style>
		table {
			width : 100%;
		}
		
		thead{
			border-width : 0px;
			background-color : #81DAE3;
			color: white;
		}
		
		#button{
			border-width : 0px;
			background-color : #00a3d2;
			color: white;
		}
		th, td {
			border-width : 0px;
		}
		
		tbody {
			background-color : #D6D6D6;
			border : solid 1px #000;
			text-align: center;
		}
		
	</style>
</head>
<body>
	
	<h1> 와이파이 정보 구하기</h1>
	<div>
		<a href = "home.jsp">홈</a> |
		<a href = "history.jsp">위치 히스토리 목록</a> |
		<a href = "getWifiInfo.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	<br>
	<div>
		<form action = "NearWifiSearchService.do" method="post">
			<strong>LAT:</strong>
			<input type = "text" value = "0.0" name = "lat" id = "lat">
			<strong>&nbsp&nbspLONG:</strong>
			<input type = "text" value = "0.0" name = "long" id = "long">
			<input type = "button" value = "내 위치 가져오기" onclick="getUserLocation()" id = "button">
			<button type = "submit" id = "button">근처 WIFI 정보 보기</button>
		</form>
	</div>
	
	<script>     
        function getUserLocation() {
           if (!navigator.geolocation) {
                throw "위치 정보가 지원되지 않습니다.";
            }
         	// 사용자의 현재 위치 정보를 요청합니다.
        	navigator.geolocation.getCurrentPosition((position) => {
        		  document.getElementById("lat").value = position.coords.latitude;
        		  document.getElementById("long").value = position.coords.longitude;
        	});
        }
       
    </script>
    
	<table>
		<thead>
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>wifi접속환경</th>
				<th>위도</th>
				<th>경도</th>
				<th>작업일자</th>
			</tr>
		</thead>
		<tbody>
			<%			
			            Enumeration<String> enums = request.getAttributeNames();
						boolean isContains = false;
						while(enums.hasMoreElements()){
							if(enums.nextElement().equals("histList")){
								isContains = true;
							}
						}
						List<NearDistDTO> list = new ArrayList<>(); 
						if(isContains){
							list = (List<NearDistDTO>)request.getAttribute("histList");
						}else{ %>
							<tr height = "100">
								<td colspan="17">
									<b>위치정보를 입력한 후에 조회해 주세요</b>
								</td>
							</tr>
						<%}
						for(NearDistDTO ndt : list){
					%>
						<tr>
							<td><%= ndt.getDistance() %> </td>
							<td><%= ndt.getXSwifiMgrNo() %> </td>
							<td><%= ndt.getXSwifiWrdofc() %> </td>
							<td><%= ndt.getXSwifiMainNm() %> </td>
							<td><%= ndt.getXSwifiAdres1() %> </td>
							<td><%= ndt.getXSwifiAdres2() %> </td>
							<td><%= ndt.getXSwifiInstlFloor() %> </td>
							<td><%= ndt.getXSwifiInstlTy() %> </td>
							<td><%= ndt.getXSwifiInstlMby() %> </td>
							<td><%= ndt.getXSwifiSvcSe() %> </td>
							<td><%= ndt.getXSwifiCmcwr() %> </td>
							<td><%= ndt.getXSwifiCnstcYear() %> </td>
							<td><%= ndt.getXSwifiInoutDoor() %> </td>
							<td><%= ndt.getXSwifiRemars3() %> </td>
							<td><%= ndt.getLat() %> </td>
							<td><%= ndt.getLnt() %> </td>
							<td><%= ndt.getWorkDttm() %> </td>
						</tr>
					<%	
						}
					%>
		</tbody>
	</table>
</body>
</html>