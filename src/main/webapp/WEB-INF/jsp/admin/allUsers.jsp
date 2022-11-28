<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookweb users</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	<div  class="container-fluid bg">
		<%@ include file="../header.jsp"%>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-auto">
					<p class="h2 text-muted" >Registered users</p>
				</div>
			</div>
		</div>
		<br>
		<br>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Username</th>
					<th>E-mail address</th>
					<th>Current role</th>
					<th>Modify rights</th>
					<th>Details</th>
					<th>Action</th>

				</tr>
			</thead>
			<c:forEach items="${allUsers}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>${user.role.role}</td>
					<c:if test="${admin.id != user.id}">
						<c:if test="${user.role.role == 'ROLE_USER'}">
							<td><a href="${contextPath}/admin/rights/${user.id}"><button class="btn btn-warning">Add admin rights</button></a></td>
						</c:if>
						<c:if test="${user.role.role == 'ROLE_ADMIN'}" >
							<td><a href="${contextPath}/admin/remove-rights/${user.id}"><button class="btn btn-warning">Remove admin rights</button></a></td>
						</c:if>
					</c:if>
					<c:if test="${admin.id == user.id}">
						<td>You can't modify your own access</td>
					</c:if>
					<td><a href="${contextPath}/admin/my-page/${user.id}"><button type="button" class="btn btn-dark">More</button></a></td>
					<td>
						<div class="btn-group btn-group-lg">
							<a href="${contextPath}/admin/edit-user/${user.id}"><button type="button" class="btn btn-warning">Edit</button></a>
							<a href="${contextPath}/admin/delete-user/${user.id}"><button type="button" class="btn btn-danger">Delete</button></a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<br>
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto">
			      	<div class="btn-group btn-group-lg">
						<a href="${contextPath}/admin/panel"><button type="button" class="btn btn-success">Back</button></a>
						<a href="${contextPath}/"><button type="button" class="btn btn-info">Home</button></a>
					</div>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>