<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit your profile</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<div align=center>	
		<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<div  style="background-color: hsl(150, 100%, 65%)">
			<%@ include file="header.jsp"%>
		</div>
		<h2>Edit profile</h2>
		<table class="table">
			<form:form method="POST" modelAttribute="user">
				<tr>
					<td>Username: <form:input path="username"/><br></td>
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
					<td><input type="submit" value="Save changes" />
					<input type="reset" value="Reset the form" /></td>
				</tr>
			</form:form>
		</table>
		<br>
	<div class="btn-group btn-group-lg">
		<a href="${contextPath}/user/my-page/${user.id}"><button type="button" class="btn btn-warning">My books</button></a>
		<a href="${contextPath}/home"><button type="button" class="btn btn-warning">Main page</button></a> 
	</div>
		
	
	</div>
</body>
</html>