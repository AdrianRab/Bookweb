<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All publishers</title>
</head>
<body>

	<div  style="background-color: hsl(150, 100%, 65%)">
		<%@ include file="header.jsp"%>
	</div>
		<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	<h2>Available publishers</h2>
	<br>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<sec:authorize access ="hasRole('ROLE_ADMIN')">
				<th>Action</th>
			</sec:authorize>
		</tr>
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
	<a href="${contextPath}/publ/add"><button type="button" class="btn btn-dark">Add new publisher</button></a>
	<br>
	<a href="${contextPath}/"><button type="button" class="btn btn-dark">Home</button></a>
</body>
</html>