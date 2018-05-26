<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Witaj na stronie <c:out value="${pageContext.request.remoteUser}"></c:out>
	
	Add author: <a href="${contextPath}/author/add"><button>Add new author</button></a>
	<br>
	All authors: <a href="${contextPath}/author/all"><button>All authors</button></a>
	
	Add publisher:<a href="${contextPath}/publ/add"><button>Add new publisher</button></a>
	<br>
	All publishers: <a href="${contextPath}/publ/all"><button>List of publishers</button></a> 
	
		<form action="/logout" method="post">
			<input type="submit" value="Sign Out" /> 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
</body>
</html>