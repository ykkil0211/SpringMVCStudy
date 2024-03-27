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
<title>GradeInsertForm.jsp</title>
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


	$(document).ready(function()
	{
		
		//테스트 
		//alert("테스트~!!!");
		
		$(".btn-sm").click(function()
		{
		    if($("#sub1").val() >= 100 || $("#sub2").val() >= 100 || $("#sub3").val() >= 100)
		    {
		        $("#err").html("필수 입력 과목 점수가 100점이 넘었습니다.");
		        $("#err").css("display", "inline");
		        
		        event.preventDefault();
		        
		        return;  
		    }
		
		    
		});
	});
	
</script>
</head>
<body>

<div>
	<h1>성적 추가</h1>
	<hr>
</div>


<div class="container">
	<div class="panel-group">
		<div class="panel panel-default">
		
			<div class="panel-heading" id="title">
				성적 정보 입력
			</div>
			
			<div class="panel-body">
				<form role="form" action="gradeinsert.action?sid=${param.sid}" method="post">
					<div class="form-group">
					    번호 : 
					    <input type="text" class="form-control" id="sid" value="${param.sid}" disabled="disabled">
					</div>
					<div class="form-group">
						<label for="sub1">
							국어 : 
						</label>
						<input type="text" class="form-control" id="sub1" name="sub1">
					</div>

					<div class="form-group">
						<label for="sub2">
							영어 : 
						</label>
						<input type="text" class="form-control" id="sub2" name="sub2">
					</div>
					
					<div class="form-group">
						<label for="sub3">
							수학 : 
						</label>
						<input type="text" class="form-control" id="sub3" name="sub3">
					</div>					
				
					
					<button type="submit" class="btn btn-default btn-sm">추가하기</button>
					<button type="reset" class="btn btn-default btn-sm btnCancel">CANCEL</button>
					
				</form>
			</div>
		
		</div><!-- close .panel .panel-default -->
					<span id="err" style="color: red; font-weight: bold; display: none;"></span>
	</div>
</div>

</body>
</html>