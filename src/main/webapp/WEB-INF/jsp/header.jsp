<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script> 
<title>Insert title here</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="${contextPath}/home">BOOKWEB</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="${contextPath}/home">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	      	<c:if test="${pageContext.request.remoteUser == null}">
	        	<a class="nav-link" href="${contextPath}/login">Login</a>
	        </c:if>
	      </li>
	      <li class="nav-item">
	      	<c:if test="${pageContext.request.remoteUser == null}">
	        	<a class="nav-link" href="${contextPath}/register">Register</a>
	        </c:if>
	      </li>
	      <c:if test="${pageContext.request.remoteUser != null}">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          My shelf
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		          <a class="dropdown-item" href="${contextPath}/user/owned"><button type="button" class="btn btn-warning">My books</button></a>
		          <a class="dropdown-item" href="${contextPath}/user/read"><button type="button" class="btn btn-warning">Read books</button></a>
		          <a class="dropdown-item" href="${contextPath}/user/to-read"><button type="button" class="btn btn-warning">Want to read</button></a>
		          <div class="dropdown-divider"></div>
		          <a class="dropdown-item" href="${contextPath}/book/add"><button type="button" class="btn btn-warning">Add new book</button></a>
		        </div>
		      </li>
	      </c:if>
	      <li class="nav-item">
	      	 <sec:authorize access ="hasAnyRole('ADMIN', 'USER')">
				<form action="/logout" method="post">
					<input class="nav-link" class="btn btn-default" type="submit" value="Sign Out" /> 
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
			   </sec:authorize>
		   </li>
	    </ul>
	  </div>
	</nav>
</body>
</html>