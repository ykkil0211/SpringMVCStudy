<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PosList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">

</head>
<body>
	
<!----------------------------------------------------------------
	#15. EmployeeList.jsp
		- 직원 리스트 출력 페이지 
		- 관리자가 접근하는 직원 데이터 출력 페이지
		  (일반 직원이 접근하는 직원 데이터 출력 페이지는
		   EmpList.jsp로 구성할 예정) 
------------------------------------------------------------------>

<div>
	
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 컨텐츠 영역 -->
	<div id="content">
		<h1>[ 직위 관리 (일반직원 전용) ]</h1>
		<hr>
			
		<!---------------------------------------------------
			DEPARTMENTID, DEPARTMENTNAME, DELCHECKE
		----------------------------------------------------->    
		<!-- 15EA -->
		<table id="customers" class="table">
			<tr>
				<th>번호</th>
				<th>직위</th>
				<th>최소 연봉</th>
			</tr>
			
			
<!-- 		
			<tr>
				<td>1</td>
				<td>서울</td>
				<td>수정가능</td>
				<td><button type="button" class="button">수정</button></td>
				<td><button type="button" class="button">삭제</button></td>
			</tr>
			
-->
			
			<c:forEach var="position" items="${positionList }">
			<tr>
				<td>${position.positionId }</td>
				<td>${position.positionName }</td>
 				<td>
					<fmt:formatNumber value="${position.minBasicPay }"
					groupingUsed="true"></fmt:formatNumber>
				</td> 

			</tr>
			</c:forEach>
			
		</table>      
	</div>
	
	<!-- 회사 소개 및 어플리케이션 소개 영역 -->
	<div id="footer">
	
	</div>

</div>

</body>
</html>