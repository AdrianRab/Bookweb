<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books to read</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
</head>
<body>
	<div class="container-fluid bg">	
			<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
			<%@ include file="../header.jsp"%>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-auto">
					<p class="h2 text-muted" >${user.username}'s books to read</p>
				</div>
			</div>
		</div>
		<br>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col">
					<a href="${contextPath}/user/my-page"><button type="button" class="btn btn-success">Back</button></a>
			    </div>
				<div class="col-md-auto">
					 <div class="btn-group btn-group-lg">
						<a href="${contextPath}/user/owned"><button type="button" class="btn btn-warning">My books</button></a>
						<a href="${contextPath}/user/read"><button type="button" class="btn btn-warning">Read books</button></a>
						<a href="${contextPath}/book/all"><button type="button" class="btn btn-warning">All books</button></a>
					</div>
				</div>
				<div class="col">
					
			    </div>
			</div>
		</div>
			<div class="container">
				<div id="owned">
					<table class="table">
						<thead class="thead-dark">
							<tr class="info">
								<th>Title</th>
								<th>Authors</th>
								<th>Genre</th>
								<th>Cover</th>
								<th>Details</th>
							</tr>
						</thead>
						<c:forEach items="${user.wannaRead}" var="book">
							<tr class="active">
								<td>${book.title}</td>
								<c:forEach items="${book.authors}" var="author">
									<td><a href="${contextPath}/author/all-books/${author.id}"><button type="button" class="btn btn-info">
										${author.firstName} ${author.lastName}</button></a></td>
								</c:forEach>
								<td><c:forEach items="${book.genre}" var="genre">
									 ${genre} 
								</c:forEach></td>
								<td>
								<c:if test="${book.cover != null}">
									<div>
										<img src="http://localhost:8090/cover/image-display/${book.id}"/>
									</div>
								</c:if>
								</td>
								<td><a href="${contextPath}/book/details/${book.id}"><button type="button" class="btn btn-info">Details</button></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		<hr>
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