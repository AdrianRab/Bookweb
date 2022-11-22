<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin panel</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<style>
.custom {
    width: 78px !important;
}
body, html {
    height: 100%;
    margin: 0;
}

.bg {
    background-image: url("../blur-book-books-176103.jpg");

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
			      	<div class="btn-group btn-group-lg">
						<p class="h2 text-muted">Welcome ${user.username} in admin panel</p>
					</div>
			    </div>
			</div>
		</div>
	<br>
	<br>
	<br>
	<br>
	<br>
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto">
			      	<div class="btn-group btn-group-lg">
						<a href="${contextPath}/book/top-rated"><button type="button" class="btn btn-warning">Top 20 books</button></a>
						<a href="${contextPath}/book/all"><button type="button" class="btn btn-warning">All books</button></a>
						<a href="${contextPath}/publisher/all"><button type="button" class="btn btn-warning">All publishers</button></a>
						<a href="${contextPath}/author/all"><button type="button" class="btn btn-warning">All authors</button></a>
						<a href="${contextPath}/admin/all-users"><button type="button" class="btn btn-warning">All users</button></a>
					</div>
			    </div>
			</div>
		</div>
	<br>
	<br>
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto">
			      	<div class="btn-group btn-group-lg">
						<a href="${contextPath}/"><button type="button" class="btn btn-info">Home</button></a>
					</div>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>