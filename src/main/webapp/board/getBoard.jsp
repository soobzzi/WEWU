<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 보기</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		getCommentList();
		
	}
	function addComment(){
		// form 데이터 가져오기
    	var form = document.getElementById('commentForm');
    	var formData = new FormData(form);

    	// JSON으로 변환
    	var jsonData = Object.fromEntries(formData);

    	//모임 리스트
		$.ajax ({
			url	: "/app/board/addComment", // (Required) 요청이 전송될 URL 주소
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
				alert("댓글 등록 완료.");
				
			    $("#commentList").empty();
				getCommentList();
				 
			},
			error	: function(xhr, status, error) {
			  // 응답을 받지 못하거나, 정상 응답이지만 데이터 형식을 확인할 수 없는 경우
			},
			complete : function(xhr, status) {
			  // success와 error 콜백이 호출된 후에 반드시 호출, finally 구문과 동일
			}
		});
    }
	
	function deleteComment(commentNo){
		if(!confirm("댓글을 삭제하시겠습니까?")){
			return;
		}else{
			$.ajax ({
				url	: "/app/board/deleteComment", // (Required) 요청이 전송될 URL 주소
				type  : "POST", // (default: ‘GET’) http 요청 방식
				async : true,  // (default: true, asynchronous) 요청 시 동기화 여부
				cache : true,  // (default: true, false for dataType 'script' and 'jsonp') 캐시 여부
				timeout : 3000, // (ms) 요청 제한 시간 안에 완료되지 않으면 요청을 취소하거나 error 콜백 호출
				data  : JSON.stringify
						({
							commentNo:commentNo,
				 			boardNo:${board.boardNo}
				 		}), // 요청 시 전달할 데이터
				processData : true, // (default: true) 데이터를 컨텐트 타입에 맞게 변환 여부
				contentType : "application/json", // (default: 'application/x-www-form-urlencoded; charset=UTF-8')
				dataType    : "json", // (default: Intelligent Guess (xml, json, script, or html)) 응답 데이터 형식
				beforeSend  : function () {
				  // XHR Header 포함, HTTP Request 하기전에 호출
				  
				},
				success : function(data, status, xhr) {
					alert("삭제가 완료되었습니다.");
					$("#commentList").empty();
					getCommentList();
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
	function showUpdateForm(commentNo) {
	    $('#updateCommentForm_' + commentNo).toggle();
	}
	
	function updateComment(commentNo){
		var form = document.getElementById('updateCommentForm_'+commentNo);
    	var formData = new FormData(form);

    	// JSON으로 변환
    	var jsonData = Object.fromEntries(formData);

		$.ajax ({
			url	: "/app/board/updateComment", // (Required) 요청이 전송될 URL 주소
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
				alert("댓글 수정 완료.");
				
			    $("#commentList").empty();
				getCommentList();
				 
			},
			error	: function(xhr, status, error) {
			  // 응답을 받지 못하거나, 정상 응답이지만 데이터 형식을 확인할 수 없는 경우
			},
			complete : function(xhr, status) {
			  // success와 error 콜백이 호출된 후에 반드시 호출, finally 구문과 동일
			}
		});
    }
	function getCommentList()
	{
		$.ajax ({
			  url	: "/app/board/getCommentListByBoard", // (Required) 요청이 전송될 URL 주소
			  type	: "POST", // (default: ‘GET’) http 요청 방식
			  async : true,  // (default: true, asynchronous) 요청 시 동기화 여부
			  cache : true,  // (default: true, false for dataType 'script' and 'jsonp') 캐시 여부
			  timeout : 3000, // (ms) 요청 제한 시간 안에 완료되지 않으면 요청을 취소하거나 error 콜백 호출
			  data  : JSON.stringify(
			 				{boardNo:'${board.boardNo}'}
		 				), // 요청 시 전달할 데이터
			  processData : true, // (default: true) 데이터를 컨텐트 타입에 맞게 변환 여부
			  contentType : "application/json", // (default: 'application/x-www-form-urlencoded; charset=UTF-8')
			  dataType    : "json", // (default: Intelligent Guess (xml, json, script, or html)) 응답 데이터 형식
			  beforeSend  : function () {
			    // XHR Header 포함, HTTP Request 하기전에 호출
			  },
			  success : function(data, status, xhr) {
				  var str = "";
				  for(var i = 0 ; i < data.length ; i ++){
					  str += "작성자 : " + data[i].commentNickName + 
					  	" ===  내용 : " + data[i].commentContents + 
					  	" ===  작성날짜 : " + data[i].commentDate +
					  	"<a href=javascript:deleteComment("+ data[i].commentNo +");>댓글 삭제</a>"+
					  	"<a href=javascript:updateComment("+ data[i].commentNo +");>댓글 수정</a><br>" +
					  "<form id='updateCommentForm_" + data[i].commentNo + "'>" +
                      "<input type='hidden' name='commentNo' value='" + data[i].commentNo + "'>" +
                      "<input type='hidden' name='boardNo' value='" + '${board.boardNo}' + "'>" +
                      "<input type='hidden' name='commentNickName' value='" + data[i].commentNickName + "'>" +
                      "<textarea name='commentContents'>" + data[i].commentContents + "</textarea>" +
                      "<button type='button' onclick='updateComment(" + data[i].commentNo + ");'>수정 완료</button>" +
                      "</form><br>";
				  }
				  $('#commentList').append(str);
				  str = "";
			  },
			  error	: function(xhr, status, error) {
			    // 응답을 받지 못하거나, 정상 응답이지만 데이터 형식을 확인할 수 없는 경우
			  },
			  complete : function(xhr, status) {
			    // success와 error 콜백이 호출된 후에 반드시 호출, finally 구문과 동일
			  }
		});
	}

	 function showUpdateForm(commentNo) {
	        document.getElementById('updateCommentForm_' + commentNo).style.display = 'block';
	    }
	
	$(function(){
		$("button.btn:contains('삭제')").on("click",function(){
			self.location="/board/deleteBoard?boardType=${param.boardType}&boardNo=${param.boardNo}"
		});
	});
</script>

</head>
<body>

<input type="hidden" name="boardNo" value="${param.boardNo}" >
<input type="hidden" name="boardType" value="${param.boardType}" >
	
	<h2>게시판 종류</h2>
	${board.boardType}
	<br/>
	
	<h2>닉네임</h2>
	${board.nickName}
	<br/>
	
	<h2>제목</h2>
	${board.title}
	<br/>	
	
	<h2>내용</h2>
	${board.contents}
	<br/>

	<h2>사용자 참가 모임 명</h2>
	${board.userGroupNo}
	<br/>
	
	<h2>등록 날짜</h2>
	${board.regDate}
	<br/>
	
	<h2>썸네일 이미지 번호</h2>
	${board.thumnail}
	<br/>
	
	<h2>즐겨찾기 수</h2>
	${board.bookmarkCnt}
	<br/>
	
	<h2>조회수</h2>
	${board.views}
	<br/>
	
	<h2>댓글 수</h2>
	${board.commentCnt}
	<br/>
	
	<h2>댓글</h2>
	<form id="commentForm">
			<input type="hidden" name="boardNo" id="boardNo" value="${board.boardNo}">
			닉네임 : nick1<input type="hidden" name="commentNickName" id="commentNickName" value="nick1">
			<br>
			댓글 내용 : <input type="text" name="commentContents" id="commentContents">
			<br>
			
	</form>
	<a href="javascript:addComment();">댓글 쓰기</a>
	
	<div id="commentList">
		
	</div>
	<br/>
		

	<button type="button" class="btn">삭제</button>



</body>
</html>