<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Bookweb home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.custom {
    width: 78px !important;
}
</style>
</head>
<body>
	<div align=center>
		<div  style="background-color: hsl(150, 100%, 65%)">
			<%@ include file="header.jsp"%>
		</div>
	
	<c:if test="${pageContext.request.remoteUser == null}">
		<div>
			<a href="${contextPath}/login"><button type="button" class="btn btn-success custom">Login</button></a><br>
			<p>Does not have account yet?</p>
			<a href="${contextPath}/register"><button type="button" class="btn btn-danger custom">Register</button></a><br>
		</div>
	</c:if>
	
	<sec:authorize access ="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
		<div align=left>
			<a href="${contextPath}/user/my-page"><button type="button" class="btn btn-success">My profile</button></a>
		</div>
	</sec:authorize>
		<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
			<h1>Welcome!</h1>
			<div> 
				<br>
					Add author: <a href="${contextPath}/author/add"><button type="button" class="btn btn-dark" >Add new author</button></a> <br>
					All authors: <a href="${contextPath}/author/all"><button type="button" class="btn btn-dark">All authors</button></a> <br>
					Add publisher:<a href="${contextPath}/publ/add"><button type="button" class="btn btn-dark">Add new publisher</button></a> <br> 
					All publishers: <a href="${contextPath}/publ/all"><button type="button" class="btn btn-dark">List of publishers</button></a><br>
					Add books: <a href="${contextPath}/book/add"><button type="button" class="btn btn-dark">Add new book</button></a><br>
					All books: <a href="${contextPath}/book/all"><button type="button" class="btn btn-dark">List of books</button></a><br>
					Top 20 books <a href="${contextPath}/book/top-rated"><button type="button" class="btn btn-dark">List of top 20 books</button></a><br>
			<br>
			<sec:authorize access ="hasAnyRole('ADMIN', 'USER')">
				<form action="/logout" method="post">
					 <div class="form-group">
						<input class="btn btn-default" type="submit" value="Sign Out" /> 
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</div>
				</form>
			</sec:authorize>
			</div>
		</div>
</body>
</html>