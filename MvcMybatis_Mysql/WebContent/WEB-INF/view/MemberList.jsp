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
<title>MemberList.jsp</title>

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

<!-- 삭제 기능 처리 -->
<script type="text/javascript">

	$(function()
	{
		$(".btnDelete").click(function()
		{
			// 테스트
			//alert("확인~!!!");
			//alert($(this).val());
			
			if (confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href", "memberdelete.action?id=" + $(this).val());
			}
			
		});
		
		$(".btnUpdate").click(function()
		{
			$(location).attr("href", "memberupdateform.action?id=" + $(this).val());
					
		});
	});

</script>


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
				회원 정보 입력
			</div>
			
			<div class="panel-body">
				<form role="form" action="memberinsert.action" method="post">
					
					<div class="form-group">
						<label for="id">
							ID : 
						</label>
						<input type="text" class="form-control" id="id" name="id">
					</div>
					
					<div class="form-group">
						<label for="pw">
							PW : 
						</label>
						<input type="password" class="form-control" id="pw" name="pw">
					</div>
					
					<div class="form-group">
						<label for="name">
							NAME : 
						</label>
						<input type="text" class="form-control" id="name" name="name">
					</div>
					
					<div class="form-group">
						<label for="tel">
							TELEPHONE : 
						</label>
						<input type="tel" class="form-control" id="tel" name="tel">
					</div>
					
					<div class="form-group">
						<label for="email">
							E-MAIL : 
						</label>
						<input type="email" class="form-control" id="email" name="email">
					</div>
					
					<button type="submit" class="btn btn-default btn-sm">SUBMIT</button>
					<button type="button" class="btn btn-default btn-sm btnCancel">CANCEL</button>
					
				</form>
			</div>
		
		</div><!-- close .panel .panel-default -->
		
		<div class="panel panel-default">
			
			<div class="panel-heading">
				회원 정보 출력
			</div>
			
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>NAME</th>
							<th>TEL</th>
							<th>EMAIL</th>
							<th>수정 / 삭제</th>
						</tr>
					</thead>
					<tbody>
						<!-- 
						<tr>
							<td>1</td>
							<td>박가영</td>
							<td>010-2234-5678</td>
							<td>
								<button type="button" class="btn btn-default btn-xs btnUpdate">수정</button>
								<button type="button" class="btn btn-default btn-xs btnDelete">삭제</button>
							</td>
						</tr>
						-->
						<c:forEach var="member" items="${list }">
						<tr>
							<td>${member.id }</td>
							<td>${member.name }</td>
							<td>${member.tel }</td>
							<td>${member.email }</td>
							<td>
								<button type="button" class="btn btn-default btn-xs btnUpdate"
								value="${member.id }">수정</button>
								<button type="button" class="btn btn-default btn-xs btnDelete"
								value="${member.id }">삭제</button>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<button type="button" class="btn btn-default btn-sm" role="badgeFrame">
					<!-- Count <span class="badge" role="badge">1</span> -->
					Count <span class="badge" role="badge">${count }</span>
				</button>
			
			</div>
			
		</div><!-- close .panel .panel-default -->
		
	</div>
</div>



</body>
</html>