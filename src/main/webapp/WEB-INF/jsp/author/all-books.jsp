<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${author.firstName} ${author.lastName}</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<div align=center>
		<div  style="background-color: hsl(150, 100%, 65%)">
			<%@ include file="header.jsp"%>
		</div>
	
		<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
		<h3>Books by ${author.firstName} ${author.lastName}</h3>
			
			
			<table class="table table-dark">
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Genre</th>
				<th>Publisher</th>
				<th>ISBN</th>
				<th>Book details</th>
			</tr>
			<c:forEach items="${authorBooks}" var="book">
				<tr>
					<td>${book.id}</td>
					<td>${book.title}</td>
					<td><c:forEach items="${book.genre}" var ="genre">
							${genre} 
						</c:forEach>
					</td>
					<td>${book.publisher.name}</td>
					<td>${book.isbn}</td>
					<td><a href="${contextPath}/book/details/${book.id}"><button type="button" class="btn btn-info">Details</button></a></td>
				</tr>
			</c:forEach>
			</table>
			
	<%-- 	<a href="${contextPath}/author/add-new-book/${author.id}"><button>Add new book to author</button></a> --%>
		<br>
		<a href="${contextPath}/author/all"><button type="button" class="btn btn-warning">All authors</button></a>
		<br>
		<a href="${contextPath}/"><button type="button" class="btn btn-warning">Home</button></a>
	</div>
</body>
</html>