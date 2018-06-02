<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user.username}'s books</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div align=center>	
			<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
			<div  style="background-color: hsl(150, 100%, 65%)">
				<%@ include file="header.jsp"%>
			</div>
		<div align=left>
			<a href="${contextPath}/user/my-page"><button type="button" class="btn btn-success">My profile</button></a>
		</div>
		<h1>${user.username}'s books</h1>
		<br>
		 <div class="btn-group btn-group-lg">
				<a href="${contextPath}/user/read"><button type="button" class="btn btn-warning">Read books</button></a>
				<a href="${contextPath}/user/to-read"><button type="button" class="btn btn-warning">Books to read</button></a>
				<a href="${contextPath}/book/all"><button type="button" class="btn btn-warning">All books</button></a>
		</div>
			<div class="container">
				<div id="owned">
					<table class="table">
						<tr class="info">
							<th>Title</th>
							<th>Authors</th>
							<th>Genre</th>
							<th>Cover</th>
							<th>Details</th>
							<th>Remove</th>
						</tr>
						<c:forEach items="${user.owned}" var="book">
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
								<td><a href="${contextPath}/user/remove-from-owned/${book.id}"><button type="button" class="btn btn-success">Remove</button></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		<hr>
		<br>
		<a href="${contextPath}/"><button type="button" class="btn btn-primary">Home</button></a>
	</div>
</body>
</html>