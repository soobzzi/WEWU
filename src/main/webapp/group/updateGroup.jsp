<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>�������� ���� View ������</h1>
	${group }<br>
	<input type="hidden" name="groupNo" value="${group.groupNo}">
	�г��� : <input type="text" name="leaderNick" value="${group.leaderNick}">
	<br>
	���Ӹ� : <input type="text" name="groupName" value="${group.groupName}">
	<br>
	�Ұ�  : <input type="text" name="groupIntro" value="${group.groupIntro}">
	<br>
	�±�  : <input type="text" name="groupHash" value="${group.groupHash}">
	<br>
	�ּ�  : <input type="text" name="groupAddr" value="${group.groupAddr}" disabled>
	<br>
	��ȹ  : <input type="text" name="groupPlan" value="${group.groupPlan}" disabled>
	<br>
	�ο��� : <input type="text" name="groupPers" value="${group.groupPers}" disabled>
	<br>
	���  : <input type="text" name="groupLevel" value="${group.groupLevel}" disabled>
</body>
</html>