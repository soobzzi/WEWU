<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script>
		
			function updateGroupAcle()
			{
		        if(!confirm("정말 수정하시겠습니까?")){
					return;
					
		        }else{
		        	// form 데이터 가져오기
		        	var form = document.getElementById('updateGroupAcle');
		        	var formData = new FormData(form);

		        	// JSON으로 변환
		        	var jsonData = Object.fromEntries(formData);
					console.log(jsonData);
		        	//모임 리스트
					$.ajax ({
						url	: "/app/group/updateGroupAcle", // (Required) 요청이 전송될 URL 주소
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
									
							str += "<input type='hidden' name='boardNo' value = '"+ data.boardNo +"'>";
							$('#getGroupAcle').append(str);
							$('#getGroupAcle').submit();
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
		${groupAcle}
		<h1>게시글 수정</h1>
		<form id="updateGroupAcle">
			<input type="hidden" name="memberNo" value="${groupAcle.memberNo}">
			<input type="hidden" name="typeNo" value="${groupAcle.typeNo}">
			<input type="hidden" name="boardNo" value="${groupAcle.boardNo}">
			<input type="hidden" name="wrteName" value="${groupAcle.wrteName}">
			작성자 : ${groupAcle.wrteName}
			<br>
			제목 : <input type="text" name="acleName" value="${groupAcle.acleName}">
			<br>
			내용 : <input type="text" name="acleContents" value="${groupAcle.acleContents}">
		</form>
		<a href="javascript:updateGroupAcle()">신청서 수정하기</a>
		
		<form id="getGroupAcle" method="post" action="/group/getGroupAcle">
			
		</form>
	</body>
</html>