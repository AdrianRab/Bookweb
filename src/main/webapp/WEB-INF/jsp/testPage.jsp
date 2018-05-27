<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div  style="background-color: hsl(150, 100%, 65%)">
		<%@ include file="header.jsp"%>
	</div>

	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	Witaj na stronie <c:out value="${pageContext.request.remoteUser}"></c:out>
	<br>
	Add author: <a href="${contextPath}/author/add"><button type="button" class="btn btn-dark">Add new author</button></a>
	<br>
	All authors: <a href="${contextPath}/author/all"><button type="button" class="btn btn-dark">All authors</button></a>
	<br>
	Add publisher:<a href="${contextPath}/publ/add"><button type="button" class="btn btn-dark">Add new publisher</button></a>
	<br>
	All publishers: <a href="${contextPath}/publ/all"><button  type="button" class="btn btn-dark"">List of publishers</button></a> 
	<br>
	Add books: <a href="${contextPath}/book/add"><button type="button" class="btn btn-dark">Add new book</button></a>
	<br>
	All books: <a href="${contextPath}/book/all"><button type="button" class="btn btn-dark">List of books</button></a>
	<br>
	Top 20 books <a href="${contextPath}/book/top-rated"><button type="button" class="btn btn-dark">List of top 20 books</button></a>
	<br>
	
		<form action="/logout" method="post">
			<input type="submit" value="Sign Out" /> 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
</body>
</html>