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
<title>GradeList.jsp</title>
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
<script type="text/javascript">


	$(function()
	{
		
		$(".btnUpdate").click(function()
		{
			$(location).attr("href", "gradeupdateform.action?sid=" + $(this).val());
		}); 

		$(".btnDelete").click(function()
		{
			//테스트
			//alert("확인~!!!");
			//alert($(this).val());
					
 			if (confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href", "gradedelete.action?sid=" + $(this).val());
			}  
					
		}); 
			
	});



</script>
</head>
<body>

<div>
	<h1>성적리스트</h1>
	<hr>
</div>


<div class="container">
	<div class="panel-group">
		<div class="panel panel-default">
		
<!-- 			<div class="panel-body">
				<form role="form" action="gradeinsertform.action" method="get">

					<button type="submit" class="btn btn-default btn-sm">성적추가</button>
					
				</form>
			</div>	 -->	
		
		<div class="panel panel-default">
			
			<div class="panel-heading">
				성적
			</div>
			
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>국어</th>
							<th>영어</th>
							<th>수학</th>
							<th>합계</th>
							<th>평균</th>
							<th>결과</th>
							<th>입력</th>
							<th>수정 / 삭제</th>
						</tr>
					</thead>
					<tbody>
						
  						<c:forEach var="member" items="${list }">
							<tr>
								<td>${member.sid }</td>
								<td>${member.name }</td>
								<td>${member.sub1 }</td>
								<td>${member.sub2 }</td>
								<td>${member.sub3 }</td>
								<td>${member.tot }</td>
								<td>${member.avg }</td>
								<td>${member.ch }</td>
								<td><button type="button" class="btn btn-default btn-xs btn-sm" 
            						onclick="location.href='gradeinsertform.action?sid=${member.sid }'" ${member.sub1==-1?"" : "disabled='disabled'"  }>
            						 ${member.sub1==-1?"입력" : "입력불가"  }
            						</button>
								</td>
								<td>
									<button type="button" class="btn btn-default btn-xs btnUpdate"
									value="${member.sid }">수정</button>
									<button type="button" class="btn btn-default btn-xs btnDelete"
									value="${member.sid }">삭제</button>
								</td>
							</tr>
						</c:forEach>  
					</tbody>
				</table>

				<button type="submit" class="btn btn-default btn-sm"
				onclick="location.href='studentlist.action'">학생정보</button>
				
				<button type="button" class="btn btn-default btn-sm" role="badgeFrame">
					Count <span class="badge" role="badge">${count }</span>
				</button>

			
			</div>
			
		</div><!-- close .panel .panel-default -->
		
	</div>
</div>
</div>

</body>
</html>