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
<div class="col-lg-12 grid-margin stretch-card">
	<div class="card">
		<div class="card-body">
		    <h4 class="card-title">${groupBoard.boardName}</h4>
		    <div style="float:right;">
		    	<c:if test="${!empty groupMember && groupMember.joinFlag == 'T' || groupMember.joinFlag == 'L'}">
		           	<img src="/group/img/pencil.svg"><span>�ۼ�</span>&nbsp;
		           	<c:if test="${user.nickname == group.leaderNick}">
			           	<img src="/group/img/arrow-repeat.svg"><span>�Խ��� ����</span>&nbsp;
			           	<img src="/group/img/eraser-fill.svg"><span>�Խ��� ����</span>
			       </c:if>
		        </c:if>
	        </div>
			<p class="card-description">
				${groupBoard.boardIntro}
		    </p>
			<div class="table-responsive">
			  	<table class="table table-striped table-hover">
				    <thead>
				      <tr>
				        <th>
				          �ۼ���
				        </th>
				        <th>
				          ����
				        </th>
				        <th>
				          �ۼ���
				        </th>
				        <th>
				          ��ȸ��
				        </th>
				        <th>
				          ��ۼ�
				        </th>
				      </tr>
				    </thead>
				    <tbody id="acleList">
				    
				    </tbody>
			    </table>
			</div>
		</div>
		<div class="container">
		  <div class="row justify-content-center">
		    <div id="paging" style="float:center;"></div>
		  </div>
		</div>
		
	</div>
</div>
	<form id="getGroupBoard">
		<input type="hidden" name="typeNo" value="${groupBoard.typeNo}">
		<input type="hidden" name="groupNo" id="groupNo" value="${groupBoard.groupNo}">
	</form>
	
	<form id="updateGroupBoard" method="post" action="/group/updateGroupBoard">
		<input type="hidden" name="typeNo" value="${groupBoard.typeNo}">
	</form>
	
	<form id="deleteGroupBoard" method="post">
		<input type="hidden" name="typeNo" value="${groupBoard.typeNo}">
	</form>
		
	<form id="addGroupAcle" method="post">
		<input type="hidden" name="typeNo" value="${groupBoard.typeNo}">
		<input type="hidden" name="groupNo" value="${groupBoard.groupNo}">
	</form>
		
	<form id="getGroup" method="post" action="/group/getGroup">
		<input type="hidden" name="groupNo" value="${groupBoard.groupNo}">
	</form>
</body>
</html>