<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	${groupBoard}
	<h1>���� �����Խ��� View ������</h1>
	<form id="getGroupBoard">
		<input type="hidden" name="typeNo" value="${groupBoard.typeNo}">
		<input type="hidden" name="groupNo" id="groupNo" value="${groupBoard.groupNo}">
		�Խ��� �� : ${groupBoard.boardName}
		<br>
		�Ұ� : ${groupBoard.boardIntro}
		<br>
		Ÿ��  : ${groupBoard.boardType}
		<br>
		����  : ${groupBoard.boardRole}
	</form>
	<a href="javascript:updateGroupBoard()">�Խ��� �����ϱ�</a>
	
	<form id="updateGroupBoard" method="post" action="/group/updateGroupBoard">
		<input type="hidden" name="typeNo" value="${groupBoard.typeNo}">
	</form>
	
	<form id="deleteGroupBoard" method="post">
		<input type="hidden" name="typeNo" value="${groupBoard.typeNo}">
	</form>
		<a href="javascript:deleteGroupBoard();">�Խ��� �����ϱ�</a>
		
	<form id="addGroupAcle" method="post">
		<input type="hidden" name="typeNo" value="${groupBoard.typeNo}">
		<input type="hidden" name="groupNo" value="${groupBoard.groupNo}">
	</form>
		<a href="javascript:addGroupAcle();">�Խñ� �ۼ��ϱ�</a>
		
	<form id="getGroup" method="post" action="/group/getGroup">
		<input type="hidden" name="groupNo" value="${groupBoard.groupNo}">
	</form>
</body>
</html>