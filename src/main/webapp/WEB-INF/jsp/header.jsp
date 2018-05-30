<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<sec:authorize access ="hasAnyRole('ADMIN', 'USER')">
		Logged as: <c:out value="${pageContext.request.remoteUser}"></c:out>
	</sec:authorize>
	
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	
	<div class="container">
	  <div class="page-header">
	    <h1>Bookweb</h1> 
	   <h3><span class="label label-primary"><a href="${contextPath}/home"><button>Main page</button></a> </span></h3>     
	  <p>	
		  <sec:authorize access ="hasAnyRole('ADMIN', 'USER')">
			<form action="/logout" method="post">
				<input class="btn btn-default" type="submit" value="Sign Out" /> 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</sec:authorize>
	</p>      
	  </div>
	</div>
</body>
</html>