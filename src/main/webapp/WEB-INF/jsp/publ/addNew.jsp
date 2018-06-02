<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new publisher</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script> 
</head>
<body>
	<div class="container-fluid">
			<%@ include file="../header.jsp"%>

		<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<div class="container">
			<div class="row">
			    <div class="col">
			    </div>
			    <div class="col-6">
			      	<h3 class="text-muted">Add new publisher</h3	>
			    </div>
			    <div class="col">
			    </div>
			</div>
		</div>
			
		<form:form method="POST" modelAttribute="publisher">
		<div class="container" align="center">
			Name: <form:input path="name"/><br>
			<form:errors path="name" cssClass="error" element="div"/>
		</div>
		<div class="container" align="center">
			<input class="btn btn-success" type="submit" value="Add" />
			<input class="btn btn-danger" type="reset" value="Reset the form" />
		</div>
		</form:form>
		<br>
		<div class="container">
			 <div class="row">
			 <div class="col-lg">
			 	</div>
			 	<div class="col-lg">
					<a href="${contextPath}/publ/all"><button class="btn btn-info">List of publishers</button></a> 
					<a href="${contextPath}/home"><button class="btn btn-info">Main page</button></a> 
				</div>
				<div class="col-lg">
				</div>
			</div>
		</div>
	</div>
</body>
</html>