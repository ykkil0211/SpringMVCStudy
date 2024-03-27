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
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MemberUpdateForm.jsp</title>

<!-- 기본 CSS -->
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">

<!-- 부트스트랩 적용 CSS -->
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부트스트랩 부가 테마 CSS -->
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 제이쿼리 적용 JS -->
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>

<!-- 부트스트랩 관련 스크립트 적용 JS -->
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

<div>
	<h1>회원 정보</h1>
	<hr>
</div>

<div class="container">
	<div class="panel-group">
		<div class="panel panel-default">
		
			<div class="panel-heading" id="title">
				회원 정보 수정
			</div>
			
			<div class="panel-body">
				<form role="form" action="memberupdate.action" method="post">
					
					<div class="form-group">
							ID : <span style="color: gray; font-weight: bold;">${member.id }</span>
						<input type="hidden" id="id" name="id" value="${member.id }">
					</div>
					
					<div class="form-group">
						<label for="pw">
							PW : 
						</label>
						<input type="password" class="form-control" id="pw" name="pw"
						value="${member.pw }">
					</div>
					
					<div class="form-group">
						<label for="name">
							NAME : 
						</label>
						<input type="text" class="form-control" id="name" name="name" value="${member.name }">
					</div>
					
					<div class="form-group">
						<label for="tel">
							TELEPHONE : 
						</label>
						<input type="tel" class="form-control" id="tel" name="tel" value="${member.tel }">
					</div>
					
					<div class="form-group">
						<label for="email">
							E-MAIL : 
						</label>
						<input type="email" class="form-control" id="email" name="email"
						 value="${member.email }">
					</div>
					
					<button type="submit" class="btn btn-default btn-sm">SUBMIT</button>
					<button type="button" class="btn btn-default btn-sm btnCancel">CANCEL</button>
					
				</form>
			</div>
		
		</div><!-- close .panel .panel-default -->
	</div>
</div>



</body>
</html>