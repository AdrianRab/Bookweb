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
<title>${book.title}</title>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div  style="background-color: hsl(150, 100%, 65%)">
		<%@ include file="header.jsp"%>
	</div>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	
	<h2>Book ${book.title} details</h2>
	<br>
	
	<div>
		${book.cover}
	</div>	
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
					${author.firstName} ${author.lastName };
				</c:forEach>
			</td>
			<td>${book.publisher.name}</td>
			<td><a href="${contextPath}/book/edit/${book.id}"><button type="button" class="btn btn-dark">Edit</button></a></td>
			<sec:authorize access ="hasRole('ROLE_ADMIN')">
				<td><a href="${contextPath}/admin/edit-book/${book.id}"><button type="button" class="btn btn-dark">Edit</button></a>
					<a href="${contextPath}/admin/delete-book/${book.id}"><button type="button" class="btn btn-dark">Delete</button></a></td>
			</sec:authorize>
		</tr>
	</table>
		
	<br>
	<c:if test="${confirmation == null}">
			<h3>Rate ${book.title}</h3>
		
		<form action="${contextPath}/book/details/${book.id}/rating" method="get">
			Your evaluation: 
			<select id="grade" name="rateParam">
				<c:forEach items="${listOfRates}" var="rate">
					<option value="${rate}">${rate}</option>
				</c:forEach>
			</select>
			<p>	<input type="submit" value="Add" />
				<input type="reset" value="Reset the form"/></p>
		</form>	
	</c:if>
	
	<c:if test="${confirmation != null}">
		<c:out value="${confirmation}"/>
	</c:if>

		
	<br>
	<a href="${contextPath}/book/add"><button type="button" class="btn btn-dark">Add new book</button></a>
	<br>
	<a href="${contextPath}/book/top-rated"><button type="button" class="btn btn-dark">Top 20 books</button></a>
	<br>
	<a href="${contextPath}/book/all"><button type="button" class="btn btn-dark">All books</button></a>
	<br>
	<a href="${contextPath}/"><button type="button" class="btn btn-dark">Home</button></a>
</body>
</html>