<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All authors</title>
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
					<p class="h2 text-muted" >Available authors</p>
				</div>
			</div>
		</div>
		<br>
		<br>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Biography</th>
					<th>List of books</th>
					<sec:authorize access ="hasRole('ROLE_ADMIN')">
						<th>Action</th>
					</sec:authorize>
				</tr>
			</thead>
			<c:forEach items="${authorsList}" var="author">
				<tr>
					<td>${author.id}</td>
					<td>${author.firstName} ${author.lastName}</td>
					<td>${author.biography}</td>
					<td><a href="${contextPath}/author/all-books/${author.id}"><button type="button" class="btn btn-dark">Books</button></a></td>
					<sec:authorize access ="hasRole('ROLE_ADMIN')">
					<td><a href="${contextPath}/admin/edit-author/${author.id}"><button type="button" class="btn btn-warning">Edit</button></a>
						<a href="${contextPath}/admin/delete-author/${author.id}"><button type="button" class="btn btn-danger">Delete</button></a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>
		<br>
		<br>
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto">
			      	<div class="btn-group btn-group-lg">
						<a href="${contextPath}/author/add"><button type="button" class="btn btn-success">Add new author</button></a>
						<a href="${contextPath}/"><button type="button" class="btn btn-info">Home</button></a>
						<sec:authorize access ="hasRole('ADMIN')">
							<a href="${contextPath}/admin/panel"><button type="button" class="btn btn-success">Back to admin panel</button></a>
						</sec:authorize>
					</div>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>