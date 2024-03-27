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
<title>Send.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">
</head>
<body>

<div>
	<h1>파일 시스템 및 파일 업로드</h1>
	<hr>
</div>

<div>
	<!-- 『multipart/form-data』 : 파일을 물리적으로 업로드 하기 위한 속성 -->
	<!-- 『method="post』 : 파일을 물리적으로 업로드 하기 위한 요청 및 전송 방식 -->
	<form action="Receive.jsp" method="post" enctype="multipart/form-data">
	
	 이름 : <input type="text" name="name" />
	<br />
	
	<!-- 파일 업로드 대화창 구성 -->
	파일 : <input type="file" name="upload"/>
	<br />
	
	<!-- submit 액션 처리 -->
	<button type="submit" class="btn">전송</button>
	
	</form>
</div>

<!-- 

	※ 『Send.jsp』 의 form 엘리먼트 enctype 속성의 속성값을 
	   『enctype="multipart/form-data"』와 같이 구성할 경우 
	   form 의 내용을 바이너리 값의 형태로 전송한다는 의미로 활용되며,
	   이와 같은 방식을 통해 전송할 경우 수신된 데이터는
	   
	   이름 : null
	   파일 : null
	   
	   로 확인됨 
	   
	   ==> 이는, 바이너리 값을 getParameter() 를 통해 수신할 수 없다는 의미임 

 -->



</body>
</html>