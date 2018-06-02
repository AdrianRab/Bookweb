<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${book.title}</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	<div class="jumbotron text-center">
		<%@ include file="../header.jsp"%>
	
		<h1>Book ${book.title} details</h1>
		<br>
		
		<div align=right>
			<p><span style="background-color:yellow;font-size:160%">User's rate:
			 <fmt:formatNumber type="number" maxFractionDigits="2" value="${book.rate}"/></span></p>
		</div>
		
		<c:if test="${confirmation != null}">
			<c:out value="${confirmation}"/>
		</c:if>
		<c:if test="${myRate>0}">
			<div align=left>
				<p><span style="background-color:green;font-size:160%">My rate: ${myRate}</span></p>
			</div>
		</c:if>
		<div align=left>
			<a href="${contextPath}/user/my-page"><button type="button" class="btn btn-success">My profile</button></a>
		</div>
	<div class="btn-group btn-group-lg" align="left">
				
					
		<div class="container">
			<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#addLinks">Expand to add to your shelve</button>
			  <div id="addLinks" class="collapse">
			  	<div class="btn-group btn-group-lg">
					<a href="${contextPath}/user/add-to-owned/${book.id}"><button type="button" class="btn btn-success">Add to owned books</button></a>
					<a href="${contextPath}/user/add-to-reading/${book.id}"><button type="button" class="btn btn-success">Add to currently reading books</button></a>
					<a href="${contextPath}/user/add-read/${book.id}"><button type="button" class="btn btn-success">Add to read books</button></a>
					<a href="${contextPath}/user/add-to-read/${book.id}"><button type="button" class="btn btn-success">Add to books to want to read</button></a>
				</div>
			</div>
		</div>
		<div class="container">
			<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#removeLinks">Expand to remove from your shelve</button>
			  <div id="removeLinks" class="collapse">
			  	<div class="btn-group btn-group-lg">
			  	  	<a href="${contextPath}/user/remove-from-owned/${book.id}"><button type="button" class="btn btn-success">Remove from owned books</button></a>
					<a href="${contextPath}/user/remove-from-reading/${book.id}"><button type="button" class="btn btn-success">Remove from currently reading books</button></a>
					<a href="${contextPath}/user/remove-from-read/${book.id}"><button type="button" class="btn btn-success">Remove from read books</button></a>
					<a href="${contextPath}/user/remove-from-to-read/${book.id}"><button type="button" class="btn btn-success">Remove books from want to read</button></a>
				</div>
			</div>
		</div>
	</div>
			<br>
			<table class="table table-dark">
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Genre</th>
				<th>Rate</th>
				<th>Authors</th>
				<th>Publisher</th>
				<th>Edit</th>
				<sec:authorize access ="hasRole('ROLE_ADMIN')">
					<th>Action</th>
				</sec:authorize>
			</tr>
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
						<a href="${contextPath}/author/all-books/${author.id}"><button type="button" class="btn btn-warning">${author.firstName} ${author.lastName}</button></a>;
					</c:forEach>
				</td>
				<td>${book.publisher.name}</td>
				<td><a href="${contextPath}/book/edit/${book.id}"><button type="button" class="btn btn-warning">Edit</button></a></td>
				<sec:authorize access ="hasRole('ROLE_ADMIN')">
					<td><a href="${contextPath}/admin/delete-book/${book.id}"><button type="button" class="btn btn-danger">Delete</button></a></td>
				</sec:authorize>
			</tr>
		</table>
		<br>
		<br>
		<c:if test="${book.cover != null}">
			<div>
				<img src="http://localhost:8090/cover/image-display/${book.id}"/>
			</div>
		</c:if>
			
		<br>
		<hr>
		<c:if test="${confirmation == null}">
				<h3>Rate ${book.title}</h3>
			
			<form action="${contextPath}/book/details/${book.id}/rating" method="get">
				Evaluate: 
				<select id="grade" name="rateParam">
					<c:forEach items="${listOfRates}" var="rate">
						<option value="${rate}">${rate}</option>
					</c:forEach>
				</select>
				<p>	<input type="submit" value="Add" /></p>
			</form>	
		</c:if>
	
		<hr>
		<br>
		 <div class="btn-group btn-group-lg">
		 	<c:if test="${book.cover == null}">
				<a href="${contextPath}/cover/add/${book.id}"><button type="button" class="btn btn-warning">Add cover page</button></a>
			</c:if>
			<c:if test="${book.cover != null}">
				<a href="${contextPath}/cover/delete/${book.id}"><button type="button" class="btn btn-warning">Delete cover</button></a>
			</c:if>
				<a href="${contextPath}/book/add"><button type="button" class="btn btn-warning">Add new book</button></a>
				<a href="${contextPath}/book/top-rated"><button type="button" class="btn btn-warning">Top 20 books</button></a>
				<a href="${contextPath}/book/all"><button type="button" class="btn btn-warning">All books</button></a>
		</div>
		<br>
		<a href="${contextPath}/"><button type="button" class="btn btn-primary">Home</button></a>
	</div>
</body>
</html>