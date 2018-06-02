<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit your profile</title>
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
</style> 
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<div class="container-fluid">	
		<%@ include file="../header.jsp"%>
		<div class="container">
			<p class="h2 text-muted" >Edit profile</p>
		</div>
			<form:form method="POST" modelAttribute="user">
				<div class="container">
					Username: <form:input path="username"/>
					<form:errors path="username" cssClass="error" element="div"/>
			
				
					E-mail address: <form:input path="email"/>
					<form:errors path="email" cssClass="error" element="div"/>
		
				
					Password: <form:password path="password"/>
					<form:errors path="password" cssClass="error" element="div"/>
				</div>
				<div class="container">
					<input class="btn btn-success"  type="submit" value="Save changes" />
					<input class="btn btn-danger"  type="reset" value="Reset the form" />
				</div>
			</form:form>

		<br>
		<div class="container">
			<div class="row">
			    <div class="col">
			    </div>
			   	<div class="col">
					<div class="btn-group btn-group-lg">
						<a href="${contextPath}/user/my-page"><button type="button" class="btn btn-warning">My page</button></a>
						<a href="${contextPath}/home"><button type="button" class="btn btn-warning">Main page</button></a> 
					</div>	   		
			    </div>
			    <div class="col">
			    </div>
			</div>
		</div>
	</div>
</body>
</html>