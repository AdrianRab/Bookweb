<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new author</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<div  style="background-color: hsl(150, 100%, 65%)">
		<%@ include file="header.jsp"%>
	</div>

	
	<h2>Add new author</h2>
	
	<form:form method="POST" modelAttribute="author">
		First name: <form:input path="firstName"/><br>
		<form:errors path="firstName" cssClass="error" element="div"/>
		Last name: <form:input path="lastName"/><br>
		<form:errors path="lastName" cssClass="error" element="div"/>
		Biography: <form:textarea path="biography" rows="20" cols="40"/><br>
		<form:errors path="biography" cssClass="error" element="div"/>
		
		<input type="submit" value="Add" />
		<input type="reset" value="Reset the form" />
	</form:form>
	<br>
	
	<a href="${contextPath}/author/all"><button type="button" class="btn btn-dark">List of authors</button></a> 
	<a href="${contextPath}/home"><button type="button" class="btn btn-dark">Main page</button></a> 
</body>
</html>