<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new book</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div align=center>	
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<%@ include file="../header.jsp"%>


	<h2>Add new book</h2>
	
	<form:form method="POST" modelAttribute="book">
		<table class="table table-dark">
			<tr>
				<td>Title: <form:input path="title"/></td>
				<td><form:errors path="title" cssClass="error" element="div"/></td>
			</tr>
			<tr>
				<td>Genre: <form:select path="genre" items="${listOfGenres}" multiple="true"/></td>
				<td><form:errors path="genre" cssClass="error" element="div"/></td>
			</tr>
			<tr>
				<td>
					Publisher: <form:select path="publisher">
						<form:option value="" label="--Please	Select--"/>
						<form:options items="${listOfPublishers}" itemLabel="name"/>
					</form:select>
				</td>
				<td><form:errors path="publisher" cssClass="error" element="div"/></td>
			</tr>
			<tr>
				<td><p>Publisher not available? <a href="${contextPath}/publ/add"><button type="button" class="btn btn-success">Add new</button></a></p></td>
			</tr>
			<tr>
				<td>Author/s: <form:select path="authors">
					<form:options items="${listOfAuthors}" itemLabel="lastName" itemValue="id"/>
				</form:select></td>
				<td><form:errors path="authors" cssClass="error" element="div"/></td>
			</tr>
			<tr>
				<td><p>Author not available? <a href="${contextPath}/author/add"><button type="button" class="btn btn-success">Add new</button></a></p></td>
			</tr>
			<tr>
				<td>ISBN: <form:input path="isbn"/></td>
				<td><form:errors path="isbn" cssClass="error" element="div"/></td>
			</tr>
			<tr>
				<td><form:hidden path="rating"/></td>
				<td><form:errors path="rating" cssClass="error" element="div"/></td>
			</tr>
			<tr>
				<td><form:hidden path="rate"/></td>
				<td><form:errors path="rate" cssClass="error" element="div"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add" /></td>
				<td><input type="reset" value="Reset the form"/></td>
			</tr>
		</table>
	</form:form>
	<br>
	
	<a href="${contextPath}/book/all"><button type="button" class="btn btn-warning">List of all books</button></a> 
	<a href="${contextPath}/home"><button type="button" class="btn btn-warning">Main page</button></a> 
</div>

</body>
</html>