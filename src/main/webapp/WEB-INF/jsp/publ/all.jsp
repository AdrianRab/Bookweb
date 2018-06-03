<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All publishers</title>
</head>
<body>
	<div class="container-fluid bg">
		<%@ include file="../header.jsp"%>
		<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-auto">
					<p class="h2 text-muted" >Available publishers</p>
				</div>
			</div>
		</div>
		<br>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<sec:authorize access ="hasRole('ROLE_ADMIN')">
						<th>Action</th>
					</sec:authorize>
				</tr>
			</thead>
			<c:forEach items="${publishersList}" var="publisher">
				<tr>
					<td>${publisher.id}</td>
					<td>${publisher.name}</td>
					<sec:authorize access ="hasRole('ROLE_ADMIN')">
					<td><a href="${contextPath}/admin/edit-publ/${publisher.id}"><button type="button" class="btn btn-dark">Edit</button></a>
						<a href="${contextPath}/admin/delete-publ/${publisher.id}"><button type="button" class="btn btn-dark">Delete</button></a></td>
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
			      		<a href="${contextPath}/publ/add"><button type="button" class="btn btn-dark">Add new publisher</button></a>
						<a href="${contextPath}/"><button type="button" class="btn btn-info">Home</button></a>
					</div>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>