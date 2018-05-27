<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new publisher</title>
</head>
<body>
	<div  style="background-color: hsl(150, 100%, 65%)">
		<%@ include file="header.jsp"%>
	</div>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	
	<h2>Add new publisher</h2>
	
	<form:form method="POST" modelAttribute="publisher">
		Name: <form:input path="name"/><br>
		<form:errors path="name" cssClass="error" element="div"/>
		<input type="submit" value="Add" />
		<input type="reset" value="Reset the form" />
	</form:form>
	<br>
	<a href="${contextPath}/publ/all"><button>List of publishers</button></a> 
	<a href="${contextPath}/home"><button>Main page</button></a> 
</body>
</html>