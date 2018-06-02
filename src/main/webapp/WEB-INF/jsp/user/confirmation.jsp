<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete confirmation</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script> 
</head>
<body>
		<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<div class="container-fluid">	
		<%@ include file="../header.jsp"%>

			<div class="container">
			<div class="row">
			    <div class="col">
			    </div>
			    <div class="col-6">
			      	<p class="h2 text-muted">Are you sure you want to delete your account?</p>
			      	<br>
			      	<br>
			      	<div class="container">
			      		<div class="row">
							<div class="col">
								<a href="${contextPath}/user/confirmation/${user.id}"><button type="button" class="btn btn-danger">Yes</button></a>
							</div>
							<div class="col">
								<a href="${contextPath}/user/my-page"><button type="button" class="btn btn-success">No</button></a>
							</div>
						</div>
					</div>
			    </div>
			    <div class="col">
			    </div>
			</div>
		</div>
	<br>
	<br>
		<div class="container">
			<div class="row">
			    <div class="col">
			    </div>
			    <div class="col-6">
			      	<div class="btn-group btn-group-lg">
						<a href="${contextPath}/"><button type="button" class="btn btn-info">Home</button></a>
					</div>
			    </div>
			    <div class="col">
			    </div>
			</div>
		</div>
	</div>
</body>
</html>