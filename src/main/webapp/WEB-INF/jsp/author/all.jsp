<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All authors</title>
</head>
<body>
		<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	<h2>Available authors</h2>
	<br>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Biography</th>
			<th>List of books</th>
			<sec:authorize access ="hasRole('ROLE_ADMIN')">
				<th>Action</th>
			</sec:authorize>
		</tr>
		<c:forEach items="${authorsList}" var="author">
			<tr>
				<td>${author.id}</td>
				<td>${author.firstName} ${author.lastName}</td>
				<td>${author.biography}</td>
				<td><a href="${contextPath}/author/all-books/${author.id}"><button>Books</button></a></td>
				<sec:authorize access ="hasRole('ROLE_ADMIN')">
				<td><a href="${contextPath}/admin/edit-auth/${author.id}"><button>Edit</button></a>
					<a href="${contextPath}/admin/detele-auth/${author.id}"><button>Delete</button></a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="${contextPath}/author/add"><button>Add new author</button></a>
	<br>
	<a href="${contextPath}/"><button>Home</button></a>
</body>
</html>