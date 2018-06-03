<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookweb home</title>
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
    background-image: url("blur-book-books-176103.jpg");

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
		<%@ include file="header.jsp"%>
		<div class="container">
		  <div class="row">
		    <div class="col">
		    </div>
		    <div class="col-6">
		      <h1 class="bg-dark text-white">Welcome on Bookweb!</h1>
		      <br>
		      <br>
		      <c:if test="${pageContext.request.remoteUser == null}">
				<div class="container">
					<table class="table">
						<tr>
							<td><p class="h2 bg-dark text-white">Login to enjoy all features</p></td>
							<td><a href="${contextPath}/login"><button type="button" class="btn btn-success custom">Login</button></a></td>
						</tr>
						<tr>
							<td><p class="h2 bg-dark text-white">Don't have account yet?</p></td>
							<td><a href="${contextPath}/register"><button type="button" class="btn btn-danger custom">Register</button></a></td>
						</tr>
					</table>
				</div>
			</c:if>
		    </div>
		    <div class="col">
		    </div>
		  </div>
		</div>
		<div class="container">
			  <div class="row">
			    <div class="col">
			    </div>
			    <div class="col-6">
			    	<sec:authorize access ="hasAnyRole('ADMIN', 'USER')">
						<div class="btn-group" role="group" aria-label="Button group with nested dropdown">
						 <sec:authorize access ="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
							<a href="${contextPath}/user/my-page"><button type="button" class="btn btn-success">My profile</button></a>
						</sec:authorize>	
						  <div class="btn-group" role="group">
						    <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						      Book menu
						    </button>
						    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						     	<a class="dropdown-item" href="${contextPath}/book/add"><button type="button" class="btn btn-dark">Add new book</button></a>
								<a class="dropdown-item" href="${contextPath}/book/all"><button type="button" class="btn btn-dark">List of books</button></a>
								<a class="dropdown-item" href="${contextPath}/book/top-rated"><button type="button" class="btn btn-dark">List of top 20 books</button></a>
						    </div>
						  </div>
						  <div class="btn-group" role="group">
						    <button id="btnGroupDrop2" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						      Author menu
						    </button>
						    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
						     	<a class="dropdown-item" href="${contextPath}/author/add"><button type="button" class="btn btn-dark" >Add new author</button></a>
								<a class="dropdown-item" href="${contextPath}/author/all"><button type="button" class="btn btn-dark">All authors</button></a>
						    </div>
						  </div>
						  <div class="btn-group" role="group">
						    <button id="btnGroupDrop3" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						      Publisher menu
						    </button>
						    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
								<a class="dropdown-item" href="${contextPath}/publ/all"><button type="button" class="btn btn-dark">List of publishers</button></a>
								<a class="dropdown-item" href="${contextPath}/publ/add"><button type="button" class="btn btn-dark">Add new publisher</button></a>
						    </div>
						  </div>
						</div>
					</sec:authorize>
			    </div>
			    <div class="col">
					
			    </div>
			  </div>
			  <div class="row">
			    <div class="col">

			    </div>
			    <div class="col-5">
					<sec:authorize access ="hasAnyRole('ADMIN', 'USER')">
						<br>
			  			<br>
						<form action="/logout" method="post">
							 <div class="form-group">
								<input class="btn btn-danger" type="submit" value="Sign Out" /> 
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</div>
						</form>
					</sec:authorize>
			    </div>
			    <div class="col">
			    </div>
			  </div>
		</div>
	</div>
</body>
</html>