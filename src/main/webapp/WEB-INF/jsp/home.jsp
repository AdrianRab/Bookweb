<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Spring Security Example - ProgrammingFree</title>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
	<div  style="background-color: hsl(150, 100%, 65%)">
		<%@ include file="header.jsp"%>
	</div>
	
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<h1>Welcome!</h1>
		<div>
			Click <a href="<spring:url value='/hello' />">here</a> to see a
			greeting. 
			<br>
			Add author: <a href="${contextPath}/author/add"><button type="button" class="btn btn-dark" >Add new author</button></a> <br>
			All authors: <a href="${contextPath}/author/all"><button type="button" class="btn btn-dark">All authors</button></a> <br>
			Add publisher:<a href="${contextPath}/publ/add"><button type="button" class="btn btn-dark">Add new publisher</button></a> <br> 
			All publishers: <a href="${contextPath}/publ/all"><button type="button" class="btn btn-dark">List of publishers</button></a><br>
			Add books: <a href="${contextPath}/book/add"><button type="button" class="btn btn-dark">Add new book</button></a><br>
			All books: <a href="${contextPath}/book/all"><button type="button" class="btn btn-dark">List of books</button></a><br>
			Top 20 books <a href="${contextPath}/book/top-rated"><button type="button" class="btn btn-dark">List of top 20 books</button></a><br>
		<br>
		<form action="/logout" method="post">
			 <div class="form-group">
				<input class="btn btn-default" type="submit" value="Sign Out" /> 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</div>
		</form>
		</div>
	</div>
</body>
</html>