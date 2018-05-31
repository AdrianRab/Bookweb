<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top 20 books</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	 <div align=center>
	<div  style="background-color: hsl(150, 100%, 65%)">
		<%@ include file="header.jsp"%>
	</div>
	

		<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

	<h2>20 top rated books</h2>
	<br>
	<table class="table table-dark">
		<tr>
			<th>Id</th>
			<th>Title</th>
			<th>Genre</th>
			<th>Rate</th>
			<th>Authors</th>
			<th>Publisher</th>
			<th>Details</th>
			<sec:authorize access ="hasRole('ROLE_ADMIN')">
				<th>Action</th>
			</sec:authorize>
		</tr>
		<c:forEach items="${topRatedBooks}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>
					<c:forEach items="${book.genre}" var="genre">
						${genre} 
					</c:forEach>
				
				</td>
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${book.rate}"/></td>
				<td>
					<c:forEach items="${book.authors}" var="author">
						${author.firstName} ${author.lastName };
					</c:forEach>
				</td>
				<td>${book.publisher.name}</td>
				<td><a href="${contextPath}/book/details/${book.id}/${user.id}"><button type="button" class="btn btn-info">Details</button></a></td>
				<sec:authorize access ="hasRole('ROLE_ADMIN')">
					<td><a href="${contextPath}/admin/edit-book/${book.id}"><button type="button" class="btn btn-warning">Edit</button></a>
						<a href="${contextPath}/admin/detele-book/${book.id}"><button type="button" class="btn btn-danger">Delete</button></a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
	<br>
	 <div class="btn-group btn-group-lg">
		<a href="${contextPath}/book/add"><button type="button" class="btn btn-warning">Add new book</button></a>
		<a href="${contextPath}/book/all"><button type="button" class="btn btn-warning">All books</button></a>
		<a href="${contextPath}/"><button type="button" class="btn btn-dark">Home</button></a>
	</div>
 </div>
</body>
</html>