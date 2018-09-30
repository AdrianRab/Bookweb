<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${author.firstName} ${author.lastName}</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	<div class="container-fluid bg">
		<%@ include file="../header.jsp"%>
		
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-auto">
					<p class="h2 text-muted" >${author.firstName} ${author.lastName}</p>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto">
					<c:if test="${author.authorPicture != null}">
						<div>
							<img src="http://localhost:8090/author/image-display/${author.id}"/>
						</div>
					</c:if>
					<c:if test="${author.authorPicture == null}">
						<div>
							<a href="${contextPath}/author/add-photo/${author.id}"><button type="button" class="btn btn-success">Add photography</button></a>
						</div>
					</c:if>
			    </div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-auto">
				<div class="container">
					<button type="button" class="btn btn-lg btn-info" data-toggle="collapse" data-target="#authorDetails">About</button>
					  <div id="authorDetails" class="collapse">
						<table class="table">
							<thead class="thead-dark">
								<tr>
									<th>First name</th>
									<th>Last name</th>
									<th>Information</th>
								</tr>
							</thead>
							<tr>
								<th>${author.firstName}</th>
								<th>${author.lastName}</th>
								<th>${author.biography}</th>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-auto">
					<p class="h2 text-muted" >Books by ${author.firstName} ${author.lastName}</p>
				</div>
			</div>
		</div>

		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Title</th>
					<th>Genre</th>
					<th>Publisher</th>
					<th>ISBN</th>
					<th>Book details</th>
				</tr>
			</thead>
			<c:forEach items="${authorBooks}" var="book">
				<tr>
					<td>${book.id}</td>
					<td>${book.title}</td>
					<td><c:forEach items="${book.genre}" var ="genre">
							${genre} 
						</c:forEach>
					</td>
					<td>${book.publisher.name}</td>
					<td>${book.isbn}</td>
					<td><a href="${contextPath}/book/details/${book.id}"><button type="button" class="btn btn-info">Details</button></a></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<br>
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto">
			      	<div class="btn-group btn-group-lg">
						<a href="${contextPath}/author/all"><button type="button" class="btn btn-success">All authors</button></a>
				<%-- 	<a href="${contextPath}/author/add-new-book/${author.id}"><button>Add new book to author</button></a> --%>
						<a href="${contextPath}/"><button type="button" class="btn btn-info">Home</button></a>
					</div>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>