<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Spring Security Example - ProgrammingFree</title>
</head>
<body>
	<div>
		<h1>Welcome!</h1>
		<div>
			Click <a href="<spring:url value='/hello' />">here</a> to see a
			greeting. 
			Add author: <a href="${contextPath}/author/add"><button>Add
					new author</button></a> <br> All authors: <a
				href="${contextPath}/author/all"><button>All authors</button></a>

			Add publisher:<a href="${contextPath}/publ/add"><button>Add
					new publisher</button></a> <br> All publishers: <a
				href="${contextPath}/publ/all"><button>List of
					publishers</button></a>
					
		<form action="/logout" method="post">
			<input type="submit" value="Sign Out" /> 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
		</div>
	</div>
</body>
</html>