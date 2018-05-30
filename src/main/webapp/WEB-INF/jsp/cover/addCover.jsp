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
<title>Cover for ${book.title}</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<div  style="background-color: hsl(150, 100%, 65%)">
		<%@ include file="header.jsp"%>
		
		
		<c:if test="${message !=  null}">
			<div>	
				${message}
			</div>
		</c:if>
		
	</div>
	<div align=center>
	<h1>Upload cover for book ${book.title}</h1>
		<form method="post" action="http://localhost:8090/cover/add/${book.id}" enctype="multipart/form-data">
			<table border=0>
				<tr>	
					<td>Select file:</td>
					<td><input type="file" name="fileUpload" size="50"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Upload"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>