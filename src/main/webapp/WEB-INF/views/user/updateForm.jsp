<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/user/join" method="post">
		<div class="form-group">
			<label for="username">username</label> <input value="${principal.user.username}" type="text" class="form-control" placeholder="Enter username" id="username" readonly>
		</div>
		<input value=${principal.user.id } type=hidden id="id" />
		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">Password</label> <input value="${principal.user.password}" type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
		</c:if>
		<div class="form-group">
			<label for="email">Email</label> 
				<input value="${principal.user.email}" type="email" class="form-control" placeholder="Enter email"  id="email" <c:if test="${not empty principal.user.oauth}">readonly </c:if>>
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">회원수정완료</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
