<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user.username} profile</title>
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
		<div class="row">
			<div class="col">
				<c:if test="${adminMode==true}">
					<sec:authorize access ="hasRole('ADMIN')">
						<p class="h2 text-muted">You are logged as admin.</p>
					</sec:authorize>
				</c:if>
			</div>
		</div>
	</div>

		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto">
			      	<div class="btn-group btn-group-lg">
				      	<c:if test="${adminMode==false || adminMode==null}">
							<p class="h2 text-muted">Welcome ${user.username}</p>
						</c:if>
					</div>
			    </div>
			</div>
		</div>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-md-auto">
				<div  class="btn-group-vertical">
					<p align="left"><a href="${contextPath}/user/owned"><button type="button" class="btn btn-md btn-warning">My books</button></a></p>				
					<p align="left"><a href="${contextPath}/user/read"><button type="button" class="btn btn-md btn-warning">Read books</button></a></p>
					<p align="left"><a href="${contextPath}/user/to-read"><button type="button" class="btn btn-md btn-warning">Want to read</button></a></p>
				</div>
			</div>
			<div class="col">
			</div>
			<div class="col-md-auto">
				<div class="btn-group" role="group">
					<button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						My menu
					</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						<a class="dropdown-item" href="${contextPath}/user/edit"><button type="button" class="btn btn-dark">Edit your data</button></a>
						<a class="dropdown-item" href="${contextPath}/user/delete-account"><button type="button" class="btn btn-dark">Remove account</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-md-auto">
				<div class="container">
					<button type="button" class="btn btn-lg btn-info" data-toggle="collapse" data-target="#reading">Currently reading books</button>
					  <div id="reading" class="collapse">
						<table class="table">
							<thead class="thead-dark">
								<tr class="info">
									<th>Title</th>
									<th>Authors</th>
									<th>Genre</th>
									<th>Details</th>
								</tr>
							</thead>
							<c:forEach items="${readingBooks}" var="book">
								<tr class="active">
									<td>${book.title}</td>
									<c:forEach items="${book.authors}" var="author">
										<td><a href="${contextPath}/author/all-books/${author.id}"><button type="button" class="btn btn-info">
											${author.firstName} ${author.lastName}</button></a></td>
									</c:forEach>
									<td><c:forEach items="${book.genre}" var="genre">
										 ${genre} 
									</c:forEach></td>
									<td><a href="${contextPath}/book/details/${book.id}"><button type="button" class="btn btn-info">Details</button></a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
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