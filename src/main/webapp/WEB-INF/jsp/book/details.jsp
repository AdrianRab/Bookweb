<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${book.title}</title>
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
    background-image: url("../alex-loup-440761-unsplash.jpg");

    height: 100%; 

    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
</style>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	<div class="container-fluid bg">
		<%@ include file="../header.jsp"%>
		
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-auto">
					<p class="h2 text-muted">Book ${book.title} details</p>
				</div>
			</div>
		</div>
		<br>
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto text-right">
			      	<div class="btn-group btn-group-lg">
						<a href="${contextPath}/user/my-page"><button type="button" class="btn btn-success">My profile</button></a>
					</div>
			    </div>
			</div>
		</div>

	
		<div class="container">
			<div class="row">
				   <div class="col col-lg-2">
					<div class="container">
						<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#removeLinks">Expand to remove from your shelve</button>
						  <div id="removeLinks" class="collapse">
						  	<div class="btn-group-vertical">
						  	  	<a href="${contextPath}/shelf/${user.id}/remove-from-owned/${book.id}"><button type="button" class="btn btn-dark">Remove from owned books</button></a>
								<a href="${contextPath}/shelf/${user.id}/remove-from-reading/${book.id}"><button type="button" class="btn btn-dark">Remove from currently reading books</button></a>
								<a href="${contextPath}/shelf/${user.id}/remove-from-read/${book.id}"><button type="button" class="btn btn-dark">Remove from read books</button></a>
								<a href="${contextPath}/shelf/${user.id}/remove-from-to-read/${book.id}"><button type="button" class="btn btn-dark">Remove books from want to read</button></a>
							</div>
						</div>
					</div>
				   </div>
				   <div class="col col-lg-2">
					
				   </div>
				   <div class="col col-lg-2">
						
				   </div>
				   <div class="col col-lg-2">
						
				   </div>
				   <div class="col col-lg-2">
						
				   </div>
				   <div class="col col-lg-2">
						<div class="container">
							<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#addLinks">Expand to add to your shelve</button>
							  <div id="addLinks" class="collapse">
							  	<div class="btn-group-vertical">
									<a href="${contextPath}/shelf/${user.id}/add-to-owned/${book.id}"><button type="button" class="btn btn-dark">Add to owned books</button></a>
									<a href="${contextPath}/shelf/${user.id}/add-to-reading/${book.id}"><button type="button" class="btn btn-dark">Add to currently reading books</button></a>
									<a href="${contextPath}/shelf/${user.id}/add-read/${book.id}"><button type="button" class="btn btn-dark">Add to read books</button></a>
									<a href="${contextPath}/shelf/${user.id}/add-to-read/${book.id}"><button type="button" class="btn btn-dark">Add to books to want to read</button></a>
								</div>
							</div>
						</div>
				    </div>
				</div>
		</div>
		<br>
		<br>
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col">
					<c:if test="${myRate>0}">
						<div class="btn-group btn-group-lg">
							<p><button type="button" class="btn btn-success">My rate: ${myRate}</button></p>	
						</div>
					</c:if>
			    </div>
			    <div class="col col-lg-2">
					
			    </div>
			    <div class="col col-lg-2">
					
			    </div>
			    <div class="col col-lg-2">
					
			    </div>
			    <div class="col col-lg-2">
					
			    </div>
			    <div class="col">
					<p><button type="button" class="btn btn-warning">Users rate:
					<fmt:formatNumber type="number" maxFractionDigits="2" value="${book.rate}"/></button></p>
			    </div>
			</div>
		</div>

		
		<c:if test="${confirmation != null}">
			<c:out value="${confirmation}"/>
		</c:if>
		<div align=left>
			
		</div>

			<br>
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th>Id</th>
						<th>Title</th>
						<th>Genre</th>
						<th>Rate</th>
						<th>Authors</th>
						<th>Publisher</th>
						<th>Edit</th>
						<sec:authorize access ="hasRole('ROLE_ADMIN')">
							<th>Action</th>
						</sec:authorize>
					</tr>
			</thead>
				<tr>
					<td>${book.id}</td>
					<td>${book.title}</td>
					<td>
						<c:forEach items="${book.genre}" var="genre">
							${genre} 
						</c:forEach>
						
					</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${book.rate}"/></td>
					<td>
						<c:forEach items="${book.authors}" var="author">
							<a href="${contextPath}/author/all-books/${author.id}"><button type="button" class="btn btn-warning">${author.firstName} ${author.lastName}</button></a>
						</c:forEach>
					</td>
					<td>${book.publisher.name}</td>
					<td><a href="${contextPath}/book/edit/${book.id}"><button type="button" class="btn btn-warning">Edit</button></a></td>
					<sec:authorize access ="hasRole('ROLE_ADMIN')">
						<td><a href="${contextPath}/admin/delete-book/${book.id}"><button type="button" class="btn btn-danger">Delete</button></a></td>
					</sec:authorize>
				</tr>
		</table>
		<br>
		<br>
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto">
					<c:if test="${book.cover != null}">
						<div>
							<img src="http://localhost:8090/cover/image-display/${book.id}"/>
						</div>
					</c:if>
					<c:if test="${book.cover == null}">
						<div>
							<a href="${contextPath}/author/add-photo/${author.id}"></a>
						</div>
					</c:if>
			    </div>
			</div>
		</div>
		<br>
		<hr>
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto">
					<c:if test="${confirmation == null}">
						<p class="h3 text-muted">Rate ${book.title}</p>
						<form action="${contextPath}/book/details/${book.id}/rating" method="get">
							Evaluate: 
							<select id="grade" name="rateParam">
								<c:forEach items="${listOfRates}" var="rate">
									<option value="${rate}">${rate}</option>
								</c:forEach>
							</select>
							<p>	<input type="submit" value="Add" /></p>
						</form>	
					</c:if>
			    </div>
			</div>
		</div>
		<hr>
		<br>
		<div class="container">
			<div class="row justify-content-md-center">
			    <div class="col-md-auto">
			      	<div class="btn-group btn-group-lg">
						<div class="btn-group btn-group-lg">
						 	<c:if test="${book.cover == null}">
								<a href="${contextPath}/cover/add/${book.id}"><button type="button" class="btn btn-warning">Add cover page</button></a>
							</c:if>
							<c:if test="${book.cover != null}">
								<a href="${contextPath}/cover/delete/${book.id}"><button type="button" class="btn btn-warning">Delete cover</button></a>
							</c:if>
							<a href="${contextPath}/book/add"><button type="button" class="btn btn-warning">Add new book</button></a>
							<a href="${contextPath}/book/top-rated"><button type="button" class="btn btn-warning">Top 20 books</button></a>
							<a href="${contextPath}/book/all"><button type="button" class="btn btn-warning">All books</button></a>
						</div>
					</div>
			    </div>
			</div>
		</div>
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