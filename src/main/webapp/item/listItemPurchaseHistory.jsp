<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script>
	window.onload=function()
	{
		var flag = "${flag}";
		if (flag != "") {
	        if(flag == "Y"){
	        	alert("������� �Ϸ�");
	        }else{
	        	alert("������� ����");
	        }
		}else {
			
	    }
	}
	
	function updatePurchase(itemPurchaseNo)
	{
		if (!confirm("���� ���Ÿ� ����Ͻðڽ��ϱ�?")) {
	        // ���(�ƴϿ�) ��ư Ŭ�� �� �̺�Ʈ
	    	return;
		} else {
	    	location.href="/item/updatePurchase?itemPurchaseNo="+itemPurchaseNo;
	        // Ȯ��(��) ��ư Ŭ�� �� �̺�Ʈ
	    }
	}

</script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:set var="i" value="0" />
	<c:forEach var="itemPurchase" items="${list}"> <!-- ���� for�� ���� �� ���� �����ϴ� ���� �̸��� itemPurchase��� �Ѵٴ� �ǹ�. ���� refundCompdate, itemName�� ����� ���� �̸��� itemPurchase. ���� ������.   -->
	<tr>
		<a href="/item/getItemSalesHistory?itemPurchaseNo=${itemPurchase.itemPurchaseNo}">${itemPurchase.itemName}</a> ==== <a href="javascript:updatePurchase(${itemPurchase.itemPurchaseNo})">�������</a><br>
	</tr>
	</c:forEach>
</body>
</html>