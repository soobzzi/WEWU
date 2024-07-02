<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.jumbotron
{
    padding: 6rem 2rem;
	background-color: #00A06C;
	margin-top:160px;
	margin-bottom:0;
	background-size: contain; /* �̹����� �����̳� �ȿ� �µ��� */
	background-image: url(); /* ��� �̹��� ��� */
	background-size: cover; /* �̹����� �����̳ʸ� ������ */
	background-position: center; /* �̹����� �߾ӿ� ��ġ�ϵ��� */
}
.main-panel
{
	margin:0;
}
</style>
<c:if test="${group.groupImg != null }">
<div class="jumbotron" style="background-image:url(/group/upload/${group.groupImg});">
</c:if>
<c:if test="${group.groupImg == null }">
<div class="jumbotron" style="background-image:url(/group/img/illustration.jpg);">
</c:if>
  <div class="container">
    <p class="display-4"></p>
  </div>
</div>