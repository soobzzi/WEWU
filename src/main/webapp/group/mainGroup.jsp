<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		window.onload = function() {
			//���� ����Ʈ
			$.ajax ({
				  url	: "/app/group/getGroupList", // (Required) ��û�� ���۵� URL �ּ�
				  type	: "POST", // (default: ��GET��) http ��û ���
				  async : true,  // (default: true, asynchronous) ��û �� ����ȭ ����
				  cache : true,  // (default: true, false for dataType 'script' and 'jsonp') ĳ�� ����
				  timeout : 3000, // (ms) ��û ���� �ð� �ȿ� �Ϸ���� ������ ��û�� ����ϰų� error �ݹ� ȣ��
				  data  : JSON.stringify({
					  		searchCondition : "T"
					  	}), // ��û �� ������ ������
				  processData : true, // (default: true) �����͸� ����Ʈ Ÿ�Կ� �°� ��ȯ ����
				  contentType : "application/json", // (default: 'application/x-www-form-urlencoded; charset=UTF-8')
				  dataType    : "json", // (default: Intelligent Guess (xml, json, script, or html)) ���� ������ ����
				  beforeSend  : function () {
				    // XHR Header ����, HTTP Request �ϱ����� ȣ��
				  },
				  success : function(data, status, xhr) {
					  var str = "";
					  for(var i = 0 ; i < data.length ; i ++){
						  str += "<option>"+ data[i].groupName +"</option>";
					  }
					  $('#getGroupList').append(str);
					  str = "";
				  },
				  error	: function(xhr, status, error) {
				    // ������ ���� ���ϰų�, ���� ���������� ������ ������ Ȯ���� �� ���� ���
				  },
				  complete : function(xhr, status) {
				    // success�� error �ݹ��� ȣ��� �Ŀ� �ݵ�� ȣ��, finally ������ ����
				  }
			});
			
			//���� ��ŷ ����Ʈ
			$.ajax ({
				  url	: "/app/group/getGroupRankingList", // (Required) ��û�� ���۵� URL �ּ�
				  type	: "POST", // (default: ��GET��) http ��û ���
				  async : true,  // (default: true, asynchronous) ��û �� ����ȭ ����
				  cache : true,  // (default: true, false for dataType 'script' and 'jsonp') ĳ�� ����
				  timeout : 3000, // (ms) ��û ���� �ð� �ȿ� �Ϸ���� ������ ��û�� ����ϰų� error �ݹ� ȣ��
				  data  : JSON.stringify({
					  		searchCondition : "Ranking"
					  	}), // ��û �� ������ ������
				  processData : true, // (default: true) �����͸� ����Ʈ Ÿ�Կ� �°� ��ȯ ����
				  contentType : "application/json", // (default: 'application/x-www-form-urlencoded; charset=UTF-8')
				  dataType    : "json", // (default: Intelligent Guess (xml, json, script, or html)) ���� ������ ����
				  beforeSend  : function () {
				    // XHR Header ����, HTTP Request �ϱ����� ȣ��
				  },
				  success : function(data, status, xhr) {
					  var str = "";
					  for(var i = 0 ; i < data.length ; i ++){
						  str += "<option>"+ data[i].groupName +"</option>";
					  }
					  $('#getGroupRankingList').append(str);
					  str = "";
				  },
				  error	: function(xhr, status, error) {
				    // ������ ���� ���ϰų�, ���� ���������� ������ ������ Ȯ���� �� ���� ���
				  },
				  complete : function(xhr, status) {
				    // success�� error �ݹ��� ȣ��� �Ŀ� �ݵ�� ȣ��, finally ������ ����
				  }
			});
			
			//�� ���� ��û����
			$.ajax ({
				  url	: "/app/group/getGroupList", // (Required) ��û�� ���۵� URL �ּ�
				  type	: "POST", // (default: ��GET��) http ��û ���
				  async : true,  // (default: true, asynchronous) ��û �� ����ȭ ����
				  cache : true,  // (default: true, false for dataType 'script' and 'jsonp') ĳ�� ����
				  timeout : 3000, // (ms) ��û ���� �ð� �ȿ� �Ϸ���� ������ ��û�� ����ϰų� error �ݹ� ȣ��
				  data  : JSON.stringify({
					  		searchCondition : "My",
					  		searchKeyword : "nick1"
					  	}), // ��û �� ������ ������
				  processData : true, // (default: true) �����͸� ����Ʈ Ÿ�Կ� �°� ��ȯ ����
				  contentType : "application/json", // (default: 'application/x-www-form-urlencoded; charset=UTF-8')
				  dataType    : "json", // (default: Intelligent Guess (xml, json, script, or html)) ���� ������ ����
				  beforeSend  : function () {
				    // XHR Header ����, HTTP Request �ϱ����� ȣ��
				  },
				  success : function(data, status, xhr) {
					  var str = "";
					  for(var i = 0 ; i < data.length ; i ++){
						  str += "<option>"+ data[i].groupName +"</option>";
					  }
					  $('#getMyGroupList').append(str);
					  str = "";
				  },
				  error	: function(xhr, status, error) {
				    // ������ ���� ���ϰų�, ���� ���������� ������ ������ Ȯ���� �� ���� ���
				  },
				  complete : function(xhr, status) {
				    // success�� error �ݹ��� ȣ��� �Ŀ� �ݵ�� ȣ��, finally ������ ����
				  }
			});
			
			//��� ���� ��û����(�������)
			$.ajax ({
				  url	: "/app/group/getGroupList", // (Required) ��û�� ���۵� URL �ּ�
				  type	: "POST", // (default: ��GET��) http ��û ���
				  async : true,  // (default: true, asynchronous) ��û �� ����ȭ ����
				  cache : true,  // (default: true, false for dataType 'script' and 'jsonp') ĳ�� ����
				  timeout : 3000, // (ms) ��û ���� �ð� �ȿ� �Ϸ���� ������ ��û�� ����ϰų� error �ݹ� ȣ��
				  data  : JSON.stringify({
					  		searchCondition : "E",
					  	}), // ��û �� ������ ������
				  processData : true, // (default: true) �����͸� ����Ʈ Ÿ�Կ� �°� ��ȯ ����
				  contentType : "application/json", // (default: 'application/x-www-form-urlencoded; charset=UTF-8')
				  dataType    : "json", // (default: Intelligent Guess (xml, json, script, or html)) ���� ������ ����
				  beforeSend  : function () {
				    // XHR Header ����, HTTP Request �ϱ����� ȣ��
				  },
				  success : function(data, status, xhr) {
					  var str = "";
					  for(var i = 0 ; i < data.length ; i ++){
						  str += "<option>"+ data[i].groupName +"</option>";
					  }
					  $('#getGroupListWait').append(str);
					  str = "";
				  },
				  error	: function(xhr, status, error) {
				    // ������ ���� ���ϰų�, ���� ���������� ������ ������ Ȯ���� �� ���� ���
				  },
				  complete : function(xhr, status) {
				    // success�� error �ݹ��� ȣ��� �Ŀ� �ݵ�� ȣ��, finally ������ ����
				  }
			});
			
			//��� ���� ��û����(��������)
			$.ajax ({
				  url	: "/app/group/getGroupList", // (Required) ��û�� ���۵� URL �ּ�
				  type	: "POST", // (default: ��GET��) http ��û ���
				  async : true,  // (default: true, asynchronous) ��û �� ����ȭ ����
				  cache : true,  // (default: true, false for dataType 'script' and 'jsonp') ĳ�� ����
				  timeout : 3000, // (ms) ��û ���� �ð� �ȿ� �Ϸ���� ������ ��û�� ����ϰų� error �ݹ� ȣ��
				  data  : JSON.stringify({
					  		searchCondition : "T",
					  	}), // ��û �� ������ ������
				  processData : true, // (default: true) �����͸� ����Ʈ Ÿ�Կ� �°� ��ȯ ����
				  contentType : "application/json", // (default: 'application/x-www-form-urlencoded; charset=UTF-8')
				  dataType    : "json", // (default: Intelligent Guess (xml, json, script, or html)) ���� ������ ����
				  beforeSend  : function () {
				    // XHR Header ����, HTTP Request �ϱ����� ȣ��
				  },
				  success : function(data, status, xhr) {
					  var str = "";
					  for(var i = 0 ; i < data.length ; i ++){
						  str += "<option>"+ data[i].groupName +"</option>";
					  }
					  $('#getMyGroupListTrue').append(str);
					  str = "";
				  },
				  error	: function(xhr, status, error) {
				    // ������ ���� ���ϰų�, ���� ���������� ������ ������ Ȯ���� �� ���� ���
				  },
				  complete : function(xhr, status) {
				    // success�� error �ݹ��� ȣ��� �Ŀ� �ݵ�� ȣ��, finally ������ ����
				  }
			});
			
			//��� ���� ��û����(�����ź�)
			$.ajax ({
				  url	: "/app/group/getGroupList", // (Required) ��û�� ���۵� URL �ּ�
				  type	: "POST", // (default: ��GET��) http ��û ���
				  async : true,  // (default: true, asynchronous) ��û �� ����ȭ ����
				  cache : true,  // (default: true, false for dataType 'script' and 'jsonp') ĳ�� ����
				  timeout : 3000, // (ms) ��û ���� �ð� �ȿ� �Ϸ���� ������ ��û�� ����ϰų� error �ݹ� ȣ��
				  data  : JSON.stringify({
					  		searchCondition : "F",
					  	}), // ��û �� ������ ������
				  processData : true, // (default: true) �����͸� ����Ʈ Ÿ�Կ� �°� ��ȯ ����
				  contentType : "application/json", // (default: 'application/x-www-form-urlencoded; charset=UTF-8')
				  dataType    : "json", // (default: Intelligent Guess (xml, json, script, or html)) ���� ������ ����
				  beforeSend  : function () {
				    // XHR Header ����, HTTP Request �ϱ����� ȣ��
				  },
				  success : function(data, status, xhr) {
					  var str = "";
					  for(var i = 0 ; i < data.length ; i ++){
						  str += "<option>"+ data[i].groupName +"</option>";
					  }
					  $('#getMyGroupListNone').append(str);
					  str = "";
				  },
				  error	: function(xhr, status, error) {
				    // ������ ���� ���ϰų�, ���� ���������� ������ ������ Ȯ���� �� ���� ���
				  },
				  complete : function(xhr, status) {
				    // success�� error �ݹ��� ȣ��� �Ŀ� �ݵ�� ȣ��, finally ������ ����
				  }
			});
			
			//�� ���� ���Խ�û ����
			$.ajax ({
				  url	: "/app/group/getApplJoinList", // (Required) ��û�� ���۵� URL �ּ�
				  type	: "POST", // (default: ��GET��) http ��û ���
				  async : true,  // (default: true, asynchronous) ��û �� ����ȭ ����
				  cache : true,  // (default: true, false for dataType 'script' and 'jsonp') ĳ�� ����
				  timeout : 3000, // (ms) ��û ���� �ð� �ȿ� �Ϸ���� ������ ��û�� ����ϰų� error �ݹ� ȣ��
				  data  : JSON.stringify({
					  		searchCondition : "user",
					  		searchKeyword : "nick2"
					  	}), // ��û �� ������ ������
				  processData : true, // (default: true) �����͸� ����Ʈ Ÿ�Կ� �°� ��ȯ ����
				  contentType : "application/json", // (default: 'application/x-www-form-urlencoded; charset=UTF-8')
				  dataType    : "json", // (default: Intelligent Guess (xml, json, script, or html)) ���� ������ ����
				  beforeSend  : function () {
				    // XHR Header ����, HTTP Request �ϱ����� ȣ��
				  },
				  success : function(data, status, xhr) {
					  var str = "";
					  for(var i = 0 ; i < data.length ; i ++){
						  str += "<option>"+ data[i].groupNo +"===="+ data[i].memberNickName+"</option>";
					  }
					  $('#getApplJoinList').append(str);
					  str = "";
				  },
				  error	: function(xhr, status, error) {
				    // ������ ���� ���ϰų�, ���� ���������� ������ ������ Ȯ���� �� ���� ���
				  },
				  complete : function(xhr, status) {
				    // success�� error �ݹ��� ȣ��� �Ŀ� �ݵ�� ȣ��, finally ������ ����
				  }
			});
	    }
	</script>
	<meta charset="EUC-KR">
	<title>Insert title here</title>
</head>
	<body>
		<div>
			<h1>���Ӹ�� ( ���ε� ���Ӹ� )</h1>
			<select id="getGroupList">
				
			</select>
			
			<h1>���ӷ�ŷ ( ���ε� ���Ӹ� ��� �� �ο� �� order by )</h1>
			<select id="getGroupRankingList">
				
			</select>
			
			<h1>�� ���� ��û����( �� ��� ���ӽ�û ���� )</h1>
			<select id="getMyGroupList">
				
			</select>
			
			<h1>��� ���� (�������)</h1>
			<select id="getGroupListWait">
				
			</select>
			
			<h1>��� ���� (��������)</h1>
			<select id="getMyGroupListTrue">
				
			</select>
			
			<h1>��� ���� (�����ź�)</h1>
			<select id="getMyGroupListNone">
				
			</select>
			
			<h1>�� ���� ���Խ�û ����</h1>
			<select id="getApplJoinList">
				
			</select>
		</div>
	</body>
</html>