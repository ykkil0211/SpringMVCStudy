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
<title>RegionUpdateForm.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/jquery-ui.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp %>/js/jquery-ui.js"></script>

<script type="text/javascript">

	$(document).ready(function()
	{	
		// 직원 추가 버튼이 클릭되었을 때 수행되어야 할 코드 처리 
		$("#submitBtn").click(function()
		{
			//확인 
			//alert("확인");
			
			// 데이터 검사 (누락된 입력값이 있는지 없는지에 대한 여부 확인)
			
 			if($("#regionName").val() == "" )
			{
				$("#err").html("필수 입력 항목이 누락되었습니다.");
				$("#err").css("display", "inline");
				
				return;								// submit 액션 중단 처리
			}
			
			// 폼 submit 액션 처리 
			$("#regionForm").submit();
			
		});
	});	

</script>

</head>
<body>

<div>

	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div> 
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
	
		<h1>[ 지역 수정 ]</h1>
		<hr>
		
		<form action="regionupdate.action" method="post" id="regionForm">
			<table>
				<tr>
					<th>지역 번호</th>
					<td>
						<input type="text" id="regionId" name="regionId" 
						value="${region.regionId }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>지역 이름</th>
					<td>
						<input type="text" id="regionName" name="regionName" 
						value="${region.regionName }" >
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<br /><br />
						<button type="button" class="btn" id="submitBtn"
						style="width: 40%; height: 50%">지역 수정</button>
						<button type="button" class="btn" id="listBtn"
						style="width: 40%; height: 50%"
						onclick="location.href='regionlist.action'">지역 리스트</button>
						<br><br>
						
						<span id="err" style="color: red; font-weight: bold; display: none;">
						</span>
						
					</td>
				</tr>
				
			</table>
		</form>
	
	</div>
	
	<!-- 회사 소개 및 어플리케이션 소개 영역 -->
	<div id="footer">
		
	</div>

</div>



</body>
</html>