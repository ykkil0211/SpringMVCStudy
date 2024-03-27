<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MembetList.jsp</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>

<!-- memberlist.action -->
<!--         ↓        -->
<!-- MemberRecordController → MemberList.jsp    -->
<!-- 								↓			 -->
<!-- 						MemberRecordController → MemberInsertForm.jsp    											-->
<!-- 													    	 ↓			   											-->
<!-- 													     (memberinsert.action  										-->
<!-- 																	↓			 									-->
<!-- 															MemberRecordController → redirect:memberlist.action    -->
<div>
	<h1>회원 명단</h1>
	<hr />
	
	
	<h2><a href="memberinsertform.action">회원등록</a></h2>
</div>

<div>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화</th>
			<th>이메일</th>
		</tr>
<!-- 		<tr>
			<td>superman</td>
			<td>김경태</td>
			<td>010-9998-8887</td>
			<td>kkt@test.com</td>
		</tr> -->
		<c:forEach var="member" items="${dto }">
		<tr>
			<td>${member.id }</td>
			<td>${member.name }</td>
			<td>${member.tel }</td>
			<td>${member.email }</td>
		
		</tr>
		
		</c:forEach>
		
	</table>

</div>
	

</body>
</html>