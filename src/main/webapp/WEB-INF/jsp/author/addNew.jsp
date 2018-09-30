<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new author</title>
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
    background-image: url("link-hoang-31747-unsplash.jpg");
    height: 100%; 
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
.col-centered{
    margin: 0 auto;
    float: none;
}
</style> 
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<%@ include file="../header.jsp"%>
	<div class="container-fluid bg">
		
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-auto">
					<p class="h2 text-muted">Add new author</p>
				</div>
			</div>
		</div>
		<div class="container">
			<form:form method="POST" modelAttribute="author">
			<div class="container">
				<div class="form-group row">
					<label for="lgFormGroupInput" class="col-sm-2 col-form-label col-form-label-lg">First name</label>
					 <form:input path="firstName"/>
					<form:errors path="firstName" cssClass="error" element="div"/>
				</div>
				<div class="form-group row">
					<label for="lgFormGroupInput" class="col-sm-2 col-form-label col-form-label-lg">Last name</label>
					<form:input path="lastName"/>
					<form:errors path="lastName" cssClass="error" element="div"/>
				</div>
				<div class="form-group row">
					<label for="lgFormGroupInput" class="col-sm-2 col-form-label col-form-label-lg">Biography</label>
					 <form:textarea path="biography" rows="10" cols="150"/>
					<form:errors path="biography" cssClass="error" element="div"/>
				</div>

				<div class="form-group row">
					<div class="col-lg">
			 		</div>
			 		<div class="col-lg">
						<input type="submit" class="btn btn-success" value="Add" />
						<input type="reset" class="btn btn-danger" value="Reset the form" />
					</div>	
					<div class="col=lg">
					</div>
				</div>
			</div>
			</form:form>
		</div>
		<br>
		<br>
		<div class="container">
			<div class="row justify-content-md-center">
			     <div class="btn-group btn-group-lg">
					<a href="${contextPath}/author/all"><button type="button" class="btn btn-dark">List of authors</button></a>  
					<a href="${contextPath}/"><button type="button" class="btn btn-info">Home</button></a>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>