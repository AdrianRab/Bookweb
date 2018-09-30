<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add photo to ${author.firstName} ${author.lastName}</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	<div class="container-fluid bg">
		<%@ include file="../header.jsp"%>
	
		<div class="form-group row">
			<div class="container">
			 	<div class="row">
			 		<label for="lgFormGroupInput" class="col-sm-2 col-form-label col-form-label-lg">Add author photography</label>
			 		<div class="col-lg">
			 		</div>
			 		<div class="col-lg">
						<form method="post" action="" enctype="multipart/form-data">
							<div class="custom-file">
								<input type="file" class="custom-file-input" name="fileUpload" size="50">
								<label class="custom-file-label" for="customFile">Select file</label>
							</div>
								<input type="submit" value="Upload">
						</form>
					</div>
					<div class="col-lg">
					</div>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row justify-content-md-center">
			     <div class="btn-group btn-group-lg">
					<a href="${contextPath}/author/all"><button type="button" class="btn btn-dark">List of authors</button></a>
					<a href="${contextPath}/author/all-books/${author.id}"><button type="button" class="btn btn-dark">Back</button></a>
					<a href="${contextPath}/"><button type="button" class="btn btn-info">Home</button></a>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>