<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<script>
	if("${flag}" == "a")
	{
		alert("���� �����Ͽ����ϴ�. \n���� Ȩ���� �̵��մϴ�.");
		location.href="/item/getItemList";	
	}else
	{
		alert("�Ŀ��� �����Ͽ����ϴ�. \n�Ŀ� �Խ������� �̵��մϴ�.");
		location.href="/board/listBoard?boardType=4";
	}
	
</script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>