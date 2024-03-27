<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>

<%
	// 이전 페이지(Send.jsp)로부터 넘어온 데이터 수신 
	// → name, upload
	
	String name = request.getParameter("name");
	String upload = request.getParameter("upload");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Receive.jsp</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>

<div>
	<!-- 이름 : 어쩌구 -->
	이름 : <%=name %> <br>
	<!-- 파일 : 저쩌구 -->
	파일 : <%=upload %> <br>
</div>



</body>
</html>