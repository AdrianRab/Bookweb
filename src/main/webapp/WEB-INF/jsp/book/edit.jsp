<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit ${book.title}</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script> 
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}
button:hover {
    opacity: 0.8;
}

.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
}

img.avatar {
    width: 40%;
    border-radius: 50%;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
}
.custom {
    width: 78px !important;
}
body, html {
    height: 100%;
    margin: 0;
}

.bg {
    background-image: url("../link-hoang-31747-unsplash.jpg");

    height: 100%; 

    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
</style>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<div class="container-fluid bg">
		<%@ include file="../header.jsp"%>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-auto">
					<p class="h2 text-muted">Edit ${book.title}</p>
				</div>
			</div>
		</div>
	<div class="container">
		<form:form method="POST" modelAttribute="book">
				<div class="form-group">
					Title: <form:input path="title"/>
					<form:errors path="title" cssClass="error" element="div"/>
				</div>
				<div class="form-group">
					Genre: <form:select path="genre" items="${listOfGenres}" multiple="true" class="form-control"/>
					<form:errors path="genre" cssClass="error" element="div"/>
				</div>
				<div class="form-group">
					Publisher: <form:select path="publisher">
						<form:option value="" label="--Please	Select--"/>
						<form:options items="${listOfPublishers}" itemLabel="name"/>
					</form:select>
					<form:errors path="publisher" cssClass="error" element="div"/>
					<small class="form-text text-muted">Publisher not available? <a href="${contextPath}/publisher/add">Add new</a></small>
				</div>
				<div class="form-group">
					Author/s: <form:select path="authors" multiple="true"  class="form-control" >
						<form:options items="${listOfAuthors}" itemLabel="lastName" itemValue="id"/>
					</form:select>
					<form:errors path="authors" cssClass="error" element="div"/>
					<small class="form-text text-muted">Author not available? <a href="${contextPath}/author/add">Add new</a></small>
				</div>
				<div class="form-group">
					ISBN: <form:input path="isbn"/>
					<form:errors path="isbn" cssClass="error" element="div"/>
				</div>
				<div class="form-group">	
					<form:hidden path="rating"/>
					<form:errors path="rating" cssClass="error" element="div"/>
				</div>
				<div class="form-group">
					<form:hidden path="rate"/>
					<form:errors path="rate" cssClass="error" element="div"/>
				</div>
					<form:hidden path="created"/>
					<form:errors path="created" cssClass="error" element="div"/>
				<div class="form-group">
					<form:hidden path="cover"/>
					<form:errors path="cover" cssClass="error" element="div"/>
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-success" value="Save changes" />
					<input type="reset" class="btn btn-danger" value="Reset the form"/>
				</div>
		</form:form>
	</div>
	<br>
	<br> 
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto">
			      	<div class="btn-group btn-group-lg">
						<a href="${contextPath}/book/all"><button type="button" class="btn btn-warning">List of all books</button></a> 
						<a href="${contextPath}/book/top-rated"><button type="button" class="btn btn-warning">Top rated</button></a> 
						<a href="${contextPath}/"><button type="button" class="btn btn-info">Home</button></a>
					</div>
			    </div>
			</div>
		</div>
</div>
</body>
</html>