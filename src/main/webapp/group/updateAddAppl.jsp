<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script>
		
			function updateApplGroup()
			{
		        if(!confirm("정말 수정하시겠습니까?")){
					return;
					
		        }else{
		        	// form 데이터 가져오기
		        	var form = document.getElementById('MyForm');
		        	var formData = new FormData(form);

		        	// JSON으로 변환
		        	var jsonData = Object.fromEntries(formData);

		        	//모임 리스트
					$.ajax ({
						url	: "/app/group/updateApplGroup", // (Required) 요청이 전송될 URL 주소
						type	: "POST", // (default: ‘GET’) http 요청 방식
						async : true,  // (default: true, asynchronous) 요청 시 동기화 여부
						cache : true,  // (default: true, false for dataType 'script' and 'jsonp') 캐시 여부
						timeout : 3000, // (ms) 요청 제한 시간 안에 완료되지 않으면 요청을 취소하거나 error 콜백 호출
						data  : JSON.stringify(
						 			jsonData
						 		), // 요청 시 전달할 데이터
						processData : true, // (default: true) 데이터를 컨텐트 타입에 맞게 변환 여부
						contentType : "application/json", // (default: 'application/x-www-form-urlencoded; charset=UTF-8')
						dataType    : "json", // (default: Intelligent Guess (xml, json, script, or html)) 응답 데이터 형식
						beforeSend  : function () {
						  // XHR Header 포함, HTTP Request 하기전에 호출
						  
						},
						success : function(data, status, xhr) {
							alert("수정이 완료되었습니다. \n변경된 내용을 확인하세요");
							var str = "";
									
							str += "<input type='hidden' name='groupNo' value = '"+ data.groupNo +"'>";
							$('#getAddAppl').append(str);
							$('#getAddAppl').submit();
						},
						error	: function(xhr, status, error) {
						  // 응답을 받지 못하거나, 정상 응답이지만 데이터 형식을 확인할 수 없는 경우
						},
						complete : function(xhr, status) {
						  // success와 error 콜백이 호출된 후에 반드시 호출, finally 구문과 동일
						}
					});
		        }
			}
		</script>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>모임 개설신청 수정 View 페이지</h1>
		<form id="MyForm">
			<input type="hidden" name="groupNo" value="${group.groupNo}">
			<input type="hidden" name="groupPers" id="groupPers" value="${group.groupPers}">
			<input type="hidden" name="groupLevel" id="groupLevel" value="${group.groupLevel}">
			
			닉네임 : <input type="text" name="leaderNick" id="leaderNick" value="${group.leaderNick}">
			<br>
			모임명 : <input type="text" name="groupName" id="groupName" value="${group.groupName}">
			<br>
			소개  : <input type="text" name="groupIntro" id="groupIntro" value="${group.groupIntro}">
			<br>
			태그  : <input type="text" name="groupHash" id="groupHash" value="${group.groupHash}">
			<br>
			주소  : <input type="text" name="groupAddr" id="groupAddr" value="${group.groupAddr}">
			<br>
			계획  : <input type="text" name="groupPlan" id="groupPlan" value="${group.groupPlan}">
		</form>
		<a href="javascript:updateApplGroup()">신청서 수정하기</a>
		
		<form id="getAddAppl" method="post" action="/group/getAddAppl">
			
		</form>
	</body>
</html>