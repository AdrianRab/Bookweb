<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookweb register</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
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

.registerbtn {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}

.cancelbtn {
    padding: 10px 18px;
    background-color: #f44336;
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
    .cancelbtn {
       width: 100%;
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
</style>
</head>
<body>
	<div class="container-fluid bg">	
		<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<%@ include file="../header.jsp"%>
		
	<c:if test="${errorMessage != null}">
		<div><h3>Error</h3>
			<p style="color:red">${errorMessage}</p>
		</div>
	</c:if>
	<div class="container">
		<p class="h2 text-muted">Register</p>
	</div>
		<form:form method="POST" modelAttribute="userDto">
			<div class="container">
				Username: <form:input path="username" placeholder="Enter Username"/>
				<form:errors path="username" cssClass="error" element="div"/>
	
				E-mail address: <form:input path="email" placeholder="Enter E-mail"/>
				<form:errors path="email" cssClass="error" element="div"/>
		
				Password: <form:password path="password" placeholder="Enter Password"/>
				<form:errors path="password" cssClass="error" element="div"/>
	
				Confirm password: <form:password path="passwordConfirmed" placeholder="Confirm Password"/>
				<form:errors path="passwordConfirmed" cssClass="error" element="div"/>
			</div>
			 <div class="container">
				<input class="btn btn-success" type="submit" value="Register" />
				<input class="btn btn-danger" type="reset" value="Reset the form" />
			</div>
		</form:form>

		<div class="container">
			<div class="row">
			    <div class="col">
			    </div>
			   	<div class="col">
					<a href="${contextPath}/home"><button type="button" class="btn btn-info">Main page</button></a>		   		
			    </div>
			    <div class="col">
			    </div>
			</div>
		</div>
	</div>

</body>
</html>