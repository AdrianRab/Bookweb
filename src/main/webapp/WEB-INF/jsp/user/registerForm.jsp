<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookweb register</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div align=center>	
		<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<div  style="background-color: hsl(150, 100%, 65%)">
			<%@ include file="header.jsp"%>
		</div>
		
	<c:if test="${errorMessage != null}">
		<div><h3>Error</h3>
			<p style="color:red">${errorMessage}</p>
		</div>
	</c:if>
	<h1>Register</h1>
	
	<table class="table table-dark">
		<form:form method="POST" modelAttribute="user">
			<tr>
				<td>Username: <form:input path="username"/></td>
				<td><form:errors path="username" cssClass="error" element="div"/></td>
			</tr>
			
			<tr>
				<td>E-mail address: <form:input path="email"/></td>
				<td><form:errors path="email" cssClass="error" element="div"/></td>
			</tr>
			
			<tr>	
				<td>Password: <form:password path="password"/></td>
				<td><form:errors path="password" cssClass="error" element="div"/></td>
			</tr>
			
			<tr>
				<td>Confirm password: <form:password path="passwordConfirmed"/><td>
				<td><form:errors path="passwordConfirmed" cssClass="error" element="div"/></td>
			</tr>
			<tr>	
				<td><input type="submit" value="Register" />
				<input type="reset" value="Reset the form" /></td>
			</tr>
		</form:form>
	</table>
		<hr>
		<a href="${contextPath}/home"><button type="button" class="btn btn-warning">Main page</button></a> 
	</div>

</body>
</html>