<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:set var="i" value="0" />
		<c:forEach var="itemPurchase" items="${list}"> <!-- ���� for�� ���� �� ���� �����ϴ� ���� �̸��� itemPurchase��� �Ѵٴ� �ǹ�. ���� refundCompdate, itemName�� ����� ���� �̸��� itemPurchase. ���� ������.   -->
		<tr>
			<a href="/item/getItemSalesHistory?itemPurchaseNo=${itemPurchase.itemPurchaseNo}">${itemPurchase.itemName}</a><br>
		</tr>
		</c:forEach>
</body>
</html>